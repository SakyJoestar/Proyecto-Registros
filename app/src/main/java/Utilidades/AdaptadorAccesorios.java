package Utilidades;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import modelo.Accesorio;

public class AdaptadorAccesorios extends BaseAdapter
{
    private ArrayList<Accesorio> auxArrayList;
    public AdaptadorAccesorios(ArrayList<Accesorio> arrayList)
    {
        this.auxArrayList = arrayList;
    }
    @Override
    public int getCount()
    {
        return auxArrayList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return auxArrayList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
       return null;
    }
}
