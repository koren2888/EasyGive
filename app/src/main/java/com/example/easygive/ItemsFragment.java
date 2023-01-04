package com.example.easygive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easygive.model.Item;
import com.example.easygive.model.Model;

import java.util.List;

public class ItemsFragment extends Fragment {

    List<Item> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        items = Model.instance().getAllItems();
        RecyclerView list = view.findViewById(R.id.items_list);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemsRecyclerAdapter adapter = new ItemsRecyclerAdapter(getLayoutInflater(), items);
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(pos -> Navigation.findNavController(view)
                .navigate(R.id.action_global_itemFragment));
        return view;
    }
}