package com.example.pertamaapp;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    Button btnsubmit;
    TextView view,result;
    EditText edtnama,edtprodi,edtemail,edtnilai1,edtnilai2;
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

        btnsubmit=findViewById(R.id.btnSubmit);
        edtnama=findViewById(R.id.edtName);
        edtprodi=findViewById(R.id.edtProdi);
        edtemail=findViewById(R.id.edtEmail);
        edtnilai1=findViewById(R.id.edtNilai1);
        edtnilai2=findViewById(R.id.edtNilai2);
        result=findViewById(R.id.txtResult);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama =edtnama.getText().toString();
                String prodi=edtprodi.getText().toString();
                String email=edtemail.getText().toString();
                String nilai1=edtnilai1.getText().toString();
                String nilai2=edtnilai2.getText().toString();

                result.setText(
                        nama +"\n"+
                        prodi +"\n"+
                        email+"\n"+
                        nilai1+"\n"+
                        nilai2+"\n"
                );
            }
        });
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
            return super.onOptionsItemSelected(item)
        }
    }


}