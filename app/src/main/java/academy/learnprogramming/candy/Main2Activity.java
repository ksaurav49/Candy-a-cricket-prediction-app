package academy.learnprogramming.candy;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Timer;
import java.util.TimerTask;

import academy.learnprogramming.candy.response.TeamResponse;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView t1,t2,t3,run,wicket,over,batsman1,run1,ball1,batsman2,run2,ball2,fav,ov;
    Button lgai,khai,yes,no;
     MainActivityViewModel model = null;
    Timer timer = new Timer();
    //timer = new Timer();

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main2);
      try {
          if (!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
              //Toast.makeText(this, "login nhi hai", Toast.LENGTH_SHORT).show();
              startActivity(new Intent(getApplicationContext(), Main.class));
          }


          model = ViewModelProviders.of(this).get(MainActivityViewModel.class);
          Toolbar toolbar = findViewById(R.id.toolbar);
          t1 = findViewById(R.id.team1);
          t2 = findViewById(R.id.team2);
          t3 = findViewById(R.id.vs);
          run = findViewById(R.id.r);
          wicket = findViewById(R.id.w);
          over = findViewById(R.id.o);
          batsman1 = findViewById(R.id.bt1);
          batsman2 = findViewById(R.id.bt2);
          run1 = findViewById(R.id.r1);
          run2 = findViewById(R.id.r2);
          ball1 = findViewById(R.id.b1);
          ball2 = findViewById(R.id.b2);
          lgai = findViewById(R.id.lgai);
          khai = findViewById(R.id.khai);
          yes = findViewById(R.id.yes);
          no = findViewById(R.id.no);
          fav = findViewById(R.id.fav);
          ov = findViewById(R.id.ov);
          setSupportActionBar(toolbar);
          getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
          YoYo.with(Techniques.Landing).duration(1000).repeat(0).playOn(yes);

          if (savedInstanceState == null) {

              LiveData<TeamResponse> myRandomNumber = model.getNumber();

              myRandomNumber.observe(this, new Observer<TeamResponse>() {
                  @Override
                  public void onChanged(@Nullable TeamResponse teamResponse) {
                      //Log.d("y","iska v ho gya");
                      t1.setText(teamResponse.getFteam());
                      t2.setText(teamResponse.getSteam());
                      run.setText(teamResponse.getRun());
                      wicket.setText(teamResponse.getWicket());
                      over.setText(teamResponse.getOver());
                      batsman1.setText(teamResponse.getFname());
                      batsman2.setText(teamResponse.getSname());
                      run1.setText(teamResponse.getFrun());
                      run2.setText(teamResponse.getSrun());
                      ball1.setText(teamResponse.getFball());
                      ball2.setText(teamResponse.getSball());
                      lgai.setText(teamResponse.getLgai());
                      khai.setText(teamResponse.getKhai());
                      yes.setText(teamResponse.getYes());
                      no.setText(teamResponse.getNo());
                      fav.setText(teamResponse.getFavourite());
                      ov.setText(teamResponse.getOv());
                  }
              });


              timer.schedule(new TimerTask() {
                  @Override
                  public void run() { // Function runs every MINUTES minutes.
                      // Run the code you want here

                      // Log.d("here is","the number we want to display");
                      model.createNumber();
                      // If the function you wanted was static
                  }
              }, 0, 1000);

          } else {
              timer.cancel();
              model.onCleared();

          }
          t3.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {


              }
          });

          lgai.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent i = new Intent(view.getContext(), chat.class);

                  i.putExtra("lgai", "" + lgai.getText().toString());
                  i.putExtra("khai", "");
                  i.putExtra("yes", "");
                  i.putExtra("no", "");
                  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  startActivity(i);
                  Main2Activity.this.finish();

              }
          });
          khai.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent i = new Intent(view.getContext(), chat.class);
                  i.putExtra("khai", "" + khai.getText().toString());
                  i.putExtra("lgai", "");
                  i.putExtra("yes", "");
                  i.putExtra("no", "");
                  startActivity(i);
              }
          });
          yes.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent i = new Intent(view.getContext(), chat.class);
                  i.putExtra("yes", "" + yes.getText().toString());
                  i.putExtra("lgai", "");
                  i.putExtra("khai", "");
                  i.putExtra("no", "");
                  startActivity(i);
              }
          });
          no.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent i = new Intent(view.getContext(), chat.class);
                  i.putExtra("no", "" + no.getText().toString());
                  i.putExtra("lgai", "");
                  i.putExtra("khai", "");
                  i.putExtra("yes", "");
                  startActivity(i);
              }
          });
          FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
          fab.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Intent i = new Intent(view.getContext(), chat.class);
                  i.putExtra("lgai", "");
                  i.putExtra("khai", "");
                  i.putExtra("yes", "");
                  i.putExtra("no", "");
                  startActivity(i);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
              }
          });

          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
          ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                  this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
          drawer.addDrawerListener(toggle);
          toggle.syncState();

          NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
          navigationView.setNavigationItemSelectedListener(this);
          View headerView = navigationView.getHeaderView(0);
          TextView name = (TextView) headerView.findViewById(R.id.uname);
          TextView candy = (TextView) headerView.findViewById(R.id.candy);


          name.setText("" + SharedPrefManager.getInstance(getApplicationContext()).getUser().getName());
          candy.setText("CANDY :" + SharedPrefManager.getInstance(getApplicationContext()).getUser().getCandy());
      }catch (Exception e){

      }
        }

        @Override
        public void onBackPressed () {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main2, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                Intent i = new Intent(getApplicationContext(), Main.class);
                startActivity(i);
                return true;
            } else if (id == R.id.terms) {
                Intent i = new Intent(getApplicationContext(), UserChat.class);
                startActivity(i);
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected (MenuItem item){
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_camera) {
                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.putExtra(Intent.EXTRA_TEXT,"Sir i want to add money in cricket app");
                sendIntent.setComponent(new  ComponentName("com.whatsapp","com.whatsapp.Conversation"));
                sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.ContactPicker"));

                sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators("917987901419")+"@s.whatsapp.net");

                startActivity(sendIntent);
            } else if (id == R.id.nav_gallery) {
                Toast.makeText(this, "Whatsapp number: 7987901419", Toast.LENGTH_SHORT).show();

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);


            return true;
        }



        @Override
        protected void onPause () {
            timer.cancel();

            Intent intent = new Intent(Main2Activity.this, MainActivityViewModel.class);
            stopService(intent);
            model.onCleared();

            super.onPause();
        }

        @Override
        protected void onDestroy () {
//timer.cancel();
            model.onCleared();
            super.onDestroy();

        }

}
