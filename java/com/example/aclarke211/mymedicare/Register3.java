//==================================================================================================================================\\
package com.example.aclarke211.mymedicare;
//==================================================================================================================================\\
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//==================================================================================================================================\\
public class Register3 extends Activity {

    //variables
    DatabaseHelper dbHelper;


//==================================================================================================================================\\
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        dbHelper = new DatabaseHelper(this);

    }

//==================================================================================================================================\\
    public void onButtonClick(View v) {

       if(v.getId() == R.id.btnSignUp_register3Activity) {

           //find the values entered by user
           EditText temperature = (EditText) findViewById(R.id.etTemperature_register3Activity);
           EditText lowBloodPressure = (EditText) findViewById(R.id.etLowBloodPressure_register3Activity);
           EditText highBloodPressure = (EditText) findViewById(R.id.etHighBloodPressure_register3Activity);
           EditText heartRate = (EditText) findViewById(R.id.etHeartRate_register3Activity);

           //retrieving Strings etc. from other classes
           String passedName = getIntent().getStringExtra("Name");
           String passedEmail = getIntent().getStringExtra("Email");
           String passedUsername = getIntent().getStringExtra("Username");
           String passedPassword = getIntent().getStringExtra("Password");
           String passedAge = getIntent().getStringExtra("Age");
           String passedAddress = getIntent().getStringExtra("Adress");
           String passedGpName = getIntent().getStringExtra("GpName");
           String passedGpNumber = getIntent().getStringExtra("GpNumber");

           //convert values to strings and then to integers
           String strTemperature = temperature.getText().toString();
                float fltTemperature = Float.parseFloat(strTemperature);

           String strLowBloodPressure = lowBloodPressure.getText().toString();
           int intLowBloodPressure = Integer.parseInt(strLowBloodPressure);
           String strHighBloodPressure = highBloodPressure.getText().toString();
           int intHighBloodPressure = Integer.parseInt(strHighBloodPressure);
           String strHeartRate = heartRate.getText().toString();
           int intHeartRate = Integer.parseInt(strHeartRate);

           if(strTemperature.isEmpty() || strLowBloodPressure.isEmpty() || strHighBloodPressure.isEmpty() || strHeartRate.isEmpty()) {

               //show an error to the user, stating to fill all fields
               Toast noInfo = Toast.makeText(Register3.this, "Please fill all boxes", Toast.LENGTH_SHORT);
               noInfo.show();

           } else if (fltTemperature <= 0 || fltTemperature >= 200 || intLowBloodPressure <= 0 || intLowBloodPressure >= 200
                   || intHighBloodPressure <= 0 || intHighBloodPressure >= 200 || intHeartRate <= 0 || intHeartRate >= 200) {

               Toast incorrectValuesAlert = Toast.makeText(Register3.this, "Please enter correct details", Toast.LENGTH_SHORT);
               incorrectValuesAlert.show();

           } else {

              try {
                  //insert user details into the database
                  Contact newContact = new Contact();
                  newContact.setName(passedName);
                  newContact.setEmail(passedEmail);
                  newContact.setUsername(passedUsername);
                  newContact.setPassword(passedPassword);
                  newContact.setAge(passedAge);
                  newContact.setAddress(passedAddress);
                  newContact.setGpName(passedGpName);
                  newContact.setGpNumber(passedGpNumber);
                  newContact.setTemperature(fltTemperature);
                  newContact.setLowBloodPressure(strLowBloodPressure);
                  newContact.setHighBloodPressure(strHighBloodPressure);
                  newContact.setHeartRate(strHeartRate);
                  newContact.setBackgroundColour("White");

                  dbHelper.insertContact(newContact);

              } catch (Exception e) {

                  Toast errorE = Toast.makeText(Register3.this, "Data Not Saved!", Toast.LENGTH_LONG);
                  errorE.show();
              }

               Toast successfulRegister = Toast.makeText(Register3.this, "Thankyou for Registering " + passedName + ".", Toast.LENGTH_SHORT);
               successfulRegister.show();

               Intent takeUserToLoginScreen = new Intent(Register3.this, Login.class);
               startActivity(takeUserToLoginScreen);
               //close the activity so the user cannot press 'back' to return
               this.finish();

           }

        }

    }

//==================================================================================================================================\\
}
//==================================================================================================================================\\