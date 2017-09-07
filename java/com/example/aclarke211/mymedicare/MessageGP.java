//==================================================================================================================================\\
package com.example.aclarke211.mymedicare;
//==================================================================================================================================\\
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//==================================================================================================================================\\
public class MessageGP extends AppCompatActivity {

    DatabaseHelper dbHelper = new DatabaseHelper(this);
    String username, gpName, gpNumber, strMessage, bgReceivedColour;
    EditText etMessage;
    TextView tvGpName, tvGpNumber;
    BackgroundAnalyser backgroundAnalyser = new BackgroundAnalyser();

//==================================================================================================================================\\
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_gp);
    }

//==================================================================================================================================\\
    @Override
    protected void onStart() {
        super.onStart();

        //retrievng the username of currently logged in user from the Login class
        username = getIntent().getStringExtra("Username");
        gpName = getIntent().getStringExtra("GpName");
        gpNumber = getIntent().getStringExtra("GpNumber");


        etMessage = (EditText) findViewById(R.id.etMessage_messageGPActivity);

        tvGpName = (TextView) findViewById(R.id.tvGpName_messageGPActivity);
            tvGpName.setText(gpName);
        tvGpNumber = (TextView) findViewById(R.id.tvGpNumber_messageGPActivity);
            tvGpNumber.setText(gpNumber);

        bgReceivedColour = dbHelper.searchBackgroundColour(username);
        LinearLayout backgroundLayout = (LinearLayout)findViewById(R.id.changableBackgroundLayout_messageGPActivity);

        backgroundAnalyser.changeBackground(backgroundLayout, bgReceivedColour);

        }

//==================================================================================================================================\\
    private void sendSMS(){

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(gpNumber, null, strMessage, null, null);

    }

//==================================================================================================================================\\
    public void onButtonClick(View v) {

        switch (v.getId()) {

            case R.id.btnSendMessage_messageGPActivity:

                strMessage = etMessage.getText().toString();

                if (strMessage.isEmpty()) {

                    Toast.makeText(MessageGP.this, "Please insert a message.", Toast.LENGTH_SHORT).show();

                } else {

                    sendSMS();
                    Toast.makeText(getBaseContext(), "Message sent to: " + gpName,
                            Toast.LENGTH_SHORT).show();
                    goToOverviewScreen();
                }

                break;

            case R.id.btnBack_messageGPActivity:

                goToOverviewScreen();

                break;

        }

    }

//==================================================================================================================================\\
    public void goToOverviewScreen() {

        //new Intent object (Context/attributes of this class, new class to change to)
        Intent goBacktoOverviewScreen = new Intent(MessageGP.this, Overview.class);
        //passing strings etc. to other classes
        goBacktoOverviewScreen.putExtra("Username", username);
        //open the activity from Intent above
        startActivity(goBacktoOverviewScreen);

        //close the activity so the user cannot press 'back' to return
        this.finish();

    }

//==================================================================================================================================\\
                                                    /*Menus*/
//==================================================================================================================================\\
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message_g, menu);
        return true;
    }

//==================================================================================================================================\\
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout_menu_button) {

            //when the option in menu is clicked, take the user back to the main activity screen
            startActivity(new Intent(this, MainActivity.class));

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

//==================================================================================================================================\\
}
//==================================================================================================================================\\