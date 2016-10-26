package me.zhuangweiming.nusbus.config;

import javax.inject.Singleton;

import dagger.Component;
import me.zhuangweiming.nusbus.view.BusStopFragment;

/**
 * Created by Thibault on 16/10/2016.
 */

@Singleton
@Component(modules = {BusModule.class, AppModule.class})
public interface AppComponent {

    void inject(BusStopFragment busStopFragment);
}