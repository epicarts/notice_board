package com.osstem.notice.controller.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResult {
    private int errorCode;
    private String errorMessage;
}
