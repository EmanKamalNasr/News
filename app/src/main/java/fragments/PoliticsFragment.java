package fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.news.NewsAdapter;
import com.example.android.news.NewsApiInterface;
import com.example.android.news.R;

import java.util.ArrayList;
import java.util.List;

import models.Article;
import models.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 2/7/2019.
 */

public class PoliticsFragment extends Fragment {
    private static final String BASE_URL = "https://content.guardianapis.com/";
    private NewsApiInterface newsApiInterface;
    private static final String API_KEY = "test";
    Retrofit retrofit;

    private List<Article> articleList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        articleList = new ArrayList<>();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        newsApiInterface = retrofit.create(NewsApiInterface.class);
        Call<News> call;
        call = newsApiInterface.getNews(API_KEY, getString(R.string.sec_politics));
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() &&
                        response.body().getResponse().getArticles() != null) {
                    if (!articleList.isEmpty()) {
                        articleList.clear();
                    }
                    articleList = response.body().getResponse().getArticles();
                    final NewsAdapter newsAdapter = new NewsAdapter(articleList, getContext());
                    final RecyclerView politicsRecyclerView = (RecyclerView) view.findViewById(R.id.home_recyclerview);
                    politicsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    politicsRecyclerView.setAdapter(newsAdapter);
                    newsAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "no result", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(getContext(), getString(R.string.no_internet), Toast.LENGTH_LONG).show();

            }
        });

    }


}
