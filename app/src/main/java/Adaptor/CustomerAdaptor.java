package Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment4.R;

import java.util.List;

import Models.Customer;
import Models.Stock;
import VH.CustomerVH;
import VH.StockVH;

public class CustomerAdaptor extends RecyclerView.Adapter<CustomerVH> {

    private Context mContext;
    private List<Customer> customerList;

    public CustomerAdaptor(Context mContext, List<Customer> customerList) {
        this.mContext = mContext;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public CustomerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.customer_item, parent, false);
        return new CustomerVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerVH holder, int position) {
        Customer c = customerList.get(position);
        holder.cName.setText(c.getFullName());
        holder.cShip.setText(c.getShippingAddress());
        holder.cEmail.setText(c.getEmail());
//        holder.cID.setText(c.getId());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }
}
