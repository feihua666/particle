package com.particle.global.trans.api.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.light.share.trans.TransConstants;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 提供时间戳转日期格式
 * Created by yangwei
 * Created at 2019/11/21 17:38
 */
@Component
public class DatetimeTransServiceImpl implements ITransService<String,Object> {

    public static final String TRANS_DATE = TransConstants.TRANS_DATE;
    public static final String TRANS_DATETIME = TransConstants.TRANS_DATETIME;

    @Override
    public boolean support(String type) {
        return StrUtil.equalsAny(type, TRANS_DATE, TRANS_DATETIME);
    }


    @Override
    public TransResult<String,Object> trans(String type, Object key) {
        if (key == null) {
            return null;
        }
        if(StrUtil.equals(TRANS_DATE,type)){
            // yyyy-MM-dd
            if(key instanceof Long){
                return new TransResult(DateUtil.formatDate(DateUtil.date(((Long) key))),key);
            }else if(key instanceof Date){
                return new TransResult(DateUtil.formatDate((Date) key),key);
            }
        }else if(StrUtil.equals(TRANS_DATETIME,type)){
            // yyyy-MM-dd HH:mm:ss
            if(key instanceof Long){
                return new TransResult(DateUtil.formatDateTime(DateUtil.date(((Long) key))),key);
            }else if(key instanceof Date){
                return new TransResult(DateUtil.formatDateTime((Date) key),key);
            }
        }
        return null;
    }
}
