package in.krharsh17.habita.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dishesop {
    @SerializedName("image")
    String image;
    @SerializedName("missedIngredientCount")
    int missedIngredient;
    @SerializedName("title")
    String title;
    @SerializedName("missedIngredients")
    List<missedIngredients> missedIngredientsList;

    public dishesop(String image, int missedIngredient, String title) {
        this.image = image;
        this.missedIngredient = missedIngredient;
        this.title = title;
    }

    public List<missedIngredients> getMissedIngredientsList() {
        return missedIngredientsList;
    }

    public void setMissedIngredientsList(List<missedIngredients> missedIngredientsList) {
        this.missedIngredientsList = missedIngredientsList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMissedIngredient() {
        return missedIngredient;
    }

    public void setMissedIngredient(int missedIngredient) {
        this.missedIngredient = missedIngredient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}