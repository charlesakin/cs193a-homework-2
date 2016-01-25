package com.example.charlesakin_david.homework2to_dolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list);
        ListView itemList = (ListView) findViewById(R.id.item_list);
        itemList.setAdapter(adapter);
        itemList.setOnItemLongClickListener(this);
    }

    /*
     * This method saves the state of our ArrayList when the
     * device is flipped from portrait to landscape
     */
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("list", list);
    }

    /*
     * This method resets 'list' to the saved state of list
     * before the device was flipped. The adapter is also
     * reset to the new list and the ListView is set to the
     * new adapter. Finally we notify the adapter that the
     * list has changed.
     */
    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        list = inState.getStringArrayList("list");
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list);
        ListView itemList = (ListView) findViewById(R.id.item_list);
        itemList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /*
     * This method is called when the 'Add' button is clicked.
     * It grabs the text from the text field and places it in
     * our ArrayList, 'list'. The adapter is notified of the
     * change in the list and the text field is reset to be empty.
     */
    public void addItem(View view) {
        EditText text  = (EditText) findViewById(R.id.input);
        Toast.makeText(this, "You entered: "+text.getText().toString(), Toast.LENGTH_SHORT).show();
        list.add(text.getText().toString());
        adapter.notifyDataSetChanged();
        text.setText("");
    }

    /*
     * This method deletes an item from the list if the user
     * clicks on the item for a "long" period. We get the index
     * of the item that was clicked on and remove it from our 'list'.
     * We then notify the adapter of the change in our list.
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int index, long id) {
        Toast.makeText(this, "You removed: "+list.get(index), Toast.LENGTH_SHORT).show();
        list.remove(index);
        adapter.notifyDataSetChanged();
        return false;
    }
}
