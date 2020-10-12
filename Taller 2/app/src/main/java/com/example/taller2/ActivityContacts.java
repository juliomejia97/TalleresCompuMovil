package com.example.taller2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityContacts extends AppCompatActivity {
    private String[] mProjection;
    private Cursor mCursor;
    private ContactsAdapter mContactsAdapter;
    private ListView lista;
    private static final int READ_CONTACTS_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        lista = findViewById(R.id.listContacts);
        mProjection = new String[]{ContactsContract.Profile._ID,
        ContactsContract.Profile.DISPLAY_NAME_PRIMARY};
        mContactsAdapter = new ContactsAdapter(this,null, 0);
        lista.setAdapter(mContactsAdapter);

        solicitarPermiso(this, Manifest.permission.READ_CONTACTS, "Necesito leer los contactos", READ_CONTACTS_ID);
        usarPermiso();
    }

    private void solicitarPermiso(Activity context, String permiso, String justificacion, int idPermiso){
        if(ContextCompat.checkSelfPermission(context,permiso) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{permiso}, idPermiso);
            if(ActivityCompat.shouldShowRequestPermissionRationale(context,permiso)){
                Toast.makeText(this, justificacion, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void usarPermiso(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            mCursor=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, mProjection, null, null, null);
            mContactsAdapter.changeCursor(mCursor);
        }
    }

    //Call Back para recuperar el control
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case READ_CONTACTS_ID:{
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    usarPermiso();
                }
            }
        }
    }
}