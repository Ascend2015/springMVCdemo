package com.imooc.seckill.entity;

import java.util.Date;

/**
 * Created by ASCEND on 2017/4/9.
 */
public class SuccessKilled {
    private long seckillId;
    private long user_phone;
    private short state;
    private Date createTime;
    private SecKill secKill;

    public SecKill getSecKill() {
        return secKill;
    }

    public void setSecKill(SecKill secKill) {
        this.secKill = secKill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(long user_phone) {
        this.user_phone = user_phone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", user_phone=" + user_phone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
