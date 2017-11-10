package kmitl.project.surasee2012.eatrightnow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RandomFragment.RandomFragmentListener {

    FragmentManager fragmentManager;

    FoodDbAdapter foodDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodDbAdapter = new FoodDbAdapter(this);

        Button randomFragBtn = findViewById(R.id.randomFragBtn);
        Button foodListFragBtn = findViewById(R.id.foodListFragBtn);
        Button profileFragBtn = findViewById(R.id.profileFragBtn);

        if(savedInstanceState == null) {
            initialFragment();
        }

        randomFragBtn.setOnClickListener(this);
        foodListFragBtn.setOnClickListener(this);
        profileFragBtn.setOnClickListener(this);
    }

    private void initialFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, RandomFragment.newInstance("",MainActivity.this)).commit();
    }

    @Override
    public void onClick(View view) {
        if (R.id.randomFragBtn == view.getId()) {
            viewFragment(RandomFragment.newInstance("", MainActivity.this));
        } else if (R.id.foodListFragBtn == view.getId()) {
            viewFragment(new FoodListFragment());
        } else if (R.id.profileFragBtn == view.getId()) {
            viewFragment(new ProfileFragment());
        }
    }

    private void viewFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void random() {
        ArrayList<String> foodList = foodDbAdapter.getData();
        Random random = new Random();
        viewFragment(RandomFragment.newInstance(foodList.get(random.nextInt(foodList.size()))
                , MainActivity.this));
//        Message.message(this, foodList.get(random.nextInt(foodList.size())));
    }
}
