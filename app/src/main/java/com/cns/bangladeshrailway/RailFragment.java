package com.cns.bangladeshrailway;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.leanback.app.BackgroundManager;
import androidx.leanback.app.BrowseFragment;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.PageRow;
import androidx.leanback.widget.Row;

public class RailFragment extends BrowseSupportFragment {
    private static final long HEADER_ID_1 = 1;
    private static final String HEADER_NAME_1 = "Train";
    private static final long HEADER_ID_2 = 2;
    private static final String HEADER_NAME_2 = "Rows Fragment";
    private static final long HEADER_ID_3 = 3;
    private static final String HEADER_NAME_3 = "Settings Fragment";
    private static final long HEADER_ID_4 = 4;
    private static final String HEADER_NAME_4 = "User agreement Fragment";
    private BackgroundManager mBackgroundManager;

    private ArrayObjectAdapter mRowsAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        setupUi();
//        loadData();
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
//        getMainFragmentRegistry().registerFragment(PageRow.class, new PageRowFragmentFactory(mBackgroundManager));

    }

    private static class PageRowFragmentFactory extends BrowseFragment.FragmentFactory {
        private final BackgroundManager mBackgroundManager;

        PageRowFragmentFactory(BackgroundManager backgroundManager) {
            this.mBackgroundManager = backgroundManager;
        }

        @Override
        public Fragment createFragment(Object rowObj) {
            Row row = (Row)rowObj;
            mBackgroundManager.setDrawable(null);
            if (row.getHeaderItem().getId() == HEADER_ID_1) {
//                return new SampleFragmentA();
            } else if (row.getHeaderItem().getId() == HEADER_ID_2) {
//                return new SampleFragmentB();
            } else if (row.getHeaderItem().getId() == HEADER_ID_3) {
//                return new SettingsFragment();
            } else if (row.getHeaderItem().getId() == HEADER_ID_4) {
//                return new WebViewFragment();
            }

            throw new IllegalArgumentException(String.format("Invalid row %s", rowObj));
        }
    }
}
