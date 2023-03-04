package BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import modelo.Lugar;

public class DbLugares extends DbHelper
{
    Context context;
    public DbLugares(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarLugar(String auxNombre, int auxValor)
    {
        long id = 0;

        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues lugar = new ContentValues();
            lugar.put("NOMBRE", auxNombre);
            lugar.put("INCREMENTO", auxValor);

            id = db.insert(TABLE_LUGARES, null, lugar);
            db.close();

        } catch (Exception e) {
            e.toString();
        }

        return id;
    }

    public ArrayList<Lugar> mostrarLugares()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Lugar> listaLugares = new ArrayList<>();
        Lugar lugar = null;

        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_LUGARES, null);

        if(cursor.moveToFirst())
        {
            do
            {
                lugar = new Lugar();
                lugar.setId(Integer.parseInt(cursor.getString(0)));
                lugar.setNombre(cursor.getString(1));
                lugar.setValorAgregado(Integer.parseInt(cursor.getString(2)));

                listaLugares.add(lugar);
            }while (cursor.moveToNext());
        }
        cursor.close();

        return listaLugares;
    }

    public Lugar getLugar()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Lugar lugar = null;

        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_LUGARES + " WHERE id = " + "id" + " LIMIT 1", null);

        if(cursor.moveToFirst())
        {
            lugar = new Lugar();
            lugar.setId(Integer.parseInt(cursor.getString(0)));
            lugar.setNombre(cursor.getString(1));
            lugar.setValorAgregado(Integer.parseInt(cursor.getString(2)));

        }

        cursor.close();

        return lugar;
    }

    public boolean editarLugar(int id, String auxNombre, int auxValor)
    {
        boolean valido = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {
            db.execSQL(" UPDATE " + TABLE_LUGARES + " SET NOMBRE = ' " + auxNombre + " ', INCREMENTO = ' " + auxValor + " ' WHERE id = ' " + id +  " ' ");
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

    public boolean eliminarLugar(int id)
    {
        boolean valido = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {
            db.execSQL("DELETE FROM " + TABLE_LUGARES + " WHERE id =  ' " +  id  + " '");
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
