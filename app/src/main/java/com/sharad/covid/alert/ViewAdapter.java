package com.sharad.covid.alert;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;


public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

    private Context context;
    private List<ViewModel> list;

    public ViewAdapter(Context context, List<ViewModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.state_card, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewModel viewModel = list.get(position);
        holder.stateName.setText(viewModel.getState());
        holder.textConfirm.setText(viewModel.getConfirmed());
        holder.textactive.setText(viewModel.getActive());
        holder.textRecover.setText(viewModel.getRecovered());
        holder.textDeath.setText(viewModel.getDeaths());
        holder.confirmDeltaText.setText("[+" + viewModel.getDeltaconfirmed() + "]");
        holder.recoverDeltaText.setText("[+" + viewModel.getDeltarecovered() + "]");
        holder.deathDeltaText.setText("[+" + viewModel.getDeltadeaths() + "]");
        try {
            holder.textTimeStamp.setText(TimeAgo.getTimeAgo(Objects.requireNonNull(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                    .parse(viewModel.getLastupdatedtime()))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView stateName, textConfirm, textactive, textRecover, textDeath, textTimeStamp, confirmDeltaText, recoverDeltaText, deathDeltaText;

        public ViewHolder(View itemView) {
            super(itemView);
            stateName = itemView.findViewById(R.id.state_name);
            textConfirm = itemView.findViewById(R.id.con_text);
            textactive = itemView.findViewById(R.id.act_text);
            textRecover = itemView.findViewById(R.id.rec_text);
            textDeath = itemView.findViewById(R.id.dea_text);
            textTimeStamp = itemView.findViewById(R.id.timeStamp);
            confirmDeltaText = itemView.findViewById(R.id.confirmDelta);
            recoverDeltaText = itemView.findViewById(R.id.recoverDelta);
            deathDeltaText = itemView.findViewById(R.id.deathDelta);
        }
    }

}