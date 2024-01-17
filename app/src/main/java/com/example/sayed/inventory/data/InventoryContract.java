package com.example.sayed.inventory.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


    //كلاس ثوابت قواعد البيانات يحتوي علي اسم الجدول واسماء الاعمدة في قواعد البيانات
public class InventoryContract {

    public InventoryContract() {}

    /**
     * //شكل مسار قاعدة بيانات تطبيق الجرد
     *         //# هي رقم الصف ف الجدول
     *     //content://com.example.sayed.inventory/inventory/#
     *     //content://com.example.sayed.inventory/customers/#
     *     //content://com.example.sayed.inventory/document/#
     *     //content://com.example.sayed.inventory/suppliers/#
     *     //content://com.example.sayed.inventory/stocks/#
     * */
    //سلطة المحتوي لتحديد url لقاعدة البيانات المراد التفاعل معها
    public static final String CONTENT_AUTHORITY = "com.example.sayed.inventory";

    //مثل ال url للويب اي انشأنا رابط للوصول لقاعدة البيانات
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //اسم قاعدة البيانات
    public static final String PATH_INVENTORY = "inventory";

    // اسم قاعدة البيانات2
    public static final String PATH_Customers = "customers";

        // اسم قاعدة البيانات3
    public static final String PATH_DOCUMENTS = "documents";

        // اسم قاعدة البيانات4
        public static final String PATH_SUPPLIERS = "suppliers";

        // اسم قاعدة البيانات4
        public static final String PATH_STOCKS = "stocks";

    //كل قاعدة بيانات او جدول لها كلاس خاص بها وب اعمدتها
    public static final class InventoryEntry implements BaseColumns {

        //مسار الوصول لقاعدة البيانات المنتجات
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of products.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single production.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        public final static String TABLE_NAME = "inventory";

        public final static String _ID = BaseColumns._ID;
        //0
        public final static String PRODUCTION = "production";
        //1
        public final static String QUANTITY = "quantity";
        //2
        public final static String WEIGHT = "weight";
        //3
        public final static String PRICE = "price";
        //4
        public final static String DEALER = "dealer";
        //5
        public final static String DESCRIPTION = "description";
        //6
        public final static String PHOTO = "image";
    }
    /////////////////////////////////////////////////////////////////////////////////

        public static final class CustomersEntry implements BaseColumns{

            //مسار الوصول لقاعدة البيانات العملاء
            public static final Uri CONTENT_URI_2 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_Customers);

            /**
             * The MIME type of the {@link #CONTENT_URI_2} for a list of Customers.
             */
            public static final String CONTENT_LIST_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_Customers;

            /**
             * The MIME type of the {@link #CONTENT_URI_2} for a single Customer.
             */
            public static final String CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_Customers;


            public final static String TABLE_NAME_2 = "customers";

            public final static String _ID = BaseColumns._ID;
            public final static String CUSTOMER_NAME = "name";
            public final static String CUSTOMER_ADDRESS = "address";
            public final static String CUSTOMER_PHONE = "phone";
            public final static String CUSTOMER_EMAIL = "email";
            public final static String CUSTOMER_NOTES = "notes";
        }
        public static final class DocumentsEntry implements BaseColumns{

            //مسار الوصول لقاعدة البيانات التقارير
            public static final Uri CONTENT_URI_3 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_DOCUMENTS);
            /**
             * The MIME type of the {@link #CONTENT_URI_3} for a list of Customers.
             */
            public static final String CONTENT_LIST_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DOCUMENTS;

            /**
             * The MIME type of the {@link #CONTENT_URI_3} for a single Customer.
             */
            public static final String CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DOCUMENTS;


            public final static String TABLE_NAME_3 = "documents";

            public final static String _ID = BaseColumns._ID;

            public final static String CUSTOMER = "customerName";
            public final static String PRODUCT = "productName";
            public final static String QUANTITY = "quantity";
            public final static String DEBIT = "debit";
            public final static String CREDIT = "credit";
            public final static String REBATE = "rebate";
            public final static String AMOUNT = "total";
            public final static String DATE = "date";
            public final static String NOTES = "notes";

        }
        public static final class SuppliersEntry implements BaseColumns{

            // مسار الوصول لقاعدة البيانات الموردين
            public static final Uri CONTENT_URI_4 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SUPPLIERS);
            /**
             * The MIME type of the {@link #CONTENT_URI_4} for a list of suppliers.
             */
            public static final String CONTENT_LIST_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SUPPLIERS;

            /**
             * The MIME type of the {@link #CONTENT_URI_4} for a single suppliers.
             */
            public static final String CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SUPPLIERS;


            public final static String TABLE_NAME_4 = "suppliers";

            public final static String _ID = BaseColumns._ID;
            public final static String SUPPLIER_NAME = "supplierName";
            public final static String PRODUCT_NAME = "productName";
            public final static String NUMBER_UNITS = "numberUnits";
            public final static String DEBIT = "debit";
            public final static String CREDIT = "credit";
            public final static String REBATE = "rebate";
            public final static String TOTAL_BALANCE = "totalBalance";
            public final static String DATE = "date";
            public final static String NOTES = "note";

        }
        public static final class StocksEntry implements BaseColumns{

            // مسار الوصول لقاعدة البيانات المخازن
            public static final Uri CONTENT_URI_5 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_STOCKS);
            /**
             * The MIME type of the {@link #CONTENT_URI_5} for a list of suppliers.
             */
            public static final String CONTENT_LIST_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STOCKS;

            /**
             * The MIME type of the {@link #CONTENT_URI_5} for a single suppliers.
             */
            public static final String CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STOCKS;


            public final static String TABLE_NAME_5 = "stocks";

            public final static String _ID = BaseColumns._ID;
            public final static String STOCK_NAME = "stockName";
            public final static String PRODUCT_NAME = "productName";
            public final static String QUANTITY = "quantity";
            public final static String UNIT_PRICE = "unitPrice$";
            public final static String SELLING_PRICE = "sellingPrice$";
            public final static String TOTAL = "stockedValue";

        }
}
