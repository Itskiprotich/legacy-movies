package com.example.keeprawteach.legacydigital.Address;

import android.content.Context;
import android.database.Cursor;

public class Ip {
    Context context;
    String folder = "Legacy";
    String apk = "Apk";
    String images = "upload";
    Database sqLitedb;

    public Ip(Context context) {
        this.context = context;
        sqLitedb = new Database(context);
    }
    public String IpAddressFromSqlite() {

        String id = "1";

        Cursor cursor = sqLitedb.checkIp(id);

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String obtainedIp = cursor.getString(1);

                String ip = obtainedIp;
                if (ip != null) {
                    return ip;
                }

            }

        }

        return null;
    }

    public String movies() {

        String ip = "http://" + IpAddressFromSqlite() + "/" + folder + "/movies.php";

        if (ip != null) {

            return ip;
        }
        return null;
    }

    public String getimage() {

        String ip = "http://" + IpAddressFromSqlite() + "/" + folder + "/" + images + "/";

        if (ip != null) {

            return ip;
        }
        return null;
    }
}
