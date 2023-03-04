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

        }
        catch (Exception e)
        {
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

        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_ACCESORIOS, null);

        if(cursor.moveToFirst())
        {
            do
            {
             accesorio = new Accesorio();
             accesorio.setId(Integer.parseInt(cursor.getString(0)));
             accesorio.setNombre(cursor.getString(1));
             accesorio.setValor(Integer.parseInt(cursor.getString(2)));

             listaAccesorios.add(accesorio);
            }while (cursor.moveToNext());
        }
        cursor.close();

        return listaAccesorios;
    }

    public Accesorio getAccesorio()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Accesorio accesorio = null;

        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_ACCESORIOS + " WHERE id = " + "id" + " LIMIT 1", null);

        if(cursor.moveToFirst())
        {
            accesorio = new Accesorio();
            accesorio.setId(Integer.parseInt(cursor.getString(0)));
            accesorio.setNombre(cursor.getString(1));
            accesorio.setValor(Integer.parseInt(cursor.getString(2)));

        }

        cursor.close();

        return accesorio;
    }

    public boolean editarAccesorio(int id, String auxNombre, int auxValor)
    {
        boolean valido = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {
            db.execSQL(" UPDATE " + TABLE_ACCESORIOS + " SET NOMBRE = ' " + auxNombre + " ', PRECIO = ' " + auxValor + " ' WHERE id = ' " + id +  " ' ");
            valido = true;

        } catch (Exception e) {
            e.toString();
            valido = false;
        }
        finally
        {
            db.close();
        }

        return valido;
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
