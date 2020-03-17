package in.krharsh17.habita.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Concepts {
    @SerializedName("concepts")
    List<Items> items;

    public Concepts(List<Items> items) {
        this.items = items;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}