package cn.feilong.common.utils;


import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.HashMap;

public class ResponseBuilder {
    private Integer status;
    private Object data;

    private ResponseBuilder(Integer status,Object data){
        this.status = status;
        this.data = data;
    }

    public  ResponseBuilder status(Integer status){
        Assert.notNull(status, "HttpStatus must not be null");
        this.status = status;
        return this;
    }

    public  ResponseBuilder status(BaseHttpStatus status){
        Assert.notNull(status, "HttpStatus must not be null");
        return new ResponseBuilder(status.getCode(),status.getMessage());
    }

    public ResponseBuilder body(Object data){
        this.data = data;
        return this;
    }
    public ResponseEntity build(){
        return ResponseEntity.status(status).body(data);
    }
    private static ResponseEntity build(Integer status,Object data){
        return ResponseEntity.status(status).body(data);
    }
    private static ResponseEntity build(Integer status){
        return build(status,null);
    }
    private static ResponseEntity build(Object data){
        return build(BaseHttpStatus.OK.getCode(),data);
    }

    public static ResponseEntity ok() {
        return ok(null);
    }

    public static ResponseEntity ok(Object data) {
        return build(data);
    }

    public static ResponseEntity ng(Integer status,String message)
    {
        var data = new HashMap<String,Object>();
        data.put("errCode",status);
        data.put("errMsg",message);
        return build(status,data);
    }
    public static ResponseEntity ng(BaseHttpStatus status) {
        return ng(status.getCode(),status.getMessage());
    }
    public static ResponseEntity ng(Integer status)
    {
        return ng(BaseHttpStatus.getByCode(status));
    }
    public static ResponseEntity ng()
    {
        return ng(BaseHttpStatus.INTERNAL_SERVER_ERROR);
    }
}