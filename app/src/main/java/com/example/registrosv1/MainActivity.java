package com.example.registrosv1;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.registrosv1.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import modelo.Accesorio;
import modelo.Lugar;
import modelo.RegistroInstalaciones;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private TextView txtCarro, txtPlaca, txtSerial, txtOrden, txtAccesorio, txtLugar, txtFecha, txtUtiAccesorio, txtUtiLugar;
    private EditText etCarro, etPlaca, etSerial, etOrden, etFecha, etNombreAcc, etValorAcc, etNombreLugar, etValorLugar;
    public static Spinner spAcessorio;
    public static Spinner spLugar;
    private CheckBox cbFecha;
    private Button btnFecha, btnNuevo, btnAgregar, btnAgregarAccesorio, getBtnAgregarLugar;
    private ArrayList<Lugar> listaLugares;
    private ArrayList<Accesorio> listaAccesorios;
    private ArrayList<RegistroInstalaciones> listaRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /*
        //Asignar ventana home

        //TextView
        txtCarro = (TextView) findViewById(R.id.txtCarro);
        txtPlaca = (TextView) findViewById(R.id.txtPlaca);
        txtSerial = (TextView) findViewById(R.id.txtSerial);
        txtOrden = (TextView) findViewById(R.id.txtOrden);
        txtAccesorio = (TextView) findViewById(R.id.txtAccesorio);
        txtLugar = (TextView) findViewById(R.id.txtLugar);
        txtFecha = (TextView) findViewById(R.id.txtFecha);

        //EditText
        etCarro = (EditText) findViewById(R.id.etCarro);
        etPlaca = (EditText) findViewById(R.id.etPlaca);
        etSerial = (EditText) findViewById(R.id.etSerial);
        etOrden = (EditText) findViewById(R.id.etOrden);
        etFecha = (EditText) findViewById(R.id.etFecha);

        //Spinner
        spAcessorio = (Spinner) findViewById(R.id.spAcessorio);
        spLugar = (Spinner) findViewById(R.id.spLugar);

        //RadioButton
        cbFecha = (CheckBox) findViewById(R.id.cbFecha);

        //Button
        btnFecha = (Button) findViewById(R.id.btnFecha);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnNuevo = (Button) findViewById(R.id.btnNuevo);

        //Asignar ventana Utilidades

        //TextView
        txtUtiAccesorio = (TextView) findViewById(R.id.txtUtiAceesorio);
        txtUtiLugar = (TextView) findViewById(R.id.txtUtiLugar);

        //EditText
        etNombreAcc = (EditText) findViewById(R.id.etNombreAcc);
        etValorAcc = (EditText) findViewById(R.id.etValorAcc);
        etNombreLugar = (EditText) findViewById(R.id.txtLugarAgregar);
        etValorAcc = (EditText) findViewById(R.id.txtValorLugar);

        //Button
        btnAgregarAccesorio = (Button) findViewById(R.id.btnAgregarAcc);
        getBtnAgregarLugar = (Button) findViewById(R.id.btnAgregarLugar);


        //Post Asignar
        ArrayAdapter<Lugar> adapterLugar = new ArrayAdapter<Lugar>(this, android.R.layout.simple_spinner_item, funcionesGlobales.getLugares());
        ArrayAdapter<Accesorio> adapterAccesorio = new ArrayAdapter<Accesorio>(this, android.R.layout.simple_spinner_item, funcionesGlobales.getAccesorios());
        //rellenarLugar(adapterLugar);
        //rellenarAccesorios(adapterAccesorio);

         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
/*
    public void AgregarAccesorio(View auxView)
    {
        Accesorio auxAccesorio;
        String auxNombre;
        int auxValor;

        auxNombre = etNombreAcc.getText().toString();
        auxValor = Integer.parseInt(etValorAcc.getText().toString());

        if(comprobarCamposAccesorio())
        {
            auxAccesorio = new Accesorio(auxNombre, auxValor);
            if(funcionesGlobales.agregarAccesorio(auxAccesorio))
            {
                ArrayList accesorios = null;
                accesorios = funcionesGlobales.getAccesorios();
                vaciarAccesorios();
                ArrayAdapter<Accesorio> adapterAccesorio = new ArrayAdapter<Accesorio>(this, android.R.layout.simple_spinner_item, funcionesGlobales.getAccesorios());
                rellenarAccesorios(adapterAccesorio);
            }
        }
        else
        {
            //hacer un toast
        }
    }

    public boolean comprobarCamposAccesorio()
    {
        boolean camposValidos;
        camposValidos = false;
        if(!etNombreAcc.equals("") && !etValorAcc.equals(""))
        {
            camposValidos = true;
        }
        return camposValidos;
    }


    public void rellenarAccesorios(ArrayAdapter<Accesorio> adapter)
    {
        spAcessorio.setAdapter(adapter);
    }
    public void vaciarAccesorios()
    {
        spAcessorio.setAdapter(null);
    }

    public void rellenarLugar(ArrayAdapter<Lugar> adapter)
    {
        spLugar.setAdapter(adapter);
    }

 */
}