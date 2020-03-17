package in.krharsh17.habita.models;

import com.google.gson.annotations.SerializedName;

public class data {
    @SerializedName("url")
    String url;

    public data(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}