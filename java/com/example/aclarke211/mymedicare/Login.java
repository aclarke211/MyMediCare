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
public class Login extends Activity {

    //variables available to class
    DatabaseHelper dbHelper = new DatabaseHelper(this);

//==================================================================================================================================\\
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    //=========================================================================================================================================\\
    public void onButtonClick(View v) {

        switch (v.getId()) {

            case R.id.btnLogin_loginActivity: {
                //finding the user inputted username
                EditText etUsername = (EditText) findViewById(R.id.etUsername_loginActivity);
                //converting it to a string
                String strUsername = etUsername.getText().toString();

                EditText etPassword = (EditText) findViewById(R.id.etPassword_loginActivity);
                String strPassword = etPassword.getText().toString();

                String password = dbHelper.searchPassword(strUsername);

                if (strPassword.equals(password)) {

                    //new Intent object (Context/attributes of this class, new class to change to)
                    Intent i = new Intent(Login.this, Overview.class);
                    //passing strings etc. to other classes
                    i.putExtra("Username", strUsername);
                    //open the activity from Intent above
                    startActivity(i);

                    //close the activity so the user cannot press 'back' to return
                    this.finish();


                } else if (strUsername.isEmpty() || strPassword.isEmpty()) {

                    Toast noInfo = Toast.makeText(Login.this, "Please fill all boxes", Toast.LENGTH_SHORT);
                    noInfo.show();

                } else {

                    //creating toast message for passwords that do not match
                    Toast wrongDetailsAlert = Toast.makeText(Login.this, "Incorrect Details", Toast.LENGTH_SHORT);
                    //displaying the toast to user
                    wrongDetailsAlert.show();

                }

                break;
            }

            case R.id.btnRegister_loginActivity: {

                //new Intent object (Context/attributes of this class, new class to change to)
                Intent i = new Intent(Login.this, Register1.class);
                //open the activity from Intent above
                startActivity(i);

                //close the activity so the user cannot press 'back' to return
                this.finish();

            }
        }
    }

//==================================================================================================================================\\
}
//==================================================================================================================================\\