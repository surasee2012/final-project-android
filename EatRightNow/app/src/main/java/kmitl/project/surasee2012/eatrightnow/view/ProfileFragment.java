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

import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.preference.CommonSharePreference;
import kmitl.project.surasee2012.eatrightnow.model.Message;
import kmitl.project.surasee2012.eatrightnow.model.UserProfile;

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
}
