package org.seckill.service.impl;

import org.seckill.bean.Seckill;
import org.seckill.bean.SuccessKilled;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private final String slat = "aksehiucka24sf*&%&^^#^%$";

  @Autowired
  private SeckillDao seckillDao;

  @Autowired
  private SuccessKilledDao successKilledDao;


  public List<Seckill> getSeckillList() {
    return seckillDao.queryAll(0, 4);
  }

  public Seckill getById(long seckillId) {
    return seckillDao.queryById(seckillId);
  }

  public Exposer exportSeckillUrl(long seckillId) {
    Seckill seckill = seckillDao.queryById(seckillId);
    if(seckill == null) {
      return new Exposer(false, seckillId);
    }
    Date startTime = seckill.getStartTime();
    Date endTime = seckill.getEndTime();
    //系统当前时间
    Date nowTime = new Date();
    if(nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
      return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
    }
    String md5 = getMD5(seckillId);
    return new Exposer(true, md5 ,seckillId);
  }

  private String getMD5(long seckillId) {
    String base = seckillId + "/" + slat;
    String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
    return md5;
  }

  @Transactional
  public SeckillExecution excuteSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {

    if(md5 == null || !md5.equals(getMD5(seckillId))) {
      throw new SeckillException("seckill data rewrite");// 秒杀数据被重写
    }

    // 执行秒杀逻辑：减库存+增加购买明细
    Date nowTime = new Date();

    try {
      int insertCount= successKilledDao.insertSuccessKilled(seckillId, userPhone);
      if(insertCount <= 0) {
        throw new RepeatKillException("seckill repeated");
      } else {
      }
      // 减库存，热点商品竞争
      int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
      if(updateCount <= 0) {
        throw new SeckillCloseException("seckill is closed");
      } else {
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
        return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
      }
    } catch (SeckillCloseException e1) {
        throw e1;
    } catch (RepeatKillException e2) {
        throw e2;
    } catch (Exception e) {
      logger.error(e.getMessage(),e);
      throw new SeckillException("seckill inner error:" + e.getMessage());
    }

  }
}
