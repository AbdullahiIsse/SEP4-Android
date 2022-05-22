package com.abdu.and_sep4.View.Account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abdu.and_sep4.Adapter.OnListItemClickListener;
import com.abdu.and_sep4.Adapter.PetAdapter;
import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.Pet;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.Terrarium;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class AnimalListFragment extends Fragment implements OnListItemClickListener {


    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<Pet> petArrayList = new ArrayList<>();
    private AnimalListFragmentViewmodel animalListFragmentViewmodel;
    private PetAdapter petAdapter;
    private FloatingActionButton floatingActionButton;
    private TextView error;
    private View inflate;
    private TextView petListError;
    private Bundle bundle = new Bundle();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_animal_list, container, false);


        recyclerView = inflate.findViewById(R.id.rv_Petlist);

        progressBar = inflate.findViewById(R.id.petprogress_bar);
        floatingActionButton = inflate.findViewById(R.id.fab);
        petListError = inflate.findViewById(R.id.pet_list_error);

        recyclerView.setLayoutManager(new LinearLayoutManager(inflate.getContext()));
        recyclerView.hasFixedSize();

        petAdapter = new PetAdapter(petArrayList, this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(petAdapter);

        animalListFragmentViewmodel = new ViewModelProvider(this).get(AnimalListFragmentViewmodel.class);

        getPetList();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(inflate).navigate(R.id.action_animalListFragment_to_AddPetFragments);
            }
        });

        return inflate;
    }


    private void getPetList() {
        Terrarium terrarium = SaveInfo.getInstance().getTerrarium();

        animalListFragmentViewmodel.getPetsLiveData(terrarium.getId()).observe(getViewLifecycleOwner(), petsResponse -> {
            if (petsResponse != null && !petsResponse.isEmpty()) {

                progressBar.setVisibility(View.GONE);
                petListError.setVisibility(View.GONE);
                List<Pet> pets = petsResponse;
                petArrayList.clear();
                petArrayList.addAll(pets);
                petsResponse.clear();
                petAdapter.notifyDataSetChanged();
                Log.e("terrarium", petsResponse.toString());

            } else {
                progressBar.setVisibility(View.GONE);


            }

        });

    }


    @Override
    public void onClick(int position) {

        Toast.makeText(getContext(), "Position: " + position, Toast.LENGTH_SHORT).show();






    }


    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Pet remove = petArrayList.remove(viewHolder.getAdapterPosition());
            petAdapter.notifyDataSetChanged();
            animalListFragmentViewmodel.deletingPet(remove.getId());

        }
    };
}