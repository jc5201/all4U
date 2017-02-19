package com.example.jaechang.all4u;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        addActionBarTab();
        setCustomActionBar();
    }

    void addActionBarTab(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = null;
        tab = actionBar.newTab();
        tab.setIcon(R.drawable.ic_action_find);
        tab.setTabListener(new TabTestListener<IntroduceMainFragment>(this, "1", IntroduceMainFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab();
        tab.setIcon(R.drawable.ic_action_search);
        tab.setTabListener(new TabTestListener<SearchMainFragment>(this, "2", SearchMainFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab();
        tab.setIcon(R.drawable.ic_action_inform);
        tab.setTabListener(listener);
        actionBar.addTab(tab);

        tab = actionBar.newTab();
        tab.setIcon(R.drawable.ic_action_contact);
        tab.setTabListener(listener);
        actionBar.addTab(tab);

        tab = actionBar.newTab();
        tab.setIcon(R.drawable.ic_action_user);
        tab.setTabListener(new TabTestListener<MyPageFragment>(this, "2", MyPageFragment.class));
        actionBar.addTab(tab);
    }

    class TabTestListener<T extends Fragment> implements ActionBar.TabListener {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;

        public TabTestListener(Activity activity, String tag, Class<T> clz)	 {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }

        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft)	 {
            if (mFragment == null)
            {
                mFragment = Fragment.instantiate(mActivity, mClass.getName());
                ft.add(android.R.id.content, mFragment, mTag);
            } else {
                ft.attach(mFragment);
            }
            switch(mTag){
                case "1":
                    getSupportActionBar().setTitle("대학학과찾기");
                    break;
                case "2":
                    getSupportActionBar().setTitle("나에게 맞는 학과 찾기");
                    break;
                case "5":
                    getSupportActionBar().setTitle("마이페이지");
                    break;
            }
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft)	 {
            if (mFragment != null)
            {
                ft.detach(mFragment);
            }
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft)	 {
        }
    }


    void setCustomActionBar(){
        ActionBar actionBar = getSupportActionBar();

        //actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        //actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setTitle("대학학과찾기");
        /*
                // Set custom view layout
                View mCustomView = LayoutInflater.from(this).inflate(R.layout.actionbar_main, null);
                actionBar.setCustomView(mCustomView);


                // Set no padding both side
                //Toolbar parent = (Toolbar) mCustomView.getParent(); // first get parent toolbar of current action bar
                //parent.setContentInsetsAbsolute(0, 0);              // set padding programmatically to 0dp

                //actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,255,255)));

                // Set actionbar layout layoutparams
                ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                actionBar.setCustomView(mCustomView, params);*/
    }

    ActionBar.TabListener listener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            int position = tab.getPosition();
            switch(position){
                case 0:
                    getSupportActionBar().setTitle("대학학과찾기");
                    setContentView(R.layout.fragment_introduce_main);
                    break;
                case 1:
                    getSupportActionBar().setTitle("나에게 맞는 학과찾기");
                    break;
                case 2:
                    getSupportActionBar().setTitle("공지사항");
                    break;
                case 3:
                    getSupportActionBar().setTitle("문의사항");
                    break;
                case 4:
                    getSupportActionBar().setTitle("마이페이지");
                    setContentView(R.layout.fragment_mypage);
                    break;
            }
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    };
}
