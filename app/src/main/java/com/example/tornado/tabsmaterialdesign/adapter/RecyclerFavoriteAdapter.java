package com.example.tornado.tabsmaterialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tornado.tabsmaterialdesign.model.ModelMovies;
import com.example.tornado.tabsmaterialdesign.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tornado on 7/13/2018.
 */

public class RecyclerFavoriteAdapter extends RecyclerView.Adapter<RecyclerFavoriteAdapter.FavoriteViewHoder> {

    List<ModelMovies> moviesList;
    Context context;

    public RecyclerFavoriteAdapter(List<ModelMovies> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public void setMoviesList(List<ModelMovies> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerFavoriteAdapter.FavoriteViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.rv_row_favorite,parent,false);
       return new FavoriteViewHoder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerFavoriteAdapter.FavoriteViewHoder holder, int position) {
      ModelMovies modelMovies=moviesList.get(position);
      holder.textView.setText(modelMovies.getTitle());
        Picasso.with(context).load(modelMovies.getImageUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if(moviesList!=null){
            return moviesList.size();
        }
        return 0;
    }


   // for searching
    public void setFilter(List<ModelMovies> movieModels){
        moviesList = new ArrayList<>();
        moviesList.addAll(movieModels);
        notifyDataSetChanged();
    }




    public class FavoriteViewHoder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public FavoriteViewHoder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.titleFavorite);
            imageView=(ImageView)itemView.findViewById(R.id.imageFavorite);
        }
    }
}
