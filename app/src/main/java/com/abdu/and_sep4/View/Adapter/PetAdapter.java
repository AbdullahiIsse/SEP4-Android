package com.abdu.and_sep4.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.abdu.and_sep4.Shared.Pet;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetsViewHolder> implements OnListItemClickListener {

    private ArrayList<Pet> pets;
    OnListItemClickListener listener;
    Context context;
    private Bundle bundle = new Bundle();


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
    public void onBindViewHolder(@NonNull PetsViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.species.setText(pets.get(position).getSpecies());
        holder.name.setText(pets.get(position).getName());
        holder.age.setText(Integer.toString(pets.get(position).getAge()));
        holder.petEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putLong("id",pets.get(position).getId());
                bundle.putString("name", pets.get(position).getName());
                bundle.putString("species",pets.get(position).getSpecies());
                bundle.putInt("age",pets.get(position).getAge());



                Navigation.findNavController(holder.itemView).navigate(R.id.action_animalListFragment_to_updatePetFragment,bundle);
            }
        });

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
        TextView petEdit;


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
            petEdit = itemView.findViewById(R.id.petEdit);


        }

    }

}

