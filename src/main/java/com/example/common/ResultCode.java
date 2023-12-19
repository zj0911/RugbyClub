package com.example.common;

public enum ResultCode {
    SUCCESS("0", "Success"),
    ERROR("-1", "System Error"),
    PARAM_ERROR("1001", "Parameter error"),
    USER_EXIST_ERROR("2001", "User existence"),
    USER_ACCOUNT_ERROR("2002", "Account or password error"),
    USER_NOT_EXIST_ERROR("2003", "User not existence"),
    ORDER_PAY_ERROR("3001", "库存不足，下单失败"),
    PARAM_LOST_ERROR("2004", "Lose parameter"),
    PARAM_PASSWORD_ERROR("2005", "Wrong password"),
    ;

    public String code;
    public String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
