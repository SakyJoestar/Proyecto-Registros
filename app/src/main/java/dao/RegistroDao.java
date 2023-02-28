package dao;

import java.util.ArrayList;

import modelo.Fecha;
import modelo.RegistroInstalaciones;

public class RegistroDao
{
    private final ArrayList<RegistroInstalaciones> registros;

    public RegistroDao(){registros = new ArrayList<RegistroInstalaciones>();}

    public boolean crearRegistro(RegistroInstalaciones auxRegistro)
    {
        registros.add(auxRegistro);
        return true;
    }

    public ArrayList<RegistroInstalaciones> getRegistros()
    {
        return registros;
    }

    public RegistroInstalaciones getRegistro(int auxId)
    {
        RegistroInstalaciones auxRegistro = null;

        for(RegistroInstalaciones registro: registros)
        {
            if(registro.getId()==auxId)
            {
                auxRegistro = registro;
                break;
            }
        }
        return auxRegistro;
    }

    public ArrayList<RegistroInstalaciones> getRegistrosFecha(Fecha auxFecha)
    {
        ArrayList<RegistroInstalaciones> auxRegistros = new ArrayList<RegistroInstalaciones>();

        for(RegistroInstalaciones registroInstalaciones: registros)
        {
            if(registroInstalaciones.getFecha().equals(auxFecha))
            {
                auxRegistros.add(registroInstalaciones);
            }
        }
        return auxRegistros;
    }

    public ArrayList<RegistroInstalaciones> getRegistrosPlaca(String auxPlaca)
    {
        ArrayList<RegistroInstalaciones> auxRegistros = new ArrayList<RegistroInstalaciones>();

        for(RegistroInstalaciones registroInstalaciones: registros)
        {
            if(registroInstalaciones.getPlaca().equals(auxPlaca))
            {
                auxRegistros.add(registroInstalaciones);
            }
        }
        return auxRegistros;
    }

    public ArrayList<RegistroInstalaciones> getRegistrosSerie(String auxSerie)
    {
        ArrayList<RegistroInstalaciones> auxRegistros = new ArrayList<RegistroInstalaciones>();

        for(RegistroInstalaciones registroInstalaciones: registros)
        {
            if(registroInstalaciones.getSerie().equals(auxSerie))
            {
                auxRegistros.add(registroInstalaciones);
            }
        }
        return auxRegistros;
    }

    public ArrayList<RegistroInstalaciones> getRegistrosOrden(int auxOrden)
    {
        ArrayList<RegistroInstalaciones> auxRegistros = new ArrayList<RegistroInstalaciones>();

        for(RegistroInstalaciones registroInstalaciones: registros)
        {
            if(registroInstalaciones.getOrdenDeTrabajo() == auxOrden)
            {
                auxRegistros.add(registroInstalaciones);
            }
        }
        return auxRegistros;
    }

    public ArrayList<RegistroInstalaciones> getRegistrosAccesorio(String auxAccesorio)
    {
        ArrayList<RegistroInstalaciones> auxRegistros = new ArrayList<RegistroInstalaciones>();

        for(RegistroInstalaciones registroInstalaciones: registros)
        {
            if(registroInstalaciones.getAccesorio().getNombre().equals(auxAccesorio))
            {
                auxRegistros.add(registroInstalaciones);
            }
        }
        return auxRegistros;
    }
}
