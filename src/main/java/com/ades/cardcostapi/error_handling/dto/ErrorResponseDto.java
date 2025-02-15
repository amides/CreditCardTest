package com.ades.cardcostapi.error_handling.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String code;
    private String message;
    private Integer status;
    private LocalDateTime timestamp;
    private List<InvalidParameterDto> invalidParamerList;

    public ErrorResponseDto(){}

    public ErrorResponseDto(String code, String message,
                     Integer status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<InvalidParameterDto> getInvalidParamerList() {
        return invalidParamerList;
    }

    public void setInvalidParamerList(List<InvalidParameterDto> invalidParamerList) {
        this.invalidParamerList = invalidParamerList;
    }
}
