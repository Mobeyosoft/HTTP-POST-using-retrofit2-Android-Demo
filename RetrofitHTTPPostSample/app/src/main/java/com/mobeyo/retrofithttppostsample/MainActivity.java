package com.mobeyo.retrofithttppostsample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mobeyo.retrofithttppostsample.api.APIService;
import com.mobeyo.retrofithttppostsample.api.APIUrl;
import com.mobeyo.retrofithttppostsample.api.Result;
import com.mobeyo.retrofithttppostsample.api.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtUserName, txtPassword;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                String name = txtName.getText().toString();
                String userName = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();
                callHTTPPOSTMethod(name, userName, password);
            }
        });
    }

    private  void initialize(){
        txtName = (EditText)findViewById(R.id.txtName);
        txtUserName = (EditText)findViewById(R.id.txtUserName);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);

    }

    private void closeKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    private void callHTTPPOSTMethod(String name, String username, String password){

            //You can show a progress dialog here

            //building retrofit object
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIUrl.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            //Defining retrofit api service
            APIService service = retrofit.create(APIService.class);

            //Defining the user object as we need to pass it with the call
            User user = new User(name, username, password);

            //defining the API call
            Call<Result> call = service.createUser(
                    user.getName(),
                    user.getUsername(),
                    user.getPassword()
            );

            //calling the api
            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    //hiding progress dialog
                    //displaying the message from the response
                    Snackbar.make(btnSubmit, response.body().getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    //hiding progress dialog

                    Snackbar.make(btnSubmit, t.getMessage(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
            });
        }

}
