package kmitl.project.surasee2012.eatrightnow.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.preference.CommonSharePreference;
import kmitl.project.surasee2012.eatrightnow.model.Message;
import kmitl.project.surasee2012.eatrightnow.model.UserProfile;
import kmitl.project.surasee2012.eatrightnow.validator.ageValidator.AgeValidator;
import kmitl.project.surasee2012.eatrightnow.validator.ageValidator.NullAgeValidator;
import kmitl.project.surasee2012.eatrightnow.validator.ageValidator.Over123AgeValidator;
import kmitl.project.surasee2012.eatrightnow.validator.ageValidator.ZeroAgeValidator;
import kmitl.project.surasee2012.eatrightnow.validator.genderValidator.AbnormalGenderValidator;
import kmitl.project.surasee2012.eatrightnow.validator.genderValidator.GenderValidator;
import kmitl.project.surasee2012.eatrightnow.validator.genderValidator.NullGenderValidator;
import kmitl.project.surasee2012.eatrightnow.validator.heightValidator.HeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.heightValidator.NullHeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.heightValidator.Over272HeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.heightValidator.Under55HeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userActivityValidator.NullUserActivityValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userActivityValidator.UndefinedUserActivityValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userActivityValidator.UserActivityValidator;
import kmitl.project.surasee2012.eatrightnow.validator.weightValidator.NullWeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.weightValidator.Over635WeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.weightValidator.WeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.weightValidator.ZeroWeightValidator;

public class ProfileFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText weightEdt, heightEdt, ageEdt;
    private Spinner genderSpinner, activitySpinner;

    private CommonSharePreference preference;
    private Message message;
    private UserProfile userProfile;
    private String userGender, userActivity;
    private String[] gender_array, activity_array;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        message = new Message(getContext());
        preference = new CommonSharePreference(getContext());
        userProfile = (UserProfile) preference.read("UserProfile", UserProfile.class);

        initView(rootView);

        if (userProfile != null) {
            getUserProfile();
        } else  {
            userProfile = new UserProfile();
        }

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                try {
                    saveUserProfile();
                } catch (Exception e) {
                    message.alert("ขออภัย ข้อมูลส่วนตัวไม่ถูกต้อง");
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.genderSpinner:
                userGender = gender_array[i];
                break;
            case R.id.activitySpinner:
                userActivity = activity_array[i];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void initView(View rootView) {
        setHasOptionsMenu(true);

        weightEdt = rootView.findViewById(R.id.weightEdt);
        heightEdt = rootView.findViewById(R.id.heightEdt);
        ageEdt = rootView.findViewById(R.id.ageEdt);

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
    }

    public void getUserProfile() {
        weightEdt.setText(String.valueOf(userProfile.getWeight()), TextView.BufferType.EDITABLE);
        heightEdt.setText(String.valueOf(userProfile.getHeight()), TextView.BufferType.EDITABLE);
        ageEdt.setText(String.valueOf(userProfile.getAge()), TextView.BufferType.EDITABLE);

        ArrayAdapter ArrayAdapter = (ArrayAdapter) genderSpinner.getAdapter();
        int spinnerPosition = ArrayAdapter.getPosition(userProfile.getGender());
        genderSpinner.setSelection(spinnerPosition);

        ArrayAdapter = (ArrayAdapter) activitySpinner.getAdapter();
        spinnerPosition = ArrayAdapter.getPosition(userProfile.getUserActivity());
        activitySpinner.setSelection(spinnerPosition);
    }

    public void saveUserProfile() throws Exception {
        Double userWeight = Double.parseDouble(weightEdt.getText().toString());
        Double userHeight = Double.parseDouble(heightEdt.getText().toString());
        Integer userAge = Integer.parseInt(ageEdt.getText().toString());

        userProfile.setWeight(userWeight);
        userProfile.setHeight(userHeight);
        userProfile.setAge(userAge);
        userProfile.setGender(userGender);
        userProfile.setUserActivity(userActivity);

        preference.save("UserProfile", userProfile);
        message.setToast(getContext(), "บันทึกแล้ว");
    }

//    public void validate(Double userWeight,Double userHeight, Integer userAge) throws Exception {
//        List<WeightValidator> weightValidators = new ArrayList<>();
//        weightValidators.add(new NullWeightValidator());
//        weightValidators.add(new ZeroWeightValidator());
//        weightValidators.add(new Over635WeightValidator());
//        for (WeightValidator weightValidator: weightValidators) {
//            if (!weightValidator.isValid(userWeight)) {
//                throw new Exception();
//            }
//        }
//
//        List<HeightValidator> heightValidators = new ArrayList<>();
//        heightValidators.add(new NullHeightValidator());
//        heightValidators.add(new Under55HeightValidator());
//        heightValidators.add(new Over272HeightValidator());
//        for (HeightValidator heightValidator: heightValidators) {
//            if (!heightValidator.isValid(userHeight)) {
//                throw new Exception();
//            }
//        }
//
//        List<AgeValidator> ageValidators = new ArrayList<>();
//        ageValidators.add(new NullAgeValidator());
//        ageValidators.add(new ZeroAgeValidator());
//        ageValidators.add(new Over123AgeValidator());
//        for (AgeValidator ageValidator: ageValidators) {
//            if (!ageValidator.isValid(userAge)) {
//                throw new Exception();
//            }
//        }
//
//        List<GenderValidator> genderValidators = new ArrayList<>();
//        genderValidators.add(new NullGenderValidator());
//        genderValidators.add(new AbnormalGenderValidator());
//        for (GenderValidator genderValidator: genderValidators) {
//            if (!genderValidator.isValid(userProfile.getGender())) {
//                throw new Exception();
//            }
//        }
//
//        List<UserActivityValidator> userActivityValidators = new ArrayList<>();
//        userActivityValidators.add(new NullUserActivityValidator());
//        userActivityValidators.add(new UndefinedUserActivityValidator());
//        for (UserActivityValidator userActivityValidator: userActivityValidators) {
//            if (!userActivityValidator.isValid(userProfile.getUserActivity())) {
//                throw new Exception();
//            }
//        }
//    }
}
