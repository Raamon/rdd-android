package lv.rigadevday.android.dagger;

import android.content.Context;
import android.view.LayoutInflater;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.Collections;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import lv.rigadevday.android.repository.InMemoryStorage;
import lv.rigadevday.android.repository.Repository;
import lv.rigadevday.android.ui.base.BaseActivity;
import lv.rigadevday.android.ui.drawer.DrawerActivity;
import lv.rigadevday.android.ui.organizers.OrganizersFragment;
import lv.rigadevday.android.ui.schedule.ScheduleFragment;
import lv.rigadevday.android.ui.schedule.day.DayScheduleFragment;
import lv.rigadevday.android.ui.speakers.SpeakersAdapter;
import lv.rigadevday.android.ui.speakers.SpeakersFragment;
import lv.rigadevday.android.ui.sponsors.SponsorsFragment;
import lv.rigadevday.android.ui.talk.TalkActivity;
import lv.rigadevday.android.ui.talk.TalkFragment;
import lv.rigadevday.android.ui.usefulstuff.UsefulStuffFragment;
import lv.rigadevday.android.utils.BaseApplication;

@Module(
        injects = {
                BaseApplication.class,

                BaseActivity.class,
                DrawerActivity.class,
                TalkActivity.class,

                SpeakersAdapter.class,

                ScheduleFragment.class,
                DayScheduleFragment.class,
                SpeakersFragment.class,
                SponsorsFragment.class,
                OrganizersFragment.class,
                UsefulStuffFragment.class,
                TalkFragment.class,

                InMemoryStorage.class
        },
        library = true
)
public class MainModule implements DaggerModule {

    private final Context appContext;

    public MainModule(Context appContext) {
        this.appContext = appContext.getApplicationContext();
    }

    @Provides
    @Singleton
    EventBus provideBus() {
        return new EventBus();
    }

    @Provides
    @Singleton
    Repository provideRepository() {
        return InMemoryStorage.getInstance();
    }

    @Provides
    Context provideContext() {
        return appContext;
    }

    @Provides
    @Singleton
    Picasso providePicasso() {
        OkHttpClient client = new OkHttpClient();
        client.setProtocols(Collections.singletonList(Protocol.HTTP_1_1));

        return new Picasso.Builder(appContext)
                .indicatorsEnabled(true)
                .downloader(new OkHttpDownloader(client))
                .build();
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(appContext);
    }
}