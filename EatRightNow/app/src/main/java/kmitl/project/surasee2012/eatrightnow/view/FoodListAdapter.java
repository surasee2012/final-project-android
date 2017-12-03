package kmitl.project.surasee2012.eatrightnow.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kmitl.project.surasee2012.eatrightnow.R;
import kmitl.project.surasee2012.eatrightnow.sqliteDB.FoodDbAdapter;
import kmitl.project.surasee2012.eatrightnow.model.FoodListItem;
import kmitl.project.surasee2012.eatrightnow.model.Message;

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

    private FoodDbAdapter foodDbAdapter;
    private Message message;
    private ArrayList<FoodListItem> foodList;
    private int favoriteImgId;
    private int noFavoriteImgId;

    public FoodListAdapter(Context context, FoodDbAdapter foodDbAdapter) {
        this.context = context;
        this.foodDbAdapter = foodDbAdapter;

        foodList = foodDbAdapter.getData();

        favoriteImgId = context.getResources().getIdentifier("favorite", "drawable", context.getPackageName());
        noFavoriteImgId = context.getResources().getIdentifier("no_favorite", "drawable", context.getPackageName());
    }

    @Override
    public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View ItemView = inflater.inflate(R.layout.food_item, parent, false);

        final FoodHolder foodHolder = new FoodHolder(ItemView);
        message = new Message(context);

        foodHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(foodHolder.getAdapterPosition(), 0, 0, "แก้ไขอาหาร");
                menu.add(foodHolder.getAdapterPosition(), 1, 0, "ลบอาหาร");
            }
        });

        return foodHolder;
    }

    @Override
    public void onBindViewHolder(FoodHolder foodHolder, final int position) {
        final ImageView favoriteImg = foodHolder.favoriteImg;
        foodHolder.favoriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (foodList.get(position).getFood_Favorite()) {
                    case 0:
                        foodDbAdapter.setFavorite(foodList.get(position).getFood_ID(), 1);
                        foodList.get(position).setFood_Favorite(1);
                        Glide.with(context).load(favoriteImgId).into(favoriteImg);
                        message.setToast(context, "เพิ่ม" + foodList.get(position).getFood_Name() + "ในของโปรดเรียบร้อยแล้ว");
                        break;
                    case 1:
                        foodDbAdapter.setFavorite(foodList.get(position).getFood_ID(), 0);
                        foodList.get(position).setFood_Favorite(0);
                        Glide.with(context).load(noFavoriteImgId).into(favoriteImg);
                        message.setToast(context, "ลบ" + foodList.get(position).getFood_Name() + "ออกจากของโปรดเรียบร้อยแล้ว");
                        break;
                }
            }
        });
        if (foodList.get(position).getFood_Favorite() == 1) {
            Glide.with(context).load(favoriteImgId).into(favoriteImg);
        } else {
            Glide.with(context).load(noFavoriteImgId).into(favoriteImg);
        }
        foodHolder.foodNameItem.setText(foodList.get(position).getFood_Name());
        foodHolder.foodCalItem.setText(Integer.toString(foodList.get(position).getFood_Calories()) + " แคลอรี่/จาน");
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void update(ArrayList<FoodListItem> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        foodList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, foodList.size());
    }
}
