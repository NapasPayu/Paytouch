package com.napas.paytouch.ui.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.napas.paytouch.R;
import com.napas.paytouch.model.Filmography;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmographyListAdapter extends RecyclerView.Adapter<FilmographyListAdapter.ViewHolder> {

    private Context mContext;
    private List<Filmography> mFilmographies;

    public FilmographyListAdapter(Context context, List<Filmography> filmographies) {
        mContext = context;
        mFilmographies = filmographies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filmography, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Filmography filmography = mFilmographies.get(position);
        Long releaseDate = filmography.getReleaseDate();
        Picasso.with(mContext).load(filmography.getPosterPath()).fit().centerInside().into(holder.ivPoster);
        holder.tvTitle.setText(filmography.getTitle());
        holder.tvReleaseDate.setText(releaseDate == null ? "" : DateUtils.formatDateTime(mContext, releaseDate, DateUtils.FORMAT_NUMERIC_DATE));
        holder.tvVoteAverage.setText(String.format("%.2f", filmography.getVoteAverage()));
        holder.tvVoteCount.setText(mContext.getString(R.string.vote_count, filmography.getVoteCount()));
    }

    @Override
    public int getItemCount() {
        return mFilmographies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster)
        ImageView ivPoster;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_release_date)
        TextView tvReleaseDate;
        @BindView(R.id.tv_vote_average)
        TextView tvVoteAverage;
        @BindView(R.id.tv_vote_count)
        TextView tvVoteCount;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
