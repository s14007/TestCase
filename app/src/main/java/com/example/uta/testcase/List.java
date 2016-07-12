package com.example.uta.testcase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class List extends AppCompatActivity {
    // リストに設定するアイテム
    private final String[] item = new String [] {
            "listView item 1",
            "listView item 2",
            "listView item 3"
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // 追加するアイテムを生成する
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_multiple_choice, item);

        // リストビューにアイテム (adapter) を追加
        ListView listView1 = (ListView)findViewById(R.id.listView);
        listView1.setAdapter(adapter);

        // ボタンクリックイベント
        Button btn = (Button)findViewById(R.id.btnOk);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 選択アイテムを取得
                ListView listView1 = (ListView) findViewById(R.id.listView);
                SparseBooleanArray checked = listView1.getCheckedItemPositions();

                // チェックされたアイテムの文字列を生成
                // checked には、「チェックされているアイテム」ではなく、
                // 「一度でもチェックされたアイテム」が入ってくる。
                // なので、現在チェックされているかどうかを valutAt の戻り値
                // で判定する必要がある！！！
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < checked.size(); i++) {
                    if (checked.valueAt(i)) {
                        sb.append(item[i] + ",");
                    }
                }
                // 通知
                Toast.makeText(List.this,
                        sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
