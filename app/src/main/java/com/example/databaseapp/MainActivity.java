package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DBHelper dbHelper;
    TextView tvOut;
    EditText ename, esname, eyear, eNew;
    Button  btnDel, btnAdd, btnGet, btnDelOne, btnUpd, btnSearch;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        tvOut = findViewById(R.id.tvOut);

        ename = findViewById(R.id.editName);
        esname = findViewById(R.id.editSurname);
        eyear = findViewById(R.id.editYear);
        eNew = findViewById(R.id.editNew);

        btnDel = findViewById(R.id.buttonDel);
        btnAdd = findViewById(R.id.buttonAdd);
        btnGet = findViewById(R.id.buttonGet);
        btnDelOne = findViewById(R.id.buttonDelOne);
        btnUpd = findViewById(R.id.buttonUpdate);
        btnSearch = findViewById(R.id.buttonSearch);

        btnDel.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnGet.setOnClickListener(this);
        btnDelOne.setOnClickListener(this);
        btnUpd.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        LinkedList<Data> list;

        String text = "";

        int id = v.getId();

        if (id == R.id.buttonDel)
        {
            dbHelper.DeleteAll();
        }
        else if (id == R.id.buttonAdd)
        {
            String name = ename.getText().toString();
            String sname = esname.getText().toString();
            int year = Integer.parseInt(eyear.getText().toString());

            Data data = new Data(name, sname, year);
            dbHelper.AddOne(data);

        }
        else if (id == R.id.buttonGet)
        {
            list = dbHelper.GetAll();

            for (Data d : list) text = text + (d.name) + "  " + (d.surname) + " " + (d.year) + "\n";
            tvOut.setText(text.toString());
        }
        else if(id == R.id.buttonDelOne)
        {
            dbHelper.DeleteOne(eNew.getText().toString());
        }
        else if (id == R.id.buttonUpdate)
        {
            dbHelper.UpdateOne(eNew.getText().toString());
        }
        else if (id == R.id.buttonSearch)
        {
            list = dbHelper.Search(eNew.getText().toString());

            for (Data d : list) text = text + (d.name) + "  " + (d.surname) + " " + (d.year) + "\n";
            tvOut.setText(text.toString());
        }
        else if (id ==  R.id.buttonTime)
        {
            tvOut.setText("" + dbHelper.Insert1000());
        }

    }
}
