//==================================================================================================================================\\
package com.example.aclarke211.mymedicare;
//==================================================================================================================================\\
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

//==================================================================================================================================\\
public class EditDetails extends Activity {

    //variables
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    String username, receivedDbAge, receivedDbAddress, receivedDbGpName, receivedDbGpNumber, bgReceivedColour;
    EditText age, address, gpName, gpNumber;
    BackgroundAnalyser backgroundAnalyser;

    public String CONTACT_NUMBER;

    //==================================================================================================================================\\
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
    }

    //==================================================================================================================================\\
    @Override
    protected void onStart() {
        super.onStart();

        //retrievng the username of currently logged in user from the Overview class
        username = getIntent().getStringExtra("Username");

        receivedDbAge = dbHelper.searchAge(username);
        receivedDbAddress = dbHelper.searchAddress(username);
        receivedDbGpName = dbHelper.searchGpName(username);
        receivedDbGpNumber = dbHelper.searchGpNumber(username);

        //finding the values entered by user
        age = (EditText) findViewById(R.id.etAge_editDetailsActivity);
        address = (EditText) findViewById(R.id.etAddress_editDetailsActivity);
        gpName = (EditText) findViewById(R.id.etGpName_editDetailsActivity);
        gpNumber = (EditText) findViewById(R.id.etGpNumber_editDetailsActivity);

        age.setText(receivedDbAge);
        address.setText(receivedDbAddress);
        gpName.setText(receivedDbGpName);
        gpNumber.setText(receivedDbGpNumber);

        Button openContactsButton = (Button)findViewById(R.id.btnContacts_editDetailsActivity);
        final EditText gpNumberEditText = (EditText)findViewById(R.id.etGpNumber_editDetailsActivity);
        gpNumberEditText.setText(CONTACT_NUMBER);


        openContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open a screen where the contacts can be accessed
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, 1);
                gpNumberEditText.setText(CONTACT_NUMBER);
            }
        });

        bgReceivedColour = dbHelper.searchBackgroundColour(username);
        LinearLayout backgroundLayout = (LinearLayout)findViewById(R.id.changableBackgroundLayout_editDetailsActivity);

        backgroundAnalyser = new BackgroundAnalyser();
        backgroundAnalyser.changeBackground(backgroundLayout, bgReceivedColour);

    }

//==================================================================================================================================\\
    public void onButtonClick(View v) {

        switch (v.getId()) {

            case R.id.btnConfirm_editDetailsActivity:

                //converting the values to readable strings
                String strAge = age.getText().toString();
                int intAge = Integer.parseInt(strAge);
                String strAddress = address.getText().toString();
                String strGpName = gpName.getText().toString();
                String strGpNumber = gpNumber.getText().toString();



                if (strAge.isEmpty() || strAddress.isEmpty() || strGpName.isEmpty() || strGpNumber.isEmpty()) {

                    //show an error to the user, stating to fill all fields
                    Toast noInfo = Toast.makeText(EditDetails.this, "Please fill all boxes", Toast.LENGTH_SHORT);
                    noInfo.show();

                }else if (intAge <= 0 || intAge >= 255) {

                        Toast incorrectAgeAlert = Toast.makeText(EditDetails.this, "Please enter a valid age", Toast.LENGTH_SHORT);
                        incorrectAgeAlert.show();

                } else {

                    //create a contact with data to be read by database
                    Contact editContact = new Contact();
                    editContact.setAge(strAge);
                    editContact.setAddress(strAddress);
                    editContact.setGpName(strGpName);
                    editContact.setGpNumber(strGpNumber);

                    //run updateDetails sending the contact creatd above
                    dbHelper.updateDetails(editContact);

                    //display a toast confirming to the user that the details have saved
                    Toast saveSuccessfulToast = Toast.makeText(EditDetails.this, "Details Saved!", Toast.LENGTH_SHORT);
                    saveSuccessfulToast.show();

                    //new Intent object (Context/attributes of this class, new class to change to)
                    Intent i = new Intent(EditDetails.this, Overview.class);
                    //passing strings etc. to other classes
                    i.putExtra("Username", username);
                    //open the activity from Intent above
                    startActivity(i);

                    //close the activity so the user cannot press 'back' to return
                    this.finish();

                }

                break;

            //see if the back button was pressed via its ID
            case R.id.btnBack_editDetailsActivity:

                //new Intent object (Context/attributes of this class, new class to change to)
                Intent i = new Intent(EditDetails.this, Overview.class);
                //passing strings etc. to other classes
                i.putExtra("Username", username);
                //open the activity from Intent above
                startActivity(i);

                //close the activity so the user cannot press 'back' to return
                this.finish();

                break;

        }

    }

//==================================================================================================================================\\
    //display the number selected from contacts
    public void showSelectedNumber(int type, String number) {
        CONTACT_NUMBER = number.toString();

        EditText ed = (EditText)findViewById(R.id.etGpNumber_editDetailsActivity);
        ed.setText(CONTACT_NUMBER);
    }

//==================================================================================================================================\\
    @Override
    //finding the number of selected contact
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                Cursor c = null;
                try {
                    c = getContentResolver().query(uri, new String[]{
                                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                                    ContactsContract.CommonDataKinds.Phone.TYPE },
                            null, null, null);
                    if (c != null && c.moveToFirst()) {
                        String number = c.getString(0);
                        int type = c.getInt(1);
                        showSelectedNumber(type, number);
                    }
                } finally {
                    if (c != null) {
                        c.close();
                    }
                }
            }
        }
    }

//==================================================================================================================================\\
}
//==================================================================================================================================\\