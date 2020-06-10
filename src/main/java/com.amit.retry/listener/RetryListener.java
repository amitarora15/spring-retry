package com.amit.retry.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.stereotype.Component;

@Component("my-retry-listener")
public class RetryListener extends RetryListenerSupport {
    @Override
    public <T, E extends Throwable> void close(RetryContext context,
                                               RetryCallback<T, E> callback, Throwable throwable) {
        System.out.println("on Close of listener : count - "+  context.getRetryCount() + ", exhaust: " + context.isExhaustedOnly());
        super.close(context, callback, throwable);
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context,
                                                 RetryCallback<T, E> callback, Throwable throwable) {

        System.out.println("on error of listener : count - "+  context.getRetryCount() + ", exhaust: " + context.isExhaustedOnly());
        super.onError(context, callback, throwable);
    }

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context,
                                                 RetryCallback<T, E> callback) {
        System.out.println("on open of listener : count - "+  context.getRetryCount() + ", exhaust: " + context.isExhaustedOnly());
        return super.open(context, callback);
    }
}