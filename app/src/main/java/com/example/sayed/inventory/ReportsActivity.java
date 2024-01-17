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

import com.example.sayed.inventory.data.InventoryContract.DocumentsEntry;

public class ReportsActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

    private ReportsAdapter reportsAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        setTitle("Customers");

        FloatingActionButton fab = findViewById(R.id.fab_reports);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportsActivity.this, EditReportActivity.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.list_report);
        //View emptyView = findViewById(R.id.empty_view);
       //listView.setEmptyView(emptyView);
        reportsAdapter = new ReportsAdapter(this, null);
        listView.setAdapter(reportsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ReportsActivity.this, EditReportActivity.class);
                Uri currentProductionUri = ContentUris.withAppendedId(DocumentsEntry.CONTENT_URI_3, id);

                intent.setData(currentProductionUri);
                startActivity(intent);
            }
        });
        getLoaderManager().initLoader(0, null,this);
    }
        private void insertReports(){
            ContentValues values = new ContentValues();
            values.put(DocumentsEntry.CUSTOMER, "JONE");
            values.put(DocumentsEntry.PRODUCT, "Pepsi");
            values.put(DocumentsEntry.QUANTITY, 100);
            values.put(DocumentsEntry.DEBIT, 0);
            values.put(DocumentsEntry.CREDIT, 0);
            values.put(DocumentsEntry.REBATE, 0);
            values.put(DocumentsEntry.AMOUNT, 1000);
            values.put(DocumentsEntry.DATE, 2019);
            values.put(DocumentsEntry.NOTES, "VERY GOOD");

            //لاحظ ان مثد الادراج بترجع uri
            Uri newUri = getContentResolver().insert(DocumentsEntry.CONTENT_URI_3, values);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.insert_dummy_report:
                insertReports();
                //displayInfo();
                return true;
            case R.id.delete_all_reports:
                deleteAll();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void deleteAll() {

        int deletAll = getContentResolver().delete(DocumentsEntry.CONTENT_URI_3, null, null);
        Log.v("ReportsActivity", deletAll + " rows deleted from reports database");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                DocumentsEntry._ID,
                DocumentsEntry.CUSTOMER,
                DocumentsEntry.PRODUCT,
                DocumentsEntry.DEBIT,
                DocumentsEntry.REBATE,
                DocumentsEntry.AMOUNT,

        };
        return new android.content.CursorLoader(this,
                DocumentsEntry.CONTENT_URI_3,
                projection,
                null,
                null,
                null);
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        reportsAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        reportsAdapter.swapCursor(null);

    }
}
