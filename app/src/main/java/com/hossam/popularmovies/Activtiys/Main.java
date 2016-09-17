package com.hossam.popularmovies.Activtiys;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hossam.popularmovies.DataProcess.Encap;
import com.hossam.popularmovies.Fragments.MainFragment;
import com.hossam.popularmovies.Fragments.SideFragment;
import com.hossam.popularmovies.R;

public class Main extends AppCompatActivity {

    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_main);

        mainFragment.setMain(this);

    }

    public void updateMovie(Encap encapOPject) {

        SideFragment sideFragment = new SideFragment();

        if (findViewById(R.id.detalis_frame) != null) {

            Bundle bundle = new Bundle();
            bundle.putString("poster", encapOPject.getPosterFilm());
            bundle.putString("Title", encapOPject.getTitleFilm());
            bundle.putString("vote", encapOPject.getVoteFilm());
            bundle.putString("popularity", encapOPject.getPopularityFilm());
            bundle.putString("release_date", encapOPject.getReleaseFilm());
            bundle.putString("overview_film", encapOPject.getOverviewFilm());
            bundle.putString("id", encapOPject.getID());
            bundle.putString("key", encapOPject.getKey());

            sideFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.detalis_frame, sideFragment).commit();

        } else {

            Intent intent = new Intent(getApplicationContext(), Details.class);

            intent.putExtra("poster", encapOPject.getPosterFilm());
            intent.putExtra("Title", encapOPject.getTitleFilm());
            intent.putExtra("vote", encapOPject.getVoteFilm());
            intent.putExtra("popularity", encapOPject.getPopularityFilm());
            intent.putExtra("release_date", encapOPject.getReleaseFilm());
            intent.putExtra("overview_film", encapOPject.getOverviewFilm());
            intent.putExtra("id", encapOPject.getID());
            intent.putExtra("key", encapOPject.getKey());
            startActivity(intent);
            sideFragment.setEncap(encapOPject);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            startActivity(new Intent(this, Settings.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}