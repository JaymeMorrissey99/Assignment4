package VH;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment4.R;

public class ReviewVH extends RecyclerView.ViewHolder {

    public TextView iN;

    public ReviewVH(@NonNull View itemView) {
        super(itemView);
        iN = itemView.findViewById(R.id.iNAME);

    }
}
