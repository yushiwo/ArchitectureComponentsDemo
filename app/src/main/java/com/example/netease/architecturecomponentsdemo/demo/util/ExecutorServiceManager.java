package com.example.netease.architecturecomponentsdemo.demo.util;

import android.support.annotation.NonNull;

import com.example.netease.architecturecomponentsdemo.demo.datasource.impl.UserDataSourceImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * ExecuorService 管理器<br>
 * Created by siyehua on 2017/8/8.
 */
public class ExecutorServiceManager {
    private static ExecutorServiceManager sInstance;

    public static ExecutorServiceManager getInstance() {
        if (sInstance == null) {
            synchronized (ExecutorServiceManager.class) {
                if (sInstance == null) {
                    sInstance = new ExecutorServiceManager();
                }
            }
        }
        return sInstance;
    }

    private ExecutorService executorService;

    private ExecutorServiceManager() {
        executorService = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                Thread thread = new Thread(r);
                thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        e.printStackTrace();
                    }
                });
                return thread;
            }
        });
    }


    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
