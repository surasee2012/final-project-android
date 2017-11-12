package kmitl.project.surasee2012.eatrightnow.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import kmitl.project.surasee2012.eatrightnow.model.FoodDbAdapter;
import kmitl.project.surasee2012.eatrightnow.model.FoodsListItems;

public class RandomFragment extends Fragment implements View.OnClickListener
        , AdapterView.OnItemSelectedListener{

    private FoodDbAdapter foodDbAdapter;

    private TextView foodNameTv;
    private TextView foodCalTv;
    private Spinner tagSpinner;

    private String[] tag_array;

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
        tagSpinner = rootView.findViewById(R.id.tagSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.tags_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagSpinner.setAdapter(adapter);
        tagSpinner.setOnItemSelectedListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        ArrayList<FoodsListItems> foodList = foodDbAdapter.getData();
        Random random = new Random();
        int randomIndex = random.nextInt(foodList.size());

        foodNameTv.setText(foodList.get(randomIndex).getFood_Name());
        foodCalTv.setText(Integer.toString(foodList.get(randomIndex).getFood_Calories()) + " แคล/จาน");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        foodNameTv.setText(tag_array[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
