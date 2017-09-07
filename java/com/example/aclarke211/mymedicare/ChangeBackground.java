//==================================================================================================================================\\
package com.example.aclarke211.mymedicare;
//==================================================================================================================================\\
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

//==================================================================================================================================\\
public class ChangeBackground extends AppCompatActivity {

    //variables
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    String username, bgColour, bgReceivedColour;
    Contact editContact = new Contact();
    BackgroundAnalyser backgroundAnalyser;

//==================================================================================================================================\\
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);

        //retrievng the username of currently logged in user from the Login class
        username = getIntent().getStringExtra("Username");

        bgReceivedColour = dbHelper.searchBackgroundColour(username);
        LinearLayout backgroundLayout = (LinearLayout)findViewById(R.id.changableBackgroundLayout_changeBackgroundActivity);

        backgroundAnalyser = new BackgroundAnalyser();
        backgroundAnalyser.changeBackground(backgroundLayout, bgReceivedColour);

    }

    //==================================================================================================================================\\
    public void onButtonClick(View v) {

        switch (v.getId()) {

            case R.id.btnBlue_changeBackgroundActivity:

                //create a contact with data to be inserted to database
                editContact.setBackgroundColour("Blue");

                //run updateDetails sending the contact creatd above
                dbHelper.updateBackgroundColour(editContact);

                //display a toast confirming to the user that the details have saved
                Toast saveSuccessfulToast = Toast.makeText(ChangeBackground.this, "Background Changed!", Toast.LENGTH_SHORT);
                saveSuccessfulToast.show();

                gotoOverviewScreen();

                break;

            case R.id.btnYellow_changeBackgroundActivity:

                //create a contact with data to be inserted to database
                editContact.setBackgroundColour("Yellow");
                //run updateDetails sending the contact creatd above
                dbHelper.updateBackgroundColour(editContact);
                //display a toast confirming to the user that the details have saved
                Toast saveSuccessfulToast2 = Toast.makeText(ChangeBackground.this, "Background Changed!", Toast.LENGTH_SHORT);
                saveSuccessfulToast2.show();

                gotoOverviewScreen();

                break;

            case R.id.btnWhite_changeBackgroundActivity:

                //create a contact with data to be inserted to database
                editContact.setBackgroundColour("White");
                //run updateDetails sending the contact created above
                dbHelper.updateBackgroundColour(editContact);
                //display a toast confirming to the user that the details have saved
                Toast saveSuccessfulToast3 = Toast.makeText(ChangeBackground.this, "Background Changed!", Toast.LENGTH_SHORT);
                saveSuccessfulToast3.show();

                gotoOverviewScreen();

                break;

        }


    }

    public void gotoOverviewScreen() {
        //new Intent object (Context/attributes of this class, new class to change to)
        Intent i = new Intent(ChangeBackground.this, Overview.class);
        //passing strings etc. to other classes
        i.putExtra("Username", username);
        //open the activity from Intent above
        startActivity(i);

        //close the activity so the user cannot press 'back' to return
        this.finish();
    }

//==================================================================================================================================\\
    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

//==================================================================================================================================\\
                                                    /*Menus*/
//==================================================================================================================================\\
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_background, menu);
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