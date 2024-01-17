package com.example.sayed.inventory;

import android.app.LoaderManager;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sayed.inventory.data.InventoryContract;
import com.example.sayed.inventory.data.InventoryContract.SuppliersEntry;


public class Suppliers extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

    private SuppliersAdapter suppliersAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers);
        setTitle("Suppliers");

        FloatingActionButton fab = findViewById(R.id.fab_supplier);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Suppliers.this, EditSuppliers.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.list_Sup);
        //View emptyView = findViewById(R.id.empty_view);
        //listView.setEmptyView(emptyView);
        suppliersAdapter = new SuppliersAdapter(this, null);
        listView.setAdapter(suppliersAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Suppliers.this, EditSuppliers.class);
                Uri currentProductionUri = ContentUris.withAppendedId(SuppliersEntry.CONTENT_URI_4, id);

                intent.setData(currentProductionUri);
                startActivity(intent);
            }
        });
        getLoaderManager().initLoader(0, null,this);
    }

    private void insertSuppliers(){
        ContentValues values = new ContentValues();
        values.put(SuppliersEntry.SUPPLIER_NAME, "PRCM");
        values.put(SuppliersEntry.PRODUCT_NAME, "Product");
        values.put(SuppliersEntry.NUMBER_UNITS, 100);
        values.put(SuppliersEntry.DEBIT, 0);
        values.put(SuppliersEntry.CREDIT, 0);
        values.put(SuppliersEntry.REBATE, 0);
        values.put(SuppliersEntry.TOTAL_BALANCE, 1000);
        values.put(SuppliersEntry.DATE, 2019);
        values.put(SuppliersEntry.NOTES, "Notes for this Supplier");

        //لاحظ ان مثد الادراج بترجع uri
        Uri newUri = getContentResolver().insert(SuppliersEntry.CONTENT_URI_4, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_suppliers, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.insert_dummy_supplier:
                insertSuppliers();
                //displayInfo();
                return true;
            case R.id.delete_all_supplier:
                deleteAll();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void deleteAll() {

        int deletAll = getContentResolver().delete(SuppliersEntry.CONTENT_URI_4, null, null);
        Log.v("Suppliers", deletAll + " rows deleted from reports database");
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                SuppliersEntry._ID,
                SuppliersEntry.SUPPLIER_NAME,
                SuppliersEntry.PRODUCT_NAME,
                SuppliersEntry.CREDIT,
                SuppliersEntry.REBATE,
                SuppliersEntry.TOTAL_BALANCE,
                SuppliersEntry.DATE
        };
        return new android.content.CursorLoader(this,
                SuppliersEntry.CONTENT_URI_4,
                projection,
                null,
                null,
                null);
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        suppliersAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        suppliersAdapter.swapCursor(null);
    }
}
