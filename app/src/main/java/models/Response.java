package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HP on 2/6/2019.
 */

public class Response {
    @SerializedName("results")
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }
}
