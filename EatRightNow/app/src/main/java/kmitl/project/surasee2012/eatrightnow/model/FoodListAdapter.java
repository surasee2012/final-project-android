package kmitl.project.surasee2012.eatrightnow.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kmitl.project.surasee2012.eatrightnow.R;

/**
 * Created by Gun on 11/11/2017.
 */

class FoodHolder extends RecyclerView.ViewHolder {

    TextView foodNameItem;
    TextView foodCalItem;

    public FoodHolder(View itemView) {
        super(itemView);
        foodNameItem = itemView.findViewById(R.id.foodNameItem);
        foodCalItem = itemView.findViewById(R.id.foodCalItem);
    }

}

public class FoodListAdapter extends RecyclerView.Adapter<FoodHolder> {

    private ArrayList<String> foodNameList;
    private ArrayList<Integer> foodCalList;

    public FoodListAdapter() {
//        this.foodNameList = new ArrayList<>(foodNameList);
//        this.foodCalList = new ArrayList<>(foodCalList);
        foodNameList = new ArrayList<>();
        foodCalList = new ArrayList<>();
        for (int i=0;i<100;i++) {
            foodNameList.add("gun");
            foodCalList.add(i);
        }
    }

    @Override
    public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View ItemView = inflater.inflate(R.layout.food_item, null, false);
        FoodHolder holder = new FoodHolder(ItemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(FoodHolder foodHolder, int position) {
        foodHolder.foodNameItem.setText(foodNameList.get(position));
        foodHolder.foodCalItem.setText(Integer.toString(foodCalList.get(position)));
    }

    @Override
    public int getItemCount() {
        return foodNameList.size();
    }
}
