package com.example.easygive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.easygive.model.Item;
import com.example.easygive.model.Model;

import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    ItemsFragment itemsFragment;
    Fragment inDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        itemsFragment = new ItemsFragment();
        displayFragment(itemsFragment);
    }

    private void displayFragment(Fragment frag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tran = manager.beginTransaction();
        tran.add(R.id.main_frag_container, frag);
        if (inDisplay != null) tran.remove(inDisplay);
        tran.addToBackStack("TAG");
        tran.commit();
        inDisplay = frag;
    }
}