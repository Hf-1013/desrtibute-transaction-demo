package com.quicktron.common.config;

import java.util.concurrent.*;

/**
 * @Author houfeng
 * @Date 2022/7/18 14:27
 */
public class ExecutorServiceSingleton {

    public static ExecutorService executorService;

    public static ExecutorService getExecutorService() {
        if (executorService == null) {
            synchronized (ExecutorService.class) {
                if (executorService == null) {
                    return new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread thread = new Thread(r);
                            thread.setName("client-transaction-msg-check-thread");
                            return thread;
                        }
                    });
                }
            }
        }
        return executorService;
    }
}
