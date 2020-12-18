package com.example.dapm_scrolling.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dapm_scrolling.R;
import com.example.dapm_scrolling.model.Customer;

import java.util.ArrayList;

public class AdapterCustomer extends RecyclerView.Adapter<AdapterCustomer.CustomersViewHolder>
        implements View.OnClickListener {

    //arreglo para los destinos
    ArrayList<Customer> listCustomers;

    //escuchador del click en el recyclerView
    private View.OnClickListener listener;

    //constructor
    public AdapterCustomer(ArrayList<Customer> listCustomers) {
        this.listCustomers = listCustomers;
    }

    //se encarga de escuchar el evento
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override //vincular el layout con los elementos a mostrar
    public CustomersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.customers_list, null, false);

        view.setOnClickListener(this);
        return new CustomersViewHolder(view);
    }

    @Override //llenar los elementos del layout
    public void onBindViewHolder(@NonNull CustomersViewHolder holder, int position) {
        holder.txvMarca.setText(listCustomers.get(position).getIdvehicle() + "-" + listCustomers.get(position).getMarca());
        holder.txvModelo.setText(listCustomers.get(position).getModelo());
        holder.txvColor.setText(listCustomers.get(position).getColor());
        holder.txvAnio.setText(listCustomers.get(position).getAnio());
        holder.txvTipo.setText(listCustomers.get(position).getTipo());
    }

    @Override //optener el tamaño del arreglo
    public int getItemCount() {
        return listCustomers.size();
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class CustomersViewHolder extends RecyclerView.ViewHolder {
        //elementos de la vista a modificar
        TextView txvMarca, txvModelo, txvColor, txvAnio, txvTipo;

        public CustomersViewHolder(@NonNull View itemView) {
            super(itemView);

            //vincular objetos con los recursos
            txvMarca =itemView.findViewById(R.id.txvMarca);
            txvModelo =itemView.findViewById(R.id.txvModelo);
            txvColor =itemView.findViewById(R.id.txvColor);
            txvAnio =itemView.findViewById(R.id.txvAnio);
            txvTipo =itemView.findViewById(R.id.txvTipo);
        }
    }
}
