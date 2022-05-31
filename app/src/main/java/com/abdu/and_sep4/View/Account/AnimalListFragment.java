package com.abdu.and_sep4.View.Account;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abdu.and_sep4.ClickListener.OnListItemClickListener;
import com.abdu.and_sep4.Shared.Animal;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.View.Adapter.PetAdapter;
import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class AnimalListFragment extends Fragment implements OnListItemClickListener {


    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<Animal> petArrayList = new ArrayList<>();
    private AnimalListFragmentViewmodel animalListFragmentViewmodel;
    private PetAdapter petAdapter;
    private FloatingActionButton floatingActionButton;
    private TextView error;
    private View inflate;
    private TextView petListError;
    private Bundle bundle = new Bundle();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_animal_list, container, false);


        recyclerView = inflate.findViewById(R.id.rv_Petlist);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        progressBar = inflate.findViewById(R.id.petprogress_bar);
        floatingActionButton = inflate.findViewById(R.id.fab);
        error = inflate.findViewById(R.id.animal_error);
        error.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(inflate.getContext()));
        recyclerView.hasFixedSize();

        petAdapter = new PetAdapter(petArrayList, this);


        recyclerView.setAdapter(petAdapter);

        animalListFragmentViewmodel = new ViewModelProvider(this).get(AnimalListFragmentViewmodel.class);

        getPetList();




        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ifNetworkIsAvailable()){
                    Navigation.findNavController(inflate).navigate(R.id.action_animalListFragment_to_AddPetFragments);
                } else {
                    Toast.makeText(inflate.getContext(),"Please connect to the internet",Toast.LENGTH_LONG).show();
                }


            }
        });

        return inflate;
    }


    private void getPetList() {


        animalListFragmentViewmodel.getPetsLiveData(firebaseUser.getUid(),SaveInfo.getInstance().getTerrarium().getEui()).observe(getViewLifecycleOwner(), new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> animals) {
                if (animals != null && !animals.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    petArrayList.clear();
                    petArrayList.addAll(animals);
                    animals.clear();
                    petAdapter.notifyDataSetChanged();
                    Log.e("terrarium", animals.toString());


                } else {
                    Handler handler = new Handler(Looper.getMainLooper());

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (petAdapter.getItemCount() == 0){
                                progressBar.setVisibility(View.GONE);
                                error.setVisibility(View.VISIBLE);
                                return;
                            }

                        }
                    },5000);


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
    public void onClick(int position) {

        Toast.makeText(getContext(), "Position: " + position, Toast.LENGTH_SHORT).show();


    }



}