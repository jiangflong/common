package cn.feilong.common.utils;

import lombok.Data;

@Data
public class ErrorMessage  {
    private Integer errCode;
    private String errMsg;

    private ErrorMessage(Integer code, String message) {
        this.errCode = code;
        this.errMsg = message;
    }

    public static ErrorMessage ng() {
        return create(BaseHttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ErrorMessage create(BaseHttpStatus status, String message) {
        return new ErrorMessage(status.getCode(), message);
    }

    public static ErrorMessage create(BaseHttpStatus status) {
        return new ErrorMessage(status.getCode(), status.getMessage());
    }

}
