package kmitl.project.surasee2012.eatrightnow.model;

import java.util.ArrayList;

/**
 * Created by Gun on 11/24/2017.
 */

public class FoodItemWithTags extends FoodItem {

    private int Food_Favorite;
    private ArrayList<Integer> tags;

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

    @Override
    public String toString() {
        return "FoodItemWithTags{" +
                "Food_Favorite=" + Food_Favorite +
                ", tags=" + tags +
                '}';
    }
}
