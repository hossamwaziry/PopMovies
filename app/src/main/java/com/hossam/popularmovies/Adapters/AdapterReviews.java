package com.hossam.popularmovies.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hossam.popularmovies.DataProcess.Encap;
import com.hossam.popularmovies.R;

import java.util.Collections;
import java.util.List;


public class AdapterReviews extends RecyclerView.Adapter<AdapterReviews.ViewHolder> {

    List<Encap> encaplist = Collections.emptyList();
    Context context;
    Encap encapOpject = new Encap();

    public AdapterReviews(List<Encap> encaplist, Context context) {
        this.encaplist = encaplist;
        this.context = context;
    }

    @Override
    public AdapterReviews.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_reviews, parent, false);
        ViewHolder viewHolder = new ViewHolder(layout);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        encapOpject = encaplist.get(position);
        holder.cardView.setTag(position);
        holder.review.setText(encapOpject.getReviews());

    }

    @Override
    public int getItemCount() {
        return encaplist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView review;

        public ViewHolder(View layout) {
            super(layout);

            cardView = (CardView) layout.findViewById(R.id.card_reveiw);
            review = (TextView) layout.findViewById(R.id.reveiw_video);

        }


    }
}



