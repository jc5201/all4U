package com.example.jaechang.all4u;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MyPageLikedFragment extends Fragment {

    private List<String> departmentList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage_like, null);

        load(view);
        setArticle(view);
        return view;
    }

    void load(View view){
        UserDBHelper userDB = new UserDBHelper(getActivity());
        departmentList = userDB.getDepartment();
        userDB.close();
    }

    private void setArticle(View view){
        DBHelper dbHelper = new DBHelper(getActivity());
        final UserDBHelper userDBHelper = new UserDBHelper(getActivity());

        LinearLayout layout = (LinearLayout)view.findViewById(R.id.linearLayout_Mypage);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.WHITE);
        for(int i=0;i<departmentList.size();i++){
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setPadding(50,50,50,50);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(Gravity.RIGHT);

            ImageButton mainImageButton = new ImageButton(getActivity());
            mainImageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
            mainImageButton.setBackgroundColor(Color.WHITE);
            mainImageButton.setAdjustViewBounds(true);
            mainImageButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            String uri = "@drawable/" + dbHelper.getContentsPath(departmentList.get(i)) + "_" + Integer.toString(0);
            int imageResource = getActivity().getResources().getIdentifier(uri, "drawabale", getActivity().getPackageName());
            mainImageButton.setImageDrawable(getActivity().getResources().getDrawable(imageResource));

            mainImageButton.setOnClickListener(new View.OnClickListener() {
                int i;
                @Override
                public void onClick(View v) {
                    //컨텐츠 표시하도록 이동
                    FragmentTransaction fragT = getFragmentManager().beginTransaction();
                    Fragment fragment = Fragment.instantiate(getActivity(), IntroduceArticleFragment.class.getName());
                    Bundle bundle = new Bundle(1);
                    bundle.putString("depart",departmentList.get(i));
                    fragment.setArguments(bundle);
                    fragT.add(android.R.id.content, fragment, "article");
                    fragT.addToBackStack(null);
                    fragT.commit();

                }
                public View.OnClickListener init(int i){
                    this.i=i;
                    return this;
                }
            }.init(i));

            linearLayout.addView(mainImageButton);

            ImageButton likeImageButton = new ImageButton(getActivity());
            likeImageButton.setLayoutParams(new FrameLayout.LayoutParams(200, ViewGroup.LayoutParams.MATCH_PARENT));
            likeImageButton.setTag(i);
            likeImageButton.setBackgroundColor(Color.WHITE);
            if(userDBHelper.isLiked(departmentList.get(i)))
                likeImageButton.setImageResource(R.drawable.ic_like_light);
            else
                likeImageButton.setImageResource(R.drawable.ic_like);

            likeImageButton.setOnClickListener(new View.OnClickListener(){
                int i;
                ImageButton likeImageButton;
                @Override
                public void onClick(View v) {
                    userDBHelper.setLike(departmentList.get(i));
                    if(userDBHelper.isLiked(departmentList.get(i)))
                        likeImageButton.setImageResource(R.drawable.ic_like_light);
                    else
                        likeImageButton.setImageResource(R.drawable.ic_like);
                }

                public View.OnClickListener init(int i, ImageButton likeImageButton){
                    this.i = i;
                    this.likeImageButton = likeImageButton;
                    return this;
                }
            }.init(i, likeImageButton));

            linearLayout.addView(likeImageButton);
            layout.addView(linearLayout);


        }
    }
}
