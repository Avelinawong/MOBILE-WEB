package com.example.pertamaapp;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pertamaapp.model.mahasiswa;

import io.realm.Realm;

public class ProfileActivity extends AppCompatActivity {

    Button btnsubmit;
    TextView result;
    EditText edtnama,edtprodi,edtemail,edtnilai1,edtnilai2;
    Spinner sprProdi;
    RadioButton rdbPria, rdbWanita;
    RadioGroup rdgJenisKelamin;
    CheckBox ckbMakan,ckbTidur,ckbBelajar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        edtnama.setText(getIntent().getStringExtra("nama"));
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { simpan();}
        });
    }
    public void init() {
        btnsubmit = findViewById(R.id.btnSubmit);
        edtnama = findViewById(R.id.edtName);
        sprProdi = findViewById(R.id.sprProdi);
        edtemail = findViewById(R.id.edtEmail);
        edtnilai1 = findViewById(R.id.edtNilai1);
        edtnilai2 = findViewById(R.id.edtNilai2);
        result = findViewById(R.id.txtResult);
        sprProdi = findViewById(R.id.sprProdi);
        rdgJenisKelamin = findViewById(R.id.rdgJenisKelamin);
        rdbPria = findViewById(R.id.rdbPria);
        rdbWanita = findViewById(R.id.rdbWanita);
        ckbTidur = findViewById(R.id.ckbTidur);
        ckbMakan = findViewById(R.id.ckbMakan);
        ckbBelajar = findViewById(R.id.ckbBelajar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.prodi_array,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprProdi.setAdapter(adapter);
    }
    public boolean isValidated(){
        if(edtnama.getText().toString().equals("")) {
            edtnama.setError("Nama wajib di isi");
            return false;
        }else if(edtemail.getText().toString().equals("")) {
            edtemail.setError("Email wajib di isi");
            return false;
        }
            return true;
    }

    public void simpan(){
        if(isValidated()){
            // Menampilkan hasil di txvHasil
            String nama = edtnama.getText().toString();
            String email = edtemail.getText().toString();
            String prodi = sprProdi.getSelectedItem().toString();
            String jenisKelamin = "";
            String hobi="";
            if(rdbWanita.isChecked()) jenisKelamin= rdbWanita.getText().toString();
            else if(rdbPria.isChecked()) jenisKelamin= rdbPria.getText().toString();
            String jk=jenisKelamin;

            if(ckbBelajar.isChecked()) hobi += ckbBelajar.getText().toString()+"; ";
            if(ckbMakan.isChecked()) hobi += ckbMakan.getText().toString()+"; ";
            if(ckbTidur.isChecked()) hobi += ckbTidur.getText().toString()+"; ";
            String hobby=hobi;

            result.setText(nama + "("+jenisKelamin+")"+
                    "\nHobi "+hobi
                    + "\n"+ email + "\nProgram Studi " + prodi + "\n" +
                    getNamaFakultas(prodi));

            //simpan ke realm
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(r -> {
                Number maxId = r.where(mahasiswa.class).max("studentid");
                int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
                mahasiswa mhs = r.createObject(mahasiswa.class, nextId);
                mhs.setNama(nama);
                mhs.setEmail(email);
                mhs.setProgramstudi(prodi);
                mhs.setJeniskelamin(jk);
                mhs.setHobi(hobby);
            });
            Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show();
        }
    }
    String getNamaFakultas(String prodi){
        String namprodi=prodi.toLowerCase();
        if (namprodi.equals("sistem informasi") || namprodi.equals("informatika")){
            return "Fakultas Teknologi Informasi";
        } else if(namprodi.equals("hukum") || namprodi.equals("law")){
            return "Fakultas Hukum";
        } else if(namprodi.equals("akuntansi") || namprodi.equals("manajemen")||
                namprodi.equals("perhotelan")) {
            return "Fakultas Ekonomi dan Bisnis";
        }
        else
            return "Fakultas Tidak di Temukan";
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId()==R.id.msimpan){
            btnsubmit.performClick();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }


}