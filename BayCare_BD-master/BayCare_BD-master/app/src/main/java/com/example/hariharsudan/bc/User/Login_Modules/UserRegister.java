package com.example.hariharsudan.bc.User.Login_Modules;

/**
 * Created by Hariharsudan on 2/10/2017.
 */

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hariharsudan.bc.Adapter.CurrentUser;
import com.example.hariharsudan.bc.Adapter.Loginnext;
import com.example.hariharsudan.bc.Adapter.User_Update;
import com.example.hariharsudan.bc.Adapter.User_Update2;
import com.example.hariharsudan.bc.R;
import com.example.hariharsudan.bc.User.Login_Modules.Profile.UserEditProf;
import com.example.hariharsudan.bc.User.Login_Modules.Profile.UserProfView;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.Result;
import com.iceteck.silicompressorr.SiliCompressor;
import com.kofigyan.stateprogressbar.StateProgressBar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import de.hdodenhof.circleimageview.CircleImageView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.hariharsudan.bc.Adapter.User_Update.finalstreet;
import static com.example.hariharsudan.bc.Adapter.User_Update.uaadhar;
import static com.example.hariharsudan.bc.Adapter.User_Update.udist;
import static com.example.hariharsudan.bc.Adapter.User_Update.uname;
import static com.example.hariharsudan.bc.Adapter.User_Update.upin;
import static com.example.hariharsudan.bc.Adapter.User_Update.usex;
import static com.example.hariharsudan.bc.Adapter.User_Update.ustate;
import static com.example.hariharsudan.bc.Adapter.User_Update.uyob;

public class UserRegister extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int PICK_IMAGE = 1;
    private static final int PICK_Camera_IMAGE = 2;
    String[] descriptionData = {"Scan", "Details", "Photo"};
    String uid, name, yob, gender, house, street, dist, state, pc;
    private ZXingScannerView mScannerView;
    FloatingActionButton pro_pic_button;
    User_Update2 user_update = new User_Update2();
    CircleImageView pro_pic_upload;

    String base_url = "https://baycare-bfeee.firebaseio.com/";
    Firebase fb_db;
    public static final String BARCODE_KEY = "BARCODE";


    StateProgressBar stateProgressBar;
    Button next,next2,next3;
    Uri imageUri,selectedImageUri,compressedUri;
    public Loginnext obj;

    LinearLayout l1,l2,l3;
    private TextView textView10,textView11,textView12,textView13,textView14,textView15,textView16,textView17;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        String filei = Environment.getExternalStorageDirectory() + "/BayCare/Login/logs.tmp";
        File f = new File(filei);
        if (f.exists()) {
            System.out.println("FILE READING");
            FileInputStream fisi = null;

            try {

                fisi = new FileInputStream(filei);
                ObjectInputStream ois = new ObjectInputStream(fisi);
                obj = (Loginnext) ois.readObject();
                ois.close();
                if (obj.islog) {
                    CurrentUser.Aadhar = obj.user;
                    Intent intent = new Intent(UserRegister.this, UserProfView.class);
                    intent.putExtra("AADHAR",user_update.getUaadhar());

                    startActivity(intent);
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        l3 = (LinearLayout) findViewById(R.id.l3);

        next = (Button) findViewById(R.id.next);
        next.setVisibility(View.INVISIBLE);
        l2.setVisibility(View.GONE);
        l3.setVisibility(View.GONE);
        Firebase.setAndroidContext(this);
        fb_db = new Firebase(base_url);
        FirebaseApp.initializeApp(getApplicationContext());
        pro_pic_button = (FloatingActionButton)findViewById(R.id.pro_pic_button);
        pro_pic_upload = (CircleImageView)findViewById(R.id.pro_pic_upload);
        stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScannerView = new ZXingScannerView(UserRegister.this);   // Programmatically initialize the scanner view
                setContentView(mScannerView);
                mScannerView.setResultHandler(UserRegister.this);
                mScannerView.startCamera();
              //  mScannerView.stopCamera();

            }
        });
