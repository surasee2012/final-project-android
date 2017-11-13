package kmitl.project.surasee2012.eatrightnow.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.model.FoodDbAdapter;
import kmitl.project.surasee2012.eatrightnow.model.FoodsListItems;


public class FoodListFragment extends Fragment {

    public FoodListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.food_list_fragment, container, false);

        FoodDbAdapter foodDbAdapter = new FoodDbAdapter(getContext());
        ArrayList<FoodsListItems> foodList = foodDbAdapter.getData();

        FoodListAdapter foodListAdapter = new FoodListAdapter(getContext(), foodList);
        RecyclerView recyclerView = rootView.findViewById(R.id.foodList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(foodListAdapter);

        return rootView;
    }

}
