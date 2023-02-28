package BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Base de datos";
    public static final String TABLE_ACCESORIOS = "tAccesorios";
    public static final String TABLE_LUGARES = "tLugares";
    public static final String TABLE_REGISTROS = "tRegistros";

    public DbHelper(@Nullable Context context/*, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase baseDeDatos)
    {
        baseDeDatos.execSQL
                ("CREATE TABLE " + TABLE_ACCESORIOS +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "NOMBRE TEXT NOT NULL," + "PRECIO INTEGER NOT NULL)");

        baseDeDatos.execSQL
                ("CREATE TABLE " + TABLE_LUGARES +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "NOMBRE TEXT NOT NULL," + "INCREMENTO INTEGER NOT NULL)");

        baseDeDatos.execSQL
                ("CREATE TABLE " + TABLE_REGISTROS +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "FECHA Fecha NOT NULL," + "MODELO TEXT NOT NULL," + " PLACA TEXT," +
                        "" + "ORDEN INTEGER," + "ACCESORIO TEXT," + "LUGAR TEXT," + "VALOR INTEGER, " + "ESTADO BOOLEAN )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_NOMBRE);
        onCreate(sqLiteDatabase);
    }
}