if(User_Update.getUaadhar()!=null){
        next.setVisibility(View.VISIBLE);
}

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);

                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                stateProgressBar.enableAnimationToCurrentState(true);
                textView10 = (TextView) findViewById(R.id.textView10);
                textView11 = (TextView) findViewById(R.id.textView11);
                textView12 = (TextView) findViewById(R.id.textView12);
                textView13 = (TextView) findViewById(R.id.textView13);
                textView14 = (TextView) findViewById(R.id.textView14);
                textView15 = (TextView) findViewById(R.id.textView15);
                textView16 = (TextView) findViewById(R.id.textView16);
                textView17 = (TextView) findViewById(R.id.textView17);


                textView10.setText(User_Update.getUaadhar());
                textView11.setText(User_Update.getUname());
                textView12.setText(User_Update.getUyob());
                textView13.setText(User_Update.getUsex());
                textView14.setText(User_Update.getFinalstreet());
                textView15.setText(User_Update.getUdist());
                textView16.setText(User_Update.getUstate());
                textView17.setText(User_Update.getUpin());

            }
        });

        next2 = (Button) findViewById(R.id.next2);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                stateProgressBar.enableAnimationToCurrentState(true);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.VISIBLE);

            }
        });

        next3 = (Button) findViewById(R.id.next3);
        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stateProgressBar.setAllStatesCompleted(true);
                uploadprofpic();

            }
        });
        pro_pic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

    }
    @Override
    public void onPause() {
        super.onPause();
//        mScannerView.stopCamera();           // Stop camera on pause
    }


    @Override
    public void handleResult(Result result) {
        System.out.println("WTF " + result.toString());
//        mScannerView.stopCamera();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(result.toString())));
            Element rootElement = document.getDocumentElement();

            System.out.println("AADHAR " + rootElement.getAttribute("uid"));
            System.out.println("NAME  " + rootElement.getAttribute("name"));
            System.out.println("YOB " + rootElement.getAttribute("yob"));
            System.out.println("SEX " + rootElement.getAttribute("gender"));
            System.out.println("SEX " + rootElement.getAttribute("house"));
            System.out.println("SEX " + rootElement.getAttribute("street"));
            System.out.println("SEX " + rootElement.getAttribute("dist"));
            System.out.println("SEX " + rootElement.getAttribute("state"));
            System.out.println("SEX " + rootElement.getAttribute("pc"));

            uid = rootElement.getAttribute("uid");
            name = rootElement.getAttribute("name");
            yob = rootElement.getAttribute("yob");
            gender = rootElement.getAttribute("gender");
            house = rootElement.getAttribute("house");
            street = rootElement.getAttribute("street");
            dist = rootElement.getAttribute("dist");
            state = rootElement.getAttribute("state");
            pc = rootElement.getAttribute("pc");

            String finalstreet = house+" "+street;
            mScannerView.stopCamera();


            User_Update.setUaadhar(uid);
            User_Update.setUyob(yob);
            User_Update.setUname(name);
            User_Update.setUsex(gender);
            User_Update.setUdist(dist);
            User_Update.setUstate(state);
            User_Update.setFinalstreet(finalstreet);
            User_Update.setUpin(pc);

            Intent i = new Intent(this,UserRegister.class);

           startActivity(i);
            finish();



        } catch (Exception e) {
            System.out.println("EXCEPTION ))))" + e);
        }



    }
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(UserRegister.this);
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
                    imageUri = UserRegister.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
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
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver() , selectedImageUri);
                    String filePath = SiliCompressor.with(getApplicationContext()).compress(selectedImageUri.toString());
                    compressedUri = Uri.fromFile(new File(filePath));
                    pro_pic_upload.setImageURI(compressedUri);
                    uploadprofpic();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else if (requestCode == PICK_IMAGE) {
                if (resultCode == this.RESULT_OK) {
                    selectedImageUri = data.getData();
                } else if (resultCode == this.RESULT_CANCELED) {
                    Toast.makeText(this, "Picture was not selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Picture was not selected", Toast.LENGTH_SHORT).show();
                }
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver() , selectedImageUri);
                    String filePath = SiliCompressor.with(getApplicationContext()).compress(selectedImageUri.toString());
                    compressedUri = Uri.fromFile(new File(filePath));
                    pro_pic_upload.setImageURI(compressedUri);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }}}


    public void uploadprofpic()
    {
        final String imagenode = User_Update.getUaadhar();
        System.out.println("  imagenode  " + imagenode);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("ProfPic").child(imagenode);

        System.out.println("Storage refference : " + storageReference);



        UploadTask up = storageReference.putFile(compressedUri);
        up.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {


            }
        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


             /*   File dir = new File("/sdcard/SiliCompressor");
                try {
                    FileUtils.deleteDirectory(dir);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                fb_db.child("UsersProfile").child(uaadhar).setValue(user_update);
                File folder = new File(Environment.getExternalStorageDirectory()+ "/BayCare/Login");
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdirs();
                }
                if (success) {
                    // Do something on success
                    try {
                        String file1 = Environment.getExternalStorageDirectory() + "/BayCare/Login/logs.tmp";
                        new PrintWriter(file1).close();
                        FileOutputStream fos = new FileOutputStream(file1);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(new Loginnext(uaadhar,true));
                        System.out.println("File is "+oos.toString());
                        oos.close();
                    }
                    catch(Exception e)
                    {
                        System.out.println("Errror in file"+e);
                    }
                } else {
                    // Do something else on failure

                }


                Intent intent = new Intent(UserRegister.this, UserEditProf.class);
                intent.putExtra("AADHAR",user_update.getUaadhar());



                startActivity(intent);
                finish();

            }
        });
    }

}
