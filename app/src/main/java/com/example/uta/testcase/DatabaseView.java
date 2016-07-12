package com.example.uta.testcase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DatabaseView extends AppCompatActivity {

    /* CreateDumyボタンClickリスナー */
    private View.OnClickListener buttonCreateDumy_OnClickListener = new View.OnClickListener(){
        public void onClick(View v) {buttonCreateDumyData_OnClick(v);}};
    /* buttonRowQueryボタンClickリスナー 　*/
    private View.OnClickListener buttonRowQuery_OnClickListener = new View.OnClickListener(){
        public void onClick(View v) {buttonRowQuery_OnClick(v);}};
    /* buttonQueryボタンClickリスナー 　*/
    private View.OnClickListener buttonQuery_OnClickListener = new View.OnClickListener(){
        public void onClick(View v) {buttonQuery_OnClick(v);}};

    /*
     * onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_view);
        //
        Button btnCreateDumy = (Button)this.findViewById(R.id.btnCreateDumy);
        btnCreateDumy.setOnClickListener(buttonCreateDumy_OnClickListener);
        Button btnRowQuery = (Button)this.findViewById(R.id.buttonRowQuery);
        btnRowQuery.setOnClickListener(buttonRowQuery_OnClickListener);
        Button btnQuery = (Button)this.findViewById(R.id.buttonQuery);
        btnQuery.setOnClickListener(buttonQuery_OnClickListener);
    }

    /*
     * ダミーデータ作成ボタン　クリック処理
     */
    private void buttonCreateDumyData_OnClick(View v){
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            for (int i=0; i<10; i++){
                values.put("Name", "yan" + (i+1));
                values.put("Tel", "000-000-0000");
                values.put("Age", 20+i);
                db.insert("MyTable", null, values);
                values.clear();
            }
        }finally{
            db.close();
        }

    }

    /*
     * RowQueryボタン　クリック処理
     */
    private void buttonRowQuery_OnClick(View v){
        //SQL作成
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append(" No");
        sql.append(" ,Name");
        sql.append(" ,Tel");
        sql.append(" ,Age");
        sql.append(" FROM MyTable;");
        //rawQueryメソッドでデータを取得
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        StringBuilder text;
        try{
            Cursor cursor = db.rawQuery(sql.toString(), null);
            //TextViewに表示
            text = new StringBuilder();
            while (cursor.moveToNext()){
                text.append(cursor.getInt(0));
                text.append("," + cursor.getString(1));
                text.append("," + cursor.getString(2));
                text.append("," + cursor.getString(3));
                text.append("\n");
            }
        }finally{
            db.close();
        }
        TextView lblList = (TextView)this.findViewById(R.id.labelList);
        lblList.setText(text);
    }

    /*
     * queryボタン　クリック処理
     */
    private  void buttonQuery_OnClick(View v){
        //queryメソッドでデータを取得
        String[] cols = {"No","Name","Tel","Age"};
        String selection = "No = ?";
        String[] selectionArgs = {"5"};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        StringBuilder text;
        try{
            Cursor cursor = db.query("MyTable", cols, selection, selectionArgs, groupBy, having, orderBy);
            //TextViewに表示
            text = new StringBuilder();
            while (cursor.moveToNext()){
                text.append(cursor.getInt(0));
                text.append("," + cursor.getString(1));
                text.append("," + cursor.getString(2));
                text.append("," + cursor.getString(3));
                text.append("\n");
            }
        }finally{
            db.close();
        }
        TextView lblList = (TextView)this.findViewById(R.id.labelList);
        lblList.setText(text);
    }
}
