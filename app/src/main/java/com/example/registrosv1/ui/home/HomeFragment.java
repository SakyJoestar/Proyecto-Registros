package com.example.registrosv1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.registrosv1.R;
import com.example.registrosv1.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TextView txtCarro, txtPlaca, txtSerial, txtOrden, txtAccesorio, txtLugar, txtFecha;
    private EditText etCarro, etPlaca, etSerial, etOrden, etFecha;
    public static Spinner spAcessorio;
    public static Spinner spLugar;
    private CheckBox cbFecha;
    private Button btnFecha, btnNuevo, btnAgregar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //Asignar ventana home

        //TextView
        txtCarro = (TextView) view.findViewById(R.id.txtCarro);
        txtPlaca = (TextView) view.findViewById(R.id.txtPlaca);
        txtSerial = (TextView) view.findViewById(R.id.txtSerial);
        txtOrden = (TextView) view.findViewById(R.id.txtOrden);
        txtAccesorio = (TextView) view.findViewById(R.id.txtAccesorio);
        txtLugar = (TextView) view.findViewById(R.id.txtLugar);
        txtFecha = (TextView) view.findViewById(R.id.txtFecha);

        //EditText
        etCarro = (EditText) view.findViewById(R.id.etCarro);
        etPlaca = (EditText) view.findViewById(R.id.etPlaca);
        etSerial = (EditText) view.findViewById(R.id.etSerial);
        etOrden = (EditText) view.findViewById(R.id.etOrden);
        etFecha = (EditText) view.findViewById(R.id.etFecha);

        //Spinner
        spAcessorio = (Spinner) view.findViewById(R.id.spAcessorio);
        spLugar = (Spinner) view.findViewById(R.id.spLugar);

        //RadioButton
        cbFecha = (CheckBox) view.findViewById(R.id.cbFecha);

        //Button
        btnFecha = (Button) view.findViewById(R.id.btnFecha);
        btnAgregar = (Button) view.findViewById(R.id.btnAgregar);
        btnNuevo = (Button) view.findViewById(R.id.btnNuevo);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}