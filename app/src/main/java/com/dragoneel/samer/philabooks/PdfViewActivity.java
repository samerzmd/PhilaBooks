package com.dragoneel.samer.philabooks;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.pdfview.PDFView;

import java.io.File;

public class PdfViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        setupActionBar(true, "كتاب");

        PDFView pdfView= (PDFView) findViewById(R.id.pdfview);

        String myPath = getIntent().getExtras().getString("path");
        pdfView.fromFile(new File(myPath))
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .load();

    }

}
