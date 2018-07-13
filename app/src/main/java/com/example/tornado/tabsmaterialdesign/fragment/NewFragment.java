package com.example.tornado.tabsmaterialdesign.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.tornado.tabsmaterialdesign.utility.ApiClient;
import com.example.tornado.tabsmaterialdesign.interfase.ApiInterface;
import com.example.tornado.tabsmaterialdesign.model.ModelMovies;
import com.example.tornado.tabsmaterialdesign.R;
import com.example.tornado.tabsmaterialdesign.adapter.RecyclerNewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends Fragment {

    private SearchView searchView;
    RecyclerView recyclerView;
    RecyclerNewAdapter adapter;
    List<ModelMovies> moviesList;

//    private OnFragmentInteractionListener listener;
    public static NewFragment newInstance() {
        return new NewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_new, container, false);


        moviesList= new ArrayList<>();
        adapter=new RecyclerNewAdapter(moviesList,getContext());
        recyclerView=(RecyclerView) view.findViewById(R.id.rv_new);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);

       loadContent();

        return view;
    }


    private void loadContent() {

        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<List<ModelMovies>> call = apiService.getMovie();
        call.enqueue(new Callback<List<ModelMovies>>() {
            @Override
            public void onResponse(Call<List<ModelMovies>> call, Response<List<ModelMovies>> response) {

                moviesList=response.body();
                adapter.setMovieList(moviesList);
            }

            @Override
            public void onFailure(Call<List<ModelMovies>> call, Throwable t) {

            }
        });

    }





}
