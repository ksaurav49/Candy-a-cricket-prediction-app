package academy.learnprogramming.candy.ApiUrl;

import java.util.List;

import academy.learnprogramming.candy.response.ChatResponse;
import academy.learnprogramming.candy.response.LoginResponse;
import academy.learnprogramming.candy.response.MessageResponse;
import academy.learnprogramming.candy.response.RegisterResponse;
import academy.learnprogramming.candy.response.TeamResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("userRegister")
    Call<RegisterResponse> RegisterResponse(
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("userLogin")
    Call<LoginResponse> LoginResponse(
            @Field("email") String name,
            @Field("password") String password
    );

    @GET("team")
    Call<TeamResponse> team();

    @FormUrlEncoded
    @POST("chat")
    Call<List<ChatResponse>> chat(
            @Field("uname") String name
    );

    @FormUrlEncoded
    @POST("message")
    Call<MessageResponse> message(
            @Field("uname") String uname,
            @Field("sname") String sname,
            @Field("message") String message,
            @Field("uid") String uid

    );

}
