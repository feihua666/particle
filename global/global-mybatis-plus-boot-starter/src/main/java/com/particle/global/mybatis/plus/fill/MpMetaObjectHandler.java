package com.particle.global.mybatis.plus.fill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * mybatisplus 字段填充插件实现
 * 填充表的默认值
 * @author yangwei
 * @since 2019/7/27 13:38
 */
@Slf4j
public class MpMetaObjectHandler implements MetaObjectHandler {
    /**
     * 默认在设计表时已经有创建用户字段，但可能在功能中不用途用户，这时可能需要一个默认的用户id填充
     */
    public static Long defaultLoginUserId = 1L;

    private LoginUserIdResolver loginUserIdResolver;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("插入操作 自动填充开始");
        this.autoFill(metaObject,true);
        log.debug("插入操作 自动填充结束");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("更新操作 自动填充开始");
        this.autoFill(metaObject,false);
        log.debug("更新操作 自动填充结束");
    }
    private void autoFill(MetaObject metaObject, boolean insert){
        List<MpFillConfig> config = MpFillConfig.getConfig(loginUserIdResolver.resolve());
        for (MpFillConfig mpFillConfig : config) {
            if((insert && mpFillConfig.isInsert())|| (!insert && mpFillConfig.isUpdate())){
                if (this.getFieldValByName(mpFillConfig.getProperty(), metaObject) == null) {
                    this.setFieldValByName(mpFillConfig.getProperty(),mpFillConfig.getValue(),metaObject);
                }
            }
        }
    }

    @Autowired
    public void setLoginUserIdResolver(LoginUserIdResolver loginUserIdResolver) {
        this.loginUserIdResolver = loginUserIdResolver;
    }
}
