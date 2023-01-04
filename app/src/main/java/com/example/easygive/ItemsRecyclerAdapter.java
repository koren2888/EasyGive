package com.example.easygive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easygive.model.Item;
import com.example.easygive.model.Model;

import java.util.List;


class ItemViewHolder extends RecyclerView.ViewHolder {

    TextView typeTv;
    TextView conditionTv;
    TextView locationTv;
    ImageView favoriteIcon;

    public ItemViewHolder(@NonNull View itemView,
                          ItemsRecyclerAdapter.OnItemClickListener listener) {
        super(itemView);

        typeTv = itemView.findViewById(R.id.itemRow_type_value);
        conditionTv = itemView.findViewById(R.id.itemRow_condition_value);
        locationTv = itemView.findViewById(R.id.itemRow_location_value);
        favoriteIcon = itemView.findViewById(R.id.itemRow_favorite_icon);

        itemView.setOnClickListener(view -> {
            int pos = getAdapterPosition();
            listener.onItemClick(pos);
        });
    }

    private void updateFavoriteIcon(Item item) {
        if (item.isFavorite) {
            favoriteIcon.setImageResource(R.drawable.full_heart_icon);
        } else {
            favoriteIcon.setImageResource(R.drawable.empty_heart_icon);
        }
    }

    public void bind(Item item) {
        typeTv.setText(item.item_type);
        conditionTv.setText(item.condition);
        locationTv.setText(item.location);
        updateFavoriteIcon(item);

        favoriteIcon.setOnClickListener(view -> {
            Model.instance().updateFavorites(item);
            updateFavoriteIcon(item);
        });
    }
}

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    OnItemClickListener listener;
    LayoutInflater inflater;
    List<Item> items;

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
    public ItemsRecyclerAdapter(LayoutInflater inflater, List<Item> items){
        this.inflater = inflater;
        this.items = items;
    }

    void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_row, parent, false);
        return new ItemViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}