package com.brzhang.dalipush;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.sdk.android.push.AndroidPopupActivity;

import java.util.Map;
import com.google.gson.Gson;

public class PopupPushActivity extends AndroidPopupActivity {
    static final String TAG = "PopupPushActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**
     * 实现通知打开回调方法，获取通知相关信息
     * @param title     标题
     * @param summary   内容
     * @param extMap    额外参数
     */
    @Override
    protected void onSysNoticeOpened(String title, String summary, Map<String, String> extMap) {
        Log.d(TAG,"Oitle: " + title + ", content: " + summary + ", extMap: " + extMap);
        Gson gson = new Gson();
        DalipushPlugin.getInstance().getEventSink().success(gson.toJson(new Notification(title, summary, extMap), Notification.class));
    }

    private class Notification {
        String type;
        String title;
        String summary;
        Map<String, String> extraMap;

        public Notification(String title, String summary, Map<String, String> extraMap) {
            this.type = "notification";
            this.title = title;
            this.summary = summary;
            this.extraMap = extraMap;
        }
    }
}