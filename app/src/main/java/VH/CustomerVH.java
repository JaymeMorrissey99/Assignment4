package VH;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerVH extends RecyclerView.ViewHolder {

    public TextView cName, cID, cEmail, cShip;

    public CustomerVH(@NonNull View itemView) {
        super(itemView);


    }
}
