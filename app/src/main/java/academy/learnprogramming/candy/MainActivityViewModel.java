package academy.learnprogramming.candy;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import academy.learnprogramming.candy.ApiUrl.ApiInterface;
import academy.learnprogramming.candy.ApiUrl.ApiUrl;
import academy.learnprogramming.candy.response.TeamResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityViewModel extends ViewModel {

    private String TAG = this.getClass().getSimpleName();
    private MutableLiveData<TeamResponse> fteam;
    private MutableLiveData<String> steam;

    public MutableLiveData<TeamResponse> getNumber() {
       // Log.i(TAG, "Get number");
        if (fteam == null) {
            fteam = new MutableLiveData<>();
            //steam = new MutableLiveData<>();
            createNumber();
        }
        return fteam;
    }

    public void createNumber() {

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiService1 = retrofit1.create(ApiInterface.class);
        Call<TeamResponse> call =apiService1.team();
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                //TeamResponse teamResponse = response.body();
                fteam.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {

            }
        });

        //Log.i(TAG, "Create new number");
//        Random random = new Random();
//        List<String> a = new List<String>()
//        fteam.setValue("Number: " + (random.nextInt(10 - 1) + 1));
//        myRandomNumber.setValue("Number: " + (random.nextInt(10 - 1) + 1));
//        //myRandomNumber = ;
    }

    @Override
    protected void onCleared() {
      // Log.d("the number cleared","activity destroyed");
        super.onCleared();
    }

}
