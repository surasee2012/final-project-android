package kmitl.project.surasee2012.eatrightnow.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import kmitl.project.surasee2012.eatrightnow.AddEditActivity;
import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.model.FoodDbAdapter;


public class FoodListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_food_list, container, false);

        FoodDbAdapter foodDbAdapter = new FoodDbAdapter(getContext());

        FoodListAdapter foodListAdapter = new FoodListAdapter(getContext(), foodDbAdapter);
        RecyclerView recyclerView = rootView.findViewById(R.id.foodList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(foodListAdapter);

        return rootView;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Intent intent = new Intent(getContext(), AddEditActivity.class);
//                EditText editText = (EditText) findViewById(R.id.editText);
//                String message = editText.getText().toString();
//                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                break;
            case 1:

                break;
        }
        return super.onContextItemSelected(item);
    }

}
