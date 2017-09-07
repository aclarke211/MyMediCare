//==================================================================================================================================\\
package com.example.aclarke211.mymedicare;
//==================================================================================================================================\\
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//==================================================================================================================================\\
public class Register2 extends Activity {

    //variables
    public String CONTACT_NUMBER;

//==================================================================================================================================\\
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        Button openContactsButton = (Button)findViewById(R.id.btnContacts_register2Activity);
        final EditText gpNumberEditText = (EditText)findViewById(R.id.etGpNumber_register2Activity);
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
    }

//==================================================================================================================================\\
    public void onButtonClick(View v) {

        //if the register button on sign up screen is pressed
        switch (v.getId()) {

            case R.id.btnContinue_register2Activity:

            //retrieving Strings etc. from other classes
            String passedName = getIntent().getStringExtra("Name");
            String passedEmail = getIntent().getStringExtra("Email");
            String passedUsername = getIntent().getStringExtra("Username");
            String passedPassword = getIntent().getStringExtra("Password");

            //finding the values entered by user
            EditText age = (EditText) findViewById(R.id.etAge_register2Activity);
            EditText address = (EditText) findViewById(R.id.etAddress_register2Activity);
            EditText gpName = (EditText) findViewById(R.id.etGpName_register2Activity);
            EditText gpNumber = (EditText) findViewById(R.id.etGpNumber_register2Activity);

            //converting the values to readable strings
            String strAge = age.getText().toString();
                int intAge = Integer.parseInt(strAge);
            String strAddress = address.getText().toString();
            String strGpName = gpName.getText().toString();
            String strGpNumber = gpNumber.getText().toString();

            if(strAge.isEmpty() || strAddress.isEmpty() || strGpName.isEmpty() || strGpNumber.isEmpty()) {

                //show an error to the user, stating to fill all fields
                Toast noInfo = Toast.makeText(Register2.this, "Please fill all boxes", Toast.LENGTH_SHORT);
                noInfo.show();

            } else if (intAge <= -1 || intAge >= 255) {

                Toast incorrectAgeAlert = Toast.makeText(Register2.this, "Please enter a valid age", Toast.LENGTH_SHORT);
                incorrectAgeAlert.show();

            } else {

                Intent takeUsertoRegister3Screen = new Intent(Register2.this, Register3.class);

                //passing strings etc. to other classes
                takeUsertoRegister3Screen.putExtra("Name", passedName);
                takeUsertoRegister3Screen.putExtra("Email", passedEmail);
                takeUsertoRegister3Screen.putExtra("Username", passedUsername);
                takeUsertoRegister3Screen.putExtra("Password", passedPassword);

                takeUsertoRegister3Screen.putExtra("Age", strAge);
                takeUsertoRegister3Screen.putExtra("Address", strAddress);
                takeUsertoRegister3Screen.putExtra("GpName", strGpName);
                takeUsertoRegister3Screen.putExtra("GpNumber", strGpNumber);

                startActivity(takeUsertoRegister3Screen);
                //close the activity so the user cannot press 'back' to return
                this.finish();

            }

                break;
        }
    }

//==================================================================================================================================\\
    public void showSelectedNumber(int type, String number) {
        CONTACT_NUMBER = number.toString();

        EditText ed=(EditText)findViewById(R.id.etGpNumber_register2Activity);
        ed.setText(CONTACT_NUMBER);
    }
    @Override
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