package com.example.android.news;

import models.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by HP on 2/6/2019.
 */

public interface NewsApiInterface {

    @GET("search?show-fields=headline,shortUrl,thumbnail,byline")
    Call<News> getNews(@Query("api-key") String apiKey,
                       @Query("section") String section);
}
