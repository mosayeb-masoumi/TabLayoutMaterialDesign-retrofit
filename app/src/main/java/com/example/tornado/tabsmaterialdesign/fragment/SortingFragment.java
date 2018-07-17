package com.example.tornado.tabsmaterialdesign.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.tornado.tabsmaterialdesign.R;
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
public class SortingFragment extends Fragment implements SearchView.OnQueryTextListener {

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

        setHasOptionsMenu(true);

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






    //for searching
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        final SearchView searchView=(SearchView) MenuItemCompat.getActionView(item) ;
        final android.support.v7.widget.SearchView searchView=(android.support.v7.widget.SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        adapter.setFilter(moviesList);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
    }

    //for searching
    @Override
    public boolean onQueryTextChange(String newText) {
        final List<ModelMovies> filteredModelList = filter(moviesList, newText);
        adapter.setFilter(filteredModelList);
        return true;
    }

    //for searching
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    //for searching
    private List<ModelMovies> filter(List<ModelMovies> models, String query) {
        query = query.toLowerCase();

        final List<ModelMovies> filteredModelList = new ArrayList<>();
        for (ModelMovies model : models) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}