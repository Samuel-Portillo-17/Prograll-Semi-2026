package com.example.miprimerapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="amigos";
    private static final  int DATABASE_VERSION=1;
    private static final String SQLdb="CREATE TABLE amigos (IdAmigo INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, direccionn TEXT, telefono TEXT, email TEXT, dui TEXT, urlfoto TEXT)";

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLdb);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      //actualizar la definicion de la base de datos
    }
    public String administrar_amigos(String accion, String[] datos){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String mensaje ="ok", sql="";
            switch (accion) {
                case "nuevo":
                    sql = "INSERT INTO amigos (nombre,direccion,telefono,email,dui,urlfoto) VALUES ('" + datos[0] + "','" + datos[1] +"','" + datos[2] + "','" +
                             "'" + datos[3] + "','" + datos[4]+ "','" + datos[5] + "','" + datos[6] + ")";
                    break;
                case "modificar":
                    sql = "UPDATE amigos SET nombre='" + datos[1] + "', direccion='" + datos[2] + "', telefono='" + datos[3] + "',  email='" + datos[4] + "'," +
                            "  dui='" + datos[5] + "',  urlFoto'" + datos[6] + "' WHERE idAmigos='" + datos[0] + "''";
                    break;
                case "eliminar":
                    sql = "DELETE FROM amigos WHERE idAmigo='" + datos[0] +"'";
                    break;
            }
            db.execSQL(sql);
            db.close();
            return mensaje;
         } catch (Exception e) {
            return e.getMessage();
        }
        }

        public Cursor Listar_amigos(){
         SQLiteDatabase db = getReadableDatabase();
         return db.rawQuery("SELECT * FROM amigos", null);
        }
    }

