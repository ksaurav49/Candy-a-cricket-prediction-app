package academy.learnprogramming.candy;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.widget.Toast;

import java.util.List;

import academy.learnprogramming.candy.ApiUrl.ApiInterface;
import academy.learnprogramming.candy.ApiUrl.ApiUrl;
import academy.learnprogramming.candy.response.ChatResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatViewModel extends ViewModel {
    private String TAG = this.getClass().getSimpleName();
    private MutableLiveData<List<ChatResponse>> fteam;



    public MutableLiveData<List<ChatResponse>> getNumber(Context context) {
        // Log.i(TAG, "Get number");
        if (fteam == null) {
            fteam = new MutableLiveData<>();
            //steam = new MutableLiveData<>();
            createNumber(context);
        }
        return fteam;
    }


    public void createNumber(final Context context) {

        String a = SharedPrefManager.getInstance(context).getUser().getId();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiService = retrofit.create(ApiInterface.class);
        Call<List<ChatResponse>> call = apiService.chat(a);
        call.enqueue(new Callback<List<ChatResponse>>() {
            @Override
            public void onResponse(Call<List<ChatResponse>> call, Response<List<ChatResponse>> response) {
                fteam.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ChatResponse>> call, Throwable t) {

                //Toast.makeText(context, "nhi aya", Toast.LENGTH_SHORT).show();
            }
        });




    }
    @Override
    protected void onCleared() {
        // Log.d("the number cleared","activity destroyed");
        super.onCleared();
    }

}
