package com.example.uta.testcase;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Order extends AppCompatActivity {


    /* InsertボタンのClickリスナー */
    private View.OnClickListener buttonInsert_ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            buttonInsert_Click(v);
        }
    };
    /* UpdateボタンのClickリスナー */
    private View.OnClickListener buttonUpdate_ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            buttonUpdate_Click(v);
        }
    };
    /* DeleteボタンのClickリスナー */
    private View.OnClickListener buttonDelete_ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            buttonDelete_Click(v);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //ボタンにClickリスナーを設定する。
        Button buttonInsert = (Button) this.findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(buttonInsert_ClickListener);
        Button buttonUpdate = (Button) this.findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(buttonUpdate_ClickListener);
        Button buttonDelete = (Button) this.findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(buttonDelete_ClickListener);
    }

    /*
     * InsertボタンClick処理
     */
    private void buttonInsert_Click(View v) {
        ContentValues values = new ContentValues();
        values.put("Name", "yan");
        values.put("Tel", "0000-1234-5678");
        values.put("Age", 18);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long ret;
        try {
            ret = db.insert("MyTable", null, values);
        } finally {
            db.close();
        }
        if (ret == -1) {
            Toast.makeText(this, "Insert失敗", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Insert成功", Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * UpdateボタンClick処理
     */
    private void buttonUpdate_Click(View v) {
        ContentValues values = new ContentValues();
        values.put("Age", 24);
        String whereClause = "No = ?";
        String whereArgs[] = new String[1];
        whereArgs[0] = "1";

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret;
        try {
            ret = db.update("MyTable", values, whereClause, whereArgs);
        } finally {
            db.close();
        }
        if (ret == -1) {
            Toast.makeText(this, "Update失敗", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Update成功", Toast.LENGTH_SHORT).show();
        }
    }

    /* DeleteボタンClick処理 */
    private void buttonDelete_Click(View v) {
        String whereClause = "No = ?";
        String whereArgs[] = new String[1];
        whereArgs[0] = "1";

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret;
        try {
            ret = db.delete("MyTable", whereClause, whereArgs);
        } finally {
            db.close();
        }
        if (ret == -1) {
            Toast.makeText(this, "Delete失敗", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Delete成功", Toast.LENGTH_SHORT).show();
        }
    }
}