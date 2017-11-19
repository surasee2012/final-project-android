package kmitl.project.surasee2012.eatrightnow.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
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

import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.model.FoodDbAdapter;
import kmitl.project.surasee2012.eatrightnow.model.FoodRandom;
import kmitl.project.surasee2012.eatrightnow.model.FoodsListItems;
import kmitl.project.surasee2012.eatrightnow.model.Message;
import kmitl.project.surasee2012.eatrightnow.model.ShakeEventListener;

public class RandomFragment extends Fragment implements View.OnClickListener
        , AdapterView.OnItemSelectedListener {

    private TextView foodNameTv;
    private TextView foodCalTv;
    private Spinner tagSpinner;
    private Spinner specialSpinner;

    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;

    private FoodDbAdapter foodDbAdapter;
    private Message message;
    private Vibrator vibrator;
    private ArrayList<FoodsListItems> foodList;
    private String[] tag_array;
    private String[] special_array;
    private String tagFilter;
    private String specialFilter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.random_fragment, container, false);

        foodDbAdapter = new FoodDbAdapter(getContext());
        message = new Message(getContext());
        vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

        foodNameTv = rootView.findViewById(R.id.foodNameRandomTv);
        foodCalTv = rootView.findViewById(R.id.foodCalRandomTv);
        foodNameTv.setText("");
        foodCalTv.setText("");

        final Button randomBtn = rootView.findViewById(R.id.randomBtn);
        randomBtn.setOnClickListener(this);

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
        random();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (R.id.tagSpinner == adapterView.getId()) {
            tagFilter = tag_array[i];
        } else if (R.id.specialSpinner == adapterView.getId()) {
            specialFilter = special_array[i];
        }
        foodDbAdapter.setPreviousRandomIndex(-1);
        foodList = foodDbAdapter.getData(tagFilter, specialFilter);
        foodNameTv.setText("");
        foodCalTv.setText("");
        if (foodList.isEmpty()) {
            message.alert("ขออภัย ไม่มีรายการอาหารที่ตรงกับตัวเลือกของคุณ กรุณาเปลี่ยนตัวเลือกเพิ่มเติม");
            vibrator.vibrate(200);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

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

    public void random() {
        FoodRandom foodRandom = foodDbAdapter.getRandom(foodList);
        if (foodRandom.getErrorCollector() > 0) {
            message.alert(foodRandom.getErrorCollector());
        } else {
            foodNameTv.setText(foodRandom.getFood_Name());
            foodCalTv.setText(Integer.toString(foodRandom.getFood_Calories()) + " แคล/จาน");
        }
        vibrator.vibrate(200);
    }
}
