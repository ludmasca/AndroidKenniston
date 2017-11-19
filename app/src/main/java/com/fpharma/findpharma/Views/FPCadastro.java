package com.fpharma.findpharma.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fpharma.findpharma.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FPCadastro extends AppCompatActivity {

    EditText txtNome;
    EditText txtSobrenome;
    EditText txtEmailCadastro;
    EditText txtSenhaCadastro;
    EditText txtSenhaConfirmacao;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String googleAccountName;
    private GoogleApiClient mGoogleApiClient;

    private final String TAG = this.getClass().getCanonicalName();
    private static final int RC_SIGN_IN = 9001;
    private static final int REQ_SIGN_IN_REQUIRED = 55664;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpcadastro);

        mAuth = FirebaseAuth.getInstance();

        txtNome = (EditText) findViewById(R.id.nome);
        txtSobrenome = (EditText) findViewById(R.id.sobrenome);
        txtEmailCadastro = (EditText) findViewById(R.id.emailCadastro);
        txtSenhaCadastro = (EditText) findViewById(R.id.senhaCadastro);
        txtSenhaConfirmacao = (EditText) findViewById(R.id.senhaConfirmacao);
        Button btnCadastrar2 = (Button) findViewById(R.id.Cadastrar2);
        Button btnCancelar = (Button) findViewById(R.id.Cancelar);


        btnCadastrar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNome.getText() != null && txtNome.getText().toString().equals("")) {
                    Toast.makeText(FPCadastro.this, "Nome Inválido!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (txtSobrenome.getText() != null && txtSobrenome.getText().toString().equals("")) {
                    Toast.makeText(FPCadastro.this, "Sobrenome Inválido!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (txtEmailCadastro.getText() != null && txtEmailCadastro.getText().toString().equals("")) {
                    Toast.makeText(FPCadastro.this, "Email Inválido!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (txtSenhaCadastro.getText() != null && txtSenhaCadastro.getText().toString().equals("")) {
                    Toast.makeText(FPCadastro.this, "Senha Inválida!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (txtSenhaConfirmacao.getText() != null && txtSenhaConfirmacao.getText().toString().equals("") && txtSenhaConfirmacao.getText() != txtSenhaCadastro) {
                    Toast.makeText(FPCadastro.this, "As senhas não são iguais!", Toast.LENGTH_LONG).show();
                    return;
                }
                register();
            }

        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FPCadastro.this, FindPharma.class);
                startActivity(it);
            }
        });
    }

    private void register() {
        mAuth.createUserWithEmailAndPassword(txtEmailCadastro.getText().toString(), txtSenhaCadastro.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent it = new Intent(FPCadastro.this, FindPharma.class);
                    startActivity(it);
                }

            }
        });
    }

    }



