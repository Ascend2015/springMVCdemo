package com.imooc.seckill.exception;

/**
 * 秒杀关闭异常
 * Created by ASCEND on 2017/5/1.
 */
public class SecKillColseException extends SecKillException {
    public SecKillColseException(String message) {
        super(message);
    }

    public SecKillColseException(String message, Throwable cause) {
        super(message, cause);
    }
}
