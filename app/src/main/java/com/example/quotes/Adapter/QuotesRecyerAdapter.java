package com.example.quotes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quotes.CopyListener;
import com.example.quotes.QutoeResponse;
import com.example.quotes.R;

import java.util.List;

public class QuotesRecyerAdapter extends RecyclerView.Adapter<QutoesViewholder> {

    Context context;
    List<QutoeResponse> list;
    CopyListener listener;

    public QuotesRecyerAdapter(Context context, List<QutoeResponse> list, CopyListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QutoesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QutoesViewholder(LayoutInflater.from(context).inflate(R.layout.list_quote, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QutoesViewholder holder, int position) {
        holder.textView_quotes.setText(list.get(position).getText());
        holder.textView_author.setText(list.get(position).getAuthor());
        holder.button_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCopyClicked(list.get(holder.getAdapterPosition()).getText());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

    class QutoesViewholder  extends RecyclerView.ViewHolder{

        TextView textView_quotes,textView_author;
        AppCompatButton button_copy;

        public QutoesViewholder(@NonNull View itemView){
            super(itemView);
            textView_quotes = itemView.findViewById(R.id.textView_quotes);
            textView_author = itemView.findViewById(R.id.textView_author);
            button_copy = itemView.findViewById(R.id.button_copy);
        }
}


