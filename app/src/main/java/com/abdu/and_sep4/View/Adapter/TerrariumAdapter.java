package com.abdu.and_sep4.View.Adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.abdu.and_sep4.ClickListener.OnListItemClickListener;
import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.ArrayList;

public class TerrariumAdapter extends RecyclerView.Adapter<TerrariumAdapter.ViewHolder> implements OnListItemClickListener{

    private ArrayList<Terrarium> terrariums;
    OnListItemClickListener OnListItemClickListener;
    private Bundle bundle = new Bundle();



    public TerrariumAdapter(ArrayList<Terrarium> terrariums, OnListItemClickListener OnListItemClickListener) {
        this.terrariums = terrariums;
        this.OnListItemClickListener = OnListItemClickListener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.terrarium_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
      holder.name.setText(terrariums.get(position).getTerrariumName());
      holder.edit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              bundle.putLong("TerrariumId",terrariums.get(position).getId());
              bundle.putString("TerrariumName", terrariums.get(position).getTerrariumName());



              Navigation.findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_updateTerrariumFragment,bundle);
          }
      });

    }

    @Override
    public int getItemCount() {
        return terrariums.size();
    }

    @Override
    public void onClick(int position) {

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private  final TextView edit;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             name = itemView.findViewById(R.id.t_name);
             edit = itemView.findViewById(R.id.terrarium_edit);
             itemView.setOnClickListener(v -> {
                 OnListItemClickListener.onClick(getAdapterPosition());
             });
         }

     }


    public interface OnClickListener{
        void onClick(Terrarium terrarium);
    }



}
