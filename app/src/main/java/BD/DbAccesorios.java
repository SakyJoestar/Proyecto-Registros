package BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import modelo.Accesorio;

public class DbAccesorios extends DbHelper
{
    Context context;

    public DbAccesorios(@Nullable Context context)
    {
        super(context);
        this.context = context;
    }

    public long insertarAccesorio(String auxNombre, int auxValor)
    {
        long id = 0;

        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues accesorio = new ContentValues();
            accesorio.put("NOMBRE", auxNombre);
            accesorio.put("PRECIO", auxValor);

            id = db.insert(TABLE_ACCESORIOS, null, accesorio);
            db.close();

        } catch (Exception e) {
            e.toString();
        }

        return id;
    }

    public ArrayList<Accesorio> mostrarAccesorios()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Accesorio> listaAccesorios = new ArrayList<>();
        Accesorio accesorio = null;

        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_ACCESORIOS, null);

        if(cursorContactos.moveToFirst())
        {
            do
            {
             accesorio = new Accesorio();
             accesorio.setId(Integer.parseInt(cursorContactos.getString(0)));
             accesorio.setNombre(cursorContactos.getString(1));
             accesorio.setValor(Integer.parseInt(cursorContactos.getString(2)));

             listaAccesorios.add(accesorio);
            }while (cursorContactos.moveToNext());
        }
        cursorContactos.close();

        return listaAccesorios;
    }

    public Accesorio getAccesorio()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Accesorio accesorio = null;

        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_ACCESORIOS + " WHERE id = " + "id" + " LIMIT 1", null);

        if(cursorContactos.moveToFirst())
        {
            accesorio = new Accesorio();
            accesorio.setId(Integer.parseInt(cursorContactos.getString(0)));
            accesorio.setNombre(cursorContactos.getString(1));
            accesorio.setValor(Integer.parseInt(cursorContactos.getString(2)));

        }

        cursorContactos.close();

        return accesorio;
    }

    public boolean eliminarAccesorio(int id)
    {
        boolean valido = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {
            db.execSQL("DELETE FROM " + TABLE_ACCESORIOS + " WHERE id =  ' " +  id  + " '");
            valido = true;
        }catch (Exception ex)
        {
            ex.toString();
            valido = false;
        }
        finally
        {
            db.close();;
        }

        return valido;
    }
}
