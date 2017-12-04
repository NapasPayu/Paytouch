package com.napas.paytouch.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.napas.paytouch.R;
import com.napas.paytouch.model.Actor;
import com.napas.paytouch.model.SearchInput;
import com.napas.paytouch.ui.base.BaseActivity;
import com.napas.paytouch.ui.detail.ActorDetailDialogFragment;
import com.napas.paytouch.ui.search.SearchActivity;
import com.napas.paytouch.util.comparator.NameComparator;
import com.napas.paytouch.util.comparator.PopularityComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter<MainView> mPresenter;

    @BindView(R.id.rv_actors)
    RecyclerView rvActors;

    private static final int REQUEST_CODE_SEARCH = 1;
    private List<Actor> mActors, mSearchedActors;
    private boolean isSearchPerformed = false;
    private ActorListAdapter mAdapter;
    private SearchInput mSearchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        mPresenter.onInitialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearchActivity();
                return true;
            case R.id.action_order_name:
                showSoredActors(isSearchPerformed ? mSearchedActors : mActors, new NameComparator());
                return true;
            case R.id.action_order_popularity:
                showSoredActors(isSearchPerformed ? mSearchedActors : mActors, new PopularityComparator());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SEARCH) {
            if (resultCode == RESULT_OK) {
                mSearchInput = data.getParcelableExtra(SearchActivity.KEY_SEARCH_INPUT);
                if (TextUtils.isEmpty(mSearchInput.getName())
                        && TextUtils.isEmpty(mSearchInput.getLocation())
                        && mSearchInput.getTop() == null
                        && mSearchInput.getMinPopularity() == 0
                        && mSearchInput.getMaxPopularity() == 100) {     // empty search
                    isSearchPerformed = false;
                    mSearchedActors = null;
                    setActorList(mActors);
                } else {
                    isSearchPerformed = true;
                    mPresenter.performSearch(mActors, mSearchInput);
                }
            }
        }
    }

    @Override
    public void setActorList(List<Actor> actors) {
        if (actors == null) return;

        if (isSearchPerformed) {
            mSearchedActors = actors;
            mAdapter = new ActorListAdapter(this, mSearchedActors);
        } else {
            mActors = actors;
            mAdapter = new ActorListAdapter(this, mActors);
        }

        mAdapter.getPositionClicks().subscribe(new Consumer<Actor>() {
            @Override
            public void accept(Actor actor) throws Exception {
                showActorDetail(actor);
            }
        });

        rvActors.setAdapter(mAdapter);
    }

    @Override
    public void showActorDetail(Actor actor) {
        if (actor == null) return;

        AppCompatDialogFragment dialogFragment = ActorDetailDialogFragment.newInstance(actor);
        dialogFragment.show(getSupportFragmentManager(), ActorDetailDialogFragment.class.getSimpleName());
    }

    @Override
    public void showSoredActors(List<Actor> actors, Comparator<Actor> comparator) {
        if (actors == null || actors.isEmpty()) return;

        Collections.sort(actors, comparator);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void openSearchActivity() {
        Intent intent = SearchActivity.getStartIntent(this, mSearchInput);
        startActivityForResult(intent, REQUEST_CODE_SEARCH);
    }

}
