package com.example.dish.ui.createPost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dish.R;

public class CreatePostActivity extends AppCompatActivity {
    private Context context;
    int RESULT_LOAD_IMAGE = 1;
    // UI components
    private RadioGroup postTypeRadioGroup;
    private RadioButton donationButton, eventButton;
    private LinearLayout postEntries, donationEntries, eventEntries;
    private ImageView imgView;
    private AutoCompleteTextView dropdown;
    private Button buttonLoadImage, btCreatePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        initViews();
        Intent intent = getIntent();

        if (intent != null) {
            /* post type (donation/event) radio button selection */
            // donation selected by default
            donationEntries.setVisibility(View.VISIBLE);
            eventEntries.setVisibility(View.GONE);
            donationButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (donationEntries.getVisibility() != View.VISIBLE) {
                        donationEntries.setVisibility(View.VISIBLE);
                        eventEntries.setVisibility(View.GONE);
                    }
                }
            });
            eventButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (eventEntries.getVisibility() != View.VISIBLE) {
                        eventEntries.setVisibility(View.VISIBLE);
                        donationEntries.setVisibility(View.GONE);
                    }
                }
            });

            /* hide default image initially */
            imgView.setVisibility(View.GONE);
            /* add post image */
            buttonLoadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }
            });

            /* recurrence dropdown menu */
            String[] recurrenceTypes = {"Daily", "Weekly", "Monthly"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_item, recurrenceTypes);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dropdown.setAdapter(adapter);


            /* event time picker doesn't work yet */
//            EditText eventTimeInput = root.findViewById(R.id.create_post_event_time);
//            eventTimeInput.setInputType(InputType.TYPE_NULL);
//            eventTimeInput.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final Calendar cldr = Calendar.getInstance();
//                    int hour = cldr.get(Calendar.HOUR_OF_DAY);
//                    int minutes = cldr.get(Calendar.MINUTE);
//                    // time picker dialog
//                    TimePickerDialog picker = new TimePickerDialog(getActivity().getApplicationContext(),
//                            new TimePickerDialog.OnTimeSetListener() {
//                                @Override
//                                public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
//                                    eventTimeInput.setText(sHour + ":" + sMinute);
//                                }
//                            }, hour, minutes, true);
//                    picker.show();
//                }
//            });


            /* POST button */
            btCreatePost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    /* show uploaded image after media image select */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri imageUri = data.getData();
            ImageView imageView = findViewById(R.id.create_post_img_view);
            imageView.setImageURI(imageUri);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private void initViews() {
        postTypeRadioGroup = findViewById(R.id.rgDonationEvent);
        donationButton = findViewById(R.id.rbDonation);
        eventButton = findViewById(R.id.rbEvent);
        postEntries = findViewById(R.id.create_post_entries);
        donationEntries = findViewById(R.id.create_post_donation_entries);
        eventEntries = findViewById(R.id.create_post_event_entries);
        imgView = findViewById(R.id.create_post_img_view);
        dropdown = findViewById(R.id.create_post_recurrence_dropdown);
        btCreatePost = findViewById(R.id.btCreatePost);
        buttonLoadImage = findViewById(R.id.create_post_img_button);
    }

    public CreatePostActivity(){};
}