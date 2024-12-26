package com.example.pamprojectkedua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class WisataTersimpanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WisataAdapter wisataAdapter;
    private List<Wisata> savedWisataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_tersimpan);

        savedWisataList = getIntent().getParcelableArrayListExtra("savedWisataList");
        if (savedWisataList == null) {
            savedWisataList = new ArrayList<>();
        }

        recyclerView = findViewById(R.id.recyclerViewReviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wisataAdapter = new WisataAdapter(savedWisataList, new WisataAdapter.OnItemClickListener() {
            @Override
            public void onSimpanClick(Wisata wisata) {
            }

            @Override
            public void onDeleteClick(Wisata wisata) {
                savedWisataList.remove(wisata);
                wisata.setSaved(false);
                wisataAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(wisataAdapter);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            setResultAndFinish();
        });
    }

    public void onDeleteButtonClick(View view) {
        for (Wisata wisata : savedWisataList) {
            wisata.setSaved(false);  // Unmark as saved
        }
        savedWisataList.clear();
        wisataAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResultAndFinish();
    }

    private void setResultAndFinish() {
        Intent resultIntent = new Intent();
        resultIntent.putParcelableArrayListExtra("savedWisataList", new ArrayList<>(savedWisataList));
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}