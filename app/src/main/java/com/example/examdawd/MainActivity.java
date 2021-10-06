package com.example.examdawd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.examdawd.database.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edName;
    private EditText edQuantity;
    private DBHelper db;
    private Button btAdd;
    private Button btView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        db = new DBHelper(this);
        db.getReadableDatabase();
    }

    private void initView() {
        edName = (EditText) findViewById(R.id.edName);
        edQuantity = (EditText) findViewById(R.id.edQuantity);
        btAdd = (Button) findViewById(R.id.btAdd);
        btView = (Button) findViewById(R.id.btView);
        btView.setOnClickListener(this);
        btAdd.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if (view == btAdd){
            onAdd();
        }
        if (view == btView){
            onView();
        }
    }
    private void onAdd(){
        if (edName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter name product", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edQuantity.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter quantity", Toast.LENGTH_SHORT).show();
            return;
        }
        String isAdd = db.addProduct(edName.getText().toString(), Integer.parseInt(edQuantity.getText().toString()));
        Toast.makeText(this, isAdd, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ListProductAct.class);
        startActivity(intent);
    }
    private void onView(){
        Intent intent = new Intent(MainActivity.this, ListProductAct.class);
        startActivity(intent);
    }

}