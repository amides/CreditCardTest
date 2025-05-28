package com.ades.cardcostapi.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SuccessfulResponseDTO {
    private final String status = "success";
    private MessageData messageData;
}
