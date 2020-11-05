package com.example.asmfinal.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmfinal.database.DbHelper;
import com.example.asmfinal.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private SQLiteDatabase db;

    public AccountDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public boolean update(Account account){
        ContentValues values = new ContentValues();
        values.put("username", account.username);
        values.put("password",account.password);
        long r = db.update("ACCOUNT",values,"username=?",new String[]{account.username});
        if (r <=0 ) return false;
        else return true;
    }
    public Boolean check(String username, String password) {
        Cursor cs = db.rawQuery("select * from ACCOUNT where username=? and password=?", new String[]{username, password});
        if (cs.getCount() > 0) return true;
        else return false;
    }
    public List<Account> getKhoan(String sql, String...a){
        List<Account> list = new ArrayList<Account>();
        Cursor c = db.rawQuery(sql,a);
        while (c.moveToNext()){
            Account account = new Account();
            account.username = c.getString(c.getColumnIndex("username"));
            account.password = c.getString(c.getColumnIndex("password"));
            list.add(account);
        }
        return list;
    }

    public boolean insert(Account account){
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",account.username);
        contentValues.put("password",account.password);
        long r = db.insert("ACCOUNT",null,contentValues);
        if (r <=0 ) return false;
        else return true;
    }

    public String getTenKhoan(String i){
        String name = null;
        String sql = "SELECT password FROM ACCOUNT WHERE username='"+i+"'";
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()){
            name = c.getString(c.getColumnIndex("password"));
        }
        return name;
    }
}
