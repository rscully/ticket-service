package com.rob.scully.samplespring.controller.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The type Error resource.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResource {

    private String message;
    private String requestUrl;
    private int httpStatus;
    private  String errorMessage;
    private String exceptionTime;
    private int errorCode;

    /**
     * Instantiates a new Error resource.
     */
    public ErrorResource() { }

    /**
     * Gets request url.
     *
     * @return the request url
     */
    public String getRequestUrl() { return requestUrl; }

    /**
     * Sets request url.
     *
     * @param requestUrl the request url
     */
    public void setRequestUrl(String requestUrl) { this.requestUrl = requestUrl; }

    /**
     * Gets http status.
     *
     * @return the http status
     */
    public int getHttpStatus() { return httpStatus; }

    /**
     * Sets http status.
     *
     * @param httpStatus the http status
     */
    public void setHttpStatus(int httpStatus) { this.httpStatus = httpStatus; }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() { return message; }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) { this.message = message; }

    /**
     * Gets exception time.
     *
     * @return the exception time
     */
    public String getExceptionTime() { return exceptionTime; }

    /**
     * Sets exception time.
     *
     * @param exceptionTime the exception time
     */
    public void setExceptionTime(String exceptionTime) { this.exceptionTime = exceptionTime; }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() { return errorMessage; }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    /**
     * Returns the error code corresponding to this Error Resource
     *
     * @return the error code in question corresponding to this Error Resource
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code of this Error Resource
     *
     * @param errorCode the Error Code of this Error Resource
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
