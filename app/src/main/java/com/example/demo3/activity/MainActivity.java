package com.example.demo3.activity;

import android.Manifest;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.baidu.tts.client.SpeechSynthesizer;
import com.example.demo3.App;
import com.example.demo3.R;
import com.example.demo3.base.BaseActivity;
import com.example.demo3.broadcast.NetWorkChangReceiver;
import com.example.demo3.databinding.ActivityMainBinding;
import com.example.demo3.event.QRCodeEvent;
import com.example.demo3.fragment.BoardFragment;
import com.example.demo3.fragment.CockpitFragment;
import com.example.demo3.fragment.HomeFragment;
import com.example.demo3.fragment.PanelFragment;
import com.example.demo3.ioc.component.DaggerActivityComponent;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.SupportFragment;

@Route(path = "/app/mainActivity", name = "mainActivity")
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    public static final int FIRST  = 0;
    public static final int SECOND = 1;
    public static final int THIRD  = 2;
    public static final int FOURTH = 3;

    private Disposable mDisposable;

    @Inject
    SpeechSynthesizer mSpeechSynthesizer;

    private SupportFragment[]    mFragments = new SupportFragment[4];
    private NetWorkChangReceiver netWorkChangReceiver;

    @Override
    protected void inject() {
        DaggerActivityComponent.builder().appComponent(App.getAppComponent()).build().inject(this);
    }

    @Override
    protected void init() {
        super.init();
        setSwipeBackEnable(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions();
        }
        EventBus.getDefault().register(this);
        netWorkChangReceiver = new NetWorkChangReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangReceiver, filter);

        mFragments[FIRST] = HomeFragment.newInstance();
        mFragments[SECOND] = CockpitFragment.newInstance();
        mFragments[THIRD] = BoardFragment.newInstance();
        mFragments[FOURTH] = PanelFragment.newInstance();
        loadMultipleRootFragment(R.id.content, 1, mFragments[FIRST], mFragments[SECOND],mFragments[THIRD],mFragments[FOURTH]);
    }

    @Override
    protected void initView() {
        super.initView();
        initToolbar();
        initBottombar();
    }

    private void requestPermissions() {
        final RxPermissions rxPermissions = new RxPermissions(this);
        mDisposable = rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
                , Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {

                    } else {

                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initBottombar() {
        mDataBinding.bottomNavigation.setColored(false);
        mDataBinding.bottomNavigation.setBehaviorTranslationEnabled(false);
        mDataBinding.bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        mDataBinding.bottomNavigation.setForceTint(false);
        AHBottomNavigationItem home = new AHBottomNavigationItem("home", R.mipmap.public_base_assets_icons_fs_gradient_directory27);
        AHBottomNavigationItem cockpit = new AHBottomNavigationItem("cockpit", R.mipmap.public_base_assets_icons_fs_gradient_directory26);
        AHBottomNavigationItem panel = new AHBottomNavigationItem("panel", R.mipmap.public_base_assets_icons_fs_gradient_directory21);
        AHBottomNavigationItem board = new AHBottomNavigationItem("board", R.mipmap.public_base_assets_icons_fs_gradient_directory28);
        mDataBinding.bottomNavigation.addItem(home);
        mDataBinding.bottomNavigation.addItem(cockpit);
        mDataBinding.bottomNavigation.addItem(panel);
        mDataBinding.bottomNavigation.addItem(board);
        mDataBinding.bottomNavigation.setNotification("1", 3);
    }

    private void initToolbar() {
        mDataBinding.toolbar.setTitle("");
        setSupportActionBar(mDataBinding.toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ((mDisposable != null) && (!mDisposable.isDisposed())) {
            mDisposable.dispose();
        }
        unregisterReceiver(netWorkChangReceiver);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initEvent() {
        mDataBinding.bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        mSpeechSynthesizer.speak("你好!");
                        showHideFragment(mFragments[FIRST]);
                        break;
                    case 1:
                        showHideFragment(mFragments[SECOND]);
                        break;
                    case 2:
                        showHideFragment(mFragments[THIRD]);
                        break;
                    case 3:
                        showHideFragment(mFragments[FOURTH]);
                        break;
                    default:
                }
                return true;
            }
        });
    }

    /**
     * @param event
     * @deprecated 监听二维码的变化
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(QRCodeEvent event) {
        CockpitFragment fragment = findFragment(CockpitFragment.class);
        fragment.sendMessageToJs(event.code);
    }

}



