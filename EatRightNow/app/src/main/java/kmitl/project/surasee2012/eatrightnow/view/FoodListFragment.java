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

import kmitl.project.surasee2012.eatrightnow.activity.AddEditActivity;
import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.sqliteDB.FoodDbAdapter;

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

        initView(rootView);

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
                startAddEditActivity(foodID);
                break;
            case 1:
                deleteFoodItem(item.getGroupId(), foodID);
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void initView(View rootView) {
        recyclerView = rootView.findViewById(R.id.foodList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(foodListAdapter);
    }

    public void startAddEditActivity(int foodID) {
        Intent intent = new Intent(getContext(), AddEditActivity.class);
        intent.putExtra("isEdit", true);
        intent.putExtra("foodID", foodID);
        startActivity(intent);
    }

    public void deleteFoodItem(int position, int foodID) {
        foodDbAdapter.deleteFoodData(foodID);
        foodListAdapter.removeAt(position);
    }
}
