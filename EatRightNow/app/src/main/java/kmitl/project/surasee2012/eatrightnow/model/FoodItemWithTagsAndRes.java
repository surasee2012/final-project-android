package kmitl.project.surasee2012.eatrightnow.model;

import java.util.ArrayList;

/**
 * Created by Gun on 11/24/2017.
 */

public class FoodItemWithTagsAndRes extends FoodItem {

    private int Food_Favorite;
    private ArrayList<Integer> tags;
    private String Food_Restaurant;

    public int getFood_Favorite() {
        return Food_Favorite;
    }

    public void setFood_Favorite(int food_Favorite) {
        Food_Favorite = food_Favorite;
    }

    public ArrayList<Integer> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Integer> tags) {
        this.tags = tags;
    }

    public String getFood_Restaurant() {
        return Food_Restaurant;
    }

    public void setFood_Restaurant(String food_Restaurant) {
        Food_Restaurant = food_Restaurant;
    }
}
