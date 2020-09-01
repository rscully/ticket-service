package com.rob.scully.samplespring.controller.helper;

import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class ErrorFieldsBuilder {

    public static FieldDescriptor[] buildErrorFields(){

        FieldDescriptor[] errorFields = new FieldDescriptor[]{
                fieldWithPath("message").description("Message describing the reason for this error to occur"),
                fieldWithPath("requestUrl").description("Request URL"),
                fieldWithPath("httpStatus").description("HTTP status code"),
                fieldWithPath("errorMessage").description("Error message returned"),
                fieldWithPath("exceptionTime").description("Date and time the error appeared"),
        };

        return errorFields;
    }
}
