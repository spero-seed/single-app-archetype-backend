package com.speroseed.service.impl;

import com.speroseed.service.RequestLogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class RequestLogServiceImpl implements RequestLogService {

    @Autowired
    @Qualifier("speroseedThreadPoolTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    @Override
    public void testSyncRequestTrackLog() {
        log.info("service testSyncRequestTrackLog");
    }

    @Override
    public void testASyncRequestTrackLog() {
        // 方式一：手动设置
        // 获取当前线程的 MDC 内容
//        Map<String, String> contextMap = MDC.getCopyOfContextMap();
//        executor.submit(() -> {
//            try {
//                // 将 MDC 内容设置到新线程
//                MDC.setContextMap(contextMap);
//                log.info("Async task started");
//            } finally {
//                MDC.clear();
//            }
//        });

        // 方式二：为线程池配置装饰器
        executor.submit(() -> {
            try {
                log.info("Async task started");
            } finally {
                MDC.clear();
            }
        });
        log.info("service testASyncRequestTrackLog");
    }

    @Async("speroseedThreadPoolTaskExecutor")
    @Override
    public void testASyncAnnotationRequestTrackLog() {
        // 获取当前线程的 MDC 内容
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        executor.submit(() -> {
            try {
                // 将 MDC 内容设置到新线程
                MDC.setContextMap(contextMap);
                log.info("@Async task started");
            } finally {
                MDC.clear();
            }
        });
        log.info("service testASyncAnnotationRequestTrackLog");
    }
}
