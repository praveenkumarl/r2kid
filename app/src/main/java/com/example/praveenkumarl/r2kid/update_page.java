package com.example.praveenkumarl.r2kid;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class update_page extends AppCompatActivity  {
    Button bUpdate;
    public EditText etName,etDOB;
    ImageView imgview;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    public DBHelper db;
    RadioButton rb,rbMale,rbFemale;
    RadioGroup rg;
    Uri uri;
    Bitmap bitmap;
    Intent CropIntent;
    int id;
    private static final int PICK_FROM_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);

        bUpdate=(Button)findViewById(R.id.btUpdate);
        etName = (EditText)findViewById(R.id.etName);
        etDOB = (EditText) findViewById(R.id.etDOB);
        rg = (RadioGroup) findViewById(R.id.radioGender);
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        imgview = (ImageView) findViewById(R.id.ivPhoto);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        db = new DBHelper(this);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        //showDate(year, month+1, day);

        Intent intent = getIntent();
        etName.setText(intent.getStringExtra("name"));
        etDOB.setText(intent.getStringExtra("dob"));
        id = getIntent().getExtras().getInt("id");
        byte[] byteArray = getIntent().getByteArrayExtra("photo");
        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imgview.setImageBitmap(bitmap);

        if(intent.getStringExtra("gender").equals("Male")){
            rbMale.setChecked(true);
        }
        else{
            rbFemale.setChecked(true);
        }

        //InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //mgr.hideSoftInputFromWindow(etDOB.getWindowToken(), 0);

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Remove this later
                String gender = "";

                int selectedId = rg.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                rb = (RadioButton) findViewById(selectedId);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                if(bitmap == null){
                    bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.no_photo);
                }

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                byte imageInByte[] = stream.toByteArray();

                if(!db.updateKids(id,etName.getText().toString(),etDOB.getText().toString(),rb.getText().toString(),imageInByte)){
                    Toast.makeText(getApplicationContext(),"Error updating into DB",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Record updated",Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(),"Record count - "+db.numberOfRows(),Toast.LENGTH_LONG).show();
                }
                db.close();
                Intent intent = new Intent(getApplicationContext(), manage_page.class);
                intent.putExtra("reload","main_page");
                finish();
            }
        });


        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), PICK_FROM_GALLERY);
            }
        });
    } //OnCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            ImageCropFunction();
        }
        else if (requestCode == PICK_FROM_GALLERY) {

            if (data != null) {

                uri = data.getData();

                ImageCropFunction();
            }
        }
        else if (requestCode == 1) {

            if (data != null) {

                Bundle bundle = data.getExtras();

                bitmap = bundle.getParcelable("data");
            }
            else {
                bitmap = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.no_photo);
            }

            imgview.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @SuppressWarnings("deprecation")
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        //etDOB.setText(new StringBuilder().append(day).append("-")
                //.append(month).append("-").append(year));

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        calendar.set(year, month-1, day);
        String strDate = format.format(calendar.getTime());
        etDOB.setText(strDate);
    }

    public void ImageCropFunction() {

        // Image Crop Code
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");

            CropIntent.setDataAndType(uri, "image/*");

            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 180);
            CropIntent.putExtra("outputY", 240);
            CropIntent.putExtra("aspectX", 3);
            CropIntent.putExtra("aspectY", 4);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);

            startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(),"Error"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
