package kmitl.project.surasee2012.eatrightnow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import kmitl.project.surasee2012.eatrightnow.model.FoodDbAdapter;
import kmitl.project.surasee2012.eatrightnow.model.FoodItemWithTags;
import kmitl.project.surasee2012.eatrightnow.model.Message;

public class AddEditActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText foodNameEt;
    private EditText foodCalEt;
    private Switch favSwitch;
    private CheckBox[] tagCBs;

    private FoodDbAdapter foodDbAdapter;
    private Message message;

    private boolean isEdit;
    private int[] tagIDs = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int foodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        getSupportActionBar().setTitle("เพิ่มอาหาร");

        foodDbAdapter = new FoodDbAdapter(this);
        message = new Message(this);

        Button addEditBtn = findViewById(R.id.addEditBtn);
        Button cancelBtn = findViewById(R.id.cancelBtn);
        addEditBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        foodNameEt = findViewById(R.id.foodNameEt);
        foodCalEt = findViewById(R.id.foodCalEt);

        favSwitch = findViewById(R.id.favSwitch);

        tagCBs = new CheckBox[9];
        int[] tagsCbID = {R.id.eggCb, R.id.chicDuckCb, R.id.porkCb, R.id.beefCb, R.id.fishCb
                , R.id.seafoodCb, R.id.vegCb, R.id.riceCb, R.id.noodleCb};
        for (int i=0;i<9;i++) {
            tagCBs[i] = findViewById(tagsCbID[i]);
        }

        isEdit = getIntent().getBooleanExtra("isEdit", false);
        foodID = getIntent().getIntExtra("foodID", 0);
        if (isEdit) {
            getSupportActionBar().setTitle("แก้ไขอาหาร");
            FoodItemWithTags foodItemWithTags = foodDbAdapter.getFoodItemWithTags(foodID);
            foodNameEt.setText(foodItemWithTags.getFood_Name(), TextView.BufferType.EDITABLE);
            foodNameEt.setKeyListener(null);
            foodCalEt.setText(String.valueOf(foodItemWithTags.getFood_Calories()), TextView.BufferType.EDITABLE);
            if (foodItemWithTags.getFood_Favorite() == 1) {
                favSwitch.setChecked(true);
            }
            for(int i=0;i<9;i++) {
                for(Integer foodTag : foodItemWithTags.getTags()) {
                    if (tagIDs[i] == foodTag) {
                        tagCBs[i].setChecked(true);
                        break;
                    }
                }
            }
            addEditBtn.setText("บันทึก");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.foodCalEt:
                finish();
                break;
            case R.id.addEditBtn:
                try {
                    String action;
                    String foodName = foodNameEt.getText().toString();
                    Integer foodCal = Integer.parseInt(foodCalEt.getText().toString());
                    Integer foodFav;
                    if (favSwitch.isChecked()) {
                        foodFav = 1;
                    } else {
                        foodFav = 0;
                    }

                    if (isEdit) {
                        foodDbAdapter.updateFood(foodName, foodCal, foodFav);
                        foodDbAdapter.deleteTags(foodID);
                        action = "บันทึก";
                    } else {
                        foodDbAdapter.addFood(foodName, foodCal, foodFav);
                        action = "เพิ่ม";
                    }

                    for (int i=0;i<9;i++) {
                        if (tagCBs[i].isChecked()) {
                            foodDbAdapter.addTag(foodDbAdapter.getFoodID(foodName), tagIDs[i]);
                        }
                    }

                    message.setToast(this, action + foodName + "สำเร็จแล้ว");
                    finish();
                } catch (Exception e) {
                    message.alert("ขออภัย ข้อมูลไม่ถูกต้อง");
                }
        }
    }
}
