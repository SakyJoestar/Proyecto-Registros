package dao;

import java.util.ArrayList;

import modelo.Lugar;

public class LugarDao
{
    private final ArrayList<Lugar> lugares;

    public LugarDao()
    {
        lugares = new ArrayList<Lugar>();
    }

    public boolean crearALugar(Lugar auxLugar)
    {
        lugares.add(auxLugar);
        return true;
    }

    public boolean eliminarLugar(Lugar auxLugar)
    {
        lugares.remove(auxLugar);
        return true;
    }

    public ArrayList<Lugar> getLugares()
    {
        return lugares;
    }

    public Lugar getLugar(int auxId)
    {
        Lugar auxLugar = null;
        for(Lugar lugar: lugares)
        {
            if(lugar.getId()==auxId)
            {
                auxLugar = lugar;
                break;
            }
        }
        return auxLugar;
    }
}
