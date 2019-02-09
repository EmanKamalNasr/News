package models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 2/6/2019.
 */

public class Field {
    @SerializedName("headline")
    private String newsTitle;
    @SerializedName("byline")
    private String authorName;
    @SerializedName("shortUrl")
    private String webUrl;
    @SerializedName("thumbnail")
    private String imgUrl;

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
