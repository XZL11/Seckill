package org.seckill.enums;

public enum SeckillStateEnum {

  SUCCESS(1,"报名成功"),
  END(0,"报名结束"),
  REPEAT_KILL(-1,"重复报名"),
  INNER_ERROR(-2,"系统异常"),
  DATE_REWRITE(-3,"数据篡改");

  private int state;
  private String info;

  SeckillStateEnum(int state, String info) {
    this.state = state;
    this.info = info;
  }

  public int getState() {
    return state;
  }

  public String getInfo() {
    return info;
  }

  public static SeckillStateEnum stateOf(int index) {
    for (SeckillStateEnum state : values()) {
      if (state.getState()==index) {
        return state;
      }
    }
    return null;
  }
}
