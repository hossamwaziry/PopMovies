package com.hossam.popularmovies.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hossam.popularmovies.DataBase.DataBase;
import com.hossam.popularmovies.DataProcess.Encap;
import com.hossam.popularmovies.Fragments.MainFragment;
import com.hossam.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Encap> encaplist = Collections.emptyList();
    Encap encapOpject = new Encap();
    Context context;
    SQLiteDatabase sqLiteDatabase;
    DataBase database;
    MainFragment mf;

    public Adapter(List<Encap> encaplist, Context context, MainFragment mainFragment) {
        this.encaplist = encaplist;
        this.context = context;
        this.mf = mainFragment;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);

        ViewHolder viewHolder = new ViewHolder(layout);
        return viewHolder;
    }

    public DataBase db() {
        database = new DataBase(context);
        return database;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        encapOpject = encaplist.get(position);
        holder.start.setTag(position);
        holder.cardView.setTag(position);
        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();

                encapOpject = encaplist.get(position);

                boolean check = db().CheckOpject(encapOpject.getTitleFilm());
                if (check == false) {

                    sqLiteDatabase = db().getWritableDatabase();

                    db().insertData(sqLiteDatabase, encapOpject.getOverviewFilm(),
                            encapOpject.getTitleFilm(), encapOpject.getPosterFilm(), encapOpject.getReleaseFilm(),
                            encapOpject.getPopularityFilm(), encapOpject.getVoteFilm());

                    db().close();
                    view.setBackgroundResource(R.drawable.ic_favorite_white_36dp);
                    Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(context, "This Movie already added to Favorites ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Picasso.with(context).load(encapOpject.getPosterFilm()).into(holder.img_poster);
        animate(holder);

    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    @Override
    public int getItemCount() {
        return encaplist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView img_poster;
        public CardView cardView;
        public Button start;

        public ViewHolder(View layout) {
            super(layout);

            cardView = (CardView) layout.findViewById(R.id.list_row_container);
            start = (Button) layout.findViewById(R.id.btn_favorite);
            cardView.setOnClickListener(this);
            img_poster = (ImageView) layout.findViewById(R.id.image_poster);

        }

        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();

            encapOpject = encaplist.get(position);
            mf.setEncap_get_op(encapOpject);
            mf.OnMovieClicked(encapOpject);

        }
    }

}