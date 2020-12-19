package com.example.dapm_scrolling.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dapm_scrolling.R;
import com.example.dapm_scrolling.db.DBCustomer;

public class EditActivity extends AppCompatActivity {
    //objetos
    TextView txvId;
    EditText edtMarca, edtModelo, edtColor, edtAnio;
    RadioButton rdbAutomatico, rdbMecanico;

    //para el género
    String tipo;

    //administrador de la base de datos
    DBCustomer admin = new DBCustomer(this, "dbcustomers", null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);

        //vincular las vistas
        txvId = findViewById(R.id.txvId);
        edtMarca = findViewById(R.id.edtMarca);
        edtModelo = findViewById(R.id.edtModelo);
        edtColor = findViewById(R.id.edtColor);
        edtAnio = findViewById(R.id.edtAnio);
        rdbAutomatico= findViewById(R.id.rdbAutomatico);
        rdbMecanico= findViewById(R.id.rdbMecanico);

        //llenar el formulario
        Bundle extras = getIntent().getExtras();

        if (extras!=null){
            txvId.setText(String.valueOf(extras.getInt("idvehicle")));
            edtMarca.setText(extras.getString("marca"));
            edtModelo.setText(extras.getString("modelo"));
            edtColor.setText(extras.getString("color"));
            edtAnio.setText(extras.getString("anio"));

            //para el género
            if(extras.getString("tipo").equals("Automatico")){
                rdbAutomatico.setChecked(true);
            }else{
                rdbMecanico.setChecked(true);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_clean:
                clean();
                break;
            case R.id.action_back:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void clean() {
        edtMarca.setText("");
        edtModelo.setText("");
        edtColor.setText("");
        edtAnio.setText("");
        rdbAutomatico.setChecked(true);
        edtMarca.requestFocus();

        Toast.makeText(this, getString(R.string.msg_clean), Toast.LENGTH_SHORT).show();
    }

    //editar
    public void editCustomer(View view){
        try {
            //objeto administrador de la bd
            SQLiteDatabase db = admin.getWritableDatabase();

            //para guardar
            ContentValues data = new ContentValues();

            if(edtMarca.getText().toString().isEmpty() || edtModelo.getText().toString().isEmpty()
                    || edtColor.getText().toString().isEmpty() || edtAnio.getText().toString().isEmpty()) {
                //pedir todos los datos
                Toast.makeText(this, getString(R.string.msg_data), Toast.LENGTH_SHORT).show();
            }else{
                //recolectar los datos
                data.put("marca", edtMarca.getText().toString());
                data.put("modelo", edtModelo.getText().toString());
                data.put("color", edtColor.getText().toString());
                data.put("anio", edtAnio.getText().toString());

                if (rdbAutomatico.isChecked()) {
                    tipo = getString(R.string.formnew_tipo_a);
                } else {
                    tipo = getString(R.string.formnew_tipo_m);
                }
                data.put("tipo", tipo);

                //editar
                db.update("customers", data, "idvehicle="+txvId.getText().toString(),null);

                //cerrar la conexión
                db.close();

                Toast.makeText(this, getString(R.string.msg_save),
                        Toast.LENGTH_LONG).show();

            }
        }catch (Exception ex){
            Toast.makeText(this, getString(R.string.msg_error) + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }


    public void deleteCustomer(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(EditActivity.this);
        dialog.setTitle(getString(R.string.msg_title));
        dialog.setMessage(getString(R.string.msg_alert));
        dialog.setCancelable(false);
        dialog.setPositiveButton(getString(R.string.msg_confirm), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                try {
                    //objeto administrador de la bd
                    SQLiteDatabase db = admin.getWritableDatabase();

                    //borrar
                    db.delete("customers", "idvehicle=" + txvId.getText().toString(), null);
                    //cerrar la conexión
                    db.close();

                    Toast.makeText(EditActivity.this, getString(R.string.msg_delete),
                            Toast.LENGTH_LONG).show();

                }catch (Exception ex){
                    Toast.makeText(EditActivity.this, getString(R.string.msg_error) + ex.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
            }
        });
        dialog.show();

    }
}