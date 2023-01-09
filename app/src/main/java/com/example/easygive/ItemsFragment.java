package com.example.easygive;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easygive.model.Item;
import com.example.easygive.model.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ItemsFragment extends Fragment {

    final String ANY_FILTER = "Any";

    List<Item> allItems;
    List<Item> itemsToShow;

    String typeFilter = "";
    String conditionFilter = "";
    String locationFilter = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        allItems = Model.instance().getAllItems();
        itemsToShow = Model.instance().getAllItems();

        RecyclerView list = view.findViewById(R.id.items_list);
        list.setHasFixedSize(true);
        DividerItemDecoration listDivider = new DividerItemDecoration(list.getContext(),
                DividerItemDecoration.VERTICAL);
        list.addItemDecoration(listDivider);

        list.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemsRecyclerAdapter adapter = new ItemsRecyclerAdapter(getLayoutInflater(), itemsToShow);
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(pos -> {
            NavDirections action = ItemsFragmentDirections.actionGlobalItemFragment(pos);
            Navigation.findNavController(view).navigate(action);
        });

        Spinner locationSpinner = view.findViewById(R.id.location_filter_spinner);
        Spinner typeSpinner = view.findViewById(R.id.type_filter_spinner);
        Spinner conditionSpinner = view.findViewById(R.id.condition_filter_spinner);

        locationSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(), R.array.location_array, android.R.layout.simple_spinner_item));
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                locationFilter = (String) adapterView.getItemAtPosition(i);
                UpdateItemListByFilter();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        typeSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(), R.array.types_array, android.R.layout.simple_spinner_item));
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeFilter = (String) adapterView.getItemAtPosition(i);
                UpdateItemListByFilter();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        conditionSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(), R.array.condition_array, android.R.layout.simple_spinner_item));
        conditionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                conditionFilter = (String) adapterView.getItemAtPosition(i);
                UpdateItemListByFilter();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    public void UpdateItemListByFilter()
    {
        itemsToShow.clear();
        itemsToShow.addAll(allItems);

        if(!typeFilter.equals(ANY_FILTER))
            itemsToShow.removeIf(item -> item.item_type != typeFilter);
        if(!locationFilter.equals(ANY_FILTER))
            itemsToShow.removeIf(item -> item.location != locationFilter);
        if(!conditionFilter.equals(ANY_FILTER))
            itemsToShow.removeIf(item -> item.condition != conditionFilter);
    }
}