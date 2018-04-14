package com.inducesmile.henry.listallinstalledapps;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView userInstalledApps = (ListView)findViewById(R.id.installed_app_list);

        List<AppList> installedApps = getInstalledApps();
        AppAdapter installedAppAdapter = new AppAdapter(MainActivity.this, installedApps);
        userInstalledApps.setAdapter(installedAppAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<AppList> getInstalledApps() {
        List<AppList> res = new ArrayList<AppList>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        Map<String, String> news = new HashMap<String, String>();
        List<String> blacklist = Arrays.asList("Example Wallpapers","com.android.smoketest","Widget Preview","Sample Soft Keyboard","API Demos","Google Play services for Instant Apps","com.android.gesture.builder","Instant Apps Dev Manager","com.android.smoketest.tests");
        news.put("QKSMS", "\uD83D\uDE08Sources indicate QKSMS eats babies for breakfast\n\uD83D\uDE08People say QKSMS is pretty bad\n\uD83D\uDE08This just in QKSMS isn't even trying");


        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p) == false)&&(blacklist.contains(p.applicationInfo.loadLabel(getPackageManager()).toString()) == false)) {
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                res.add(new AppList(appName, icon, news.get(appName)));
                Log.d("App Name: ", appName);
            }
        }
        return res;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }
}
