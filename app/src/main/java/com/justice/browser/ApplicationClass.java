package com.justice.browser;

import android.app.Application;
import android.os.Environment;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationClass extends Application {
    public static List<WebSite> allWebsitesList = new ArrayList<>();
    public static List<WebSite> choosenWebsitesList = new ArrayList<>();
    private static String path;




    public static void writeAllData() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path + "/choosenWebsiteData.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(choosenWebsitesList);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onCreate() {
        super.onCreate();

        createDirectory();
        readChoosenWebsiteList();
        putAllWebsiteData();
        if (choosenWebsitesList.isEmpty()) {
            putDummyData();

        }


    }

    public static void putAllWebsiteData() {
        allWebsitesList.add(new WebSite("facebook", "http://facebook.com", R.mipmap.facebook));
        allWebsitesList.add(new WebSite("google", "https://www.google.com/", R.mipmap.google));
        allWebsitesList.add(new WebSite("helb", "https://portal.helb.co.ke/auth/signin", R.mipmap.helb));
        allWebsitesList.add(new WebSite("instagram", "https://www.instagram.com/", R.mipmap.instagram));
        allWebsitesList.add(new WebSite("safaricom", "https://www.safaricom.co.ke/", R.mipmap.safaricom));
        allWebsitesList.add(new WebSite("youtube", "https://www.youtube.com/", R.mipmap.youtube2));
        allWebsitesList.add(new WebSite("twitter", "https://twitter.com/", R.mipmap.twitter));
        allWebsitesList.add(new WebSite("university of Nairobi", "https://www.uonbi.ac.ke/", R.mipmap.university_of_nairobi));

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        writeAllData();
    }

    private void putDummyData() {
        choosenWebsitesList.add(new WebSite("facebook", "http://facebook.com", R.mipmap.facebook));
        choosenWebsitesList.add(new WebSite("google", "https://www.google.com/", R.mipmap.google));
    }

    public void createDirectory() {
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/websiteData";

        File dir = new File(path);
        dir.mkdirs();

    }

    public void readChoosenWebsiteList() {
        try {
            FileInputStream fileInputStream = new FileInputStream(path + "/choosenWebsiteData.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            choosenWebsitesList = (ArrayList<WebSite>) objectInputStream.readObject();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
