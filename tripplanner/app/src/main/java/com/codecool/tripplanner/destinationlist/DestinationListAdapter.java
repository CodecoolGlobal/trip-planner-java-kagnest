package com.codecool.tripplanner.destinationlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.data.Destination;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DestinationListAdapter extends RecyclerView.Adapter<DestinationListAdapter.DestViewHolder> {

    private final List<Destination> destList;
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

        Picasso.get()
                .load(current.getUrl())
                .error(R.drawable.no_image)
                .resizeDimen(R.dimen.image_width, R.dimen.image_height)
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return destList.size();
    }

    public class DestViewHolder extends RecyclerView.ViewHolder {

        final DestinationListAdapter adapter;
        @BindView(R.id.dest_title)
        TextView title;
        @BindView(R.id.dest_image)
        ImageView imageView;


        public DestViewHolder(@NonNull View itemView, DestinationListAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            ButterKnife.bind(this, itemView);

            title.setOnClickListener(new View.OnClickListener() {

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
