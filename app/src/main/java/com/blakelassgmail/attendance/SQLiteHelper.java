package com.blakelassgmail.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.sql.PreparedStatement;

/**
 * Created by Blake Lassiter on 5/9/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MembersDB";
    private static final String TABLE_MEMBERS = "members";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMBERS_TABLE = "CREATE TABLE " +
                TABLE_MEMBERS + "( " +
                "  ID int NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "  name varchar(255), " +
                "  phone varchar(255), " +
                "  email varchar(255), " +
                "  student_id int, " +
                "  birthday TIMESTAMP, " +
                "  grade varchar(255) " +
                ")";
        SQLiteStatement stmt = db.compileStatement(CREATE_MEMBERS_TABLE);
        stmt.execute();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBERS);
        this.onCreate(db);
    }

    public void addMember(Member member){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", member.getName());
        values.put("email", member.getEmail());
        values.put("phone", member.getPhone());

        db.insert(TABLE_MEMBERS, null, values);
        db.close();
    }

    public Member getMember(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEMBERS,
                new String[]{"id", "name", "email", "phone"},
                " id = ?", new String[]{ String.valueOf(id) },
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        else
            return null;

        Member member = new Member();
        if( cursor != null && cursor.moveToFirst() ) {
            member.setId(Integer.parseInt(cursor.getString(0)));
            member.setName(cursor.getString(1));
            member.setEmail(cursor.getString(2));
            member.setPhone(cursor.getString(3));
        }
        return member;
    }
}
