package com.example.dish.ui.createPost;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dish.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;


public class createPostFragment extends Fragment {
    //private createPostViewModel createPostVM;

    int RESULT_LOAD_IMAGE = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //createPostVM = new ViewModelProvider(this).get(createPostViewModel.class);
        View root = inflater.inflate(R.layout.fragment_create_post, container, false);
        /*
        final TextView textView = root.findViewById(R.id.create_post);
        createPostVM.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        /* post type (donation/event) radio button selection */
        final RadioGroup postTypeRadioGroup = root.findViewById(R.id.rgDonationEvent);
        final RadioButton donationButton = root.findViewById(R.id.rbDonation);
        final RadioButton eventButton = root.findViewById(R.id.rbEvent);
        final LinearLayout postEntries = (LinearLayout) root.findViewById(R.id.create_post_entries);
        final LinearLayout donationEntries = (LinearLayout) root.findViewById(R.id.create_post_donation_entries);
        final LinearLayout eventEntries = (LinearLayout) root.findViewById(R.id.create_post_event_entries);
        // donation selected by default
        donationEntries.setVisibility(View.VISIBLE);
        eventEntries.setVisibility(View.GONE);
        donationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Toast.makeText(root.getContext(), "clicked donation", Toast.LENGTH_LONG).show();
                if (donationEntries.getVisibility() != View.VISIBLE) {
                    donationEntries.setVisibility(View.VISIBLE);
                    eventEntries.setVisibility(View.GONE);
                }
            }
        });
        eventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Toast.makeText(root.getContext(), "clicked event", Toast.LENGTH_LONG).show();
                if (eventEntries.getVisibility() != View.VISIBLE) {
                    eventEntries.setVisibility(View.VISIBLE);
                    donationEntries.setVisibility(View.GONE);
                }
            }
        });


        /* hide default image initially */
        ImageView imgView = root.findViewById(R.id.create_post_img_view);
        imgView.setVisibility(View.GONE);
        /* add post image */
        Button buttonLoadImage = (Button) root.findViewById(R.id.create_post_img_button);
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, recurrenceTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AutoCompleteTextView dropdown = root.findViewById(R.id.create_post_recurrence_dropdown);
        dropdown.setAdapter(adapter);


        /* event time picker doesn't work yet */
//        EditText eventTimeInput = root.findViewById(R.id.create_post_event_time);
//        eventTimeInput.setInputType(InputType.TYPE_NULL);
//        eventTimeInput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar cldr = Calendar.getInstance();
//                int hour = cldr.get(Calendar.HOUR_OF_DAY);
//                int minutes = cldr.get(Calendar.MINUTE);
//                // time picker dialog
//                TimePickerDialog picker = new TimePickerDialog(getActivity().getApplicationContext(),
//                        new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
//                                eventTimeInput.setText(sHour + ":" + sMinute);
//                            }
//                        }, hour, minutes, true);
//                picker.show();
//            }
//        });


        /* POST button */
        final Button btCreatePost = root.findViewById(R.id.btCreatePost);
        btCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(root.getContext(), "Posted", Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }


    /* show uploaded image after media image select */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri imageUri = data.getData();
            ImageView imageView = (ImageView) getView().findViewById(R.id.create_post_img_view);
            imageView.setImageURI(imageUri);
            imageView.setVisibility(View.VISIBLE);
        }
    }
}
