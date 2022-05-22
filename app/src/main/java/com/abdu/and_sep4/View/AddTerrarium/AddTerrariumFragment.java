package com.abdu.and_sep4.View.AddTerrarium;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.Shared.User;


public class AddTerrariumFragment extends Fragment {

    private View inflate;
    private AppCompatEditText et_TerrariumName;
    private Button addTerrariumBtn;
    private AddTerrariumFragmentViewModel addTerrariumFragmentViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         inflate = inflater.inflate(R.layout.fragment_add_terrarium, container, false);

         et_TerrariumName = inflate.findViewById(R.id.Terrarium_name);
         addTerrariumBtn = inflate.findViewById(R.id.addTerrarium);

         addTerrariumFragmentViewModel = new ViewModelProvider(this).get(AddTerrariumFragmentViewModel.class);

         addTerrariumBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 User user = SaveInfo.getInstance().getUser();

                 Terrarium terrarium = new Terrarium(et_TerrariumName.getText().toString(), (int) user.getId());

                 addTerrariumFragmentViewModel.addTerrarium(terrarium);

                 et_TerrariumName.setText("");

                 Navigation.findNavController(inflate).navigate(R.id.action_addTerrariumFragment_to_homeFragment);

             }
         });





        return inflate;
    }
}