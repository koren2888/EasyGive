package com.example.easygive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easygive.model.Item;
import com.example.easygive.model.Model;

import java.util.List;

public class FavoritesFragment extends Fragment {

    List<Item> items;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorites, container, false);
        items = Model.instance().getFavorites();
        RecyclerView list = view.findViewById(R.id.favorites_list);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemsRecyclerAdapter adapter = new ItemsRecyclerAdapter(getLayoutInflater(), items);
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(pos -> {
            NavDirections action = FavoritesFragmentDirections.actionGlobalItemFragment(pos);
            Navigation.findNavController(view).navigate(action);
        });
        return view;
    }
}