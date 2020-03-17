package in.krharsh17.habita.models;

import com.google.gson.annotations.SerializedName;

public class inputs {
    @SerializedName("data")
    images images;

    public inputs(in.krharsh17.habita.models.images images) {
        this.images = images;
    }

    public in.krharsh17.habita.models.images getImages() {
        return images;
    }

    public void setImages(in.krharsh17.habita.models.images images) {
        this.images = images;
    }
}