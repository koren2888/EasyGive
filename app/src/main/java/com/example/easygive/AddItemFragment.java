package com.example.easygive;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easygive.databinding.FragmentAddItemBinding;
import com.example.easygive.databinding.FragmentMyItemsBinding;
import com.example.easygive.model.Item;
import com.example.easygive.model.Model;

public class AddItemFragment extends Fragment {
    FragmentAddItemBinding binding;
    String locationFilter = "";
    String typeFilter = "";
    String conditionFilter = "";

    ActivityResultLauncher<Void> cameraLauncher;
    ActivityResultLauncher<String> galleryLauncher;

    Boolean didUploadPicture = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), result -> {
            if (result != null) {
                binding.itemImg.setImageBitmap(result);
                didUploadPicture = true;
            }
        });
        galleryLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {
            if (result != null){
                binding.itemImg.setImageURI(result);
                didUploadPicture = true;
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddItemBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Spinner locationSpinner = view.findViewById(R.id.location_filter_spinner);
        Spinner typeSpinner = view.findViewById(R.id.type_filter_spinner);
        Spinner conditionSpinner = view.findViewById(R.id.condition_filter_spinner);

        locationSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(), R.array.location_array, android.R.layout.simple_spinner_item));
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                locationFilter = (String) adapterView.getItemAtPosition(i);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.saveBtn.setOnClickListener(view1 -> {
            String title = binding.titleEt.getText().toString();
            String contactNum = binding.contactEt.getText().toString();
            Item newItem = new Item(title, typeFilter, conditionFilter, locationFilter, contactNum, "", false);

            if(didUploadPicture) {
                binding.itemImg.setDrawingCacheEnabled(true);
                binding.itemImg.buildDrawingCache();
                Bitmap itemImage = ((BitmapDrawable) binding.itemImg.getDrawable()).getBitmap();

                Model.instance().uploadImage(newItem.title, itemImage, url-> {
                    if(url != null) {
                        newItem.imageUrl = url.toString();
                        Model.instance().addItem(newItem, (unused) -> {
                            Navigation.findNavController(view1).popBackStack();
                        });
                    }
                });
            }
        });

        binding.cameraButton.setOnClickListener(view1->{
            cameraLauncher.launch(null);
        });

        binding.galleryButton.setOnClickListener(view1->{
            galleryLauncher.launch("media/*");
        });

        return view;
    }
}
