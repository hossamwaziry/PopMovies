package com.hossam.popularmovies.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hossam.popularmovies.Adapters.AdapterReviews;
import com.hossam.popularmovies.Adapters.AdapterTrailer;
import com.hossam.popularmovies.DataProcess.Encap;
import com.hossam.popularmovies.DataProcess.KeyTags;
import com.hossam.popularmovies.HttpConnection.ConnectionHttp;
import com.hossam.popularmovies.Parser.Json_reviews;
import com.hossam.popularmovies.Parser.Json_trailer;
import com.hossam.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;


public class SideFragment extends Fragment implements View.OnClickListener {
    Json_trailer jsonTrailer = new Json_trailer();
    Json_reviews jsonReviews = new Json_reviews();
    View view;
    Encap encap;
    private RecyclerView recyclerView;
    private RecyclerView recycler_review;
    private AdapterTrailer adapter;
    private AdapterReviews adapter_reviews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.total_details_activity, container, false);

        DataReceive();

        return view;
    }


    public void DataReceive() {

        InitializeUI.text_TitleFilm = (TextView) view.findViewById(R.id.text_title);
        InitializeUI.text_OverviewFilm = (TextView) view.findViewById(R.id.text_overview);
        InitializeUI.text_ReleaseFilm = (TextView) view.findViewById(R.id.text_release_date);
        InitializeUI.text_VoteFilm = (TextView) view.findViewById(R.id.text_vote_average);
        InitializeUI.text_PopularityFilm = (TextView) view.findViewById(R.id.text_popularity_film);
        InitializeUI.image_posterfilm = (ImageView) view.findViewById(R.id.img_poster);

        try {

            Bundle args = getDataBundle();

            if (args.get("id") != null) {

                System.out.println(args.get("id"));
                String c = KeyTags.api1 + args.getString("id") + KeyTags.trailer_api;
                String review = KeyTags.api1 + args.getString("id") + KeyTags.reviews_api;

                jsonTrailer.JsonProcess_trailer(new ConnectionHttp().execute(c).get());
                jsonReviews.JsonProcess(new ConnectionHttp().execute(review).get());

                InitializeUI.text_TitleFilm.setText(args.getString("Title"));
                InitializeUI.text_VoteFilm.setText("Vote : " + args.getString("vote"));
                InitializeUI.text_PopularityFilm.setText("popularity : " + args.getString("popularity"));
                InitializeUI.text_ReleaseFilm.setText("Release date : " + args.getString("release_date"));
                InitializeUI.text_OverviewFilm.setText("Overview : " + args.getString("overview_film"));
                Picasso.with(view.getContext()).load(String.valueOf(args.getString("poster"))).into(InitializeUI.image_posterfilm);

                Trailer();
                Review();

            } else if (args.get("id") == null) {

                InitializeUI.text_TitleFilm.setText(args.getString("Title"));
                InitializeUI.text_VoteFilm.setText("Vote : " + args.getString("vote"));
                InitializeUI.text_PopularityFilm.setText("popularity : " + args.getString("popularity"));
                InitializeUI.text_ReleaseFilm.setText("Release date : " + args.getString("release_date"));
                InitializeUI.text_OverviewFilm.setText("Overview : " + args.getString("overview_film"));
                Picasso.with(view.getContext()).load(String.valueOf(args.getString("poster"))).into(InitializeUI.image_posterfilm);

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }


    private Bundle getDataBundle() {

        if (getArguments() == null) {

            return getActivity().getIntent().getExtras();

        } else {

            return getArguments();

        }
    }

    private void Trailer() {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterTrailer(jsonTrailer.getlist2(), view.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(this);

    }

    private void Review() {

        recycler_review = (RecyclerView) view.findViewById(R.id.recycler_review);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recycler_review.setLayoutManager(layoutManager);
        adapter_reviews = new AdapterReviews(jsonReviews.getlist(), view.getContext());
        recycler_review.setAdapter(adapter_reviews);

    }

    @Override
    public void onClick(View v) {
        adapter.getItemCount();
    }

    public Encap getEncap() {
        return encap;
    }

    public void setEncap(Encap encap) {
        this.encap = encap;
    }

}


class InitializeUI {

    static TextView text_TitleFilm;
    static TextView text_OverviewFilm;
    static TextView text_VoteFilm;
    static TextView text_ReleaseFilm;
    static TextView text_PopularityFilm;
    static ImageView image_posterfilm;

}