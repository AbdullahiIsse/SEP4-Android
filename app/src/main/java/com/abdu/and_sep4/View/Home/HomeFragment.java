package com.abdu.and_sep4.View.Home;

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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abdu.and_sep4.ClickListener.OnListItemClickListener;
import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.View.Adapter.TerrariumAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OnListItemClickListener {

    private RecyclerView recyclerView;
    private TerrariumAdapter terrariumAdapter;
    private Bundle bundle = new Bundle();
    private ArrayList<Terrarium> terrariums = new ArrayList<>();
    private HomeFragmentViewModel homeFragmentViewModel;
    private FloatingActionButton floatingActionButton;
    private View inflate;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private ProgressBar progressBar;
    private TextView error;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.fragment_home, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        progressBar = inflate.findViewById(R.id.progress_bar3);
       // error = inflate.findViewById(R.id.terrariumError);
        floatingActionButton = inflate.findViewById(R.id.fab);
//      progressBar.setVisibility(View.GONE);

        recyclerView = inflate.findViewById(R.id.rv_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflate.getContext()));
        recyclerView.hasFixedSize();


        terrariumAdapter = new TerrariumAdapter(terrariums, this);


        recyclerView.setAdapter(terrariumAdapter);

        homeFragmentViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);

        getTerrariumByUserId();





        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ifNetworkIsAvailable()){
                    Navigation.findNavController(inflate).navigate(R.id.action_homeFragment_to_addTerrariumFragment);
                } else {
                    Toast.makeText(inflate.getContext(),"Please connect to the internet",Toast.LENGTH_LONG).show();
                }

            }
        });




        return inflate;


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

    private void getTerrariumByUserId() {



            homeFragmentViewModel.getTerrariumLiveData("jack").observe(getViewLifecycleOwner(), new Observer<List<Terrarium>>() {
                @Override
                public void onChanged(List<Terrarium> terrariums) {
                    if (terrariums != null && !terrariums.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        HomeFragment.this.terrariums.clear();
                        HomeFragment.this.terrariums.addAll(terrariums);
                        terrariums.clear();
                        terrariumAdapter.notifyDataSetChanged();



                    } else {
//                        error.setVisibility(View.VISIBLE);
//                        error.setText("Can not find any Terrarium");
                        Log.e("Viewmodel-Terrarium", "terrariumV2s.toString()");
                    }

                }
            });




    }



    @Override
    public void onClick(int position) {

        SaveInfo.getInstance().setTerrarium(terrariums.get(position));
        Navigation.findNavController(inflate).navigate(R.id.action_homeFragment_to_terrariumDetailsFragment);

    }


}