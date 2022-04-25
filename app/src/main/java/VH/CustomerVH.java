package VH;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment4.R;

public class CustomerVH extends RecyclerView.ViewHolder {

    public TextView cName, cID, cEmail, cShip;

    public CustomerVH(@NonNull View itemView) {
        super(itemView);

//        cID = itemView.findViewById(R.id.customerID);
        cName = itemView.findViewById(R.id.customerNAME);
        cEmail = itemView.findViewById(R.id.cEMAIL);
        cShip = itemView.findViewById(R.id.shipADDRESS);


    }
}
