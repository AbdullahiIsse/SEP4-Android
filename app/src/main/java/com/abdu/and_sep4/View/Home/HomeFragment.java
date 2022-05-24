package com.abdu.and_sep4.View.Home;

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
import android.widget.TextView;
import android.widget.Toast;

import com.abdu.and_sep4.Adapter.OnListItemClickListener;
import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.View.Adapter.TerrariumAdapter;
import com.abdu.and_sep4.View.Home.HomeFragmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OnListItemClickListener {

    private RecyclerView recyclerView;
    private TerrariumAdapter terrariumAdapter;
    private Bundle bundle = new Bundle();
    private ArrayList<Terrarium> terrariums = new ArrayList<>();
    private HomeFragmentViewModel homeFragmentViewModel;
    private TextView terrariumError;
    private FloatingActionButton floatingActionButton;
    private View inflate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         inflate = inflater.inflate(R.layout.fragment_home, container, false);

        terrariumError = inflate.findViewById(R.id.tv_terrarium_error);
        floatingActionButton = inflate.findViewById(R.id.fab);

        recyclerView = inflate.findViewById(R.id.rv_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflate.getContext()));
        recyclerView.hasFixedSize();


        terrariumAdapter = new TerrariumAdapter(terrariums,this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(terrariumAdapter);

        homeFragmentViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);

        getTerrariumByUserId();



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(inflate).navigate(R.id.action_homeFragment_to_addTerrariumFragment);
            }
        });



        return inflate;


    }

    private void getTerrariumByUserId() {

        homeFragmentViewModel.getTerrariumLiveData(FirebaseAuth.getInstance().getCurrentUser().getUid()).observe(getViewLifecycleOwner(), terrariums1 -> {

            if (terrariums1 != null && !terrariums1.isEmpty()){
                terrariumError.setVisibility(View.GONE);
                terrariums.clear();
                terrariums.addAll(terrariums1);

                terrariumAdapter.notifyDataSetChanged();

                Log.e("tester", String.valueOf(terrariums1.get(0).getId()));

            } else {
                Toast.makeText(getContext(),"Can not find any Terrarium",Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public void onClick(int position) {

        SaveInfo.getInstance().setTerrarium(terrariums.get(position));
        Navigation.findNavController(inflate).navigate(R.id.action_homeFragment_to_terrariumDetailsFragment);

    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Terrarium terrarium = terrariums.remove(viewHolder.getAdapterPosition());
            terrariumAdapter.notifyDataSetChanged();
            homeFragmentViewModel.deleteTerrarium(terrarium.getId());

        }
    };
}