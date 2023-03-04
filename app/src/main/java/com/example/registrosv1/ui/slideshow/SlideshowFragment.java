package com.example.registrosv1.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import BD.DbLugares;
import modelo.Accesorio;
import modelo.Lugar;


public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private TextView txtUtiAccesorio, txtUtiLugar;
    private EditText etNombreAcc, etValorAcc, etNombreLugar, etValorLugar;
    private Button btnAgregarAccesorio, btnEditarAccesorio, btnEliminarAccesorio, btnAgregarLugar, btnEditarLugar, btnEliminarLugar;
    private Spinner spListaAccesorio, spListaLugar;
    public DbAccesorios dbAccesorios;
    private ArrayList<Accesorio> accesoriosList;

    private ArrayList<Lugar> lugarList;
    public DbLugares dbLugares;


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
        btnEditarLugar = (Button) view.findViewById(R.id.btnEditarLugar);
        btnEliminarLugar = (Button) view.findViewById(R.id.btnEliminarLugar);

        //Spinnner
        spListaAccesorio = (Spinner) view.findViewById(R.id.spinnerAddAcce);
        spListaLugar = (Spinner) view.findViewById(R.id.spAddLugar);

        accesoriosList = new ArrayList<Accesorio>();
        dbAccesorios = new DbAccesorios(getActivity()/*, "BDAccesorios", null, 1*/);
        consultarListaAccesorios();


        lugarList = new ArrayList<>();
        dbLugares = new DbLugares(getActivity());
        consultarListaLugar();


        btnAgregarAccesorio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                RegistrarAccesorio();
            }
        });

        btnEditarAccesorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                EditarAccesorio();
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

        btnAgregarLugar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                registrarLugar();
            }
        });

        btnEditarLugar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                editarLugar();
            }
        });

        btnEliminarLugar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                eliminarLugar();
            }
        });

        spListaAccesorio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                Accesorio accesorio = new Accesorio();
                accesorio = (Accesorio) spListaAccesorio.getSelectedItem();
                if(accesorio.getId() != 0)
                {
                    etNombreAcc.setText(accesorio.getNombre());
                    etValorAcc.setText(accesorio.getValor() + "");
                    btnAgregarAccesorio.setEnabled(false);
                    btnEditarAccesorio.setEnabled(true);
                    btnEliminarAccesorio.setEnabled(true);
                }
                else
                {
                    limpiarCamposAccesorios();
                    btnAgregarAccesorio.setEnabled(true);
                    btnEditarAccesorio.setEnabled(false);
                    btnEliminarAccesorio.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        }
        );

        spListaLugar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                Lugar lugar = new Lugar();
                lugar = (Lugar) spListaLugar.getSelectedItem();
                if(lugar.getId() != 0)
                {
                    etNombreLugar.setText(lugar.getNombre());
                    etValorLugar.setText(lugar.getValorAgregado() + "");
                    btnAgregarLugar.setEnabled(false);
                    btnEditarLugar.setEnabled(true);
                    btnEliminarLugar.setEnabled(true);
                }
                else
                {
                    limpiarCamposLugar();
                    btnAgregarLugar.setEnabled(true);
                    btnEditarLugar.setEnabled(false);
                    btnEliminarLugar.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

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
                try
                {
                    String auxNombre = etNombreAcc.getText().toString();
                    int auxValor = Integer.parseInt(etValorAcc.getText().toString());

                    dbAccesorios.insertarAccesorio(etNombreAcc.getText().toString(), Integer.parseInt(etValorAcc.getText().toString()));
                    limpiarCamposAccesorios();

                    consultarListaAccesorios();

                    Toast.makeText(getActivity(), "Accesorio agregado", Toast.LENGTH_SHORT).show();
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(getActivity(), "Digite bien los campos", Toast.LENGTH_SHORT).show();
                }

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

    private void EditarAccesorio()
    {
        Accesorio auxAccesorio = new Accesorio();
        auxAccesorio = (Accesorio) spListaAccesorio.getSelectedItem();

        if(auxAccesorio.getId() != 0)
        {
            if(comprobarCamposAccesorio())
            {

                try
                {
                    String nombreAcc = etNombreAcc.getText().toString();
                    int valorAcc = Integer.parseInt(etValorAcc.getText().toString());

                    if (dbAccesorios.editarAccesorio(auxAccesorio.getId(), nombreAcc, valorAcc))
                    {
                        limpiarCamposAccesorios();
                        spListaAccesorio.setSelection(0);
                        consultarListaAccesorios();

                        Toast.makeText(getActivity(), "Accesorio Editado", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        Toast.makeText(getActivity(), "No se pudo Editar el Accesorio", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e)
                {
                    Toast.makeText(getActivity(), "Digite bien los campos", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(getActivity(), "Digite bien los campos", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getActivity(),"Seleccione un Accesorio", Toast.LENGTH_SHORT).show();
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
        if(!etNombreAcc.getText().toString().isBlank() && !etValorAcc.getText().toString().isBlank())
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
    private void rellenarAccesorios(ArrayList<Accesorio> auxAccesorios)
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

    private void registrarLugar()
    {
        Lugar lugar = new Lugar();
        lugar = (Lugar) spListaLugar.getSelectedItem();
        if(lugar.getId() == 0) {
            if (comprobarCamposLugar())
            {
                try
                {
                    String auxNombre = etNombreLugar.getText().toString();
                    int auxValor = Integer.parseInt(etValorLugar.getText().toString());

                    dbLugares.insertarLugar(etNombreLugar.getText().toString(), Integer.parseInt(etValorLugar.getText().toString()));
                    limpiarCamposLugar();

                    consultarListaLugar();

                    Toast.makeText(getActivity(), "Lugar agregado", Toast.LENGTH_SHORT).show();
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(getActivity(), "Digite bien los datos", Toast.LENGTH_SHORT).show();
                }
             }
            else
            {
                Toast.makeText(getActivity(), "Faltan datos en los campos", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getActivity(), "Seleccione la opcion Nuevo Lugar para Agregar ", Toast.LENGTH_SHORT).show();
        }
    }

    private void editarLugar()
    {
        Lugar auxLugar = new Lugar();
        auxLugar = (Lugar) spListaLugar.getSelectedItem();

        if(auxLugar.getId() != 0)
        {
            if(comprobarCamposLugar())
            {
                try
                {
                    String nombreLugar = etNombreLugar.getText().toString();
                    int valorLugar = Integer.parseInt(etValorLugar.getText().toString());

                    if (dbLugares.editarLugar(auxLugar.getId(), nombreLugar, valorLugar))
                    {
                        limpiarCamposLugar();
                        spListaLugar.setSelection(0);
                        consultarListaLugar();

                        Toast.makeText(getActivity(), "Lugar Editado", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "No se pudo Editar el Lugar", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(getActivity(), "Digite bien los datos", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(getActivity(), "Digite bien los campos", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getActivity(),"Seleccione un Lugar", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarLugar()
    {
        Lugar auxLugar = new Lugar();
        auxLugar = (Lugar) spListaLugar.getSelectedItem();
        if(auxLugar.getId() != 0)
        {
            if(dbLugares.eliminarLugar(auxLugar.getId()))
            {
                limpiarCamposLugar();
                spListaLugar.setSelection(0);
                consultarListaLugar();

                Toast.makeText(getActivity(), "Lugar Eliminado", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getActivity(),"No se pudo eliminar el Lugar", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getActivity(),"Seleccione un Lugar", Toast.LENGTH_SHORT).show();
        }
    }
    private void consultarListaLugar()
    {
        ArrayList<Lugar> listaLugar = new ArrayList<>();
        listaLugar = dbLugares.mostrarLugares();
        rellenarLugares(listaLugar);
    }

    private boolean comprobarCamposLugar()
    {
        boolean camposValidos;
        camposValidos = false;
        if(!etNombreLugar.getText().toString().isBlank() && !etValorLugar.getText().toString().isBlank())
        {
            camposValidos = true;
        }
        return camposValidos;
    }

    private void limpiarCamposLugar()
    {
        etNombreLugar.setText("");
        etValorLugar.setText("");
    }
    public void rellenarLugares(ArrayList<Lugar> auxLugares)
    {
        lugarList.clear();
        Lugar nuevoLugar = new Lugar("Nuevo Lugar", 0);
        lugarList.add(nuevoLugar);
        if(auxLugares.size()>0)
        {
            for (Lugar lugar : auxLugares)
            {
                lugarList.add((lugar));
            }
        }

        ArrayAdapter<Lugar> adaptadorAccesorios = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,lugarList);
        spListaLugar.setAdapter(adaptadorAccesorios);
        auxLugares = null;
    }
}