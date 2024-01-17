package com.example.sayed.inventory;
import com.example.sayed.inventory.data.InventoryContract.InventoryEntry;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import com.example.sayed.inventory.data.InventoryHelper;

public class InventoryActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final String LOG_TAG = InventoryHelper.class.getSimpleName();


    /** Identifier for the production data loader */
    private static final int Inventory_LOADER = 0;
    private InventoryAdapter inventoryAdapter;
    private GridView gridView;
    private SearchView searchView;
    ImageView imagPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InventoryActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        gridView = findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        gridView.setEmptyView(emptyView);
        inventoryAdapter = new InventoryAdapter(this, null);
        gridView.setAdapter(inventoryAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(InventoryActivity.this, EditActivity.class);

                Uri currentProductionUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, id);

                intent.setData(currentProductionUri);
                startActivity(intent);
            }
        });
        imagPrev = findViewById(R.id.empty_shelter_image);
        getLoaderManager().initLoader(Inventory_LOADER, null,this);
    }
    private void insertProduction(){
        Uri path = Uri.parse("android.resource://com.example.sayed.inventory/" + R.drawable.preview);
        String imgPath = path.toString();
        //هذا الكلاس يقوم بتخزين البيانات ف قواعد البيانات في صورة ازواج (مفتاح-قيمة)
        // اهميتة انه سياخذ القيمة مننا ويخرنها في الجدول للمفتاح المعين
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.PRODUCTION, "Camera");
        values.put(InventoryEntry.QUANTITY, 10);
        values.put(InventoryEntry.WEIGHT, 10);
        values.put(InventoryEntry.PRICE, 100);
        values.put(InventoryEntry.DEALER, "USA");
        values.put(InventoryEntry.DESCRIPTION, "description the product ");
        values.put(InventoryEntry.PHOTO, imgPath);

        //لاحظ ان مثد الادراج بترجع uri
        Uri newUri = getContentResolver().insert(InventoryEntry.CONTENT_URI, values);
    }
    private void deleteAll() {

        int deletAll = getContentResolver().delete(InventoryEntry.CONTENT_URI, null, null);
        Log.v("InventoryActivity", deletAll + " rows deleted from production database");
    }
    private SearchView.OnQueryTextListener onQueryTextListener =
            new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Cursor contacts = getListOfContactNames(query);
                    String[] cursorColumns = {InventoryEntry.PRODUCTION, InventoryEntry.PRICE,InventoryEntry.PHOTO};
                    int[] viewIds = {R.id.name_production,
                    R.id.price_price, R.id.image};
                    SimpleCursorAdapter simpleCursorAdapter  = new SimpleCursorAdapter(
                            InventoryActivity.this,
                            R.layout.list_item,
                            contacts,
                            cursorColumns,
                            viewIds,
                            0);
                    gridView.setAdapter(simpleCursorAdapter);
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    Cursor contacts = getListOfContactNames(newText);
                    inventoryAdapter = new InventoryAdapter(InventoryActivity.this,
                            contacts, searchView);
                    searchView.setSuggestionsAdapter(inventoryAdapter);
                    return true;
                }
            };
    //////////////////////////////
    private Cursor getListOfContactNames(String searchText) {
        Cursor cursor;
        ContentResolver contentResolver = getContentResolver();
        String[] projection = {InventoryEntry._ID,
                InventoryEntry.PRODUCTION,
                InventoryEntry.PRICE,
                InventoryEntry.PHOTO};

        Uri uri = InventoryEntry.CONTENT_URI;
        String selection = InventoryEntry.PRODUCTION + " Like ?";
        String[] selectionArgs =  new String[]{"%"+ searchText +"%"};

        cursor = contentResolver.query(uri, projection, selection, selectionArgs, null);
        return cursor;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inventory, menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(onQueryTextListener);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.insert_dummy_data:
                insertProduction();
                //displayInfo();
                return true;
            case R.id.delete_all_data:
                deleteAll();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.PRODUCTION,
                InventoryEntry.PRICE,
                InventoryEntry.PHOTO
        };
        return new android.content.CursorLoader(this,
                InventoryEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        inventoryAdapter.swapCursor(data);
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        inventoryAdapter.swapCursor(null);
    }
}
