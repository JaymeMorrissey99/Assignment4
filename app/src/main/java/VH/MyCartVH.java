package VH;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment4.R;

public class MyCartVH extends RecyclerView.ViewHolder {

    public TextView citemN, citemB, citemP, citemQuan, txtOption;
    public MyCartVH(@NonNull View itemView) {
        super(itemView);

        citemN = itemView.findViewById(R.id.cartitemNAME);
        citemB = itemView.findViewById(R.id.cartitemBRAND);
        citemP = itemView.findViewById(R.id.cartitemPRICE);
        citemQuan = itemView.findViewById(R.id.cartitemQ);
        txtOption = itemView.findViewById(R.id.textoption);

    }


}
