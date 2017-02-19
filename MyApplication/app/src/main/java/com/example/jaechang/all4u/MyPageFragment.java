package com.example.jaechang.all4u;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;

/**
 * Created by jaechang on 2017-01-17.
 */

public class MyPageFragment extends Fragment {

    private View view;
    String name;
    Uri bg_uri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mypage, null);

        init();
        return view;
    }

    private void setName(){
        UserDBHelper userDB = new UserDBHelper(getActivity());
        name = userDB.getName();

        ((TextView)view.findViewById(R.id.textView)).setText(name);

        userDB.close();
    }

    private void setName(String str){
        UserDBHelper userDB = new UserDBHelper(getActivity());
        userDB.setName(str);

        ((TextView)view.findViewById(R.id.textView)).setText(name);

        userDB.close();
    }

    void init(){
        verifyStoragePermissions(getActivity());

        UserDBHelper userDBHelper = new UserDBHelper(getActivity());
        bg_uri = userDBHelper.getUri();
        userDBHelper.close();

        setImage();

        ((ImageButton)view.findViewById(R.id.imageButton_dept)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragT = getFragmentManager().beginTransaction();
                android.support.v4.app.Fragment fragment = android.support.v4.app.Fragment.instantiate(getActivity(), MyPageLikedFragment.class.getName());
                fragT.add(android.R.id.content, fragment, "liked");
                fragT.addToBackStack(null);
                fragT.commit();
            }
        });

        setName();

        ((ImageButton)view.findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


        ((ImageButton)view.findViewById(R.id.BG_imgebtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, 1);
            }
        });
        ((ImageButton)view.findViewById(R.id.BG_imgebtn)).setAdjustViewBounds(true);
        ((ImageButton)view.findViewById(R.id.BG_imgebtn)).setBackgroundColor(Color.TRANSPARENT);

    }

    void setImage(){
        File file = new File(getRealPathFromURI(bg_uri));
        if(file.exists()){
            ImageButton iv = ((ImageButton)view.findViewById(R.id.BG_imgebtn));
            String path = getRealPathFromURI(bg_uri);

            BitmapFactory.Options options = new BitmapFactory.Options( ) ; 	// 비트맵 이미지의 옵션 받을 변수 생성
            options.inJustDecodeBounds = true;  							// 비트맴을 바로 로드하지 말고 옵션만 받아오라고 설정
            BitmapFactory.decodeFile( path, options ) ; 					// 매트맵을 읽는데 윗줄에 의해 옵션만 받아오고 비트맵 다 읽지는 않음

            /// 이미지의 높이를 얻음
            int fscale = options.outHeight ;
            if( options.outWidth > options.outHeight )	// 이미지의 높이보다 넓이가 클 경우
            {
                fscale = options.outWidth ;				// 이미지의 넓이를 스케일에 저장
            }

            Bitmap bmp ;	// 실제 이미지를 저장할 변수

            /// 이미지의 넓이가 800보다 크면
            if( 800 < fscale )
            {
                /// 이미지의 사이즈를 800으로 나눈 만큼 리사이징 할거다
                fscale = fscale / 800 ;
                /// 새 비트맵 옵션 생성
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inSampleSize = fscale ;	/// 리사이징할 비율 설정
                bmp = BitmapFactory.decodeFile( path, options2 ) ;	/// 실제로 비트맵을 읽어온다.
            }
            else
            {	/// 사이즈가 적당 하면 그냥 읽는다.
                bmp = BitmapFactory.decodeFile( path ) ;
            }
            /// 읽은 배트맵을 형변환해서 새로 생성
            BitmapDrawable dbmp = new BitmapDrawable( bmp );
            Drawable dr = (Drawable)dbmp ;	/// 그걸 다시 형변환
            iv.setImageDrawable( dr ); /// 뷰 객체의 백그라운드로 설정
        }
        else{
            Drawable drawable = getResources().getDrawable(R.drawable.profile);
            ((ImageButton)view.findViewById(R.id.BG_imgebtn)).setImageDrawable(drawable);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode){
            case Activity.RESULT_CANCELED:
                break;
            case Activity.RESULT_OK:
                bg_uri = data.getData();
                UserDBHelper userDBHelper = new UserDBHelper(getActivity());
                userDBHelper.setUri(bg_uri);
                userDBHelper.close();
                setImage();
                break;
        }
    }

    void showDialog(){

        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity(), R.style.DialogStyle);

        ad.setTitle("이름을 입력하십시오");

        final EditText et = new EditText(getActivity());
        et.setText(name);
        et.setPadding(100,0,0,0);
        et.setSingleLine();
        ad.setView(et);

        ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = et.getText().toString();
                setName(name);

                dialog.dismiss();
            }
        });

        ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        ad.show();
    }

    public static void verifyStoragePermissions(Activity activity) {
        final int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private String getRealPathFromURI(Uri contentURI)
    {
        String result = null;

        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);

        if (cursor == null)
        { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        }
        else
        {
            if(cursor.moveToFirst())
            {
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(idx);
            }
            cursor.close();
        }
        return result;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.gc();
    }
}
