package models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 2/6/2019.
 */

public class Article {
    @SerializedName("sectionName")
    private String sectionName;
    @SerializedName("webPublicationDate")
    private String date;
    @SerializedName("fields")
    private Field field;

    public String getSectionName() {
        return sectionName;
    }

    public String getDate() {
        return date;
    }

    public Field getField() {
        return field;
    }
}
