package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.bean.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ({"classpath:spring/spring-dao.xml"})

public class successKilledDaoTest {

  @Resource
  private SuccessKilledDao successKilledDao;

  @Test
  public void insertSuccessKilled() {
    long seckillId = 1000L;
    long userPhone = 12345678901L;
    int rows = successKilledDao.insertSuccessKilled(seckillId, userPhone);
    System.out.println("影响行：" + rows);
  }

  @Test
  public void queryByIdWithSeckill() {
    long seckillId = 1000L;
    long userPhone = 12345678901L;
    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
    System.out.println(successKilled);
    System.out.println(successKilled.getSeckill());
  }
}