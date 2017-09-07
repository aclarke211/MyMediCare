//==================================================================================================================================\\
package com.example.aclarke211.mymedicare;
//==================================================================================================================================\\
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

//==================================================================================================================================\\
public class Overview extends AppCompatActivity {

    //variables
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    Validator validator = new Validator();
    BackgroundAnalyser backgroundAnalyser;

    int youngAge = 49;
    int oldAge = 50;

    String username, temperatureStatus, lowBloodPressureStatus, highBloodPressureStatus, heartRateStatus, gpName, gpNumber, bgReceivedColour, message1, message2;

//==================================================================================================================================\\
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
    }

//==================================================================================================================================\\
    @Override
    protected void onStart() {
        super.onStart();

    //retrievng the username of currently logged in user from the Login class
        username = getIntent().getStringExtra("Username");

        //interrogating the database for data about the user, store this data in Strings
        String receivedDbName = dbHelper.searchName(username);
        String receivedDbTemperature = dbHelper.searchTemperature(username);
        String receivedDbLowBloodPressure = dbHelper.searchLowBloodPressure(username);
        String receivedDbHighBloodPressure = dbHelper.searchHighBloodPressure(username);
        String receivedDbHeartRate = dbHelper.searchHeartRate(username);

        String receivedDbAge = dbHelper.searchAge(username);
            int ageInt = Integer.parseInt(receivedDbAge);

        gpName = dbHelper.searchGpName(username);
        gpNumber = dbHelper.searchGpNumber(username);


        //finding TextViews to display the user's information
        TextView tvDisplayUsername = (TextView) findViewById(R.id.tvDisplayUsername_overviewActivity);
        TextView tvDisplayAge = (TextView) findViewById(R.id.tvDisplayAge_overviewActivity);
        TextView tvDisplayTemperature = (TextView) findViewById(R.id.tvDisplayTemperature_overviewActivity);
        TextView tvDisplayLowBloodPressure = (TextView) findViewById(R.id.tvDisplayLowBp_overviewActivity);
        TextView tvDisplayHighBloodPressure = (TextView) findViewById(R.id.tvDisplayHighBp_overviewActivity);
        TextView tvDisplayHeartRate = (TextView) findViewById(R.id.tvDisplayHeartRate_overviewActivity);
        TextView tvDisplayMessage1 = (TextView) findViewById(R.id.tvRiskStatusText_overviewActivity);
        TextView tvDisplayMessage2 = (TextView) findViewById(R.id.tvUserHintText__overviewActivity);

        //find the rows in the table view so they can be altered later in the code
        TableRow trTemperature = (TableRow) findViewById(R.id.trTemp_overviewActivity);
        TableRow trLowBloodPressure = (TableRow) findViewById(R.id.trLowBp_overviewActivity);
        TableRow trHighBloodPressure = (TableRow) findViewById(R.id.trHighBp_overviewActivity);
        TableRow trHeartRate = (TableRow) findViewById(R.id.trHeartRate_overviewActivity);

        //displaying the name acquired from the database
        tvDisplayUsername.setText(receivedDbName);
        //displaying the age aquired from the database
        tvDisplayAge.setText("Age: " + receivedDbAge);

        //displaying the heath values from the database
        tvDisplayTemperature.setText(receivedDbTemperature + "°C");
        tvDisplayLowBloodPressure.setText(receivedDbLowBloodPressure);
        tvDisplayHighBloodPressure.setText(receivedDbHighBloodPressure);
        tvDisplayHeartRate.setText(receivedDbHeartRate);

        //deciding which method to run depending on the age of the user
        if (ageInt <= youngAge) {

            temperatureStatus = validator.checkTemperature(receivedDbTemperature);
            lowBloodPressureStatus = validator.checkLowBloodPressure(receivedDbLowBloodPressure);
            highBloodPressureStatus = validator.checkHighBloodPressure(receivedDbHighBloodPressure);
            heartRateStatus = validator.checkHeartRate(receivedDbHeartRate);

        } else if (ageInt >= oldAge) {

            temperatureStatus = validator.checkOldTemperature(receivedDbLowBloodPressure);
            lowBloodPressureStatus = validator.checkOldLowBloodPressure(receivedDbLowBloodPressure);
            highBloodPressureStatus = validator.checkOldHighBloodPressure(receivedDbHighBloodPressure);
            heartRateStatus = validator.checkOldHeartRate(receivedDbHeartRate);

        }

        //check which colour to display for each of the user's health values
        checkStatus(temperatureStatus, trTemperature);
        checkStatus(lowBloodPressureStatus, trLowBloodPressure);
        checkStatus(highBloodPressureStatus, trHighBloodPressure);
        checkStatus(heartRateStatus, trHeartRate);


        //default values for the messages for when the user is healthy
        message1 = "No Risk" ;
        message2 = "Stay Healthy!" ;

        //if any of the values are of high risk
        if (temperatureStatus == "High Risk" || lowBloodPressureStatus == "High Risk" || highBloodPressureStatus == "High Risk" || heartRateStatus == "High Risk") {

            message1 = "High Risk";
            message2 = "You should contact your GP.";

        }

       //or if any of the values are of low risk
        else if (temperatureStatus == "Low Risk" || lowBloodPressureStatus == "Low Risk" || highBloodPressureStatus == "Low Risk" || heartRateStatus == "Low Risk") {

            message1 = "Low Risk";
            message2 = "Please watch your health.";

        }

        tvDisplayMessage1.setText(message1);
        tvDisplayMessage2.setText(message2);

        bgReceivedColour = dbHelper.searchBackgroundColour(username);
        LinearLayout backgroundLayout = (LinearLayout)findViewById(R.id.changableBackgroundLayout_overviewActivity);

        backgroundAnalyser = new BackgroundAnalyser();
        backgroundAnalyser.changeBackground(backgroundLayout, bgReceivedColour);

    }

