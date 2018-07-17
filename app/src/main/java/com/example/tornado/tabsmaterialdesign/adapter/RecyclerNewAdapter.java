package com.example.tornado.tabsmaterialdesign.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

public class RecyclerNewAdapter extends RecyclerView.Adapter<RecyclerNewAdapter.MyViewHolder> {

    List<ModelMovies> moviesList;
    Context context;


    public RecyclerNewAdapter(List<ModelMovies> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public void setMovieList(List<ModelMovies> movieList) {
        this.moviesList = movieList;
        notifyDataSetChanged();
    }






    @Override
    public RecyclerNewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_row_new,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerNewAdapter.MyViewHolder holder, int position) {

       ModelMovies modelMovies= moviesList.get(position);
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


   //for search
    public void setFilter(List<ModelMovies> movieModels){
        moviesList = new ArrayList<>();
        moviesList.addAll(movieModels);
        notifyDataSetChanged();
    }






    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.title);
            imageView=(ImageView)itemView.findViewById(R.id.image);
        }
    }
}