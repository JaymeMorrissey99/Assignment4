package Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment4.AdminMain;
import com.example.assignment4.AdminStock;
import com.example.assignment4.EditStock;
import com.example.assignment4.R;

import java.util.List;

import Models.Stock;
import VH.StockVH;

public class StockAdaptor extends RecyclerView.Adapter<StockVH> {

    private Context mContext;
    private List<Stock> stockList;

    public StockAdaptor(Context mContext, List<Stock> stockList) {
        this.mContext = mContext;
        this.stockList = stockList;
    }

    @NonNull
    @Override
    public StockVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.stock_item, parent, false);
        return new StockVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StockVH holder, int position) {

        Stock s = stockList.get(position);
        holder.itemN.setText(s.getItemName());
        holder.itemB.setText(s.getManufacturer());
        holder.itemP.setText(s.getPrice());
        holder.itemQuan.setText(s.getQuantity());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditStock.class);
                intent.putExtra("stockitemKey", s.getItemID());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }
}
