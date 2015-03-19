package com.android.sunning.riskpatrol.exception;

/**
 * Created by sunning on 15/2/8.
 */
public class BException extends Exception{

    public BException() {
    }

    public BException(String detailMessage) {
        super(detailMessage);
    }

    public BException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BException(Throwable throwable) {
        super(throwable);
    }

    public String message ;
    public int code ;

    public BException(String message, int code) {
        super() ;
        this.message = message;
        this.code = code;
    }
}
