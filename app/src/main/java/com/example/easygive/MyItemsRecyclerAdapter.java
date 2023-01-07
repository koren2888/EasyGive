package com.example.easygive;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easygive.databinding.FragmentMyItemsBinding;
import com.example.easygive.databinding.MyItemListRowBinding;
import com.example.easygive.model.Item;
import com.example.easygive.model.Model;

import java.util.List;


class MyItemsViewHolder extends RecyclerView.ViewHolder {

    MyItemListRowBinding binding;

    public MyItemsViewHolder(MyItemListRowBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    public void bind(Item item) {
        binding.typeValue.setText(item.item_type);
        binding.conditionValue.setText(item.condition);
        binding.locationValue.setText(item.location);
        binding.editButton.setOnClickListener(this::displayEditView);
        binding.deleteButton.setOnClickListener(this::displayDeleteView);
    }

    private void displayEditView(View view) {
        Log.i("TAG", "In Edit View");
    }

    private void displayDeleteView(View view) {
        Log.i("TAG", "In Delete View");
    }
}

public class MyItemsRecyclerAdapter extends RecyclerView.Adapter<MyItemsViewHolder> {

    LayoutInflater inflater;
    List<Item> items;

    public MyItemsRecyclerAdapter(LayoutInflater inflater, List<Item> items){
        this.inflater = inflater;
        this.items = items;
    }

    @NonNull
    @Override
    public MyItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyItemListRowBinding binding = MyItemListRowBinding.inflate(inflater, parent, false);
        return new MyItemsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemsViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}