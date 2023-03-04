package com.example.registrosv1.ui.home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.registrosv1.R;
import com.example.registrosv1.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Calendar;

import BD.DbAccesorios;
import BD.DbLugares;
import BD.DbRegistros;
import modelo.Accesorio;
import modelo.Lugar;
import modelo.RegistroInstalaciones;

public class HomeFragment extends Fragment
{

    private FragmentHomeBinding binding;
    private TextView txtCarro, txtPlaca, txtSerial, txtOrden, txtAccesorio, txtLugar, txtFecha;
    private EditText etCarro, etPlaca, etSerial, etOrden, etFecha, etValorAccesorio, etValorLugar;
    public Spinner spAcessorio;
    public Spinner spLugar;
    private Button btnFecha, btnNuevo, btnAgregar;
    public DbAccesorios dbAccesorios;
    private ArrayList<Accesorio> accesoriosList;

    private ArrayList<Lugar> lugarList;
    public DbLugares dbLugares;
    private ArrayList<RegistroInstalaciones> registrosList;
    public DbRegistros dbRegistros;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
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
        etValorAccesorio = (EditText) view.findViewById(R.id.etValorAccesorio);
        etValorLugar = (EditText) view.findViewById(R.id.etValorLugar);

        //Spinner
        spAcessorio = (Spinner) view.findViewById(R.id.spAcessorio);
        spLugar = (Spinner) view.findViewById(R.id.spLugar);

        //Button
        btnFecha = (Button) view.findViewById(R.id.btnFecha);
        btnAgregar = (Button) view.findViewById(R.id.btnAgregar);
        btnNuevo = (Button) view.findViewById(R.id.btnNuevo);


        accesoriosList = new ArrayList<>();
        dbAccesorios = new DbAccesorios(getActivity());
        consultarListaAccesorios();


        lugarList = new ArrayList<>();
        dbLugares = new DbLugares(getActivity());
        consultarListaLugar();

        registrosList = new ArrayList<>();
        dbRegistros = new DbRegistros(getActivity());

        etValorAccesorio.setEnabled(false);
        etValorLugar.setEnabled(false);
        etFecha.setEnabled(false);

        obtenerFechaActual(etFecha);


