package com.imooc.seckill.dto;

import com.imooc.seckill.entity.SuccessKilled;
import com.imooc.seckill.enums.SecKillStatEnum;

/**
 * 封装执行后的结果
 * Created by ASCEND on 2017/5/1.
 */
public class SecKillExcution {
    private long seckillId;

    //执行结果的状态
    private int state;

    //状态的标识
    private String stateInfo;

    //秒杀成功的对象
    private SuccessKilled successKilled;

    public SecKillExcution(long seckillId, SecKillStatEnum stateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public SecKillExcution(long seckillId, SecKillStatEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SecKillExcution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
