package com.napas.paytouch.ui.detail;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.napas.paytouch.R;
import com.napas.paytouch.model.Actor;
import com.napas.paytouch.model.Filmography;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class ActorDetailDialogFragment extends AppCompatDialogFragment {

    private static final String KEY_ACTOR = "actor";
    @BindView(R.id.sv_content)
    NestedScrollView svContent;
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
    @BindView(R.id.rv_filmography)
    RecyclerView rvFilmography;

    private Unbinder mUnBinder;
    private Actor mActor;
    private FilmographyListAdapter mAdapter;

    public static ActorDetailDialogFragment newInstance(Actor actor) {
        ActorDetailDialogFragment f = new ActorDetailDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_ACTOR, actor);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActor = getArguments().getParcelable(KEY_ACTOR);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_actor_detail, container, false);
        mUnBinder = ButterKnife.bind(this, view);
        setActorProfile();
        setActorFilmography();
        return view;
    }

    @Override
    public void onDestroyView() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroyView();
    }

    @OnClick(R.id.iv_back)
    public void onBackClicked() {
        dismiss();
    }

    private void setActorProfile() {
        Picasso.with(getContext()).load(mActor.getProfilePath()).fit().centerInside().into(ivProfile);
        tvName.setText(mActor.getName());
        tvLocation.setText(mActor.getLocation());
        tvPopularity.setText(String.format("%.2f", mActor.getPopularity()));
        tvDescription.setText(mActor.getDescription());
    }

    private void setActorFilmography() {
        List<Filmography> filmographies = mActor.getKnownFor();
        if (filmographies == null || filmographies.isEmpty()) return;

        mAdapter = new FilmographyListAdapter(getContext(), filmographies);
        rvFilmography.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFilmography.setFocusable(false);
        rvFilmography.setAdapter(mAdapter);
    }

}
