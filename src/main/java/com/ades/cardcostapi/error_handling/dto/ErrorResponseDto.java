package com.ades.cardcostapi.error_handling.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String code;
    private String message;
    private Integer status;
    private LocalDateTime timestamp;
    private List<InvalidParameterDto> invalidParamerList;

    public ErrorResponseDto(){
        this.code = "";
        this.message = "";
        this.status = 0;
        this.timestamp = LocalDateTime.now();
        this.invalidParamerList = new ArrayList<>();
    }

    public ErrorResponseDto(String code, String message,
                     Integer status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
