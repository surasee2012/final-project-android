package kmitl.project.surasee2012.eatrightnow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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

    private Context context;
    private ArrayList<String> foodNameList;
    private ArrayList<Integer> foodCalList;

    public FoodListAdapter(Context context, FoodDbAdapter foodDbAdapter) {

    }

    @Override
    public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FoodHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
