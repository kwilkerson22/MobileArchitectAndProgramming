package com.example.kennethwilkersonproject2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.ViewHolder> {

    private ArrayList<String> weightEntries; // List for holding weight entries

    // Constructor to initialize the adapter with the data
    public WeightAdapter(ArrayList<String> weightEntries) {
        this.weightEntries = weightEntries;
    }

    // Called whenever a new ViewHolder needs to be created
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate a simple list layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view); // Return a new ViewHolder
    }

    // Called to display data at a specific position
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Bind the weight entry to the TextView
        holder.weightText.setText(weightEntries.get(position));
    }

    // Gets the total number of items in the list
    @Override
    public int getItemCount() {
        return weightEntries.size();
    }

    // ViewHolder class to hold the layout for each item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView weightText; // TextView to display the weight
        public ViewHolder(View view) {
            super(view);
            weightText = view.findViewById(android.R.id.text1); // Find TextView inside the layout
        }
    }
}
