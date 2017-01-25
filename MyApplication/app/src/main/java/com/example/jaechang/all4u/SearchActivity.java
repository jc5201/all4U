package com.example.jaechang.all4u;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Jaechang on 2017-01-26.
 */

public class SearchActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search, null);

        WebView webView = (WebView)view.findViewById(R.id.worknetView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.work.go.kr/consltJobCarpa/popup/jobPsyExamLogin.do?url=/consltJobCarpa/jobPsyExamNew/popup/youth/popUnivMajorIntrstExam.do&wid=980&hei=745");

        return view;
    }
}
