package kmitl.project.surasee2012.eatrightnow.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.model.CommonSharePreference;
import kmitl.project.surasee2012.eatrightnow.model.Message;
import kmitl.project.surasee2012.eatrightnow.model.UserProfile;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements AdapterView.OnItemSelectedListener,
        View.OnClickListener {

    private EditText weightEdt;
    private EditText heightEdt;
    private EditText ageEdt;
    private Spinner genderSpinner;
    private Spinner activitySpinner;

    private CommonSharePreference preference;
    private Message message;
    private UserProfile userProfile;
    private String[] gender_array;
    private String[] activity_array;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        message = new Message(getContext());
        preference = new CommonSharePreference(getContext());
        userProfile = (UserProfile) preference.read("UserProfile", UserProfile.class);

        gender_array = getResources().getStringArray(R.array.gender_array);
        genderSpinner = rootView.findViewById(R.id.genderSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter1);
        genderSpinner.setOnItemSelectedListener(this);

        activity_array = getResources().getStringArray(R.array.activity_array);
        activitySpinner = rootView.findViewById(R.id.activitySpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.activity_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(adapter2);
        activitySpinner.setOnItemSelectedListener(this);

        Button saveBtn = rootView.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

        weightEdt = rootView.findViewById(R.id.weightEdt);
        heightEdt = rootView.findViewById(R.id.heightEdt);
        ageEdt = rootView.findViewById(R.id.ageEdt);

        if (userProfile != null) {
            weightEdt.setText(String.valueOf(userProfile.getWeight()), TextView.BufferType.EDITABLE);
            heightEdt.setText(String.valueOf(userProfile.getHieght()), TextView.BufferType.EDITABLE);
            ageEdt.setText(String.valueOf(userProfile.getAge()), TextView.BufferType.EDITABLE);

            ArrayAdapter ArrayAdapter = (ArrayAdapter) genderSpinner.getAdapter();
            int spinnerPosition = ArrayAdapter.getPosition(userProfile.getGender());
            genderSpinner.setSelection(spinnerPosition);

            ArrayAdapter = (ArrayAdapter) activitySpinner.getAdapter();
            spinnerPosition = ArrayAdapter.getPosition(userProfile.getUserActivity());
            activitySpinner.setSelection(spinnerPosition);
        } else  {
            userProfile = new UserProfile();
        }

        return rootView;
    }

    @Override
    public void onClick(View view) {
        try {
            Double userWeight = Double.parseDouble(weightEdt.getText().toString());
            Double userHeight = Double.parseDouble(heightEdt.getText().toString());
            Integer userAge = Integer.parseInt(ageEdt.getText().toString());

            userProfile.setWeight(userWeight);
            userProfile.setHieght(userHeight);
            userProfile.setAge(userAge);

            preference.save("UserProfile", userProfile);
        } catch (Exception e) {
            message.alert("ขออภัย ข้อมูลส่วนตัวไม่ถูกต้อง");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.genderSpinner:
                userProfile.setGender(gender_array[i]);
                break;
            case R.id.activitySpinner:
                userProfile.setUserActivity(activity_array[i]);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
