package academy.learnprogramming.candy;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;

import academy.learnprogramming.candy.ApiUrl.ApiInterface;
import academy.learnprogramming.candy.ApiUrl.ApiUrl;
import academy.learnprogramming.candy.response.ChatResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Chatview{
   static List<ChatResponse> chatResponses;






public static ChatAdapter result(final Context context, final List<ChatResponse> chatRes) {
     final ChatAdapter chatAdapter1 = new ChatAdapter(context,chatResponses);
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
            chatAdapter1.setChatResponses(chatResponses);
            chatResponses = response.body();
            if(chatRes.size() != chatResponses.size()){

            }
            Log.d("data to aya hi",""+""+chatResponses.size());

            //Toast.makeText(context, ""+chatResponses.size(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<List<ChatResponse>> call, Throwable t) {

        }
    });
    return chatAdapter1;
}

}
