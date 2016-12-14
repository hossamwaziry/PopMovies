package com.hossam.popularmovies.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.hossam.popularmovies.Activtiys.Main;
import com.hossam.popularmovies.Activtiys.Settings;
import com.hossam.popularmovies.Adapters.Adapter;
import com.hossam.popularmovies.DataBase.DataBase;
import com.hossam.popularmovies.DataBase.SQL_Commends;
import com.hossam.popularmovies.DataProcess.Encap;
import com.hossam.popularmovies.HttpConnection.ConnectionHttp;
import com.hossam.popularmovies.Parser.JsonParser;
import com.hossam.popularmovies.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainFragment extends Fragment implements View.OnClickListener {

    boolean CheckState;
    JsonParser jsonParser = new JsonParser();
    Encap encap = new Encap();
    Encap encap_get_op;
    Main main;
    View view;
    Context context;
    DataBase database = new DataBase(context);
    List<Encap> data;
    SQLiteDatabase sqLiteDatabase;
    private SharedPreferences preferences;
    private RecyclerView recyclerView;
    private Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.total_main_content, container, false);

        initViews();
        FapsButton();
        Ads();
        return view;
    }


    private void Ads() {
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void initViews() {

        if (isOnline() == true) {

            Connector(encap.getAPI());
            recyclerMain();
            animRecycleview();
        } else {

            Toast.makeText(getActivity().getApplicationContext(), "No Internet connection :( ", Toast.LENGTH_SHORT).show();
            recyclerFavorite();
        }
    }

    private void FapsButton() {

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        FloatingActionButton fab_fav = (FloatingActionButton) view.findViewById(R.id.fab_fav);

        fab_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerFavorite();
                CheckState = true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckState = false;
                if (isOnline() == true) {
                    Update();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "No Internet connection :( ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (isOnline() == true) {
            initViews();
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No Internet connection :( ", Toast.LENGTH_SHORT).show();
        }
    }

    public void OnMovieClicked(Encap encap) {
        main.updateMovie(encap);
    }

    private JsonParser Connector(String url) {

        try {

            jsonParser.JsonProcess(new ConnectionHttp().execute(url).get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return jsonParser;

    }


    private void recyclerMain() {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(jsonParser.getlist(), view.getContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter.notifyDataSetChanged();

    }

    private void recyclerFavorite() {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(getData(), view.getContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter.notifyDataSetChanged();

    }

    public List<Encap> getData() {

        data = new ArrayList<>();

        database = new DataBase(getActivity().getApplicationContext());
        database.onOpen(sqLiteDatabase);
        sqLiteDatabase = database.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SQL_Commends.SELECT_ALL_QUERY, null);

        if (cursor.moveToFirst()) {
            do {
                Encap encap = new Encap();
                encap.setOverviewFilm(cursor.getString(1));
                encap.setTitleFilm(cursor.getString(2));
                encap.setPosterFilm(cursor.getString(3));
                encap.setReleaseFilm(cursor.getString(4));
                encap.setPopularityFilm(cursor.getString(5));
                encap.setVoteFilm(cursor.getString(6));
                data.add(encap);

            } while (cursor.moveToNext());

        }
        cursor.close();
        database.close();

        return data;
    }


    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    private void Update() {
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String refresh = preferences.getString("api_option", "");

        if (refresh.contains("https")) {

            if (isOnline() == true) {

                Connector(refresh);

                recyclerMain();

            } else {
                recyclerFavorite();

                Toast.makeText(getActivity().getApplicationContext(), "No Internet connection :( ", Toast.LENGTH_SHORT).show();
            }

        } else {
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        if (CheckState == true) {

            recyclerFavorite();

        } else if (CheckState == false) {

            Update();

        } else {

            initViews();

        }
    }

    public void setMain(Main activity) {
        this.main = activity;
    }

    private void animRecycleview() {

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

    }

    public void setEncap_get_op(Encap encap_get_op) {
        this.encap_get_op = encap_get_op;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            startActivity(new Intent(getActivity().getApplicationContext(), Settings.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View v) {
        adapter.getItemCount();
    }


}
