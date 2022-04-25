package VH;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment4.R;

public class StockVH extends RecyclerView.ViewHolder {

    public TextView itemN, itemB, itemP, itemQuan;
    public StockVH(@NonNull View itemView) {
        super(itemView);

        itemN = itemView.findViewById(R.id.itemNAME);
        itemB = itemView.findViewById(R.id.itemBRAND);
        itemP = itemView.findViewById(R.id.itemPRICE);
        itemQuan = itemView.findViewById(R.id.itemQ);
    }
}
