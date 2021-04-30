package com.example.dish.ui.createPost;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dish.MainActivity;
import com.example.dish.R;
import com.example.dish.ui.home.Post;
import com.example.dish.ui.home.Utils;
import com.example.dish.ui.postDetail.PostActivity;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private EditText donationExpDateButton, eventDateRangeButton,
            eventStartTimeButton, eventEndTimeButton , postStartAndEndDate ,postEndDate;
    private TextInputEditText postTitle, postDescription, postUrl, postDonationAmount, postVolunteers, postLocation;

    int eventStartHour, eventStartMin, eventEndHour, eventEndMin;

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

            /* calendar date constraints */
            CalendarConstraints.Builder calendarConstraintBuilder = new CalendarConstraints.Builder();
            calendarConstraintBuilder.setValidator(DateValidatorPointForward.now());

            /* donation expiration date picker */
            MaterialDatePicker.Builder donationExpDateBuilder = MaterialDatePicker.Builder.datePicker();
            donationExpDateBuilder.setTitleText("Select an exp. date");
            donationExpDateBuilder.setCalendarConstraints(calendarConstraintBuilder.build());
            final MaterialDatePicker donationExpDatePicker = donationExpDateBuilder.build();
            donationExpDateButton.setShowSoftInputOnFocus(false);
            donationExpDateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    donationExpDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                }
            });
            donationExpDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                @Override
                public void onPositiveButtonClick(Object selection) {
                    donationExpDateButton.setText(donationExpDatePicker.getHeaderText(), TextView.BufferType.EDITABLE);
                }
            });

            /* event date range picker */
            MaterialDatePicker.Builder eventDateRangeBuilder = MaterialDatePicker.Builder.dateRangePicker();
            eventDateRangeBuilder.setTitleText("Select a date range");
            eventDateRangeBuilder.setCalendarConstraints(calendarConstraintBuilder.build());
            final MaterialDatePicker eventDateRangePicker = eventDateRangeBuilder.build();
            eventDateRangeButton.setShowSoftInputOnFocus(false);
            eventDateRangeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventDateRangePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                }
            });
            eventDateRangePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                @Override
                public void onPositiveButtonClick(Object selection) {
                    eventDateRangeButton.setText(eventDateRangePicker.getHeaderText(), TextView.BufferType.EDITABLE);
                }
            });

            /* event time pickers */
            eventStartTimeButton.setShowSoftInputOnFocus(false);
            eventStartTimeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar cal = Calendar.getInstance();
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int minutes = cal.get(Calendar.MINUTE);
                    TimePickerDialog picker = new TimePickerDialog(CreatePostActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hr, int min) {
                                eventStartTimeButton.setText(hr + ":" + min, TextView.BufferType.EDITABLE);
                                eventStartHour = hr;
                                eventStartMin = min;
                            }
                        }, hour, minutes, true);

                    picker.show();
                }
            });
            eventEndTimeButton.setShowSoftInputOnFocus(false);
            eventEndTimeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar cal = Calendar.getInstance();
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int minutes = cal.get(Calendar.MINUTE);
                    TimePickerDialog picker = new TimePickerDialog(CreatePostActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hr, int min) {
                                eventEndTimeButton.setText(hr + ":" + min, TextView.BufferType.EDITABLE);
                                eventEndHour = hr;
                                eventEndMin = min;
                            }
                        }, hour, minutes, true);
                    picker.show();
                }
            });

            /* POST button */
            btCreatePost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CreatePostActivity.this, "Post created", Toast.LENGTH_LONG).show();
                    String title = postTitle.getText().toString();
                    String description = postDescription.getText().toString();
                    String url = postUrl.getText().toString();
                    String location = postLocation.getText().toString();
                    double goal = 0;
                    if(donationEntries.getVisibility() == View.VISIBLE) {
                        goal = Double.parseDouble(postDonationAmount.getText().toString());
                        String donationEndDate = postEndDate.getText().toString();
                        String start = new Date().toString().substring(4, 10) + donationEndDate.substring(donationEndDate.indexOf(','));
                        Post post = new Post("DISH", Utils.getInstance().totalPosts() + 1, title, description, "TAG", "donation", url, goal, start, donationEndDate, url, location);
                        if(Utils.getInstance().addNewPost(post)){
                            Intent postDetailIntent = new Intent(v.getContext(), PostActivity.class);
                            postDetailIntent.putExtra("id", post.getID());
                            v.getContext().startActivity(postDetailIntent);
                        }
                        else {
                            Toast.makeText(CreatePostActivity.this, "Err...something wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        goal = Integer.parseInt(postVolunteers.getText().toString());
                        String eventDueDate = postStartAndEndDate.getText().toString();
                        String start = eventStartHour + ":" + eventStartMin + " " +  eventDueDate.substring(0, eventDueDate.indexOf("–"));
                        String end = eventEndHour + ":" + eventEndMin + " " + eventDueDate.substring(eventDueDate.indexOf("–")+1);
                        Post post = new Post("DISH", Utils.getInstance().totalPosts() + 1, title, description, "TAG", "event", url, (int) goal, start, end, url, location);
                        if(Utils.getInstance().addNewPost(post)){
                            Intent postDetailIntent = new Intent(v.getContext(), PostActivity.class);
                            postDetailIntent.putExtra("id", post.getID());
                            v.getContext().startActivity(postDetailIntent);
                        }
                        else {
                            Toast.makeText(CreatePostActivity.this, "Err...something wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
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
        donationExpDateButton = findViewById(R.id.create_post_donation_exp_date_input);
        eventDateRangeButton = findViewById(R.id.create_post_event_date_range);
        eventStartTimeButton = findViewById(R.id.create_post_event_start_time);
        eventEndTimeButton = findViewById(R.id.create_post_event_end_time);

        postTitle = findViewById(R.id.create_post_title);
        postDescription = findViewById(R.id.create_post_description);
        postUrl = findViewById(R.id.create_post_url);
        postVolunteers = findViewById(R.id.create_post_event_volunteers);
        postDonationAmount = findViewById(R.id.create_post_donation_amount);
        postLocation = findViewById(R.id.create_post_event_location);
        postEndDate = findViewById(R.id.create_post_donation_exp_date_input);
        postStartAndEndDate = findViewById(R.id.create_post_event_date_range); // only event posts have this
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreatePostActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public CreatePostActivity(){};
}