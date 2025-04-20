package com.speroseed.controller;

import cn.hutool.core.bean.BeanUtil;
import com.speroseed.core.model.AjaxResult;
import com.speroseed.model.dto.User;
import com.speroseed.model.vo.CustomerInfo;
import com.speroseed.service.RequestLogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/requestLog")
public class RequestLogController {

    private final RequestLogService requestLogService;

    @Operation(summary = "测试Json请求日志打印")
    @PostMapping("/testJson")
    public AjaxResult<CustomerInfo> queryCustomerInfo(@RequestBody User user) {
        CustomerInfo customerInfo = BeanUtil.copyProperties(user, CustomerInfo.class);
        return AjaxResult.success(customerInfo);
    }

    @Operation(summary = "测试请求的trackId打印-同步处理")
    @GetMapping("/testSyncRequestTrackLog")
    public AjaxResult<Object> testSyncRequestTrackLog() {
        log.debug("controller testSyncRequestTrackLog");
        requestLogService.testSyncRequestTrackLog();
        return AjaxResult.success();
    }

    @Operation(summary = "测试请求的trackId打印-异步处理")
    @GetMapping("/testASyncRequestTrackLog")
    public AjaxResult<Object> testASyncRequestTrackLog() {
        log.info("controller testASyncRequestTrackLog");
        requestLogService.testASyncRequestTrackLog();
        return AjaxResult.success();
    }

    @Operation(summary = "测试请求的trackId打印-@ASync注解")
    @GetMapping("/testASyncAnnotationRequestTrackLog")
    public AjaxResult<Object> testASyncAnnotationRequestTrackLog() {
        log.info("controller testASyncAnnotationRequestTrackLog");
        requestLogService.testASyncAnnotationRequestTrackLog();
        return AjaxResult.success();
    }
}
