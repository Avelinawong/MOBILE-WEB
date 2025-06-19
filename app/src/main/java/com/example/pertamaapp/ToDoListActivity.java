package com.example.pertamaapp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ToDoListActivity extends AppCompatActivity {

    EditText editnama;
    TextView namak,tempat,jenis;
    RadioButton radio1,radio2;
    CheckBox check1,check2,check3,check4;
    Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_to_do_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editnama = findViewById(R.id.edtnamak);
        namak=findViewById(R.id.namak);
        tempat=findViewById(R.id.tempat);
        jenis=findViewById(R.id.jenis);
        radio1=findViewById(R.id.radio1);
        radio2=findViewById(R.id.radio2);
        check1=findViewById(R.id.checkBox);
        check2=findViewById(R.id.checkBox2);
        check3=findViewById(R.id.checkBox3);
        check4=findViewById(R.id.checkBox4);
        submitbtn=findViewById(R.id.button);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidasiForm()) {
                    String namakegiatan = "";
                    String jeniskegiatan = "";
                    String tempatkegiatan = "";
                    namakegiatan = editnama.getText().toString();
                    if (radio1.isChecked()) tempatkegiatan = radio1.getText().toString();
                    else if (radio2.isChecked()) tempatkegiatan = radio2.getText().toString();

                    if (check1.isChecked()) jeniskegiatan = check1.getText().toString() + "\n";
                    if (check2.isChecked()) jeniskegiatan = check2.getText().toString() + "\n";
                    if (check3.isChecked()) jeniskegiatan = check3.getText().toString() + "\n";
                    if (check4.isChecked()) jeniskegiatan = check4.getText().toString() + "\n";

                    String pesan = "Nama Kegiatan :" + namakegiatan + "\n" +
                            "Tempat Kegiatan" + tempatkegiatan + "\n" +
                            "Jenis Kegiatan" + jeniskegiatan + "\n";

                    Toast toast = Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                    toast.show();
                }
            }
        });
    }

    Boolean ValidasiForm(){
        if(editnama.getText().toString().equals("")) {
            editnama.setText("Nama Kegiatan wajib diisi");
            return false;
        }else
            return true;


        }
    }