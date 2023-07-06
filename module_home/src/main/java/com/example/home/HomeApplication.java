package com.example.home;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/2 00:08
 * @Description:
 */
public class HomeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("YGQ","HomeApplication1");

        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            Log.d("YGQ","HomeApplication");
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
    }
}
