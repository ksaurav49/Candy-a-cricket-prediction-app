package academy.learnprogramming.candy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import academy.learnprogramming.candy.ApiUrl.ApiInterface;
import academy.learnprogramming.candy.ApiUrl.ApiUrl;
import academy.learnprogramming.candy.response.LoginResponse;
import academy.learnprogramming.candy.response.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends Fragment {
    View view;
    Button button;
    EditText username,password;
    TextView t1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment


        view = inflater.inflate(R.layout.login, container, false);
        button = view.findViewById(R.id.btn);
        username = view.findViewById(R.id.name);
        password = view.findViewById(R.id.pass);
        t1 = view.findViewById(R.id.t);



        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(view.findViewById(R.id.rl));
        YoYo.with(Techniques.RotateInUpLeft).duration(1000).repeat(0).playOn(view.findViewById(R.id.li));
        YoYo.with(Techniques.Flash).duration(6000).repeat(Animation.INFINITE).playOn(view.findViewById(R.id.lt));
        YoYo.with(Techniques.StandUp).duration(1000).repeat(0).playOn(button);
        //YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(view.findViewById(R.id.cr));
        YoYo.with(Techniques.StandUp).duration(1500).repeat(0).playOn(username);
        YoYo.with(Techniques.StandUp).duration(1500).repeat(0).playOn(password);




       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String s1,s2;
               s1=username.getText().toString();
               s2=password.getText().toString();
               if(s1.equalsIgnoreCase("")){
                   YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(username);
                   if(s2.equalsIgnoreCase("")){
                       YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(password);
                   }
               }else if(s2.equalsIgnoreCase("")){
                   YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(password);
                   if(s1.equalsIgnoreCase("")){
                       YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(username);
                   }
               }else{
                   final ProgressDialog progressDialog = new ProgressDialog(getContext());;
                   progressDialog.setMessage("Please Wait...");
                   progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                   progressDialog.show();

                   Retrofit retrofit = new Retrofit.Builder()
                           .baseUrl(ApiUrl.BASE_URL)
                           .addConverterFactory(GsonConverterFactory.create())
                           .build();
                   ApiInterface apiService = retrofit.create(ApiInterface.class);
                   Call<LoginResponse> call = apiService.LoginResponse(s1, s2);
                   call.enqueue(new Callback<LoginResponse>() {
                       @Override
                       public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                           progressDialog.dismiss();
                           if (response.body().getSuccess().equalsIgnoreCase("yes")) {
                               String name = response.body().getName();
                               String email = response.body().getEmail();
                               String id = response.body().getId();
                               String mobile = response.body().getMobile();
                               String adress = response.body().getAdress();
                               String candy = response.body().getCandy();

                               SharedPrefManager.getInstance(getContext()).userLogin(name, mobile, email, id, adress,candy);
                               String n = SharedPrefManager.getInstance(getContext()).getUser().getAdress();
                               Intent i = new Intent(getContext(), Main2Activity.class);
                               startActivity(i);

                               //Toast.makeText(getContext(), "" + n, Toast.LENGTH_SHORT).show();
                           } else {
                               Toast.makeText(getContext(), "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<LoginResponse> call, Throwable t) {

                       }

                   });
               }

           }
       });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register t = new Register();
                Bundle args = new Bundle();
                args.putString("YourKey", "jhg");
                t.setArguments(args);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack("first");
                transaction.replace(R.id.fl,t,"second");
                transaction.commit();

            }
        });

        return view;
    }

}
