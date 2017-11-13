package kmitl.project.surasee2012.eatrightnow.view;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.controller.MainActivity;
import kmitl.project.surasee2012.eatrightnow.model.FoodDbAdapter;
import kmitl.project.surasee2012.eatrightnow.model.FoodsListItems;

public class RandomFragment extends Fragment implements View.OnClickListener
        , AdapterView.OnItemSelectedListener{

    private FoodDbAdapter foodDbAdapter;

    private TextView foodNameTv;
    private TextView foodCalTv;
    private Spinner tagSpinner;
    private Spinner specialSpinner;

    private ArrayList<FoodsListItems> foodList;
    private String[] tag_array;
    private String[] special_array;
    private String tagFilter;
    private String specialFilter;
    private int previousIndex;

    public RandomFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.random_fragment, container, false);

        foodDbAdapter = new FoodDbAdapter(getContext());

        foodNameTv = rootView.findViewById(R.id.foodNameRandomTv);
        foodCalTv = rootView.findViewById(R.id.foodCalRandomTv);
        foodNameTv.setText("");
        foodCalTv.setText("");

        Button randomBtn = rootView.findViewById(R.id.randomBtn);
        randomBtn.setOnClickListener(this);

        tag_array = getResources().getStringArray(R.array.tags_array);
        tag_array = foodDbAdapter.getTags();
        tagSpinner = rootView.findViewById(R.id.tagSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.tags_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagSpinner.setAdapter(adapter1);
        tagSpinner.setOnItemSelectedListener(this);

        special_array = getResources().getStringArray(R.array.special_array);
        specialSpinner = rootView.findViewById(R.id.specialSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.special_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialSpinner.setAdapter(adapter2);
        specialSpinner.setOnItemSelectedListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        Random random = new Random();
        int randomIndex;
        do {
            randomIndex = random.nextInt(foodList.size());
        } while (randomIndex == previousIndex);
//        int randomIndex = random.nextInt(foodList.size());
        previousIndex = randomIndex;
        foodNameTv.setText(foodList.get(randomIndex).getFood_Name());
        foodCalTv.setText(Integer.toString(foodList.get(randomIndex).getFood_Calories()) + " แคล/จาน");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (R.id.tagSpinner == adapterView.getId()) {
            tagFilter = tag_array[i];
        } else if (R.id.specialSpinner == adapterView.getId()) {
            specialFilter = special_array[i];
        }
        previousIndex = -1;
        foodList = foodDbAdapter.getData(tagFilter, specialFilter);
//        foodNameTv.setText("");
//        foodCalTv.setText("");
        if (foodList.isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("ขออภัย ไม่มีรายการอาหารที่ตรงกับตัวเลือกของคุณ กรุณาเปลี่ยนตัวเลือกเพิ่มเติม");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ตกลง",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
