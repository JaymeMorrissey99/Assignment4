package VH;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment4.R;

public class ReviewVH extends RecyclerView.ViewHolder {

    public TextView iN;
    public EditText review;
    public Button submitBtn;
    public RatingBar ratingBar;

    public ReviewVH(@NonNull View itemView) {
        super(itemView);
        iN = itemView.findViewById(R.id.iNAME);
        review = itemView.findViewById(R.id.reviewTxt);
        submitBtn = itemView.findViewById(R.id.submitreview);
        ratingBar = itemView.findViewById(R.id.ratingBar);
    }
}
