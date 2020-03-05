package com.amey.quotationproject;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.snackbar.Snackbar;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfLister;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.List;
import java.util.Locale;

import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Element.ALIGN_LEFT;
import static com.itextpdf.text.Element.ALIGN_RIGHT;

public class NewWindow extends AppCompatActivity {
    private static final String MY_PREFS_NAME = "clientList";


    //Fonts
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 18,
            Font.BOLD);



    private static Font tableTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.BOLDITALIC, BaseColor.RED);


    private static Font clientFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    private static Font ttltag = new Font(Font.FontFamily.TIMES_ROMAN, 13,
            Font.BOLD, BaseColor.RED);

    private static Font endFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);
    private static Font endNoteFont = new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC);


    private static Font roomTitle = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLDITALIC);





    private static final int STORAGE_CODE = 1000;







    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter mRecyclerAdapter;


    private DBManager dbManager;


    ProgressDialog progressDialog;





    private ItemTouchHelper itemTouchHelper;





    EditText etTitle, etDesc, etAmount;
    Button btnAddItem, savePdf, savedocbtn;
    ImageView img;


    CoordinatorLayout coordinatorLayout;






    ArrayList<Integer> total = new ArrayList<>();

    ArrayList<RecyclerData> myList = new ArrayList<>();
    String title = "", desc = "", amount = "";






    itemPOJO itemPOJO = new itemPOJO();

    totalPOJO totalPOJO = new totalPOJO();












    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.doc_edit_layout);

//        try
//        {
//            this.getSupportActionBar().hide();
//        }
//        catch (NullPointerException e){}
//
//        setContentView(R.layout.doc_edit_layout);
//    }




        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerAdapter =  new RecyclerAdapter(myList);





        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);



        mRecyclerView.setLayoutManager(layoutManager);


        mRecyclerView.setAdapter(mRecyclerAdapter);






        etTitle = (EditText) findViewById(R.id.etTitle);
        etDesc = (EditText) findViewById(R.id.etDesc);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        savePdf = (Button) findViewById(R.id.pdfbtn);
        savedocbtn = (Button) findViewById(R.id.savebtn);

        coordinatorLayout = findViewById(R.id.coordinator);


        mRecyclerAdapter = new RecyclerAdapter(myList);



        mRecyclerView.setAdapter(mRecyclerAdapter);













        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {


                final int position = viewHolder.getAdapterPosition();



                myList.remove(position);
                mRecyclerAdapter.notifyItemRemoved(position);




                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);

                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                   // myList.add(position);



                    mRecyclerAdapter.notifyItemInserted(position);

                    }
                });

                snackbar.show();


            }
        };


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);




        dbManager = new DBManager(this);
        dbManager.open();















        btnAddItem.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                 desc = etDesc.getText().toString();
                amount = etAmount.getText().toString();


                RecyclerData mLog = new RecyclerData();
                mLog.setTitle(title);
                mLog.setDescription(desc);
                mLog.setAmount(amount);

                myList.add(mLog);

//
                etTitle.setText("");
                etDesc.setText("");
                etAmount.setText("");


                mRecyclerAdapter.notifyDataSetChanged();
//                enableSwipeToDeleteAndUndo();




            }

        });


// SAVE IN DATABASE:::::::::::::::::

        savedocbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                SharedPreferences result = getSharedPreferences("clientList", MODE_PRIVATE);

                String projName = result.getString("clientName","");
                String projLoc = result.getString("clientLoc", "");
                String projSubject = result.getString("clientSub","Quotation for interior work");
                String projContact = result.getString("clientContact", "");
                String projRoom = result.getString("clientRoom", "");
                String projEmail = result.getString("clientEmail", "");

                Log.e("#Secure Get Data", "client info to database"+projName);



                final String dbname = projName;
                final String dblocation =projLoc;
                final String dbcontact = projContact;
                final String dbsubject = projSubject;
                final String dbroom = projRoom;
                final String  dbemail = projEmail;


                dbManager.insert(dbname, dblocation,dbcontact,dbsubject,dbroom,dbemail);

                Log.e("db", "client info added to database ");













                for (int i = 0; i < myList.size(); i++){
                     String mItem = myList.get(i).getTitle();
                     String mQty = myList.get(i).getDescription();
                     String mAmt = myList.get(i).getAmount();

                     final String db_item_name = mItem;
                     final String db_item_qty = mQty;
                     final String db_item_rate = mAmt;




                     dbManager.insertItems(db_item_name,db_item_qty,db_item_rate);

                    Toast.makeText(
                            NewWindow.this,
                            "database created.",
                            Toast.LENGTH_LONG
                    ).show();

                }





            }
        });















        savePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String permissions[] = {(Manifest.permission.WRITE_EXTERNAL_STORAGE)};
                        requestPermissions(permissions, STORAGE_CODE);
                    } else {
                        // permision already granted
                        savePdf();
                    }

                } else {
                    // system os < marshmellow .. runtime permision
                    savePdf();
                }




            }
        });

    }
