package kmitl.project.surasee2012.eatrightnow.model;

/**
 * Created by Gun on 11/17/2017.
 */

public class FoodRandom {

    private String Food_Name;
    private int Food_Calories;
    private int errorCollector;

    public String getFood_Name() {
        return Food_Name;
    }

    public void setFood_Name(String food_Name) {
        Food_Name = food_Name;
    }

    public int getFood_Calories() {
        return Food_Calories;
    }

    public void setFood_Calories(int food_Calories) {
        Food_Calories = food_Calories;
    }

    public int getErrorCollector() {
        return errorCollector;
    }

    public void setErrorCollector(int errorCollector) {
        this.errorCollector = errorCollector;
    }
}
