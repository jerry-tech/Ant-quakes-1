package com.accounting.ant;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UsersFeedback extends AppCompatActivity {

    Dialog dialogResponse;
    Button complaintBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_feedback);

        complaintBtn = findViewById(R.id.complaintBtn);

        //dialog for log out option
        dialogResponse = new Dialog(this);
        dialogResponse.setCancelable(true);

//        complaintBtn.setOnClickListener(v -> showComplaintDialog());

        //spinner drop down for the support type
        Spinner supportType = findViewById(R.id.supportType);
        // using the array adapter class && the array adapter public constructor which accepts three parameters to get all items
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,//getting the application context(this);
                R.array.support_type,
                R.layout.spinner_support
        );

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        supportType.setAdapter(adapter);
        String feedback = "FeedBack Submitted";
        String help = "Help Submitted";
        String messFeedback = "Thank you for contacting us.";
        String messHelp = "Thank you for contacting us. We \n soon look into the matter.";

        supportType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedText = parent.getSelectedItem().toString().trim();

                if(selectedText.equalsIgnoreCase("Lodge FeedBack")){
                    complaintBtn.setOnClickListener(v -> showComplaintDialog(feedback,messFeedback));
                }else if(selectedText.equalsIgnoreCase("Lodge Help")){
                    complaintBtn.setOnClickListener(v -> showComplaintDialog(help,messHelp));
                }else
                    Toast.makeText(getApplicationContext(), "Select Support Type Before Lodging Help Or FeedBack", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void showComplaintDialog(String response,String subResponse){
        dialogResponse.setContentView(R.layout.feedback_response);
        dialogResponse.show();
        //finding the buttons Id
        Button button = dialogResponse.findViewById(R.id.btnComplaint);
        button.setOnClickListener(V -> showComplaintActivity());

        TextView textIcon = dialogResponse.findViewById(R.id.iconsResponse);
        textIcon.setText(response);

        TextView txtText = dialogResponse.findViewById(R.id.feedResp);
        txtText.setText(subResponse);


    }

    private void showComplaintActivity() {
        finish();
        startActivity(getIntent());
    }

    /* method called in a click listener in the layout */
    public void gotoDashBoard(View view){
        Intent intent = new Intent(this,UserOptions.class);
        startActivity(intent);
    }
}
