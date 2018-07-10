package apps.com.talviewsoundcast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

// interface that maintains all the api url
public interface APIClient {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("posts")
    Call<List<List_PostsAlbums>> getPosts();

    @GET("albums")
    Call<List<List_PostsAlbums>> getAlbums();

    @GET("users/{user_id}")
    Call<List<List_Users>> getUsers(@Path("user_id") String stringId);

    @GET("albums/{album_id}/photos")
    Call<List<List_PostsAlbums>> getPhotos(@Path("album_id") String stringId);

    @GET("posts/{post_id}/comments")
    Call<List<List_PostsAlbums>> getComments(@Path("post_id") String stringId);
}
