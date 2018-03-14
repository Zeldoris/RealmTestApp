package adapters.viewholdres;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.dell.firstapp.R;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Movie;

/**
 * Created by DELL on 11/03/2018.
 */

public class MoviesViewHolder extends RecyclerView.ViewHolder {

    private CircleImageView circleImageView;
    private TextView tv_nom;
    private TextView tv_realisateur;
    private TextView tv_dateSortie;

    public MoviesViewHolder(View itemView) {
        super(itemView);

        circleImageView = itemView.findViewById(R.id.circleImageView_movie);
        tv_nom = itemView.findViewById(R.id.textView_name);
        tv_realisateur = itemView.findViewById(R.id.textView_realisateur);
        tv_dateSortie = itemView.findViewById(R.id.textView_date_sortie);
    }

    public void display(Movie movie) {
        if (movie.getUrlImage() != null && !TextUtils.isEmpty(movie.getUrlImage())) {
            try {
                Uri uri = Uri.parse(movie.getUrlImage().trim());
                circleImageView.setImageURI(uri);
            } catch (Exception e) { e.printStackTrace(); }
        } else
            circleImageView.setImageResource(R.drawable.empty);

        tv_nom.setText(movie.getNom());
        tv_realisateur.setText(movie.getRealisateur());
        tv_dateSortie.setText(movie.getDateSortie());
    }
}
