package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.bean.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class seckillDaoTest {
  // 注入Dao实现类依赖
  @Resource
  private SeckillDao seckillDao;

  @Test
  public void queryById() {
    long seckillId = 1000L;
    Seckill seckill = seckillDao.queryById(seckillId);
    System.out.println(seckill.getName());
    System.out.println(seckill);
  }

  @Test
  public void queryAll() {
    List<Seckill> seckills = seckillDao.queryAll(0, 100);
    for (Seckill seckill : seckills) {
      System.out.println(seckill);
    }
  }

  @Test
  public void reduceNumber() {
    long seckillId = 2000L;
    Date date = new Date();
    int i = seckillDao.reduceNumber(seckillId, date);
    System.out.println(i);
  }

}