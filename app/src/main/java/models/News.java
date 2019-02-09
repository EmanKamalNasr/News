package models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 2/6/2019.
 */

public class News {
    @SerializedName("response")
    private Response response;

    public Response getResponse() {
        return response;
    }
}
