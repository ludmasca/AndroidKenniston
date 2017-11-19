package com.fpharma.findpharma.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fpharma.findpharma.R;

public class Fp_Logado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp_logado);
        Button btnEditar = (Button) findViewById(R.id.editar);
        Button btnLista = (Button) findViewById(R.id.listar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Fp_Logado.this, Fp_Editar.class));
            }
        });
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Fp_Logado.this, FP_Principal.class));
            }
        });
    }

}
