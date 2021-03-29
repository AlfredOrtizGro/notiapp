package SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;

public class SQLite
{
    private sql sql;
    private SQLiteDatabase db;

    public SQLite(Context context)
    {
        sql = new sql(context);
    }

    public SQLite()
    {

    }

    public void abrir()
    {
        Log.i("SQLite", "Se abre bd " + sql.getDatabaseName());
        db = sql.getWritableDatabase();
    }

    public void cerrar()
    {
        Log.i("SQLite", "Se cierra conexion " + sql.getDatabaseName());
        sql.close();
    }

    //Funcion agregar registro
    public boolean agregarNota(int id, String titulo, String nota, String prioridad)
    {
        ContentValues cv = new ContentValues();
        cv.put("ID", id);
        cv.put("TITULO", titulo);
        cv.put("NOTA", nota);
        cv.put("PRIORIDAD", prioridad);
        return (db.insert("NOTAS", null, cv) != -1) ? true : false;
    }

    //Funcion leer registros
    public Cursor getNotas()
    {
        return db.rawQuery("SELECT * FROM NOTAS", null);
    }

    //Funcion listar registros
    public ArrayList<String> getNotas(Cursor cursor)
    {
        ArrayList<String> listData = new ArrayList<>();
        String item = "";
        if (cursor.moveToFirst())
        {
            do
            {
                item += cursor.getInt(0) + ",";
                item += cursor.getString(1) + ",";
                item += cursor.getString(2) + ",";
                item += cursor.getInt(3);
                listData.add(item);
                item = "";
            } while (cursor.moveToNext());
        }
        return listData;
    }

    //Función obtener id
    public ArrayList<String> getID(Cursor cursor)
    {
        ArrayList<String> listData = new ArrayList<>();
        String item = "";
        if (cursor.moveToFirst())
        {
            do
            {
                item += cursor.getInt(0);
                listData.add(item);
                item = "";
            } while (cursor.moveToNext());
        }
        return listData;
    }

    public String editarNota(int id, String titulo, String nota, int prioridad)
    {
        ContentValues cv = new ContentValues();
        cv.put("ID", id);
        cv.put("TITULO", titulo);
        cv.put("NOTA", nota);
        cv.put("PRIORIDAD", prioridad);
        int cantidad = db.update("NOTAS", cv, "ID=" + id, null);
        if (cantidad == 1)
        {
            return "Nota modificada";
        } else
        {
            return "Error, no se pudo modificar";
        }
    }

    public Cursor getCantidad(int id)
    {
        return db.rawQuery("SELECT * FROM NOTAS WHERE ID=" + id, null);

    }

    //Funcion para elminiar nota
    public int eliminarNota(Editable id)
    {
        return db.delete("NOTAS", "ID=" + id, null);
    }

}
