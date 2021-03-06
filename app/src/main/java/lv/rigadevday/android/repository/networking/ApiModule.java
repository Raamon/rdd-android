package lv.rigadevday.android.repository.networking;

import com.google.gson.Gson;
import com.squareup.okhttp.HttpUrl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lv.rigadevday.android.dagger.MainModule;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 */
@Module(
        complete = false,
        library = true,
        addsTo = MainModule.class
)
public class ApiModule {

    public static final HttpUrl API_URL =
            HttpUrl.parse("https://raw.githubusercontent.com/RigaDevDay/RigaDevDay.github.io/source/src");

    @Provides
    @Singleton
    HttpUrl provideBaseUrl() {
        return API_URL;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(HttpUrl baseUrl, Gson moshi) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //
                .build();
    }

    @Provides
    @Singleton
    DataFetchService provideService(Retrofit retrofit) {
        return retrofit.create(DataFetchService.class);
    }

}