//==================================================================================================================================\\
    public void onButtonClick(View v) {

        switch (v.getId()) {

            case R.id.btnContactGp_overviewActivity:

                //new Intent object (Context/attributes of this class, new class to change to)
                Intent goToMessageGpScreen = new Intent(Overview.this, MessageGP.class);
                //passing strings etc. to other classes
                goToMessageGpScreen.putExtra("Username", username);
                goToMessageGpScreen.putExtra("GpName", gpName);
                goToMessageGpScreen.putExtra("GpNumber", gpNumber);
                //open the activity from Intent above
                startActivity(goToMessageGpScreen);
                //close the activity so the user cannot press 'back' to return
                this.finish();

                break;

            case R.id.btnEditDetails_overviewActivity:

                //new Intent object (Context/attributes of this class, new class to change to)
                Intent goToEditDetailsScreen = new Intent(Overview.this, EditDetails.class);
                //passing strings etc. to other classes
                goToEditDetailsScreen.putExtra("Username", username);
                //open the activity from Intent above
                startActivity(goToEditDetailsScreen);
                //close the activity so the user cannot press 'back' to return
                this.finish();


                break;

            case R.id.btnUpdateValues_overviewActivity:
                //provide context of this class and state which screen to move to
                Intent goToUpdateValuesScreen = new Intent(Overview.this, UpdateValues.class);
                //passing strings etc. to other classes
                goToUpdateValuesScreen.putExtra("Username", username);
                //open the activity from Intent above
                startActivity(goToUpdateValuesScreen);
                //close the activity so the user cannot press 'back' to return
                this.finish();

                break;

        }

    }




    //==================================================================================================================================\\
    public void checkStatus(String status, TableRow tableRow) {

        switch (status) {

            case "No Risk":

                tableRow.setBackgroundResource(R.drawable.no_risk_background);

                break;

            case "Low Risk":

                tableRow.setBackgroundResource(R.drawable.low_risk_background);

                break;

            case "High Risk":

                tableRow.setBackgroundResource(R.drawable.high_risk_background);

                break;
        }
    }


//==================================================================================================================================\\
                                                    /*Menus*/
//==================================================================================================================================\\
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
        return true;
    }

    //==================================================================================================================================\\
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.background_menu_button) {

            //new Intent object (Context/attributes of this class, new class to change to)
            Intent goToChangeBackgroundScreen = new Intent (Overview.this, ChangeBackground.class);
            //passing strings etc. to other classes
            goToChangeBackgroundScreen.putExtra("Username", username);
            //open the activity from Intent above
            startActivity(goToChangeBackgroundScreen);
            //close the activity so the user cannot press 'back' to return
            this.finish();

        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout_menu_button) {

            //when the option in menu is clicked, take the user back to the main activity screen
            startActivity(new Intent(this, MainActivity.class));
            //close the activity so the user cannot press 'back' to return
            this.finish();

        }

        return super.onOptionsItemSelected(item);
    }

//==================================================================================================================================\\
}
//==================================================================================================================================\\