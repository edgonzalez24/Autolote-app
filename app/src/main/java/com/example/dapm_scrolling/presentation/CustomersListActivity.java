package com.example.dapm_scrolling.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dapm_scrolling.R;
import com.example.dapm_scrolling.data.AdapterCustomer;
import com.example.dapm_scrolling.db.DBCustomer;
import com.example.dapm_scrolling.model.Customer;

import java.util.ArrayList;

public class CustomersListActivity extends AppCompatActivity {
    //arreglo para llenar el recyclerView
    ArrayList<Customer> listCustomers;
    RecyclerView rcvCustomers;

    //administrador de la base de datos
    DBCustomer admin = new DBCustomer(this, "dbcustomers", null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_list);

        //para el RecyclerView
        listCustomers = new ArrayList<Customer>();
        rcvCustomers = findViewById(R.id.rcvCustomers);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        //administrar el RecyclerView
        rcvCustomers.setLayoutManager(new LinearLayoutManager(this));

        //llenar el arreglo de clientes
        fillCustomersList();

        AdapterCustomer adapter = new AdapterCustomer(listCustomers);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CustomersListActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });

        rcvCustomers.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
            case R.id.action_back:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void fillCustomersList() {
        //objeto administrador de la bd
        SQLiteDatabase db = admin.getWritableDatabase();

        //para tomar cada registro de la consulta
        Customer customer = null;

        //consulta SQLite
        Cursor cursor = db.rawQuery("SELECT * FROM customers", null);

        while (cursor.moveToNext()){
            //llenar el modelo
            customer = new Customer();
            customer.setIdvehicle(cursor.getInt(0));
            customer.setMarca(cursor.getString(1));
            customer.setModelo(cursor.getString(2));
            customer.setColor(cursor.getString(3));
            customer.setAnio(cursor.getString(4));
            customer.setTipo(cursor.getString(5));

            listCustomers.add(customer);

        }
        db.close();

    }
}