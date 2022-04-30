package com.abdu.and_sep4.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.ArrayList;

public class TerrariumAdapter extends RecyclerView.Adapter<TerrariumAdapter.ViewHolder> {

    private ArrayList<Terrarium> terrariums;
    private OnClickListener onClickListener;


    public TerrariumAdapter(ArrayList<Terrarium> terrariums) {
        this.terrariums = terrariums;
    }


    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.terrarium_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.name.setText(terrariums.get(position).getTerrariumName());

    }

    @Override
    public int getItemCount() {
        return terrariums.size();
    }


      class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             name = itemView.findViewById(R.id.t_name);
             itemView.setOnClickListener(v -> {
                 onClickListener.onClick(terrariums.get(getAdapterPosition()));
             });
         }

     }


    public interface OnClickListener{
        void onClick(Terrarium terrarium);
    }



}
