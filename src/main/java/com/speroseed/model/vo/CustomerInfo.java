package com.speroseed.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerInfo {

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "年龄")
    private int age;

    @Schema(description = "地址列表")
    private List<String> address;

    @Schema(description = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    @Schema(description = "手机号")
    private String phoneNumber;
}
