package com.fpharma.findpharma.Views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fpharma.findpharma.R;
import com.fpharma.findpharma.Servicos.GlideApp;
import com.fpharma.findpharma.Servicos.ImageProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Fp_Editar extends AppCompatActivity {

    public static final String CAMERA_PERMISSION = android.Manifest.permission.CAMERA;
    public static final String READ_EXTERNAL_STORAGE_PERMISSION = android.Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String WRITE_EXTERNAL_STORAGE_PERMISSION = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

    public static final Integer CAMERA_REQUEST_CODE = 9001;
    public static final Integer CROP_REQUEST_CODE = 9002;
    public static final Integer GALLERY_REQUEST_CODE = 9003;

    public static final Integer CAMERA_PERMISSION_REQUEST_CODE = 8001;

    private Uri pictureUri;
    private String photoPath;
    Bitmap bitmap;
    String photoBase64;

    EditText txtNome;
    EditText txtSobrenome;
    EditText txtEmail;
    ImageView btnCamera;

    DatabaseReference mDb = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    StorageReference storageRef = FirebaseStorage.getInstance().getReference().child(user.getUid());
    final ProgressDialog mProgressDialog = new ProgressDialog(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp__editar);

        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Aguarde");

        txtEmail = (EditText) findViewById(R.id.email);
        txtNome = (EditText) findViewById(R.id.nomeUsuario);
        txtSobrenome = (EditText) findViewById(R.id.sobreNomeUsuario);

        if (user != null) {
            mProgressDialog.show();
            mDb.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mProgressDialog.dismiss();
                    HashMap<String, String> valores = (HashMap<String, String>) dataSnapshot.child(user.getUid()).getValue();
                    if (valores != null && !valores.isEmpty()) {
                        txtEmail.setText(valores.get("email"));
                        txtNome.setText(valores.get("nome"));
                        txtSobrenome.setText(valores.get("sobrenome"));
                        GlideApp.with(getApplicationContext()).load(valores.get("foto")).into(btnCamera);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        btnCamera = (ImageView) findViewById(R.id.cam);
        Button btnSalvar = (Button) findViewById(R.id.salvar);
        final Button btnCancelar = (Button) findViewById(R.id.cancelar2);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermission();
            }
        });

        btnSalvar.setOnClickListener(getSalvarListener());

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Fp_Editar.this, Fp_Logado.class);
                startActivity(it);
            }
        });
    }

    @NonNull
    private View.OnClickListener getSalvarListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtEmail.getText() != null && txtEmail.getText().toString().equals("")) {
                    Toast.makeText(Fp_Editar.this, "Email Inválido!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (txtNome.getText() != null && txtNome.getText().toString().equals("")) {
                    Toast.makeText(Fp_Editar.this, "Nome Inválido!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (txtSobrenome.getText() != null && txtSobrenome.getText().toString().equals("")) {
                    Toast.makeText(Fp_Editar.this, "Sobrenome Inválido!", Toast.LENGTH_LONG).show();
                    return;
                }

                mProgressDialog.show();
                mDb.child(user.getUid()).child("nome").setValue(txtNome.getText().toString());
                mDb.child(user.getUid()).child("sobrenome").setValue(txtNome.getText().toString());
                mDb.child(user.getUid()).child("email").setValue(txtEmail.getText().toString());

                if (bitmap != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] data = baos.toByteArray();
                    UploadTask uploadTask = storageRef.putBytes(data);
                    uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Uri downloadUrl = task.getResult().getDownloadUrl();
                            mDb.child(user.getUid()).child("foto").setValue(downloadUrl.toString());
                            mProgressDialog.dismiss();
                            Intent it = new Intent(Fp_Editar.this, Fp_Logado.class);
                            startActivity(it);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mProgressDialog.dismiss();
                            Toast.makeText(Fp_Editar.this, "Ocorreu um erro ao fazer upload da foto.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;

            try {
                photoFile = ImageProvider.getInstance().createImageFile(this);
            } catch (Exception e) {

            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.fpharma.findpharma.fileprovider", photoFile);
                pictureUri = photoURI;
                photoPath = photoFile.getAbsolutePath();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        }
    }


    public void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, CAMERA_PERMISSION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE_PERMISSION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{CAMERA_PERMISSION, WRITE_EXTERNAL_STORAGE_PERMISSION, READ_EXTERNAL_STORAGE_PERMISSION}, CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            openCamera();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
//                photoBase64 = encodeImage(photoPath);
//                Uri uri = ImageProvider.getInstance().getImageUri(this, photoPath);
                bitmap = ImageProvider.getInstance().resizeRotatePicture(photoPath);
                btnCamera.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            openCamera();
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private String encodeImage(String path) {
        File imagefile = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imagefile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
//Base64.de
        return encImage;

    }

}