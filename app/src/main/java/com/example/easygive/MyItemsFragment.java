package com.example.easygive;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easygive.databinding.FragmentMyItemsBinding;
import com.example.easygive.model.Item;
import com.example.easygive.model.Model;

import java.util.List;

public class MyItemsFragment extends Fragment {

    FragmentMyItemsBinding binding;
    List<Item> items;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyItemsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        items = Model.instance().getMyItems();
        RecyclerView recyclerView = binding.list;
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration listDivider = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(listDivider);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MyItemsRecyclerAdapter adapter = new MyItemsRecyclerAdapter(getLayoutInflater(), items);
        recyclerView.setAdapter(adapter);

        binding.addButton.setOnClickListener(clickedView -> {
            Log.i("TAG", "In adding item");
        });

        return view;
    }
}