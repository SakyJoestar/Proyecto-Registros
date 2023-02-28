package com.example.registrosv1.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.registrosv1.R;
import com.example.registrosv1.databinding.FragmentSlideshowBinding;

import java.util.ArrayList;

import BD.DbAccesorios;
import modelo.Accesorio;
import modelo.FuncionesGlobales;


public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private TextView txtUtiAccesorio, txtUtiLugar;
    private EditText etNombreAcc, etValorAcc, etNombreLugar, etValorLugar;
    private Button btnAgregarAccesorio, btnEditarAccesorio, btnEliminarAccesorio, btnAgregarLugar;
    private Spinner spListaAccesorio, spListaLugar;
    public DbAccesorios dbAccesorios;
    private ArrayList<Accesorio> accesoriosList;
    //private ArrayList<String> accesoriosStringList;
    private FuncionesGlobales funcionesGlobales;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //Asignar ventana Utilidades

        //TextView
        txtUtiAccesorio = (TextView) view.findViewById(R.id.txtUtiAceesorio);
        txtUtiLugar = (TextView) view.findViewById(R.id.txtUtiLugar);

        //EditText
        etNombreAcc = (EditText) view.findViewById(R.id.etNombreAcc);
        etValorAcc = (EditText) view.findViewById(R.id.etValorAcc);
        etNombreLugar = (EditText) view.findViewById(R.id.txtLugarAgregar);
        etValorLugar = (EditText) view.findViewById(R.id.txtValorLugar);

        //Button
        btnAgregarAccesorio = (Button) view.findViewById(R.id.btnAgregarAcc);
        btnEditarAccesorio = (Button) view.findViewById(R.id.btnEditarAcc);
        btnEliminarAccesorio = (Button) view.findViewById(R.id.btnEliminarAcc);
        btnAgregarLugar = (Button) view.findViewById(R.id.btnAgregarLugar);

        //Spinnner
        spListaAccesorio = (Spinner) view.findViewById(R.id.spinnerAddAcce);
        spListaLugar = (Spinner) view.findViewById(R.id.spAddLugar);

        accesoriosList = new ArrayList<Accesorio>();
        dbAccesorios = new DbAccesorios(getActivity()/*, "BDAccesorios", null, 1*/);

        ArrayList<Accesorio> accesoriosList = new ArrayList<>();
        consultarListaAccesorios();

        btnAgregarAccesorio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                RegistrarAccesorio();
            }
        });

        btnEliminarAccesorio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                eliminarAccesorio();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void RegistrarAccesorio()
    {
        Accesorio accesorio = new Accesorio();
        accesorio = (Accesorio) spListaAccesorio.getSelectedItem();
        if(accesorio.getId() == 0)
        {
            if (comprobarCamposAccesorio())
            {
                String auxNombre = etNombreAcc.getText().toString();
                int auxValor = Integer.parseInt(etValorAcc.getText().toString());
                Accesorio auxAccesorio = null;

                dbAccesorios.insertarAccesorio(etNombreAcc.getText().toString(), Integer.parseInt(etValorAcc.getText().toString()));
                limpiarCamposAccesorios();

                consultarListaAccesorios();

                Toast.makeText(getActivity(), "Accesorio agregado", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getActivity(), "Faltan datos en los campos", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getActivity(), "Seleccione la opcion Nuevo Accesorio para Agregar ", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarAccesorio()
    {
        Accesorio auxAccesorio = new Accesorio();
        auxAccesorio = (Accesorio) spListaAccesorio.getSelectedItem();
        if(auxAccesorio.getId() != 0)
        {
            if(dbAccesorios.eliminarAccesorio(auxAccesorio.getId()))
            {
                limpiarCamposAccesorios();
                spListaAccesorio.setSelection(0);
                consultarListaAccesorios();

                Toast.makeText(getActivity(), "Accesorio Eliminado", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getActivity(),"No se pudo eliminar el Accesorio", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getActivity(),"Seleccione un Accesorio", Toast.LENGTH_SHORT).show();
        }
    }
    private void consultarListaAccesorios()
    {
        ArrayList<Accesorio> listaAccesorios = new ArrayList<>();
        listaAccesorios = dbAccesorios.mostrarAccesorios();
        rellenarAccesorios(listaAccesorios);
    }

    private boolean comprobarCamposAccesorio()
    {
        boolean camposValidos;
        camposValidos = false;
        if(!etNombreAcc.getText().toString().equals("") || !etValorAcc.getText().toString().equals(""))
        {
            camposValidos = true;
        }
        return camposValidos;
    }

    private void limpiarCamposAccesorios()
    {
        etNombreAcc.setText("");
        etValorAcc.setText("");
    }
    public void rellenarAccesorios(ArrayList<Accesorio> auxAccesorios)
    {
        accesoriosList.clear();
        Accesorio nuevoAccesorio = new Accesorio("Nuevo Accesorio", 0);
        accesoriosList.add(nuevoAccesorio);
        if(auxAccesorios.size()>0)
        {
            for (Accesorio accesorios : auxAccesorios)
            {
                accesoriosList.add((accesorios));
            }
        }
        ArrayAdapter<Accesorio> adaptadorAccesorios = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,accesoriosList);
        spListaAccesorio.setAdapter(adaptadorAccesorios);
        auxAccesorios = null;
    }
}