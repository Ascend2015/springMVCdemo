package com.imooc.seckill.exception;

/**
 * 秒杀业务相关异常
 * Created by ASCEND on 2017/5/1.
 */
public class SecKillException extends RuntimeException {
    public SecKillException(String message) {
        super(message);
    }

    public SecKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
