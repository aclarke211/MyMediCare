//==================================================================================================================================\\
package com.example.aclarke211.mymedicare;
//==================================================================================================================================\\
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

//==================================================================================================================================\\
public class UpdateValues extends Activity {

    //variables
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    String username, receivedDbTemperature, receivedDbLowBloodPressure, receivedDbHighBloodPressure, receivedDbHeartRate, bgReceivedColour;
    int intAge;
    EditText temperature, lowBloodPressure, highBloodPressure, heartRate;
    BackgroundAnalyser backgroundAnalyser;

//==================================================================================================================================\\
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_values);
    }

//==================================================================================================================================\\
    @Override
    protected void onStart() {
        super.onStart();

        //retrievng the username of currently logged in user from the Overview class
        username = getIntent().getStringExtra("Username");

        receivedDbTemperature = dbHelper.searchTemperature(username);
        receivedDbLowBloodPressure = dbHelper.searchLowBloodPressure(username);
        receivedDbHighBloodPressure = dbHelper.searchHighBloodPressure(username);
        receivedDbHeartRate = dbHelper.searchHeartRate(username);

        //finding the values entered by user
        temperature = (EditText) findViewById(R.id.etTemperature_updateValuesActivity);
        lowBloodPressure = (EditText) findViewById(R.id.etLowBloodPressure_updateValuesActivity);
        highBloodPressure = (EditText) findViewById(R.id.etHighBloodPressure_updateValuesActivity);
        heartRate = (EditText) findViewById(R.id.etHeartRate_updateValuesActivity);

        temperature.setText(receivedDbTemperature);
        lowBloodPressure.setText(receivedDbLowBloodPressure);
        highBloodPressure.setText(receivedDbHighBloodPressure);
        heartRate.setText(receivedDbHeartRate);

        bgReceivedColour = dbHelper.searchBackgroundColour(username);
        LinearLayout backgroundLayout = (LinearLayout)findViewById(R.id.changableBackgroundLayout_updateValuesActivity);

        backgroundAnalyser = new BackgroundAnalyser();
        backgroundAnalyser.changeBackground(backgroundLayout, bgReceivedColour);

    }

//==================================================================================================================================\\
    public void onButtonClick(View v) {

        switch (v.getId()) {

            case R.id.btnConfirm_updateValuesActivity:

                //converting the values to readable strings
                String strTemperature = temperature.getText().toString();
                float floatTemperature = Float.parseFloat(strTemperature);

                String strLowBloodPressure = lowBloodPressure.getText().toString();
                String strHighBloodPressure = highBloodPressure.getText().toString();
                String strHeartRate = heartRate.getText().toString();

                //receive age value from database
                String strAge = dbHelper.searchAge(username);
                //covert age value to an int
                intAge = Integer.parseInt(strAge);


                if (intAge <= 0 || intAge >= 255) {

                    Toast incorrectAgeAlert = Toast.makeText(UpdateValues.this, "Please enter a valid age", Toast.LENGTH_SHORT);
                    incorrectAgeAlert.show();


                } else if (strTemperature.isEmpty() || strLowBloodPressure.isEmpty() || strHighBloodPressure.isEmpty() || strHeartRate.isEmpty()) {

                    //show an error to the user, stating to fill all fields
                    Toast noInfo = Toast.makeText(UpdateValues.this, "Please fill all boxes", Toast.LENGTH_SHORT);
                    noInfo.show();

                } else {

                    //create a contact with data to be inserted to database
                    Contact editContact = new Contact();
                    editContact.setTemperature(floatTemperature);
                    editContact.setLowBloodPressure(strLowBloodPressure);
                    editContact.setHighBloodPressure(strHighBloodPressure);
                    editContact.setHeartRate(strHeartRate);

                    //run updateDetails sending the contact created above
                    dbHelper.updateValues(editContact);

                    //display a toast confirming to the user that the details have saved
                    Toast saveSuccessfulToast = Toast.makeText(UpdateValues.this, "New Values Saved!", Toast.LENGTH_SHORT);
                    saveSuccessfulToast.show();

                    //new Intent object (Context/attributes of this class, new class to change to)
                    Intent i = new Intent(UpdateValues.this, Overview.class);
                    //passing strings etc. to other classes
                    i.putExtra("Username", username);
                    //open the activity from Intent above
                    startActivity(i);

                    //close the activity so the user cannot press 'back' to return
                    this.finish();

                }

                break;

            //see if the back button was pressed via its ID
            case R.id.btnBack_updateValuesActivity:

                //new Intent object (Context/attributes of this class, new class to change to)
                Intent goBacktoOverviewScreen = new Intent(UpdateValues.this, Overview.class);
                //passing strings etc. to other classes
                goBacktoOverviewScreen.putExtra("Username", username);
                //open the activity from Intent above
                startActivity(goBacktoOverviewScreen);

                //close the activity so the user cannot press 'back' to return
                this.finish();

                break;

        }
    }

//==================================================================================================================================\\
}
//==================================================================================================================================\\