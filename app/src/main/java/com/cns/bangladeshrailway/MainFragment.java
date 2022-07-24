package com.cns.bangladeshrailway;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.leanback.app.BrowseSupportFragment;

import com.cns.bangladeshrailway.utils.SharedPreferencesManager;
import com.cns.bangladeshrailway.view.activity.AuthorizationActivity;

public class MainFragment extends BrowseSupportFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
        // return inflater.inflate(R.layout.fragment_grid, container, true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUI();
    }

    private void setUI() {
        // setTitle("Bangladesh Railway");
        // setHeadersState(HEADERS_DISABLED);
        // setBrandColor(Color.GREEN);

        if (SharedPreferencesManager.getInstance(getActivity()).checkSession()) {
            Apps.redirect(requireActivity(), DetailsActivity.class, true);
        } else {
            Apps.redirect(requireActivity(), AuthorizationActivity.class, true);
        }

        /* new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(100);
                    startActivity(new Intent(getActivity(), DetailsActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start(); */

        // loadRows();
        // setOnItemViewClickedListener(this);
    }

    /* private void loadRows() {
        HeaderItem category1 = new HeaderItem(0, "Passenger Train");
        HeaderItem category2 = new HeaderItem(1, "Freight Train");
        ArrayObjectAdapter adapterForRow1 = new ArrayObjectAdapter(new MyPresenter());
        // ArrayObjectAdapter adapterForRow2 = new ArrayObjectAdapter(new TrainDataPresenter());

        adapterForRow1.add(new RowItem("Rail", getContext().getResources().getDrawable(R.drawable.br)));
        adapterForRow1.add(new RowItem("Rail photo", getContext().getResources().getDrawable(R.drawable.br_train)));

        ArrayObjectAdapter windowAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        windowAdapter.add(new ListRow(category1, adapterForRow1));

        setAdapter(windowAdapter);
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        startActivity(intent);
    }

    private class MyPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {

            ImageCardView icv = new ImageCardView(parent.getContext());
            icv.setCardType(BaseCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA);
            icv.setInfoVisibility(BaseCardView.CARD_REGION_VISIBLE_ACTIVATED);

            return new ViewHolder(icv);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {

            RowItem rt = (RowItem) item;

            ImageCardView icv = (ImageCardView) viewHolder.view;
            icv.setMainImage(rt.getImage());
            icv.setMainImageDimensions(313, 176);
            icv.setTitleText(rt.getName());
            icv.setContentText("Rail description...");
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {

        }
    } */
}