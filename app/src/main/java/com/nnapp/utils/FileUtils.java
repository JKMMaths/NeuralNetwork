package com.nnapp.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    public static String getPath(Context context, Uri uri) {
        String[] projection = {OpenableColumns.DISPLAY_NAME};
        try (Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                String fileName = cursor.getString(nameIndex);
                return getCacheDir(context) + "/" + fileName;
            }
        }
        return null;
    }

    public static String getFileName(Context context, Uri uri) {
        String[] projection = {OpenableColumns.DISPLAY_NAME};
        try (Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                return cursor.getString(nameIndex);
            }
        }
        return "unknown_file";
    }

    public static String getCacheDir(Context context) {
        File cacheDir = new File(context.getCacheDir(), "nnapp_files");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir.getAbsolutePath();
    }

    public static File copyFileToCache(Context context, Uri uri) throws IOException {
        String fileName = getFileName(context, uri);
        File cacheFile = new File(getCacheDir(context), fileName);
        
        try (InputStream inputStream = context.getContentResolver().openInputStream(uri);
             OutputStream outputStream = new FileOutputStream(cacheFile)) {
            
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        
        return cacheFile;
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static File getExternalStorageDir(String dirName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), dirName);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return null;
            }
        }
        return file;
    }
}
