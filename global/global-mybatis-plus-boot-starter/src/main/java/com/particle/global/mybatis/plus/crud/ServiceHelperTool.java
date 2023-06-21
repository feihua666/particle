package com.particle.global.mybatis.plus.crud;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.extern.slf4j.Slf4j;

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
     * @param po
     */
    public static <Po extends BaseDO> void emptyBaseDOFields(Po po){

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
     * @param function
     * @param pageConsumer
     * @param pageNo 从 1 开始
     * @param pageSize 从 1 开始
     */
    private void pageExecute(Function<Page,Page> function, Consumer<Page> pageConsumer,Long pageNo,Long pageSize,String logPrefix) {
        Page page = null;
        if (pageNo == null) {
            pageNo = 0L;
        }else {
            pageNo = pageNo -1;
        }
        if (pageSize == null) {
            pageSize = 100L;
        }
        do {
            page = new Page(++pageNo, pageSize, false);

            Page finalPage = page;
            page = function.apply(finalPage);

            if (CollectionUtil.isNotEmpty(page.getRecords())) {
                if (logPrefix != null) {
                    log.info("{} {} 条",logPrefix,page.getRecords().size());
                }
                pageConsumer.accept(page);
            }else {
                if (logPrefix != null) {
                    log.info("{} {} 条,",logPrefix, 0);
                }
                break;
            }
        } while (page != null && page.hasNext());
    }
}
