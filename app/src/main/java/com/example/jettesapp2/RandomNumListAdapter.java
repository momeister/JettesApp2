package com.example.jettesapp2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RandomNumListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements View.OnClickListener {

    private List<String> neueFragen;
    private MyRecyclerViewAdapter.ItemClickListener mClickListener;

    public RandomNumListAdapter(List<String> neueFragen){
        this.neueFragen = neueFragen;
    }

    @Override
    public int getItemViewType(final int position){
        return R.layout.recyclerview_row;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String Frage = neueFragen.get(position);
        holder.getView().setText(Frage);

        //holder.getView().setText(String.valueOf(random.nextInt()));
    }

    @Override
    public int getItemCount() {
        return neueFragen.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    String getItem(int id){
        return neueFragen.get(id);
    }

}
