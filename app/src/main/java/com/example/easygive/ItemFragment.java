package com.example.easygive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import com.example.easygive.databinding.FragmentItemBinding;
import com.example.easygive.model.Item;
import com.example.easygive.model.Model;

public class ItemFragment extends Fragment {

    FragmentItemBinding binding;
    Item item;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentItemBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        int itemPos = ItemFragmentArgs.fromBundle(getArguments()).getItemPos();
        item = Model.instance().getItem(itemPos);

        binding.typeValue.setText(item.item_type);
        binding.conditionValue.setText(item.condition);
        binding.locationValue.setText(item.location);
        binding.contactValue.setText(item.contact);
        updateFavoriteIcon();

        binding.favoriteIcon.setOnClickListener(view1 -> {
            Model.instance().updateFavorites(item);
            updateFavoriteIcon();
        });

        return view;
    }

    private void updateFavoriteIcon() {
        if (item.isFavorite) {
            binding.favoriteIcon.setImageResource(R.drawable.full_heart_icon);
        } else {
            binding.favoriteIcon.setImageResource(R.drawable.empty_heart_icon);
        }
    }
}