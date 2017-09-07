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
public class Register1 extends Activity {

    //variables available in class


//==================================================================================================================================\\
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

    }

//==================================================================================================================================\\
    public void onButtonClick(View v) {

        //if the register button on sign up screen is pressed
        if (v.getId() == R.id.btnContinue_register1Activity) {

            //finding the values entered by user
            EditText name = (EditText) findViewById(R.id.etName_register1Activity);
            EditText email = (EditText) findViewById(R.id.etEmail_register1Activity);
            EditText username = (EditText) findViewById(R.id.etUsername_register1Activity);
            EditText pass1 = (EditText) findViewById(R.id.etPass_register1Activity);
            EditText pass2 = (EditText) findViewById(R.id.etConfirmPass_register1Activity);

            //converting the values to readable strings
            String strName = name.getText().toString();
            String strEmail = email.getText().toString();
            String strUsername = username.getText().toString();
            String strPass1 = pass1.getText().toString();
            String strPass2 = pass2.getText().toString();

            //if the passwords do not match, i.e. if password1 does not match password2
            if(!strPass1.equals(strPass2)) {

                //creating toast message for passwords that do not match
                Toast incorrectPassAlert = Toast.makeText(Register1.this, "Passwords do not match", Toast.LENGTH_SHORT);
                //displaying the toast to user
                incorrectPassAlert.show();

            //or if any of the fields are blank
            } else if (strName.isEmpty() || strEmail.isEmpty() || strUsername.isEmpty()
                    || strPass1.isEmpty() || strPass2.isEmpty()) {

                //show an error to the user, stating to fill all fields
                Toast noInfo = Toast.makeText(Register1.this, "Please fill all boxes", Toast.LENGTH_SHORT);
                noInfo.show();

            //otherwise registration will be complete
            } else {

               Intent takeUsertoRegister2Screen = new Intent(Register1.this, Register2.class);

                //passing strings etc. to other classes
                takeUsertoRegister2Screen.putExtra("Name", strName);
                takeUsertoRegister2Screen.putExtra("Email", strEmail);
                takeUsertoRegister2Screen.putExtra("Username", strUsername);
                takeUsertoRegister2Screen.putExtra("Password", strPass1);

                startActivity(takeUsertoRegister2Screen);
                //close the activity so the user cannot press 'back' to return
                this.finish();

            }
        }
    }

//==================================================================================================================================\\
}
//==================================================================================================================================\\