//    private void runAnimationAgain() {
//
//        final LayoutAnimationController controller =
//                AnimationUtils.loadLayoutAnimation(this, animationList[i]);
//
//        mRecyclerView.setLayoutAnimation(controller);
//        mRecyclerAdapter.notifyDataSetChanged();
//        mRecyclerView.scheduleLayoutAnimation();
//
//    }
//
















    private int savePdf() {


        progressDialog = new ProgressDialog(NewWindow.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Exporting PDF...");
        progressDialog.setTitle("Quotation");
        progressDialog.show();




        SharedPreferences result = getSharedPreferences("clientList", MODE_PRIVATE);

        String projName = result.getString("clientName","");
        String projLoc = result.getString("clientLoc", "");
        String projSubject = result.getString("clientSub","Quotation for interior work");
        String projContact = result.getString("clientContact", "");
        String projRoom = result.getString("clientRoom", "");
        String projEmail = result.getString("clientEmail", "");
        String projNotes = result.getString("clientNotes", "a) Plumbing fixtures / Hardware (door, latch, handles, fittings etc.)/ Electrical fixtures (lamps, fans, regulators etc. will be provided by client." +
                "\nb) Electrical main line shifting/changing M.C.B fixing charges will be extra " +
                "\nc) Lamination basic rate is 1200/00 per sheet." +
                "\nd) We will require 7 days to start the work after issuing work order." +
                "\ne) Any Extra work will be charged."  );












        //use projEmail


        Log.e("#Secure Get Data", ""+projName);



        Document mDoc = new Document();



        String mFileName = projName + "_" + new SimpleDateFormat("yyyyMMdd_HHmm", Locale.getDefault()).format(System.currentTimeMillis());

        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".pdf";

        try {
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));

            mDoc.open();


            mDoc.setPageSize(PageSize.A4);
            // mDoc.setPageCount(2);

            Toast.makeText(this, " size of list " + myList.size(), Toast.LENGTH_SHORT).show();



            //Letterhead Image


            Drawable letterhead = getResources().getDrawable(R.drawable.letterhead);
            BitmapDrawable bitLetterhead = ((BitmapDrawable) letterhead);
            Bitmap bmpLetterhead = bitLetterhead.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmpLetterhead.compress(Bitmap.CompressFormat.PNG, 100, stream);

            Image image = Image.getInstance(stream.toByteArray());
            image.scaleToFit(515, 380);

            mDoc.add(image);


            String dispdate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());


            Paragraph date = new Paragraph("Date: " + dispdate, FontFactory.getFont(String.valueOf(smallBold)));
            date.setAlignment(ALIGN_RIGHT);


            mDoc.add(date);


            Paragraph space = new Paragraph("    ");
            mDoc.add(space);


            Paragraph to = new Paragraph("To.");
            mDoc.add(to);


            //clientinfo

            Paragraph clientInfo = new Paragraph("" + projName + "\n" + projLoc + "\n" + projContact, FontFactory.getFont(String.valueOf(clientFont)));
            clientInfo.setAlignment(Paragraph.ALIGN_LEFT);

            mDoc.add(clientInfo);


            mDoc.add(new Paragraph("    "));


            //Subject Paragraph
            Paragraph paraSub = new Paragraph("Subject: " + projSubject, FontFactory.getFont(String.valueOf(smallBold)));
            paraSub.setAlignment(Paragraph.ALIGN_CENTER);
            mDoc.add(paraSub);


            mDoc.add(new Paragraph(" " + projRoom, FontFactory.getFont(String.valueOf(roomTitle))));

            mDoc.add(new Paragraph("     "));


            PdfPTable tablehead = new PdfPTable(4);


            float[] widths1 = new float[] {50f, 20f, 20f , 20f};
            tablehead.setWidths(widths1);












            //Paragraph cellItm = new Paragraph("ITEM ", FontFactory.getFont(String.valueOf(tableTitle)));
            PdfPCell cellItm = new PdfPCell(new Phrase("ITEM"));


            cellItm.setHorizontalAlignment(ALIGN_CENTER);

//            Paragraph cellQty = new Paragraph("QUANTITY ", FontFactory.getFont(String.valueOf(tableTitle)));
            PdfPCell cellQty = new PdfPCell(new Phrase("QUANTITY"));
            cellQty.setHorizontalAlignment(ALIGN_CENTER);


//            Paragraph cellAmt = new Paragraph("RATE ", FontFactory.getFont(String.valueOf(tableTitle)));
            PdfPCell cellAmt = new PdfPCell(new Phrase("RATE(/sft)"));
            cellAmt.setHorizontalAlignment(ALIGN_CENTER);