        spAcessorio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                Accesorio accesorio = new Accesorio();
                accesorio = (Accesorio) spAcessorio.getSelectedItem();
                if(accesorio.getId() != 0)
                {
                    etValorAccesorio.setText(accesorio.getValor() + "" );
                }
                else
                {
                    etValorAccesorio.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        spLugar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                Lugar lugar = new Lugar();
                lugar = (Lugar) spLugar.getSelectedItem();
                if(lugar.getId() != 0)
                {
                    etValorLugar.setText(lugar.getValorAgregado() + "" );
                }
                else
                {
                    etValorLugar.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                agregarRegistro();
            }
        });

        btnNuevo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                limpiarCampos();
            }
        });

        btnFecha.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                abrirCalendario(getView());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void consultarListaAccesorios()
    {
        Accesorio accesorio = new Accesorio( "Seleccione",0);
        accesoriosList.add(accesorio);
        accesoriosList.addAll(dbAccesorios.mostrarAccesorios());
        ArrayAdapter<Accesorio> adaptadorAccesorios = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, accesoriosList);
        spAcessorio.setAdapter(adaptadorAccesorios);
    }

    private void consultarListaLugar()
    {
        Lugar lugar = new Lugar( "Seleccione",0);
        lugarList.add(lugar);
        lugarList.addAll(dbLugares.mostrarLugares());
        ArrayAdapter<Lugar> adaptadorLugares = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, lugarList);
        spLugar.setAdapter(adaptadorLugares);
    }

    private void agregarRegistro()
    {
        if(validarCampos() && validarSpinners())
        {
            try
            {
                String modelo = etCarro.getText().toString();
                String orden = etOrden.getText().toString();
                int valor = Integer.parseInt(etValorAccesorio.getText().toString()) + Integer.parseInt(etValorLugar.getText().toString());
                String accesorio = spAcessorio.getSelectedItem().toString();
                String lugar = spLugar.getSelectedItem().toString();
                String fecha = etFecha.getText().toString();

                if(!etPlaca.getText().toString().isBlank() || !etSerial.getText().toString().isBlank())
                {
                    if (!etPlaca.getText().toString().isBlank() && !etSerial.getText().toString().isBlank())
                    {
                        String placa = etPlaca.getText().toString();
                        String serie = etSerial.getText().toString();
                        dbRegistros.insertarRegistro(modelo, placa, serie, orden, accesorio, lugar, valor, fecha);

                        spAcessorio.setSelection(0);
                        Toast.makeText(getActivity(), "Registro con placa y serie", Toast.LENGTH_SHORT).show();
                    }
                    else if (etPlaca.getText().toString().isBlank()) {

                        String placa = "";
                        String serie = etSerial.getText().toString();
                        dbRegistros.insertarRegistroSerie(modelo, serie, orden, accesorio, lugar, valor, fecha);

                        spAcessorio.setSelection(0);
                        Toast.makeText(getActivity(), "Registro con serie", Toast.LENGTH_SHORT).show();
                    }
                    else if (etSerial.getText().toString().isBlank())
                    {
                        String placa = etPlaca.getText().toString();
                        String serie = "";
                        dbRegistros.insertarRegistroPlaca(modelo, placa, orden, accesorio, lugar, valor, fecha);

                        spAcessorio.setSelection(0);
                        Toast.makeText(getActivity(), "Registro con placa", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(), "Digite la Placa y/o la Serie", Toast.LENGTH_SHORT).show();
                }

            }
            catch (NumberFormatException e)
            {
                Toast.makeText(getActivity(), "Rellene bien los campos", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getActivity(), "Rellene los campos necesarios, seleccione el accesorio y el lugar", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarCampos()
    {
        boolean valido = false;
        if(!etCarro.getText().toString().isBlank() && !etOrden.getText().toString().isBlank())
        {
            valido = true;
        }
        return valido;
    }

    private boolean validarSpinners()
    {
        boolean valido = true;

        Accesorio accesorio = new Accesorio();
        accesorio = (Accesorio) spAcessorio.getSelectedItem();

        Lugar lugar = new Lugar();
        lugar = (Lugar) spLugar.getSelectedItem();

        if(accesorio.getId() == 0 || lugar.getId() == 0)
        {
            valido = false;
        }
        return valido;
    }

    private  void obtenerFechaActual(EditText etFecha)
    {
        Calendar calendario = Calendar.getInstance();
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int day = calendario.get(Calendar.DAY_OF_MONTH);

        String stringMonth = month + "";
        String stringDayOfMonth = day + "";

        if(month <10)
        {
            stringMonth = 0 + "" + month;
        }

        if(day<10)
        {
            stringDayOfMonth = (0 + "" + day);
        }

        String fecha = stringDayOfMonth + "/" + stringMonth + "/" + year;


        etFecha.setText(fecha);
    }
    private void abrirCalendario(View view)
    {
        Calendar calendario = Calendar.getInstance();
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int day = calendario.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int Year, int Month, int dayOfMonth)
            {
                String stringMonth = Month + "";
                String stringDayOfMonth = dayOfMonth + "";

                if(Month <10)
                {
                    stringMonth = 0 + "" + Month;
                }

                if(dayOfMonth<10)
                {
                     stringDayOfMonth = (0 + "" + dayOfMonth);
                }

                String fecha = stringDayOfMonth + "/" + stringMonth + "/" + Year;
                etFecha.setText(fecha);
            }
        },year,month,day);
        dpd.show();
    }

    private void limpiarCampos()
    {
        etCarro.setText("");
        etPlaca.setText("");
        etSerial.setText("");
        etOrden.setText("");
        spAcessorio.setSelection(0);
        spLugar.setSelection(0);
        obtenerFechaActual(etFecha);
        Toast.makeText(getActivity(),"Campos Limpiados", Toast.LENGTH_SHORT).show();
    }
}