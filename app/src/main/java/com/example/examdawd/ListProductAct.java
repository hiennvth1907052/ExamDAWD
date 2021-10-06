package com.example.examdawd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.examdawd.database.DBHelper;

public class ListProductAct extends AppCompatActivity {
    private DBHelper db;
    private Cursor c;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        db = new DBHelper(this);
        ListView lvProduct = (ListView) findViewById(R.id.lvProduct);
        c = db.getAllProduct();
        adapter = new SimpleCursorAdapter(this, R.layout.item_product, c, new String[] {DBHelper.ID, DBHelper.NAME, DBHelper.QUANTITY},
                new int[]{R.id.tvId, R.id.tvName, R.id.tvQuantity}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lvProduct.setAdapter(adapter);
    }
    @Override
    protected void onStart (){
        super.onStart();
        c = db.getAllProduct();
        adapter.changeCursor(c);
        adapter.notifyDataSetChanged();
        db.close();
    }
}