package com.example.pamprojectkedua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RekomendasiWisataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WisataAdapter wisataAdapter;
    private List<Wisata> wisataList;
    private List<Wisata> savedWisataList;

    private ActivityResultLauncher<Intent> savedWisataLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekomendasi);

        recyclerView = findViewById(R.id.listRekomendasi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        wisataList = new ArrayList<>();
        savedWisataList = new ArrayList<>();

        //data dummy
        wisataList.add(new Wisata("Pantai Kuta", "Pantai dengan pasir putih", 4.9f, false, R.drawable.pantai_kuta));
        wisataList.add(new Wisata("Gunung Bromo", "Gunung berapi aktif yang terkenal", 4.7f, false, R.drawable.gunung_bromo));
        wisataList.add(new Wisata("Candi Borobudur", "Candi Buddha terbesar di dunia", 4.8f, false, R.drawable.candi_borobudur));

        wisataAdapter = new WisataAdapter(wisataList, new WisataAdapter.OnItemClickListener() {
            @Override
            public void onSimpanClick(Wisata wisata) {
                wisata.setSaved(true);
                if (!savedWisataList.contains(wisata)) {
                    savedWisataList.add(wisata);
                }
                wisataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDeleteClick(Wisata wisata) {
                wisata.setSaved(false);
                savedWisataList.remove(wisata);
                wisataAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(wisataAdapter);

        Button savedButton = findViewById(R.id.savedButton);
        savedButton.setOnClickListener(v -> {
            Intent intent = new Intent(RekomendasiWisataActivity.this, WisataTersimpanActivity.class);
            intent.putParcelableArrayListExtra("savedWisataList", new ArrayList<>(savedWisataList));
            savedWisataLauncher.launch(intent);
        });

        savedWisataLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        List<Wisata> updatedSavedWisataList = result.getData().getParcelableArrayListExtra("savedWisataList");
                        for (Wisata wisata : wisataList) {
                            wisata.setSaved(updatedSavedWisataList.contains(wisata));
                        }
                        savedWisataList = updatedSavedWisataList;
                        wisataAdapter.updateWisataList(wisataList);
                    }
                }
        );
    }
}