package com.napas.paytouch.ui.main;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.napas.paytouch.R;
import com.napas.paytouch.model.Actor;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.subjects.PublishSubject;

public class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ViewHolder> {

    private final PublishSubject<Actor> onClickSubject = PublishSubject.create();
    private Context mContext;
    private List<Actor> mActors;


    public ActorListAdapter(Context context, List<Actor> actors) {
        mContext = context;
        mActors = actors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // set color
        boolean isEvenPosition = position % 2 == 0;
        int textColor = ContextCompat.getColor(mContext, isEvenPosition ? R.color.white : R.color.colorPrimary);
        int bgColor = ContextCompat.getColor(mContext, isEvenPosition ? R.color.colorPrimary : R.color.white);
        holder.cvRoot.setCardBackgroundColor(bgColor);
        holder.tvName.setTextColor(textColor);
        holder.tvLocation.setTextColor(textColor);
        DrawableCompat.setTint(holder.tvLocation.getCompoundDrawables()[0], textColor);
        holder.tvDescription.setTextColor(textColor);

        // set content
        final Actor actor = mActors.get(position);
        Picasso.with(mContext).load(actor.getProfilePath()).fit().centerInside().into(holder.ivProfile);
        holder.tvName.setText(actor.getName());
        holder.tvLocation.setText(actor.getLocation());
        holder.tvPopularity.setText(String.format("%.2f", actor.getPopularity()));
        holder.tvPopularity.setVisibility(View.VISIBLE);
        holder.tvDescription.setText(actor.getDescription());

        // set onclick listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubject.onNext(actor);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mActors.size();
    }

    public PublishSubject<Actor> getPositionClicks() {
        return onClickSubject;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv_root)
        CardView cvRoot;
        @BindView(R.id.iv_profile)
        CircleImageView ivProfile;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_popularity)
        TextView tvPopularity;
        @BindView(R.id.tv_description)
        TextView tvDescription;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
