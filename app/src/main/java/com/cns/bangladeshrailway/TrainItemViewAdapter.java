package com.cns.bangladeshrailway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.cns.bangladeshrailway.listener.ItemClick;

import java.util.ArrayList;
import java.util.List;

public class TrainItemViewAdapter extends RecyclerView.Adapter<TrainItemViewAdapter.TrainItemViewHolder> {

    private Context mContext;
    private ItemClick itemClick;
    private List<TrainItem> trainItemList;

    public TrainItemViewAdapter(Context mContext, ItemClick itemClick) {
        this.mContext = mContext;
        this.itemClick = itemClick;
        trainItemList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return Math.max(trainItemList.size(), 0);
    }

    @Override
    public int getItemViewType(int position) {
        return trainItemList.get(position).getId();
    }

    @NonNull
    @Override
    public TrainItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 11 || viewType == 12) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_two, parent, false);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
        }
        return new TrainItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainItemViewHolder holder, int position) {
        TrainItem ti = trainItemList.get(position);
        holder.bindData(ti, position, holder);
    }

    public void setResult(List<TrainItem> trainItemList) {
        if (this.trainItemList.size() > 0) {
            this.trainItemList.clear();
        }
        if (trainItemList.size() > 0) {
            this.trainItemList.addAll(trainItemList);
        }
        notifyDataSetChanged();
    }

    class TrainItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatTextView acTvTitle, acTvBodyOne, acTvBodyTwo, acTvBodyThree, acTvBodyFour, acTvBodyFive, acTvBodyOneLbl, acTvBodyTwoLbl, acTvBodyThreeLbl, acTvBodyFourLbl, acTvBodyFiveLbl;

        public TrainItemViewHolder(View v) {
            super(v);
            acTvTitle = (AppCompatTextView) v.findViewById(R.id.ac_tv_title);
            acTvBodyOne = (AppCompatTextView) v.findViewById(R.id.ac_tv_body_one);
            acTvBodyTwo = (AppCompatTextView) v.findViewById(R.id.ac_tv_body_two);
            acTvBodyThree = (AppCompatTextView) v.findViewById(R.id.ac_tv_body_three);
            acTvBodyFour = (AppCompatTextView) v.findViewById(R.id.ac_tv_body_four);
            acTvBodyFive = (AppCompatTextView) v.findViewById(R.id.ac_tv_body_five);
            acTvBodyOneLbl = (AppCompatTextView) v.findViewById(R.id.ac_tv_body_one_lbl);
            acTvBodyTwoLbl = (AppCompatTextView) v.findViewById(R.id.ac_tv_body_two_lbl);
            acTvBodyThreeLbl = (AppCompatTextView) v.findViewById(R.id.ac_tv_body_three_lbl);
            acTvBodyFourLbl = (AppCompatTextView) v.findViewById(R.id.ac_tv_body_four_lbl);
            acTvBodyFiveLbl = (AppCompatTextView) v.findViewById(R.id.ac_tv_body_five_lbl);
            v.setOnClickListener(this);
        }

        private void bindData(TrainItem item, int index, TrainItemViewHolder holder) {
            acTvTitle.setText(item.getTitle());
            // acTvTitle.setTextColor(item.getColorCode());

            acTvBodyOneLbl.setText(item.getBodyOneLbl());
            acTvBodyTwoLbl.setText(item.getBodyTwoLbl());
            acTvBodyThreeLbl.setText(item.getBodyThreeLbl());
            acTvBodyFourLbl.setText(item.getBodyFourLbl());
            acTvBodyFiveLbl.setText(item.getBodyFiveLbl());

            acTvBodyOne.setText(item.getBodyOne());
            acTvBodyTwo.setText(item.getBodyTwo());
            acTvBodyThree.setText(item.getBodyThree());
            acTvBodyFour.setText(item.getBodyFour());
            acTvBodyFive.setText(item.getBodyFive());

            acTvBodyOne.setTextColor(item.getColorCode());
            acTvBodyTwo.setTextColor(item.getColorCode());
            acTvBodyThree.setTextColor(item.getColorCode());
            acTvBodyFour.setTextColor(item.getColorCode());
            acTvBodyFive.setTextColor(item.getColorCode());

            acTvBodyOneLbl.setTextColor(item.getColorCode());
            acTvBodyTwoLbl.setTextColor(item.getColorCode());
            acTvBodyThreeLbl.setTextColor(item.getColorCode());
            acTvBodyFourLbl.setTextColor(item.getColorCode());
            acTvBodyFiveLbl.setTextColor(item.getColorCode());

            if (item.getId() == 7 || item.getId() == 8 || item.getId() == 9) {
                acTvBodyOne.post(
                        () -> {
                            int lineNumber = acTvBodyOne.getLineCount();
                            int lineNumberForTwo = acTvBodyTwo.getLineCount();
                            if (lineNumber > 1) {
                                holder.acTvBodyTwo.setVisibility(View.GONE);
                                holder.acTvBodyTwoLbl.setText("");
                                holder.acTvBodyThreeLbl.setText(item.getBodyTwoLbl());
                                holder.acTvBodyThree.setText(item.getBodyTwo());
                                if (lineNumberForTwo > 1) {
                                    holder.acTvBodyFive.setVisibility(View.GONE);
//                                    holder.acTvBodyFour.setVisibility(View.GONE);
                                    holder.acTvBodyFiveLbl.setVisibility(View.GONE);
                                    holder.acTvBodyFourLbl.setVisibility(View.GONE);
                                }else{
                                    holder.acTvBodyFive.setVisibility(View.GONE);
                                    holder.acTvBodyFiveLbl.setVisibility(View.GONE);
                                }
                            } else if (lineNumberForTwo > 1) {
                                holder.acTvBodyFive.setVisibility(View.GONE);
                                holder.acTvBodyFiveLbl.setVisibility(View.GONE);
                            }
                        }
                );
            }

            if (item.getId() == 11 || item.getId() == 12) {
                acTvBodyFour.post(
                        () -> {
                            int lineNumber = acTvBodyFour.getLineCount();
                            if (lineNumber > 1) {
                                holder.acTvBodyFive.setVisibility(View.GONE);
                            }
                        }
                );
            }
        }

        @Override
        public void onClick(View view) {
            itemClick.onItemClicked(trainItemList.get(getAdapterPosition()));
        }
    }
}