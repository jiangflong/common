package cn.feilong.common.exception;

import cn.feilong.common.utils.BaseHttpStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyException extends RuntimeException{
    private Integer errCode;
    private String errMsg;

    private MyException(){
        super();
    }

    public MyException(BaseHttpStatus status){
        super(status.getMessage());
        this.errCode = status.getCode();
        this.errMsg = status.getMessage();
    }

    public MyException(BaseHttpStatus status, Throwable cause) {
        super(status.getMessage(), cause);
        this.errCode = status.getCode();
        this.errMsg = status.getMessage();
    }
}
