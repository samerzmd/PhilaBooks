package com.dragoneel.samer.philabooks;

import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.etsy.android.grid.StaggeredGridView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by SamerGigaByte on 12/12/2015.
 */
public class Utilities {
    static ArrayList<File>pdfFiles=new ArrayList<File>();
    public static File[] walkdir(File dir) {
        String pdfPattern = ".pdf";

        File listFile[] = dir.listFiles();

        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    walkdir(listFile[i]);
                } else {
                    if (listFile[i].getName().endsWith(pdfPattern)){
                        pdfFiles.add(listFile[i]);
                    }
                }
            }
        }
        File[] va=new File[pdfFiles.size()];
        for (int i=0;i<pdfFiles.size();i++) {
            va[i]=pdfFiles.get(i);
        }
        return va;
    }

    public static File[] getPdfFiles(){
        return walkdir(Environment.getExternalStorageDirectory());
    }

    public static void setListViewHeightBasedOnChildren(StaggeredGridView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        View listItem = listAdapter.getView(0, null, listView);
        totalHeight=(listAdapter.getCount()%2==0)?totalHeight:totalHeight+listItem.getMeasuredHeight();
        params.height = totalHeight/2+500;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
