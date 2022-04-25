package Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment4.AddToCart;
import com.example.assignment4.AdminMain;
import com.example.assignment4.AdminStock;
import com.example.assignment4.EditStock;
import com.example.assignment4.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import Models.Stock;
import VH.StockVH;

public class StockAdaptor extends RecyclerView.Adapter<StockVH> {

    private Context mContext;
    private List<Stock> stockList;
    private FirebaseUser mUser;

    public StockAdaptor(Context mContext, List<Stock> stockList) {
        this.mContext = mContext;
        this.stockList = stockList;
    }

    @NonNull
    @Override
    public StockVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.stockitem, parent, false);
        return new StockVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StockVH holder, int position) {

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String u = mUser.getUid();
        Stock s = stockList.get(position);
        holder.itemN.setText(s.getItemName());
        holder.itemB.setText(s.getManufacturer());
//        double p = s.getPrice();
        holder.itemP.setText(s.getItemprice());
        holder.itemQuan.setText(s.getQuantity());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query q = FirebaseDatabase.getInstance().getReference().child("Users").child(u);
                q.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String t = snapshot.child("Type").getValue().toString();
                            if(t.equals("Admin")){
                                Intent intent = new Intent(mContext, EditStock.class);
                                intent.putExtra("stockitemKey", s.getItemID());
                                mContext.startActivity(intent);
                            }else if(t.equals("Customer")){
                                Intent intent = new Intent(mContext, AddToCart.class);
                                intent.putExtra("stockitemKey", s.getItemID());
                                mContext.startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                Intent intent = new Intent(mContext, EditStock.class);
//                intent.putExtra("stockitemKey", s.getItemID());
//                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }
}
