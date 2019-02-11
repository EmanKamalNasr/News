package com.example.android.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import models.Article;

/**
 * Created by HP on 2/6/2019.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>
         {
    private List<Article> articles;
    private Context context;
    private View rootView;

    public NewsAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);
        context = parent.getContext();
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Article currentArticle = articles.get(position);
        Glide.with(context)
                .load(currentArticle.getField().getImgUrl())
                .apply(new RequestOptions().centerCrop())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);
        holder.webTitle_tv.setText(currentArticle.getField().getNewsTitle());
        holder.authorName_tv.setText(currentArticle.getField().getAuthorName());
        holder.date_tv.setText(currentArticle.getDate().substring(0, 10));
        holder.sectionName_tv.setText(currentArticle.getSectionName());

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(currentArticle.getField().getWebUrl()));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        ImageView imageView;
        TextView webTitle_tv;
        TextView authorName_tv;
        TextView date_tv;
        TextView sectionName_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.loadimg_pb);
            imageView = itemView.findViewById(R.id.img);
            webTitle_tv = itemView.findViewById(R.id.headline_tv);
            authorName_tv = itemView.findViewById(R.id.byline_tv);
            date_tv = itemView.findViewById(R.id.webPublicationDate_tv);
            sectionName_tv = itemView.findViewById(R.id.sectionName_tv);
        }
    }
}
