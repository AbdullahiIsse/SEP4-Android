package com.abdu.and_sep4.View.Account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abdu.and_sep4.Adapter.OnListItemClickListener;
import com.abdu.and_sep4.Adapter.PetAdapter;
import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.Pet;

import java.util.ArrayList;
import java.util.List;


public class AccountFragment extends Fragment implements OnListItemClickListener  {


    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<Pet> petArrayList = new ArrayList<>();
    private AccountFragmentViewmodel accountFragmentViewmodel;
    private PetAdapter petAdapter;


    private TextView error;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);



        recyclerView = view.findViewById(R.id.rv_Petlist);

        progressBar = view.findViewById(R.id.petprogress_bar);


        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.hasFixedSize();

        petAdapter = new PetAdapter(petArrayList,this,getContext());

        recyclerView.setAdapter(petAdapter);

        accountFragmentViewmodel = new ViewModelProvider(this).get(AccountFragmentViewmodel.class);

        getPetList();

        return view;
    }


    private void getPetList() {

        accountFragmentViewmodel.getPetsLiveData().observe(getViewLifecycleOwner(), petsResponse -> {
            if (petsResponse != null && !petsResponse.isEmpty()){

                progressBar.setVisibility(View.GONE);
                List<Pet> pets = petsResponse;
                petArrayList.clear();
                petArrayList.addAll(pets);

            }
        });

    }



    @Override
    public void onClick(int position) {

        Toast.makeText(getContext(), "Position: " + position, Toast.LENGTH_SHORT).show();

    }
}