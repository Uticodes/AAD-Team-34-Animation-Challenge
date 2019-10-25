package com.example.tekquotez.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tekquotez.R;
import com.example.tekquotez.model.Quote;
import com.example.tekquotez.repository.RoomDb;

import java.util.List;

public class QuoteRecyclerAdapter extends RecyclerView.Adapter<QuoteRecyclerAdapter.ViewHolder> {

    List<Quote> quoteList;
    Context context;
    RoomDb roomDb;

    public QuoteRecyclerAdapter(List<Quote> quoteList, Context context,RoomDb roomDb) {
        this.quoteList = quoteList;
        this.context = context;
        this.roomDb = roomDb;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_single_quote,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Quote quote = quoteList.get(position);
        holder.author.setText(quote.getAuthor());
        holder.quoteText.setText(quote.getQuote());
        holder.deletebtn.setOnClickListener(view -> {
            deleteQuote(quote);
            updateList(position);

        });
    }

    private void deleteQuote(Quote currentQuote) {

        currentQuote.setIsFavourite(0);
        roomDb.quoteDao().updateQuote(currentQuote);
    }

    private void updateList(int position){
        quoteList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public  View view;
        public TextView author;
        public TextView quoteText;
        public ImageButton deletebtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            author = view.findViewById(R.id.quote_author);
            quoteText = view.findViewById(R.id.quote_text);
            deletebtn = view.findViewById(R.id.delete_quote);


        }
    }

    public void updateList(List<Quote> quoteList){
        this.quoteList = quoteList;
        notifyDataSetChanged();
    }
}