//            Paragraph cellTtl = new Paragraph("TOTAL COST ", FontFactory.getFont(String.valueOf(tableTitle)));
            PdfPCell cellTtl = new PdfPCell(new Phrase("AMOUNT"));
            cellTtl.setHorizontalAlignment(ALIGN_CENTER);



            tablehead.addCell(cellItm);
            tablehead.addCell(cellQty);
            tablehead.addCell(cellAmt);
            tablehead.addCell(cellTtl);

            mDoc.add(tablehead);
            mDoc.addAuthor("Amey");



//            for (int s = 1; s<=  myList.size(); s++){
//                PdfPCell srnum = new PdfPCell(new Phrase(s));
//
//            }


            for (int i = 0; i < myList.size(); i++) {



                String mTitle = myList.get(i).getTitle();
                String mQty = myList.get(i).getDescription();
                String mAmount = myList.get(i).getAmount();

                int rate = Integer.parseInt(mQty) * Integer.parseInt(mAmount);





                int finaltotal = Integer.parseInt(String.valueOf(rate));

                total.add(finaltotal);



                Log.e("#", "total value add zali");


                PdfPTable table = new PdfPTable(4);

                PdfPCell titleVal = new PdfPCell(Phrase.getInstance(mTitle));
                titleVal.setHorizontalAlignment(ALIGN_LEFT);



                PdfPCell qtyVal = new PdfPCell(Phrase.getInstance(mQty));
                qtyVal.setHorizontalAlignment(ALIGN_RIGHT);

                PdfPCell amtVal = new PdfPCell(Phrase.getInstance(mAmount));
                amtVal.setHorizontalAlignment(ALIGN_RIGHT);

                PdfPCell ttlVal = new PdfPCell(Phrase.getInstance(String.valueOf(rate)));
                ttlVal.setHorizontalAlignment(ALIGN_RIGHT);

                //totalcell.setHorizontalAlignment(Element.ALIGN_RIGHT);


                float[] widths = new float[] {50f, 20f, 20f , 20f};
                table.setWidths(widths);




                table.addCell(titleVal);
                table.addCell(qtyVal);
                table.addCell(amtVal);
                table.addCell(ttlVal);






                mDoc.add(table);
            }





            int sum = 0;
            for (int r = 0; r < total.size(); r++)
                sum += total.get(r);


            Log.e("total" , " saglya total chi sum houn final total zali = " + sum);


            mDoc.add(space);


            PdfPTable totaltable = new PdfPTable(2);

            float[] totalwidth = new float[] {45,55};
            totaltable.setWidths(totalwidth);

//            totaltable.setWidthPercentage(5.0E01f);



            //Paragraph ttltagcell = new Paragraph("Total");
            PdfPCell ttltagcell = new PdfPCell(new Phrase("TOTAL:"));

            ttltagcell.setHorizontalAlignment(ALIGN_RIGHT);
            ttltagcell.setBorder(Rectangle.NO_BORDER);
            ttltagcell.setBackgroundColor(BaseColor.LIGHT_GRAY);






           // PdfPCell totaltagcell = new PdfPCell(new Phrase("Total"));
            PdfPCell totalcell = new PdfPCell(new Phrase("₹" +sum + " /-"));




            totalcell.setHorizontalAlignment(ALIGN_RIGHT);



            totaltable.addCell(ttltagcell);
            totaltable.addCell(totalcell);

            mDoc.add(totaltable);



            Paragraph end  = new Paragraph("Yours Faithfully", FontFactory.getFont(String.valueOf(endFont)));
            Paragraph end_name = new Paragraph("AmeyDesigns",FontFactory.getFont(String.valueOf(endFont)));
            Paragraph notes = new Paragraph("Notes:", FontFactory.getFont(String.valueOf(endFont)));
            Paragraph notess = new Paragraph(projNotes, FontFactory.getFont(String.valueOf(endNoteFont)));





            mDoc.add(space);

            mDoc.add(notes);

            mDoc.add(notess);

            mDoc.add(space);
            mDoc.add(space);



            mDoc.add(end);
            mDoc.add(space);
            mDoc.add(end_name);
















            mDoc.close();



//
//            Intent intentOpen = new Intent(Intent.ACTION_VIEW);
//
//            intentOpen.setDataAndType()
//






            progressDialog.dismiss();


            Toast.makeText(this, mFileName + ".pdf\n is saved to\n " + mFilePath, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // if anything goes wrong
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

return 0;

    }


















    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case STORAGE_CODE: {
                if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    // permision was granted from popup call savepdf
                    savePdf();

                }
                else {
                    // permsion was denied from popup , show error
                    Toast.makeText(this, "permission dneied", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}

