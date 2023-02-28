package dao;

import java.util.ArrayList;

import modelo.Accesorio;

public class AccesorioDao
{
    private final ArrayList<Accesorio> accesorios;

    public AccesorioDao()
    {
        accesorios = new ArrayList<Accesorio>();
    }

    public boolean crearAccesorio(Accesorio auxAccesorio)
    {
     accesorios.add(auxAccesorio);
     return true;
    }

    public boolean eliminarAccesorio(Accesorio auxAccesorio)
    {
        accesorios.remove(auxAccesorio);
        return true;
    }

    public ArrayList<Accesorio> getAccesorios()
    {
        return accesorios;
    }

    public Accesorio getAccesorio(int auxId)
    {
        Accesorio auxAccesorio = null;
        for(Accesorio accesorio: accesorios)
        {
            if(accesorio.getId()==auxId)
            {
                auxAccesorio = accesorio;
            }
        }
        return auxAccesorio;
    }
}
