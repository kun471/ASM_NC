package com.example.asmfinal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context,"DuAnMau",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table ACCOUNT(username text primary key, password text not null)");
        db.execSQL("create table KHOAHOC(maKhoahoc integer primary key autoincrement, tenKhoahoc text not null, soBuoihoc text, tenGiaovien text, ngayBatdau date not null, ngayKetthuc date not null, ngaythi date not null)");
        db.execSQL("insert into KHOAHOC values (1,'Lập Trình Android','30','Nguyễn Thị Hường','20-7-2020','20-11-2020','20-11-2020')");
        db.execSQL("insert into KHOAHOC values (2,'Lập Trình Android Nâng Cao','32','Nguyễn Thị Hường','20-7-2020','20-11-2020','20-11-2020')");
        db.execSQL("insert into KHOAHOC values (3,'Lập Trình Android Giao Diện','28','Nguyễn Thị Hường','20-7-2020','20-11-2020','20-11-2020')");
        db.execSQL("insert into KHOAHOC values (4,'Lập Trình Java','16','Lê Anh Tú','20-7-2020','20-11-2020','20-11-2020')");
        db.execSQL("insert into KHOAHOC values (5,'Lập Trình Java 2','20','Nguyễn Thị Hường','20-7-2020','20-11-2020','20-11-2020')");
        db.execSQL("insert into KHOAHOC values (6,'Lập Trình Website Cơ Bản','32','Nguyễn Thị Hường','20-7-2020','20-11-2020','20-11-2020')");
        db.execSQL("insert into KHOAHOC values (7,'Lập Trình HTML5, CSS3','30','Nguyễn Thị Hường','20-7-2020','20-11-2020','20-11-2020')");
        db.execSQL("insert into KHOAHOC values (8,'Lập Trình Javascript','64','Lê Anh Tú','20-7-2020','20-11-2020','20-11-2020')");
        db.execSQL("insert into ACCOUNT values ('admin','admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ACCOUNT");
        db.execSQL("DROP TABLE IF EXISTS KHOAHOC");
        onCreate(db);
    }
}
