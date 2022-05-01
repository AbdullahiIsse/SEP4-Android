package com.abdu.and_sep4.View.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.View.Adapter.TerrariumAdapter;
import com.abdu.and_sep4.View.Home.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment{

    private RecyclerView recyclerView;
    private TerrariumAdapter terrariumAdapter;
    private Bundle bundle = new Bundle();
    private ArrayList<Terrarium> terrariums = new ArrayList<>();
    private HomeFragmentViewModel homeFragmentViewModel;
    private TextView terrariumError;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        terrariumError = inflate.findViewById(R.id.tv_terrarium_error);

        recyclerView = inflate.findViewById(R.id.rv_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflate.getContext()));
        recyclerView.hasFixedSize();


        terrariumAdapter = new TerrariumAdapter(terrariums);

        recyclerView.setAdapter(terrariumAdapter);

        homeFragmentViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);

        getTerrariumByUserId();

        terrariumAdapter.setOnClickListener(terrarium -> {

            SaveInfo.getInstance().setTerrarium(terrarium);
            Navigation.findNavController(inflate).navigate(R.id.action_homeFragment_to_terrariumDetailsFragment);

        });



        return inflate;


    }

    private void getTerrariumByUserId() {

        homeFragmentViewModel.getTerrariumLiveData((int) SaveInfo.getInstance().getUser().getId()).observe(getViewLifecycleOwner(), terrariums1 -> {

            if (terrariums1 != null && !terrariums1.isEmpty()){
                terrariumError.setVisibility(View.GONE);
                terrariums.clear();
                terrariums.addAll(terrariums1);

                terrariumAdapter.notifyDataSetChanged();

            } else {
                Toast.makeText(getContext(),"Can not find any Terrarium",Toast.LENGTH_SHORT).show();
            }

        });

    }
}