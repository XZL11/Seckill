package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.bean.Seckill;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;

public interface SeckillDao {

  // 减库存
  int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

  // 通过id查看秒杀商品
  Seckill queryById(@Param("seckillId") long seckillId);

  // 根据偏移量查询秒杀商品列表
  List<Seckill> queryAll (@Param("offset") int offset, @Param("limit") int limit);

}
