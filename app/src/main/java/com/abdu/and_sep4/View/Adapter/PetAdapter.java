package com.abdu.and_sep4.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.abdu.and_sep4.ClickListener.OnListItemClickListener;
import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.Animal;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetsViewHolder> implements OnListItemClickListener {

    private ArrayList<Animal> pets;
    OnListItemClickListener listener;
    Context context;
    private Bundle bundle = new Bundle();
    private View inflate;


    public PetAdapter(ArrayList<Animal> pets, OnListItemClickListener listener) {
        this.pets = pets;
        this.listener = listener;

    }


    @NonNull
    @Override
    public PetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item,parent,false);
        PetsViewHolder holder = new PetsViewHolder(inflate);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull PetsViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.species.setText(pets.get(position).getSpecies());
        holder.name.setText(pets.get(position).getName());
        holder.age.setText(Integer.toString(pets.get(position).getAge()));
        holder.petGender.setText(pets.get(position).getGender());
        holder.shedding.setText(Boolean.toString(pets.get(position).isShedding()));
        holder.hibernating.setText(Boolean.toString(pets.get(position).isHibernating()));
        holder.hasOfSpring.setText(Boolean.toString(pets.get(position).isHasOffSpring()));

        holder.petEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ifNetworkIsAvailable()){

                    bundle.putInt("id",pets.get(position).getId());
                    bundle.putString("name", pets.get(position).getName());
                    bundle.putString("species",pets.get(position).getSpecies());
                    bundle.putInt("age",pets.get(position).getAge());
                    bundle.putString("gender", pets.get(position).getGender());
                    bundle.putBoolean("shedding", pets.get(position).isShedding());
                    bundle.putBoolean("hibernating", pets.get(position).isHibernating());
                    bundle.putBoolean("hasOfSpring", pets.get(position).isHasOffSpring());

                    Navigation.findNavController(holder.itemView).navigate(R.id.action_animalListFragment_to_updatePetFragment,bundle);


                } else {
                    Toast.makeText(inflate.getContext(),"Please connect to the internet",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    public boolean ifNetworkIsAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) inflate.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null) {
            if (info.isConnected()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

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
        TextView petGender;
        TextView shedding;
        TextView hibernating;
        TextView hasOfSpring;


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
            petGender = itemView.findViewById(R.id.petGender);
            shedding = itemView.findViewById(R.id.shedding);
            hibernating = itemView.findViewById(R.id.hibernating);
            hasOfSpring = itemView.findViewById(R.id.hasOffSpring);

        }

    }

}

