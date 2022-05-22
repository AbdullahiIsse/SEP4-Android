package com.abdu.and_sep4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.Pet;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetsViewHolder> implements OnListItemClickListener {

    private ArrayList<Pet> pets;
    OnListItemClickListener listener;
    Context context;


    public PetAdapter(ArrayList<Pet> pets, OnListItemClickListener listener) {
        this.pets = pets;
        this.listener = listener;

    }


    @NonNull
    @Override
    public PetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item,parent,false);
        PetsViewHolder holder = new PetsViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull PetsViewHolder holder, int position) {

        holder.species.setText(pets.get(position).getSpecies());
        holder.name.setText(pets.get(position).getName());
        holder.age.setText(Integer.toString(pets.get(position).getAge()));

    }



    @Override
    public int getItemCount() {
        return pets.size();
    }

    @Override
    public void onClick(int position) {

    }


    public class PetsViewHolder extends RecyclerView.ViewHolder {

        TextView species;
        TextView name;
        TextView age;


        public PetsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(getAdapterPosition());

                }
            });
            species = itemView.findViewById(R.id.species);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);


        }

    }

}

