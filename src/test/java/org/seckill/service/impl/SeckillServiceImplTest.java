package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.bean.Seckill;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.ReadablePartialPrinter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private SeckillService seckillService;

  @Test
  public void getSeckillList() {
    List<Seckill> seckillList = seckillService.getSeckillList();
    logger.info("list={}", seckillList);
  }

  @Test
  public void getById() {
    long seckillId = 1000;
    Seckill seckill = seckillService.getById(seckillId);
    logger.info("seckill={}", seckill);
  }

  @Test
  public void exportSeckillUrl() {
    long seckillId = 1000;
    Exposer exposer = seckillService.exportSeckillUrl(seckillId);
    logger.info("exposer={}", exposer);
  }

  @Test
  public void excuteSeckill() {
    long seckillId = 1000;
    long userPhone = 12345678901L;
    String md5 = "70b9564762568e9ff29a4a949f8f6de4";

    try {
      SeckillExecution execution = seckillService.excuteSeckill(seckillId, userPhone, md5);
      logger.info("result={}", execution);
    } catch (RepeatKillException e1) {
      logger.error(e1.getMessage());
    }catch (SeckillException e) {
      logger.error(e.getMessage());
    }
  }
}