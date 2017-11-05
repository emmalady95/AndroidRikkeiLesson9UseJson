package com.example.emmalady.androidrikkeilesson9usejson.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emmalady.androidrikkeilesson9usejson.R;
import com.example.emmalady.androidrikkeilesson9usejson.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liz Nguyen on 05/11/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.viewHolder> {

    private OnItemClickListener mlistener;

    public List<Item> itemList = new ArrayList<>();

    public interface OnItemClickListener{
        public void onItemClick(Item item, int position);
    }
    public ItemAdapter(List<Item> userContact){
        this.itemList = userContact;
    }
    public ItemAdapter(List<Item> userContact, OnItemClickListener listener) {
        this.itemList = userContact;
        this.mlistener = listener;
    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_main, parent, false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.tvID.setText(item.getId());
        holder.tvTitle.setText(item.getTitle());

        //Item Click
        holder.bind(item, mlistener);
    }

    @Override
    public int getItemCount() {
        return null!= itemList ? itemList.size() : 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private TextView tvID;
        private  TextView tvTitle;

        public viewHolder(View itemView) {
            super(itemView);
            tvID = (TextView) itemView.findViewById(R.id.tvId);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
        public void bind(final Item item, final OnItemClickListener onItemClickListener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    onItemClickListener.onItemClick(item, adapterPosition);
                }
            });
        }
    }
}
