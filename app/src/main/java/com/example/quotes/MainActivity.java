package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quotes.Adapter.QuotesRecyerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CopyListener{
    RecyclerView recyclerView;
    RequestManager manager;
    QuotesRecyerAdapter adapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        manager = new RequestManager(this);
        manager.GetALlQutoes(listener);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();
    }

    private final QuoteResponseListener listener = new QuoteResponseListener() {
        @Override
        public void didFetch(List<QutoeResponse> responses, String message) {
            showData(responses);
            dialog.dismiss();
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();

        }
    };

    private void showData(List<QutoeResponse> responses) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        adapter = new QuotesRecyerAdapter(MainActivity.this, responses, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCopyClicked(String text) {

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("Copies data", text);
        clipboardManager.setPrimaryClip(data);
        Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
    }
}