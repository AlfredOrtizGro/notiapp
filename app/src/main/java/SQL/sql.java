package SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sql extends SQLiteOpenHelper
{
    private static final String database = "notiapp";
    private static final int VERSION = 1;

    //Comando para crear tabla
    private final String tNotas = "CREATE TABLE NOTAS (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "TITULO TEXT NOT NULL," +
            "NOTA TEXT NOT NULL," +
            "PRIORIDAD TEXT NOT NULL);";

    //Constructor de la clase
    public sql(Context context)
    {
        super(context, database, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(tNotas);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (newVersion > oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS NOTAS");
            db.execSQL(tNotas);
        }
    }
}




