package com.example.hariharsudan.bc.User.Login_Modules.Tracking;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hariharsudan.bc.Adapter.Filepath;
import com.example.hariharsudan.bc.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hariharsudan on 2/13/2017.
 */

public class Bills_view extends AppCompatActivity {
    Uri imageUri,selectedImageUri;
    ImageView imageView;
    Button save;
    Typeface typeface;
    TextView textView;
    LinearLayout linear_to_hide;
    private static final int PICK_IMAGE = 1;
    private static final int PICK_Camera_IMAGE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bills_view);

        getSupportActionBar().setTitle("Upload your reciept");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        save = (Button) findViewById(R.id.save);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        linear_to_hide = (LinearLayout) findViewById(R.id.linear_to_hide);
        linear_to_hide.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Ubuntu-R.ttf");
        save.setTypeface(typeface);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Bills_view.this,"LOL",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose picture..");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    String fileName = "new-photo-name.jpg";
                    //create parameters for Intent with filename
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, fileName);
                    values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
                    //imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
                    imageUri = Bills_view.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    //create new Intent
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    startActivityForResult(intent, PICK_Camera_IMAGE);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            PICK_IMAGE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_OK) {

            if (requestCode == PICK_Camera_IMAGE) {
                if (resultCode == this.RESULT_OK) {
                    //use imageUri here to access the image
                    selectedImageUri = imageUri;

                } else if (resultCode == this.RESULT_CANCELED) {
                    Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
                }

                linear_to_hide.setVisibility(View.VISIBLE);
                save.setVisibility(View.VISIBLE);
                textView.setText("Here's your receipt");
                String image_name = getFileName(selectedImageUri);
                String image_size = getImageSize(selectedImageUri).toString();
                String image_path = Filepath.getPaths(this, selectedImageUri);
                String image_date = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(Bills_view.this.getContentResolver(), selectedImageUri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TextView textview10 = (TextView) findViewById(R.id.textView10);
                TextView textview12 = (TextView) findViewById(R.id.textView12);
                TextView textview13 = (TextView) findViewById(R.id.textView13);
                TextView textview14 = (TextView) findViewById(R.id.textView14);

                TextView name = (TextView) findViewById(R.id.name);
                TextView path = (TextView) findViewById(R.id.path);
                TextView size = (TextView) findViewById(R.id.size);
                TextView date = (TextView) findViewById(R.id.date);

                name.setTypeface(typeface);
                path.setTypeface(typeface);
                size.setTypeface(typeface);
                date.setTypeface(typeface);
                textview10.setTypeface(typeface);
                textview12.setTypeface(typeface);
                textview13.setTypeface(typeface);
                textview14.setTypeface(typeface);

                textview10.setText(image_name);
                textview12.setText(image_path);
                textview13.setText(image_size + " bytes");
                textview14.setText(image_date);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Bills_view.this,"LOL",Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                });

            } else if (requestCode == PICK_IMAGE) {
                if (resultCode == this.RESULT_OK) {
                    selectedImageUri = data.getData();
                } else if (resultCode == this.RESULT_CANCELED) {
                    Toast.makeText(this, "Picture was not selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Picture was not selected", Toast.LENGTH_SHORT).show();
                }

                linear_to_hide.setVisibility(View.VISIBLE);
                save.setVisibility(View.VISIBLE);
                textView.setText("Here's your receipt");
                String image_name = getFileName(selectedImageUri);
                String image_size = getImageSize(selectedImageUri).toString();
                String image_path = Filepath.getPaths(this, selectedImageUri);
                String image_date = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TextView textview10 = (TextView) findViewById(R.id.textView10);
                TextView textview12 = (TextView) findViewById(R.id.textView12);
                TextView textview13 = (TextView) findViewById(R.id.textView13);
                TextView textview14 = (TextView) findViewById(R.id.textView14);

                TextView name = (TextView) findViewById(R.id.name);
                TextView path = (TextView) findViewById(R.id.path);
                TextView size = (TextView) findViewById(R.id.size);
                TextView date = (TextView) findViewById(R.id.date);

                name.setTypeface(typeface);
                path.setTypeface(typeface);
                size.setTypeface(typeface);
                date.setTypeface(typeface);
                textview10.setTypeface(typeface);
                textview12.setTypeface(typeface);
                textview13.setTypeface(typeface);
                textview14.setTypeface(typeface);

                textview10.setText(image_name);
                textview12.setText(image_path);
                textview13.setText(image_size + " bytes");
                textview14.setText(image_date);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Bills_view.this,"LOL",Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                });
            }
        }
    }

    private Long getImageSize(Uri uri) {
        Cursor cursor = this.getContentResolver().query(uri,
                null, null, null, null);
        cursor.moveToFirst();
        long size = cursor.getLong(cursor.getColumnIndex(OpenableColumns.SIZE));
        cursor.close();
        return size;
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
