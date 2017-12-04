package com.napas.paytouch.ui.search;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.napas.paytouch.R;
import com.napas.paytouch.model.SearchInput;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchActivity extends AppCompatActivity implements SearchView {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.sp_location)
    Spinner spLocation;
    @BindView(R.id.rg_is_top)
    RadioGroup rgIsTop;
    @BindView(R.id.rb_top_yes)
    RadioButton rbTopYes;
    @BindView(R.id.rb_top_no)
    RadioButton rbTopNo;
    @BindView(R.id.rsb_popularity)
    CrystalRangeSeekbar rsbPopularity;
    @BindView(R.id.tv_min_popularity)
    TextView tvMinPopularity;
    @BindView(R.id.tv_max_popularity)
    TextView tvMaxPopularity;


    public static final String KEY_SEARCH_INPUT = "search_input";
    private Unbinder mUnBinder;

    public static Intent getStartIntent(Context context, SearchInput searchInput) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(KEY_SEARCH_INPUT, searchInput);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mUnBinder = ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        SearchInput searchInput = getIntent().getParcelableExtra(KEY_SEARCH_INPUT);
        initView(searchInput);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.enter, R.anim.no_change);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.no_change, R.anim.exit);
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @OnClick(R.id.iv_close)
    public void onCloseClicked() {
        finish();
    }

    @OnClick(R.id.bt_clear)
    public void onClearClicked() {
        etName.setText("");
        spLocation.setSelection(0);
        rgIsTop.clearCheck();
        setPopularityRange(0, 100);
    }

    @OnClick(R.id.bt_search)
    public void onSearchClicked() {
        String name = etName.getText().toString();
        String location = spLocation.getSelectedItemPosition() == 0 ? "" : spLocation.getSelectedItem().toString();
        int topCheckedId = rgIsTop.getCheckedRadioButtonId();
        Boolean top = topCheckedId == -1 ? null : topCheckedId == R.id.rb_top_yes ? true : false;
        Double minPopularity = rsbPopularity.getSelectedMinValue().doubleValue();
        Double maxPopularity = rsbPopularity.getSelectedMaxValue().doubleValue();
        SearchInput searchInput = new SearchInput(name, location, top, minPopularity, maxPopularity);
        publishSearch(searchInput);
    }

    @Override
    public void initView(SearchInput searchInput) {
        String[] locations = getResources().getStringArray(R.array.locations);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, locations);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLocation.setAdapter(spinnerAdapter);

        rsbPopularity.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMinPopularity.setText(String.valueOf(minValue));
                tvMaxPopularity.setText(String.valueOf(maxValue));
            }
        });

        if (searchInput == null) return;

        String searchName = searchInput.getName();
        String searchLocation = searchInput.getLocation();
        Boolean searchTop = searchInput.getTop();
        double searchMinPopularity = searchInput.getMinPopularity();
        double searchMaxPopularity = searchInput.getMaxPopularity();

        etName.setText(searchName);
        if (!TextUtils.isEmpty(searchLocation)) {
            int selectedLocationId = Arrays.asList(locations).indexOf(searchInput.getLocation());
            spLocation.setSelection(selectedLocationId);
        }
        if (searchTop == null) {
        } else if (searchTop) {
            rbTopYes.setChecked(true);
        } else {
            rbTopNo.setChecked(true);
        }
        setPopularityRange((float) searchMinPopularity, (float) searchMaxPopularity);
    }

    @Override
    public void publishSearch(SearchInput searchInput) {
        Intent data = new Intent();
        data.putExtra(KEY_SEARCH_INPUT, searchInput);
        setResult(RESULT_OK, data);
        finish();
    }

    private void setPopularityRange(float min, float max) {
        rsbPopularity.setMinStartValue(min);
        rsbPopularity.setMaxStartValue(max);
        rsbPopularity.apply();
    }

}
