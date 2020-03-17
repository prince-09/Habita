package in.krharsh17.habita.models;

import com.google.gson.annotations.SerializedName;

public class images {
    @SerializedName("image")
    data url;

    public images(data url) {
        this.url = url;
    }

    public data getUrl() {
        return url;
    }

    public void setUrl(data url) {
        this.url = url;
    }
}