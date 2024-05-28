package com.example.thirdlaboratorywork;

import static com.example.thirdlaboratorywork.DatabaseHelper.COLUMN_ID;
import static com.example.thirdlaboratorywork.DatabaseHelper.COLUMN_NAME;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

@SuppressLint("Range")
public class DatabaseActivity extends ListActivity {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> data;

    private EditText editText;

    private Button saveChangeButton;
    private String selectedOldText;
    private Integer textId = -1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        db.execSQL("CREATE TABLE IF NOT EXISTS records (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);");

        editText = findViewById(R.id.editText);


        saveChangeButton = findViewById(R.id.onEditButtonClick);

        updateData();
        saveChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textId != -1) {
                    onEditButtonClick(textId, editText.getText().toString());
                    selectedOldText = editText.getText().toString();
                    updateData();
                    textId = -1;
                    saveChangeButton.setText("Добавить");
                    editText.setText("");
                } else {
                    addRecord(editText.getText().toString());
                }
            }
        });
        updateData();
        Log.e("TAF", "Selected" + data.get(0));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);

        registerForContextMenu(getListView());


    }

    private void addRecord(String record) {
        db.execSQL("INSERT INTO records (name) VALUES ('" + record + "');");
        updateData();
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private void updateData() {
        data = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM records", null);
        if (cursor.moveToFirst()) {
            do {
                data.add(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String selectedItem = (String) getListAdapter().getItem(position);
        Cursor cursor = db.rawQuery("SELECT _id FROM records WHERE name = ?", new String[]{selectedItem});
        if (cursor.moveToFirst()) {
            int itemId = cursor.getInt(cursor.getColumnIndex("_id"));
            selectedOldText = selectedItem;
            saveChangeButton.setText("Изменить");
            textId = itemId;
            Log.e("TAF", "edit->" + selectedItem);
            editText.setText(selectedItem);
            Toast.makeText(this, "ID: " + itemId, Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.delete) {
            deleteItem(info.position);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    private void deleteItem(int position) {
        String selectedItem = data.get(position);
        db.execSQL("DELETE FROM records WHERE name = ?", new String[]{selectedItem});
        updateData();
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    public void onEditButtonClick(Integer id, String text) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(COLUMN_NAME, text);
        String newName = text;
        String where = COLUMN_NAME + "=" + "'" + selectedOldText + "'" + "AND " + COLUMN_ID + "=" + "'" + id + "'";
        if (!newName.isEmpty()) {
            db.update("records", updatedValues, where, null);
            updateData();
            adapter.clear();
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
