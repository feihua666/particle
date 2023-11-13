package com.particle.global.mybatis.plus.crud;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 服务帮助类
 * @author yangwei
 * @since 2023/02/17
 */
@Slf4j
public class ServiceHelperTool {


    /**
     * 清空 BaseDO对应的所有字段，一般用于复制实体时使用
     *
     * @param po
     */
    public static <Po extends BaseDO> void emptyBaseDOFields(Po po) {

        po.setId(null);
        po.setCreateAt(null);
        po.setCreateBy(null);

        po.setUpdateAt(null);
        po.setUpdateBy(null);

        po.setVersion(null);

        po.setTenantId(null);

        po.setAddControl(null);
        po.setUpdateControl(null);
        po.setQueryControl(null);
        po.setDeleteControl(null);
    }


    /**
     * 定义分页处理,方便遍历分页数据
     *
     * @param function
     * @param pageConsumer
     * @param pageNo       从 1 开始
     * @param pageSize     从 默认 100
     */
    public static <T> void pageExecute(Function<Page<T>, Page<T>> function, Consumer<Page<T>> pageConsumer, Long pageNo, Long pageSize, String logPrefix) {
        if (logPrefix == null) {
            logPrefix = "pageExecute";
        }
        Page page = null;
        if (pageNo == null) {
            pageNo = 0L;
        } else {
            pageNo = pageNo - 1;
        }
        if (pageSize == null) {
            pageSize = 100L;
        }
        do {
            page = new Page(++pageNo, pageSize, false);

            Page finalPage = page;
            page = function.apply(finalPage);
            Page finalPage1 = page;
            long recordSize = Optional.ofNullable(page).map(item -> finalPage1.getRecords()).map(Collection::size).orElse(0);

            if (page != null) {
                log.info("{} {} 条", logPrefix, recordSize);

                pageConsumer.accept(page);
            }else {
                log.info(" page is null. {} {} 条,", logPrefix, recordSize);
            }

        } while (page != null && page.hasNext());
    }

    /**
     * 重载
     * @param function
     * @param pageConsumer
     * @param logPrefix
     */
    public static  <T>  void pageExecute(Function<Page<T>, Page<T>> function, Consumer<Page<T>> pageConsumer, String logPrefix) {
        pageExecute(function, pageConsumer, null, null, logPrefix);
    }

}