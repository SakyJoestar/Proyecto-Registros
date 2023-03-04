package BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import modelo.RegistroInstalaciones;

public class DbRegistros extends DbHelper
{
    Context context;
    public DbRegistros(@Nullable Context context)
    {
        super(context);
        this.context = context;
    }

    public long insertarRegistroPlaca(String auxCarro, String auxPlaca, String auxOrden, String auxNombreAcc,String auxNombreLugar, int auxValor, String auxFecha)
    {
        long id = 0;

        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("MODELO", auxCarro);
            registro.put("PLACA", auxPlaca);
            registro.put("SERIE", "");
            registro.put("ORDEN", auxOrden);
            registro.put("ACCESORIO", auxNombreAcc);
            registro.put("LUGAR", auxNombreLugar);
            registro.put("VALOR_TOTAL", auxValor);
            registro.put("FECHA", auxFecha);
            registro.put("ESTADO", 0);

            id = db.insert(TABLE_REGISTROS, null, registro);

        }
        catch (Exception e)
        {
            e.toString();
        }
        return id;
    }

    public long insertarRegistroSerie(String auxCarro, String auxSerie, String auxOrden, String auxNombreAcc,String auxNombreLugar, int auxValor, String auxFecha)
    {
        long id = 0;

        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("MODELO", auxCarro);
            registro.put("PLACA", "");
            registro.put("SERIE", auxSerie);
            registro.put("ORDEN", auxOrden);
            registro.put("ACCESORIO", auxNombreAcc);
            registro.put("LUGAR", auxNombreLugar);
            registro.put("VALOR_TOTAL", auxValor);
            registro.put("FECHA", auxFecha);
            registro.put("ESTADO", 0);

            id = db.insert(TABLE_REGISTROS, null, registro);

        }
        catch (Exception e)
        {
            e.toString();
        }
        return id;
    }

    public long insertarRegistro(String auxCarro,String auxPlaca, String auxSerie, String auxOrden, String auxNombreAcc,String auxNombreLugar, int auxValor, String auxFecha)
    {
        long id = 0;

        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("MODELO", auxCarro);
            registro.put("PLACA", auxPlaca);
            registro.put("SERIE", auxSerie);
            registro.put("ORDEN", auxOrden);
            registro.put("ACCESORIO", auxNombreAcc);
            registro.put("LUGAR", auxNombreLugar);
            registro.put("VALOR_TOTAL", auxValor);
            registro.put("FECHA", auxFecha);
            registro.put("ESTADO", 0);

            id = db.insert(TABLE_REGISTROS, null, registro);

        }
        catch (Exception e)
        {
            e.toString();
        }
        return id;
    }

    public ArrayList<RegistroInstalaciones> mostrarRegistros()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<RegistroInstalaciones> listaRegistros = new ArrayList<>();
        RegistroInstalaciones registro = null;

        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_REGISTROS, null);

        if(cursor.moveToFirst())
        {
            do
            {
                registro = new RegistroInstalaciones();
                registro.setId(Integer.parseInt(cursor.getString(0)));
                registro.setModeloVehiculo(cursor.getString(1));
                registro.setPlaca((cursor.getString(2)));
                registro.setSerie((cursor.getString(3)));
                registro.setOrdenDeTrabajo(cursor.getString(4));
                registro.setAccesorio((cursor.getString(5)));
                registro.setLugar((cursor.getString(6)));
                registro.setValor(Integer.parseInt(cursor.getString(7)));
                registro.setFecha((cursor.getString(8)));
                registro.setEstado(Boolean.parseBoolean(cursor.getString(9)));

                listaRegistros.add(registro);
            }while (cursor.moveToNext());
        }
        cursor.close();

        return listaRegistros;
    }
}
