package com.codecool.tripplanner.destinationlist;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.data.Destination;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DestinationListAdapter extends RecyclerView.Adapter<DestinationListAdapter.DestViewHolder> {

    private final List<Destination> destList;
    private LayoutInflater layoutInflater;
    private Context context;
    private DestAdapterListener destAdapterListener;

    public DestinationListAdapter(List<Destination> destList, DestAdapterListener listener) {
        this.destList = destList;
        this.destAdapterListener = listener;
    }

    @NonNull
    @Override
    public DestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dest_list_item, parent, false);
        return new DestViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DestViewHolder holder, int position) {
        Destination current = destList.get(position);
        holder.title.setText(current.getDestinationName());

//Todo
        Picasso.get()
                .load(current.getUrl())
                .into(holder.imageView, new com.squareup.picasso.Callback(){

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                } );
    }

    @Override
    public int getItemCount() {
        return destList.size();
    }

    public class DestViewHolder extends RecyclerView.ViewHolder{

        final DestinationListAdapter adapter;
        final TextView title;
        final ImageView imageView;


        public DestViewHolder(@NonNull View itemView, DestinationListAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            title = itemView.findViewById(R.id.dest_title);
            imageView = itemView.findViewById(R.id.dest_image);

            title.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    destAdapterListener.displayDestinationOnMap(v, getAdapterPosition());
                }
            });
        }
    }

    public interface DestAdapterListener {
        void displayDestinationOnMap(View view, int position);
    }
}
