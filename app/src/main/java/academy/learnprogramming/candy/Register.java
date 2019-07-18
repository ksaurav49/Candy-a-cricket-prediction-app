package academy.learnprogramming.candy;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import academy.learnprogramming.candy.ApiUrl.ApiInterface;
import academy.learnprogramming.candy.ApiUrl.ApiUrl;
import academy.learnprogramming.candy.response.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends Fragment {


    View view;
    EditText name,mobile,password,email;
    Button register;
    RelativeLayout relativeLayout;
    ImageView imageView;
    TextView t1,t2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment

        view = inflater.inflate(R.layout.register, container, false);
        name = view.findViewById(R.id.nam);
        mobile = view.findViewById(R.id.mobile);
        password = view.findViewById(R.id.pas);
        email = view.findViewById(R.id.email);
        register = view.findViewById(R.id.btn1);
        relativeLayout = view.findViewById(R.id.re);
        imageView = view.findViewById(R.id.rimg);
        t1= view.findViewById(R.id.rt);
        t2= view.findViewById(R.id.rlo);
        //animation
        YoYo.with(Techniques.FadeIn).duration(1500).repeat(0).playOn(relativeLayout);
        YoYo.with(Techniques.RotateInUpLeft).duration(1000).repeat(0).playOn(imageView);
        YoYo.with(Techniques.Flash).duration(6000).repeat(Animation.INFINITE).playOn(t1);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(register);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(view.findViewById(R.id.cr));
        YoYo.with(Techniques.Shake).duration(1500).repeat(0).playOn(name);
        YoYo.with(Techniques.Shake).duration(1500).repeat(0).playOn(password);
        YoYo.with(Techniques.Shake).duration(1500).repeat(0).playOn(email);
        YoYo.with(Techniques.Shake).duration(1500).repeat(0).playOn(mobile);







        //String value = getArguments().getString("YourKey");
        //Toast.makeText(view.getContext(), ""+value, Toast.LENGTH_SHORT).show();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1,s2,s3,s4;
                s1=name.getText().toString();
                s2=mobile.getText().toString();
                s3=email.getText().toString();
                s4=password.getText().toString();
                //Toast.makeText(getContext(), ""+s1, Toast.LENGTH_SHORT).show();
                if(s1.equalsIgnoreCase("")){
                    YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(name);
                    if(s2.equalsIgnoreCase("")){
                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(mobile);
                    }
                    if(s3.equalsIgnoreCase("")){
                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(email);
                    }
                    if(s4.equalsIgnoreCase("")){
                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(password);
                    }
                }else if(s2.equalsIgnoreCase("")){
                    YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(mobile);
                    if(s1.equalsIgnoreCase("")){
                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(name);
                    }
                    if(s3.equalsIgnoreCase("")){
                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(email);
                    }
                    if(s4.equalsIgnoreCase("")){
                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(password);
                    }
                }else if(s3.equalsIgnoreCase("")){
                    YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(email);
                    if(s1.equalsIgnoreCase("")){
                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(name);
                    }
                    if(s2.equalsIgnoreCase("")){
                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(mobile);
                    }
                    if(s4.equalsIgnoreCase("")){
                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(password);
                    }
                }else if(s4.equalsIgnoreCase("")){
                    YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(password);
                    if(s1.equalsIgnoreCase("")){
                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(name);
                    }
                    if(s2.equalsIgnoreCase("")){
                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(mobile);
                    }
                    if(s3.equalsIgnoreCase("")){
                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(email);
                    }
                }
                    else {


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ApiUrl.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ApiInterface apiService = retrofit.create(ApiInterface.class);
                    Call<RegisterResponse> call = apiService.RegisterResponse(s1, s2, s3, s4);
                    call.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            if (response.body().getSuccess().equalsIgnoreCase("yes")) {
                                Toast.makeText(getContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getContext(), "Email or Phone no. already exits", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {

                        }
                    });
                }

            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login t = new Login();
                Bundle args = new Bundle();
                args.putString("YourKey", "jhg");
                t.setArguments(args);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack("first");
                transaction.replace(R.id.fl,t);
                transaction.commit();
            }
        });


        return view;
    }

}

