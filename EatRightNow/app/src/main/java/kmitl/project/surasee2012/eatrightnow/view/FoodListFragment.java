package kmitl.project.surasee2012.eatrightnow.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kmitl.project.surasee2012.eatrightnow.AddEditActivity;
import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.model.FoodDbAdapter;


public class FoodListFragment extends Fragment {

    private FoodListAdapter foodListAdapter;
    private RecyclerView recyclerView;

    private FoodDbAdapter foodDbAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_food_list, container, false);

        setHasOptionsMenu(true);

        foodDbAdapter = new FoodDbAdapter(getContext());

        foodListAdapter = new FoodListAdapter(getContext(), foodDbAdapter);
        recyclerView = rootView.findViewById(R.id.foodList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(foodListAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        foodListAdapter.update(foodDbAdapter.getData());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String foodName = ((TextView) recyclerView.findViewHolderForAdapterPosition(item.getGroupId())
                .itemView.findViewById(R.id.foodNameItem)).getText().toString();
        int foodID = foodDbAdapter.getFoodID(foodName);
        switch (item.getItemId()){
            case 0:
                Intent intent = new Intent(getContext(), AddEditActivity.class);
                intent.putExtra("isEdit", true);
                intent.putExtra("foodID", foodID);
                startActivity(intent);
                break;
            case 1:
                foodDbAdapter.deleteFoodData(foodID);
                foodListAdapter.removeAt(item.getGroupId());
                break;
        }
        return super.onContextItemSelected(item);
    }
}
