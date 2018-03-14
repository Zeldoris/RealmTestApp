package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.firstapp.R;

import adapters.viewholdres.MoviesViewHolder;
import io.realm.RealmList;
import model.Movie;

/**
 * Created by DELL on 11/03/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder> {

    private Context context;
    private RealmList<Movie> movies;

    public MoviesAdapter(Context context, RealmList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.content_activity_main, parent, false);

        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.display(movie);
    }
}
