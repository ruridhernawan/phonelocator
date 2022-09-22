package com.example.phonelocator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonelocator.model.ResponseModel;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    // Initial variable
    ArrayList<String> arrayList ;
    ItemClickListener itemClickListener ;
    int selectedPosition = 0 ;

    //Create Constructor

    public MainAdapter(ArrayList<String> arrayList, ItemClickListener itemClickListener) {
        this.arrayList = arrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inisualisaisi view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main,parent,false);
        //Pass holder view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
// set text on radio
        System.out.println(holder);
        holder.radioButton.setText(arrayList.get(position));
        System.out.println(holder);

        //checked selected radio button
        holder.radioButton.setChecked(position==selectedPosition);

        //set listener on radio button
        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // When Checked
                    //Update selected position
                    selectedPosition = holder.getAdapterPosition();
                    ResponseModel.deviceId = ResponseModel.devices[selectedPosition].deviceId;
                    //call listener
                    itemClickListener.onClick(holder.radioButton.getText().toString());
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        //pass position
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        //pass position
        return position;
    }

    @Override
    public int getItemCount() {
        // pass total list size
        return arrayList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
// Initial variable
        RadioButton radioButton ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // asign variable
            radioButton = itemView.findViewById(R.id.radio_button);
        }
    }
}
