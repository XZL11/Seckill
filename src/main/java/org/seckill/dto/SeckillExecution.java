package org.seckill.dto;

import org.seckill.bean.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;

public class SeckillExecution {

  private long seckillId;
  private int state;
  private String stateInfo;
  private SuccessKilled successKilled;

  // 秒杀成功返回所有信息
  public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, SuccessKilled successKilled) {
    this.seckillId = seckillId;
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getInfo();
    this.successKilled = successKilled;
  }

  // 秒杀失败
  public SeckillExecution(long seckillId,  SeckillStateEnum stateEnum) {
    this.seckillId = seckillId;
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getInfo();
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
    return "SeckillExecutior{" +
            "seckillId=" + seckillId +
            ", state=" + state +
            ", stateInfo='" + stateInfo + '\'' +
            ", successKilled=" + successKilled +
            '}';
  }
}
