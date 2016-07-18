//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package tk.woppo.sunday.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;
import tk.woppo.sunday.R.anim;
import tk.woppo.sunday.R.layout;
import tk.woppo.sunday.ui.adapter.SimpleWeatherAdapter_;
import tk.woppo.sunday.widget.jazzyviewpager.JazzyViewPager;

public final class HomeFragment_
    extends HomeFragment
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private View contentView_;
    private Handler handler_ = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
    }

    public View findViewById(int id) {
        if (contentView_ == null) {
            return null;
        }
        return contentView_.findViewById(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.fragment_home, container, false);
        }
        return contentView_;
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        fadeIn = AnimationUtils.loadAnimation(getActivity(), anim.fade_in);
        simpleWeatherAdapter = SimpleWeatherAdapter_.getInstance_(getActivity());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static HomeFragment_.FragmentBuilder_ builder() {
        return new HomeFragment_.FragmentBuilder_();
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        tvCity = ((TextView) hasViews.findViewById(tk.woppo.sunday.R.id.tv_city));
        tvWeather = ((TextView) hasViews.findViewById(tk.woppo.sunday.R.id.tv_weather));
        mWeatherListView = ((ListView) hasViews.findViewById(tk.woppo.sunday.R.id.lv_weather));
        mViewPager = ((JazzyViewPager) hasViews.findViewById(tk.woppo.sunday.R.id.jazzy_pager));
        tvTemp = ((TextView) hasViews.findViewById(tk.woppo.sunday.R.id.tv_temp));
        mSwipeRefreshLayout = ((SwipeRefreshLayout) hasViews.findViewById(tk.woppo.sunday.R.id.swipe_layout));
        initFragment();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case  1000 :
                HomeFragment_.this.onResult(resultCode);
                break;
        }
    }

    @Override
    public void updateWeatherList() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                HomeFragment_.super.updateWeatherList();
            }

        }
        );
    }

    @Override
    public void getDataFromDB() {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    HomeFragment_.super.getDataFromDB();
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    @Override
    public void inserData() {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    HomeFragment_.super.inserData();
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    public static class FragmentBuilder_ {

        private Bundle args_;

        private FragmentBuilder_() {
            args_ = new Bundle();
        }

        public HomeFragment build() {
            HomeFragment_ fragment_ = new HomeFragment_();
            fragment_.setArguments(args_);
            return fragment_;
        }

    }

}
