package com.example.thirdlaboratorywork;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.List;
//
//public class MyAdapter extends ArrayAdapter<String> implements DatabaseObserver {
//
//    private List<String> itemsList;
//    private View.OnClickListener listener;
//
//    private int isEditState = 0;
//    public MyAdapter(Context context, List<String> data, View.OnClickListener listener) {
//        super(context, R.layout.list_item_layout, data);
//        this.itemsList = data;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.list_item_layout, parent, false);
//        Button editButton = view.findViewById(R.id.editButton);
//        TextView textView = view.findViewById(R.id.item_text);
//        textView.setText(itemsList.get(position));
//
//        editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isEditState == 0) {
//                    isEditState = 1;
//                    ((DatabaseActivity) getContext()).setName((String) textView.getText());
//                    editButton.setText("Сохранить");
//                } else if (isEditState == 1) {
//                    editButton.setText("Редактировать");
//                    isEditState = 0;
//                    editButton.setText("Сохранить");
//                    String selectedItem = textView.getText().toString();
//                    ((DatabaseActivity) getContext()).saveChanges(selectedItem);
//                }
//            }
//        });
//        return view;
//    }
//
//    @Override
//    public void onDataChanged() {
//        clear();
//        addAll(itemsList);
//        notifyDataSetChanged();
//    }
//}
