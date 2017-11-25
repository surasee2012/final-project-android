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

import java.util.ArrayList;
import java.util.Random;

import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.sqliteDB.FoodDbAdapter;
import kmitl.project.surasee2012.eatrightnow.model.FoodRandomItem;
import kmitl.project.surasee2012.eatrightnow.model.Message;
import kmitl.project.surasee2012.eatrightnow.listener.ShakeEventListener;

public class RandomFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    private TextView foodNameTv, foodCalTv;
    private Spinner tagSpinner, specialSpinner;
    private Button findMoreBtn, recomResBtn;

    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;

    private FoodDbAdapter foodDbAdapter;
    private Message message;
    private Vibrator vibrator;
    private ArrayList<FoodRandomItem> foodList;
    private String[] tag_array, special_array;
    private String tagFilter, specialFilter, randomFood,foodName, foodRes;
    private int previousRandomIndex = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_random, container, false);

        foodDbAdapter = new FoodDbAdapter(getContext());
        message = new Message(getContext());
        vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

        initView(rootView);

        initSensor();

        tagFilter = "ทั้งหมด";
        specialFilter = "ไม่มี";
        foodList = foodDbAdapter.getData("ทั้งหมด", "ไม่มี");

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.findMoreBtn:
                findMore(randomFood);
                break;
            case R.id.randomBtn:
                random();
                break;
            case R.id.recomResBtn:
                getFoodResMap(foodRes);
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
        foodList = foodDbAdapter.getData(tagFilter, specialFilter);
        previousRandomIndex = -1;
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
            previousRandomIndex = -1;
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
        FoodRandomItem foodRandomItem = getRandom(foodList);
        if (foodRandomItem.getErrorCollector() > 0) {
            message.alert(foodRandomItem.getErrorCollector());
            if (foodRandomItem.getErrorCollector() != 1) {
                findMoreBtn.setVisibility(View.GONE);
                recomResBtn.setVisibility(View.GONE);
            }
        } else {
            foodNameTv.setText(foodRandomItem.getFood_Name());
            foodCalTv.setText(Integer.toString(foodRandomItem.getFood_Calories()) + " แคล/จาน");
            findMoreBtn.setVisibility(View.VISIBLE);
            recomResBtn.setVisibility(View.VISIBLE);
            randomFood = foodRandomItem.getFood_Name();
            foodName = foodRandomItem.getFood_Name();
            foodRes = foodRandomItem.getFood_Restaurant();
        }
        vibrator.vibrate(200);
    }

    public FoodRandomItem getRandom(ArrayList<FoodRandomItem> foodList) {
        FoodRandomItem foodRandom = new FoodRandomItem();
        try {
            Random random = new Random();
            int randomIndex;
            do {
                randomIndex = random.nextInt(foodList.size());
            } while (randomIndex == previousRandomIndex && foodList.size() > 1);
            if (foodList.size() == 1 && randomIndex == previousRandomIndex) {
                foodRandom.setErrorCollector(1);
                return foodRandom;
            }
            previousRandomIndex = randomIndex;
            foodRandom = new FoodRandomItem();
            foodRandom.setFood_Name(foodList.get(randomIndex).getFood_Name());
            foodRandom.setFood_Calories(foodList.get(randomIndex).getFood_Calories());
            foodRandom.setFood_Restaurant(foodList.get(randomIndex).getFood_Restaurant());
        } catch (Exception e) {
            foodRandom.setErrorCollector(2);
            return foodRandom;
        }
        foodRandom.setErrorCollector(0);
        return foodRandom;
    }

    public void getFoodResMap(String foodRes) {
        if (foodRes != null) {
            String url = "https://www.google.com/maps/search/?api=1&query=" + foodRes;
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } else {
            message.alert("ขออภัย ร้านโปรดของ" + foodName + "ยังไม่ถูกเพิ่มลงในรายการ");
        }
    }

    public void initView(View rootView) {
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
    }

    public void initSensor() {
        mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();

        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
            public void onShake() {
                if (!message.getAlertDialog().isShowing()) {
                    random();
                }
            }
        });
    }
}
