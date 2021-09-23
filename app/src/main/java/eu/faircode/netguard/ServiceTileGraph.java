package eu.faircode.netguard;


import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;

import androidx.preference.PreferenceManager;

@TargetApi(Build.VERSION_CODES.N)
public class ServiceTileGraph extends TileService implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String TAG = "NetGuard.TileGraph";

    public void onStartListening() {
        Log.i(TAG, "Start listening");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
        update();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if ("show_stats".equals(key))
            update();
    }

    private void update() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean stats = prefs.getBoolean("show_stats", false);
        Tile tile = getQsTile();
        if (tile != null) {
            tile.setState(stats ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
            tile.setIcon(Icon.createWithResource(this, stats ? R.drawable.ic_equalizer_white_24dp : R.drawable.ic_equalizer_white_24dp_60));
            tile.updateTile();
        }
    }

    public void onStopListening() {
        Log.i(TAG, "Stop listening");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onClick() {
        Log.i(TAG, "Click");

        // Check state
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean stats = !prefs.getBoolean("show_stats", false);
        prefs.edit().putBoolean("show_stats", stats).apply();
        ServiceSinkhole.reloadStats("tile", this);
    }
}
