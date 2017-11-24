package kmitl.project.surasee2012.eatrightnow.model;

/**
 * Created by Gun on 11/17/2017.
 */

public class FoodRandomItem extends FoodItem {

    private String Food_Restaurant;
    private int errorCollector;

    public String getFood_Restaurant() {
        return Food_Restaurant;
    }

    public void setFood_Restaurant(String food_Restaurant) {
        Food_Restaurant = food_Restaurant;
    }

    public int getErrorCollector() {
        return errorCollector;
    }

    public void setErrorCollector(int errorCollector) {
        this.errorCollector = errorCollector;
    }
}
