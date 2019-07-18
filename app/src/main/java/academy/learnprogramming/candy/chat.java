package academy.learnprogramming.candy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import academy.learnprogramming.candy.ApiUrl.ApiInterface;
import academy.learnprogramming.candy.ApiUrl.ApiUrl;
import academy.learnprogramming.candy.response.ChatResponse;
import academy.learnprogramming.candy.response.MessageResponse;
import academy.learnprogramming.candy.response.TeamResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import static java.security.AccessController.getContext;

public class chat extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    String lgai,khai,yes,no,b,a,c;
    EditText editText;
    ImageButton imageButton;
    RecyclerView recyclerView;
    List<ChatResponse> chatResponse,chatres;
    ChatAdapter chatAdapter;

    Timer timer = new Timer();
    //timer = new Timer();

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_chat);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //model= ViewModelProviders.of(this).get(ChatViewModel.class);



            toolbar = findViewById(R.id.toolbar);
            editText = findViewById(R.id.edittext_chat);
            imageButton = findViewById(R.id.button_chat_send);
            recyclerView = findViewById(R.id.reycler_chat);


            Intent i = getIntent();
            lgai = i.getStringExtra("lgai");
            khai = i.getStringExtra("khai");
            yes = i.getStringExtra("yes");
            no = i.getStringExtra("no");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), Main2Activity.class);
                    startActivity(i);
                }
            });
            toolbar.setTitleTextColor(Color.WHITE);



         a = SharedPrefManager.getInstance(getApplicationContext()).getUser().getId();
         c = SharedPrefManager.getInstance(getApplicationContext()).getUser().getName();



        if (!lgai.equalsIgnoreCase("")) {
            //Toast.makeText(this, "" + lgai, Toast.LENGTH_SHORT).show();
            b=lgai;
            b="lgai: "+b;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUrl.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface apiService = retrofit.create(ApiInterface.class);
            Call<MessageResponse> call = apiService.message(c, "admin", b, a);
            call.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    if (response.body().getSuccess().equalsIgnoreCase("yes")) {
                        //Toast.makeText(chat.this, "ho gya", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {

                }
            });
        } if (!khai.equalsIgnoreCase("")) {
            //Toast.makeText(this, "" + khai, Toast.LENGTH_SHORT).show();
            b=khai;
            b="khai: "+b;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUrl.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface apiService = retrofit.create(ApiInterface.class);
            Call<MessageResponse> call = apiService.message(c, "admin", b, a);
            call.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    if (response.body().getSuccess().equalsIgnoreCase("yes")) {
                       // Toast.makeText(chat.this, "ho gya", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {

                }
            });
        } if (!yes.equalsIgnoreCase("")) {
            //Toast.makeText(this, "" + yes, Toast.LENGTH_SHORT).show();
            b = yes;
            b = "yes: " + b;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUrl.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface apiService = retrofit.create(ApiInterface.class);
            Call<MessageResponse> call = apiService.message(c, "admin", b, a);
            call.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    if (response.body().getSuccess().equalsIgnoreCase("yes")) {
                       // Toast.makeText(chat.this, "ho gya", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {

                }
            });
        } if (!no.equalsIgnoreCase("")) {
            //Toast.makeText(this, "" + no, Toast.LENGTH_SHORT).show();
            b=no;
            b="no: "+b;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUrl.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface apiService = retrofit.create(ApiInterface.class);
            Call<MessageResponse> call = apiService.message(c, "admin", b, a);
            call.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    if (response.body().getSuccess().equalsIgnoreCase("yes")) {
                       // Toast.makeText(chat.this, "ho gya", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {

                }
            });
        }

            imageButton.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View view) {
                    b = editText.getText().toString();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ApiUrl.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ApiInterface apiService = retrofit.create(ApiInterface.class);
                    Call<MessageResponse> call = apiService.message(c, "admin", b, a);
                    call.enqueue(new Callback<MessageResponse>() {
                        @Override
                        public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                            if (response.body().getSuccess().equalsIgnoreCase("yes")) {
                              //  Toast.makeText(chat.this, "ho gya", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<MessageResponse> call, Throwable t) {

                        }
                    });
                }
            });

            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            layoutManager.setReverseLayout(true);
            recyclerView.setLayoutManager(layoutManager);
            chatAdapter = new ChatAdapter(getApplicationContext(), chatResponse);
            recyclerView.setAdapter(chatAdapter);

            final String a = SharedPrefManager.getInstance(getApplicationContext()).getUser().getId();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUrl.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface apiService = retrofit.create(ApiInterface.class);
            Call<List<ChatResponse>> call = apiService.chat(a);
            call.enqueue(new Callback<List<ChatResponse>>() {
                @Override
                public void onResponse(Call<List<ChatResponse>> call, Response<List<ChatResponse>> response) {
                    chatResponse = response.body();
                    chatAdapter.setChatResponses(chatResponse);
                    //Toast.makeText(chat.this, "aya hi", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<List<ChatResponse>> call, Throwable t) {

                }
            });

            // Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() { // Function runs every MINUTES minutes.
                    // Run the code you want here
                    Log.d("here is", "the number we want to display");
                    runOnUiThread(new Runnable() {
                        public void run() {

                            Log.d("here is", "the letter we want to display");

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(ApiUrl.BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            ApiInterface apiService = retrofit.create(ApiInterface.class);
                            Call<List<ChatResponse>> call = apiService.chat(a);
                            call.enqueue(new Callback<List<ChatResponse>>() {
                                @Override
                                public void onResponse(Call<List<ChatResponse>> call, Response<List<ChatResponse>> response) {
                                    try{
                                    List<ChatResponse> chatRes = response.body();
                                    List<ChatResponse> chatRespons = chatAdapter.getdata();
                                        if (chatRespons.size() == chatRes.size()) {
                                            // Toast.makeText(chat.this, "size match", Toast.LENGTH_SHORT).show();
                                        } else {
                                            try {

                                               // Toast.makeText(chat.this, "New message from admin", Toast.LENGTH_SHORT).show();
                                                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                                                r.play();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }


                                            //Toast.makeText(chat.this, "hi", Toast.LENGTH_SHORT).show();
                                            //chatAdapter.setChatResponses(null);
                                            chatAdapter.setChatResponses(chatRes);
                                            //chatAdapter.setChatResponses(chatResponse);
//                                          LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//                                          layoutManager.setReverseLayout(true);
//                                          recyclerView.setLayoutManager(layoutManager);
//                                          chatAdapter = new ChatAdapter(getApplicationContext(), chatResponse);
//                                          recyclerView.setAdapter(chatAdapter);
                                        }

                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }



                                    //Log.d("data to aya hi",""+""+chatResponses.size());

                                    //Toast.makeText(context, ""+chatResponses.size(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<List<ChatResponse>> call, Throwable t) {

                                }
                            });

                        }
                    });


                    //}
                    // If the function you wanted was static
                }

            }, 0, 2000);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
       // timer.cancel();

        //Intent intent = new Intent(Main2Activity.this, MainActivityViewModel.class);
        //stopService(intent);
        //model.onCleared();

        super.onPause();
    }

    @Override
    protected void onDestroy() {
//timer.cancel();
        //model.onCleared();
        super.onDestroy();
    }


}
