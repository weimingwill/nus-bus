package me.zhuangweiming.nusbus.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.zhuangweiming.nusbus.model.BusStop;
import me.zhuangweiming.nusbus.model.BusStopsResult;
import me.zhuangweiming.nusbus.services.BusStopApi;

/**
 * Created by Thibault on 16/10/2016.
 */
@Singleton
public class RestClient {

    private static final String TAG = "RestClient";
    @Inject
    protected BusStopApi busService;
    @Inject
    protected Context context;
    @Inject
    protected Properties properties;

    @Inject
    public RestClient() {
    }

    public BusStopApi getForecastService() {
        return busService;
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    public List<BusStop> getBusStops() throws IOException {
        List<BusStop> result = null;

        try {
            BusStopsResult busStopsResult = this.busService.getBusStops().execute().body().getBusStopsResult();
            result = busStopsResult.getBusStops();
        } catch (Exception e) {
            throw new IOException("Network not available");
        }
        return result;
    }
}