package com.example.tornado.tabsmaterialdesign.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tornado.tabsmaterialdesign.R;
import com.example.tornado.tabsmaterialdesign.adapter.RecyclerFavoriteAdapter;
import com.example.tornado.tabsmaterialdesign.adapter.RecyclerSortingAdapter;
import com.example.tornado.tabsmaterialdesign.interfase.ApiInterface;
import com.example.tornado.tabsmaterialdesign.model.ModelMovies;
import com.example.tornado.tabsmaterialdesign.utility.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SortingFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerSortingAdapter adapter;
    List<ModelMovies> moviesList;

    //    public SortingFragment() {
//        // Required empty public constructor
//    }
    public static SortingFragment newInstance() {
        return new SortingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sorting, container, false);


        moviesList = new ArrayList<>();
        adapter = new RecyclerSortingAdapter(moviesList, getContext());


        recyclerView = view.findViewById(R.id.rv_sorting);

        RecyclerView.LayoutManager mLayoutMAnager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutMAnager);
        recyclerView.setAdapter(adapter);

        loadContent();

        return view;

    }

    private void loadContent() {

        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<List<ModelMovies>> call=apiInterface.getMovie();
        call.enqueue(new Callback<List<ModelMovies>>() {
            @Override
            public void onResponse(Call<List<ModelMovies>> call, Response<List<ModelMovies>> response) {
                moviesList=response.body();
                adapter.setMoviesList(moviesList);
            }

            @Override
            public void onFailure(Call<List<ModelMovies>> call, Throwable t) {

            }
        });
    }
}