package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.bean.SuccessKilled;

public interface SuccessKilledDao {

  // 插入购买明细，可过滤重复（联合主键）
  int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

  SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
