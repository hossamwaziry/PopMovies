package com.hossam.popularmovies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hossam.popularmovies.Activtiys.Details;
import com.hossam.popularmovies.DataProcess.Encap;
import com.hossam.popularmovies.DataProcess.KeyTags;
import com.hossam.popularmovies.R;

import java.util.Collections;
import java.util.List;

public class AdapterTrailer extends RecyclerView.Adapter<AdapterTrailer.ViewHolder> implements View.OnClickListener {

    List<Encap> encaplist = Collections.emptyList();
    Context context;
    Encap encapOpject = new Encap();

    public AdapterTrailer(List<Encap> encaplist, Context context) {
        this.encaplist = encaplist;
        this.context = context;
    }

    @Override
    public AdapterTrailer.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_detalis, parent, false);

        ViewHolder viewHolder = new ViewHolder(layout);
        return viewHolder;

    }

    @Override
    public void onClick(View v) {

        int position = (int) v.getTag();
        encapOpject = encaplist.get(position);
        v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(KeyTags.youtube + encapOpject.getKey())));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        encapOpject = encaplist.get(position);

        holder.cardView.setTag(position);
        holder.posterTralier.setTag(position);
        holder.posterTralier.setOnClickListener(this);
        holder.cardView.setOnClickListener(this);
        holder.textTrailer.setText("Trailer " + ++position);

    }

    @Override
    public int getItemCount() {
        return encaplist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView cardView;
        public ImageView posterTralier;
        public TextView textTrailer;

        public ViewHolder(View layout) {
            super(layout);

            cardView = (CardView) layout.findViewById(R.id.card_DETALIS);
            posterTralier = (ImageView) layout.findViewById(R.id.tralier_video);
            textTrailer = (TextView) layout.findViewById(R.id.txt_trailer);
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();

            encapOpject = encaplist.get(position);
            Intent intent = new Intent(view.getContext(), Details.class);
            intent.putExtra("key", KeyTags.youtube + encapOpject.getKey());
            view.getContext().startActivity(intent);
        }

    }
}



