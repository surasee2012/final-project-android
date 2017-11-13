package kmitl.project.surasee2012.eatrightnow.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.model.FoodDbAdapter;
import kmitl.project.surasee2012.eatrightnow.model.FoodsListItems;

/**
 * Created by Gun on 11/11/2017.
 */

class FoodHolder extends RecyclerView.ViewHolder {

    public ImageView favoriteImg;
    public TextView foodNameItem;
    public TextView foodCalItem;

    public FoodHolder(View itemView) {
        super(itemView);
        favoriteImg = itemView.findViewById(R.id.favoriteImg);
        foodNameItem = itemView.findViewById(R.id.foodNameItem);
        foodCalItem = itemView.findViewById(R.id.foodCalItem);
    }

}

public class FoodListAdapter extends RecyclerView.Adapter<FoodHolder> {

    private Context context;

    private ArrayList<String> foodNameList;
    private ArrayList<Integer> foodCalList;
    private ArrayList<Integer> foodFavList;
    private int favoriteImgId;
    private int noFavoriteImgId;

    public FoodListAdapter(Context context, ArrayList<FoodsListItems> foodList) {
        this.context = context;
        foodNameList = new ArrayList<>();
        foodCalList = new ArrayList<>();
        foodFavList = new ArrayList<>();
        for (int i=0;i<100;i++) {
            foodNameList.add(foodList.get(i).getFood_Name());
            foodCalList.add(foodList.get(i).getFood_Calories());
            foodFavList.add(foodList.get(i).getFood_Favorite());
        }
        favoriteImgId = context.getResources().getIdentifier("favorite", "drawable", context.getPackageName());
        noFavoriteImgId = context.getResources().getIdentifier("no_favorite", "drawable", context.getPackageName());
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
        ImageView favoriteImg = foodHolder.favoriteImg;
        if (foodFavList.get(position) == 1) {
            Glide.with(context).load(favoriteImgId).into(favoriteImg);
        } else {
            Glide.with(context).load(noFavoriteImgId).into(favoriteImg);
        }
        foodHolder.foodNameItem.setText(foodNameList.get(position));
        foodHolder.foodCalItem.setText(Integer.toString(foodCalList.get(position)));
    }

    @Override
    public int getItemCount() {
        return foodNameList.size();
    }
}
