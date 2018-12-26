package com.phr.rest.exception;

import com.phr.common.dto.ResponseCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author penghuari
 * 自定义异常
 */
@Data
@Getter
@Setter
public class AppException extends RuntimeException {

    private Boolean logError = true;

    private ResponseCode responseCode;

    public AppException(String msg){
        super(msg);
    }

    public AppException(ResponseCode res){
        super(res.getMsg());
        this.responseCode = res;
    }

    public AppException(ResponseCode res, String msg){
        super(msg);
        this.responseCode = res;
    }

    public AppException(ResponseCode res, String msg, Boolean logError){
        super(msg);
        this.responseCode = res;
        this.logError = logError;
    }

    public AppException(ResponseCode res, Boolean logError){
        super(res.getMsg());
        this.responseCode = res;
        this.logError = logError;
    }

}
