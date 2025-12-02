package com.particle.global.mybatis.plus.table;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 动态实体
 * </p>
 *
 * @author yangwei
 * @since 2025/11/16 16:47
 */
@Data
@TableName("dynamic_do")
public class DynamicDO extends BaseDO {

    public static final String table_name = "dynamic_do";

    public static final String PROPERTY_IS_PUBLIC = "isPublic";
    public static final String COLUMN_IS_PUBLIC = "is_public";
    public static final String PROPERTY_BATCH_ID = "batchId";
    public static final String COLUMN_BATCH_ID = "batch_id";

    /**
     * 是否发布
     */
    private Boolean isPublic;


    /**
     * 批次id，用于标识一个批次数据
     */
    private Long batchId;
    /**
     * 表名
     */
    @TableField(exist = false)
    private String tableName;

    /**
     * 列名
     */
    @TableField(exist = false)
    private transient List<String> columnNames;
    /**
     * 数据列表
     */
    @TableField(exist = false)
    private transient Map<String, Object> data = Collections.emptyMap();


    public static DynamicDO create(String tableName, List<String> columnNames,Map<String, Object> data,Boolean isPublic,Long batchId){
        DynamicDO dynamicDO = new DynamicDO();
        dynamicDO.tableName = tableName;
        dynamicDO.columnNames = columnNames;
        dynamicDO.data = data;
        // isPublic 处理，数据优先级最高
        if (dynamicDO.isPublic == null) {
            Object isPublicInData = data.get(DynamicDO.PROPERTY_IS_PUBLIC);
            if (isPublicInData instanceof Boolean) {
                dynamicDO.isPublic = ((Boolean) isPublicInData);
            }
        }
        if (dynamicDO.isPublic == null) {
            Object isPublicInData = data.get(DynamicDO.COLUMN_IS_PUBLIC);
            if (isPublicInData instanceof Boolean) {
                dynamicDO.isPublic = ((Boolean) isPublicInData);
            }
        }
        if (dynamicDO.isPublic == null) {
            dynamicDO.isPublic = isPublic;
        }
        if (dynamicDO.isPublic == null) {
            dynamicDO.isPublic = true;
        }
        // isPublic 处理结束
        dynamicDO.batchId = batchId;
        return dynamicDO;
    }

}
