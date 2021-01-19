package com.example.todosimple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//responible for taking the data at a particular position and putting it in the view holder
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items= items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflator to inflate a view

       View todoView= LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        //wrap it inside a view holder and return it
        return new ViewHolder(todoView);
    }
    // RESPONSIBLE FOR BINDING DATA TO A PARTICULAR VIEW HOLDER
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //grab item at position
        String item = items.get(position);
        //bind the item into the specified view holder
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //provide access to each row in list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //update view inside of the view holder with this data of string item
        public void bind(String item) {
        tvItem.setText(item);
        tvItem.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                longClickListener.onItemLongClicked(getAdapterPosition());
                return true;
            }
        });
        }

    }

}
