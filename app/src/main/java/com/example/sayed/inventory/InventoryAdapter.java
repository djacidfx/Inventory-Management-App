package com.example.sayed.inventory;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.sayed.inventory.data.InventoryContract;


public class InventoryAdapter extends CursorAdapter{


    private TextView nameView;
    private TextView priceView;
    private ImageView imageView;
    private SearchView searchView;

    public InventoryAdapter(Context context, Cursor c) {
        super(context, c); }

    public InventoryAdapter(Context context, Cursor c, SearchView searchView) {
        super(context, c, false);
        this.searchView = searchView;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {


        //نعريف الوجهات التي مهتمين بعرضها ع الشاشة
        nameView = view.findViewById(R.id.name_production);
        priceView = view.findViewById(R.id.price_price);
        imageView = view.findViewById(R.id.image);
        //ImageButton imageButton = view.findViewById(R.id.image_view);

        //الوصول لاعمدة الوجهات المهتمين بيها من المؤشر
        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.PRODUCTION);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.PRICE);
        int imgColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.PHOTO);

        //الحصول علي قيم اعمدة الوجهات المهتمين بيها من المؤشر
        String name = cursor.getString(nameColumnIndex);
        String price = cursor.getString(priceColumnIndex);
        String img = cursor.getString(imgColumnIndex);

        Uri path = Uri.parse(img);



        //تمرير القيم للوجهات
        nameView.setText(name);
        priceView.setText(price);
        imageView.setImageURI(path);

    }
}
