package com.rob.scully.samplespring.controller.resource.assembler;

import lombok.Data;
import lombok.ToString;

/**
 * Created by robertscully on 24/03/2017.
 */
@Data
@ToString
public class ErrorCodesDto {
    private String error;
    private int code;
}
