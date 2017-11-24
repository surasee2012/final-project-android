package kmitl.project.surasee2012.eatrightnow.view;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.model.FoodDbAdapter;
import kmitl.project.surasee2012.eatrightnow.model.FoodRandomItem;
import kmitl.project.surasee2012.eatrightnow.model.Message;
import kmitl.project.surasee2012.eatrightnow.model.ShakeEventListener;

public class RandomFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    private TextView foodNameTv;
    private TextView foodCalTv;
    private Spinner tagSpinner;
    private Spinner specialSpinner;
    private Button findMoreBtn;
    private Button recomResBtn;

    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;

    private FoodDbAdapter foodDbAdapter;
    private Message message;
    private Vibrator vibrator;
    private ArrayList<FoodRandomItem> foodList;
    private String[] tag_array;
    private String[] special_array;
    private String tagFilter;
    private String specialFilter;
    private String randomFood;
    private String foodRes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_random, container, false);

        foodDbAdapter = new FoodDbAdapter(getContext());
        message = new Message(getContext());
        vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

        foodNameTv = rootView.findViewById(R.id.foodNameRandomTv);
        foodCalTv = rootView.findViewById(R.id.foodCalRandomTv);
        foodNameTv.setText("");
        foodCalTv.setText("");

        Button randomBtn = rootView.findViewById(R.id.randomBtn);
        findMoreBtn = rootView.findViewById(R.id.findMoreBtn);
        recomResBtn = rootView.findViewById(R.id.recomResBtn);
        randomBtn.setOnClickListener(this);
        findMoreBtn.setOnClickListener(this);
        recomResBtn.setOnClickListener(this);

        tag_array = getResources().getStringArray(R.array.tags_array);
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

        mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();

        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
            public void onShake() {
                if (!message.getAlertDialog().isShowing()) {
                    random();
                }
            }
        });

        tagFilter = "ทั้งหมด";
        specialFilter = "ไม่มี";
        foodList = foodDbAdapter.getData("ทั้งหมด", "ไม่มี");

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.randomBtn:
                random();
                break;
            case R.id.findMoreBtn:
                findMore(randomFood);
                break;
            case R.id.recomResBtn:
                getFoodResMap(foodRes);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.tagSpinner:
                tagFilter = tag_array[i];
                break;
            case R.id.specialSpinner:
                specialFilter = special_array[i];
                break;
        }
        foodDbAdapter.setPreviousRandomIndex(-1);
        foodList = foodDbAdapter.getData(tagFilter, specialFilter);
        foodNameTv.setText("");
        foodCalTv.setText("");
        findMoreBtn.setVisibility(View.GONE);
        recomResBtn.setVisibility(View.GONE);
        if (foodList.isEmpty()) {
            if (specialFilter.equals("แคลอรี่ที่เหมาะสม")) {
                message.alert("ขออภัย คุณยังไม่ได้กรอกข้อมูลส่วนตัว หรือ ข้อมูลส่วนตัวไม่ถูกต้อง");
            } else {
                message.alert("ขออภัย ไม่มีรายการอาหารที่ตรงกับตัวเลือกของคุณ กรุณาเปลี่ยนตัวเลือกเพิ่มเติม");
            }
            vibrator.vibrate(200);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getView()!= null){
            foodList = foodDbAdapter.getData(tagFilter, specialFilter);
            foodNameTv.setText("");
            foodCalTv.setText("");
            findMoreBtn.setVisibility(View.GONE);
            recomResBtn.setVisibility(View.GONE);
        }
    }

    public void findMore(String foodName) {
        String url = "https://www.google.co.th/search?q=" + foodName;
        try {
            Intent i = new Intent("android.intent.action.MAIN");
            i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
            i.addCategory("android.intent.category.LAUNCHER");
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        catch(ActivityNotFoundException e) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);
        }
    }

    public void random() {
        FoodRandomItem foodRandomItem = foodDbAdapter.getRandom(foodList);
        if (foodRandomItem.getErrorCollector() > 0) {
            message.alert(foodRandomItem.getErrorCollector());
            findMoreBtn.setVisibility(View.GONE);
            recomResBtn.setVisibility(View.GONE);
        } else {
            foodNameTv.setText(foodRandomItem.getFood_Name());
            foodCalTv.setText(Integer.toString(foodRandomItem.getFood_Calories()) + " แคล/จาน");
            findMoreBtn.setVisibility(View.VISIBLE);
            recomResBtn.setVisibility(View.VISIBLE);
            randomFood = foodRandomItem.getFood_Name();
            foodRes = foodRandomItem.getFood_Restaurant();
        }
        vibrator.vibrate(200);
    }

    public void getFoodResMap(String foodRes) {
        if (foodRes != null) {
            String foodResParameter = "";
            for (char c: foodRes.toCharArray()) {
                if (c == ' ') {
                    foodResParameter += '+';
                }
            }
            String url = "https://www.google.com/maps/search/?api=1&query=" + foodResParameter;
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }
}
