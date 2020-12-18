package com.example.dapm_scrolling.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dapm_scrolling.R;
import com.example.dapm_scrolling.data.AdapterCustomer;
import com.example.dapm_scrolling.db.DBCustomer;
import com.example.dapm_scrolling.model.Customer;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    EditText edtValue;
    Spinner spnSearch;
    RecyclerView rcvCustomers;
    ArrayList<Customer> listCustomers;

    //administrador de la base de datos
    DBCustomer admin = new DBCustomer(this, "dbcustomers", null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);

        edtValue = findViewById(R.id.edtValue);
        spnSearch = findViewById(R.id.spnSearch);
        rcvCustomers = findViewById(R.id.rcvCustomers);

        String[] itemsSearch = {
                getString(R.string.itemsearch_select),
                getString(R.string.itemsearch_id),
                getString(R.string.itemsearch_marca),
                getString(R.string.itemsearch_modelo),
                getString(R.string.itemsearch_color),
                getString(R.string.itemsearch_tipo),
                getString(R.string.itemsearch_anio)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, itemsSearch);

        spnSearch.setAdapter(adapter);

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
        edtValue.setText("");
        spnSearch.setSelection(0);
    }

    public void searchCustomers(View view){
        try {
            //tomar la selección del spinner
            String searchFOr = spnSearch.getSelectedItem().toString();

            if(edtValue.getText().toString().isEmpty() || searchFOr.equals(getString(R.string.itemsearch_select))) {
                //pedir todos los datos
                Toast.makeText(this, getString(R.string.msg_data), Toast.LENGTH_SHORT).show();
            }else{

                //para el RecyclerView
                listCustomers = new ArrayList<Customer>();

                //administrar el RecyclerView
                rcvCustomers.setLayoutManager(new LinearLayoutManager(this));

                //llenar el arreglo de clientes
                fillCustomersList();

                AdapterCustomer adapter = new AdapterCustomer(listCustomers);

                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //lanzar el formulario
                        Intent i = new Intent(getApplicationContext(), EditActivity.class);

                        //enviar los valores
                        i.putExtra("idvehicle",listCustomers.get(
                                        rcvCustomers.getChildAdapterPosition(view)).getIdvehicle());
                        i.putExtra("marca", listCustomers.get(
                                rcvCustomers.getChildAdapterPosition(view)).getMarca());
                        i.putExtra("modelo", listCustomers.get(
                                rcvCustomers.getChildAdapterPosition(view)).getModelo());
                        i.putExtra("color", listCustomers.get(
                                rcvCustomers.getChildAdapterPosition(view)).getColor());
                        i.putExtra("tipo", listCustomers.get(
                                rcvCustomers.getChildAdapterPosition(view)).getTipo());
                        i.putExtra("anio", listCustomers.get(
                                rcvCustomers.getChildAdapterPosition(view)).getAnio());

                        //iniciar la acitivy
                        startActivity(i);
                    }
                });

                rcvCustomers.setAdapter(adapter);
            }


        }catch (Exception ex){

        }
    }

    private void fillCustomersList() {
        //objeto administrador de la bd
        SQLiteDatabase db = admin.getWritableDatabase();

        //para tomar cada registro de la consulta
        Customer customer = null;

        //tomar datos
        String value = edtValue.getText().toString();
        String campo = "";
        String select = spnSearch.getSelectedItem().toString();

        if(select.equals(getString(R.string.itemsearch_id))){
            campo = "idvehicle";
        }else if(select.equals(getString(R.string.itemsearch_marca))){
            campo = "brand";
        }else if(select.equals(getString(R.string.itemsearch_modelo))){
            campo = "modelo";
        }else if(select.equals(getString(R.string.itemsearch_tipo))){
            campo = "tipo";
        }else if(select.equals(getString(R.string.itemsearch_color))){
            campo = "color";
        }else{
            campo = "anio";
        }

        //consulta SQLite
        Cursor cursor = db.rawQuery("SELECT * FROM customers WHERE "+ campo +" LIKE '%"+ value + "%'",
                null);

        while (cursor.moveToNext()){
            //llenar el modelo
            customer = new Customer();
            customer.setIdvehicle(cursor.getInt(0));
            customer.setMarca(cursor.getString(1));
            customer.setModelo(cursor.getString(2));
            customer.setColor(cursor.getString(3));
            customer.setTipo(cursor.getString(4));
            customer.setAnio(cursor.getString(5));

            listCustomers.add(customer);

        }
        db.close();

    }
}