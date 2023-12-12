package com.particle.global.elasticsearch.test.demo;

import cn.hutool.core.date.DatePattern;
import com.particle.gobal.elasticsearch.dto.basic.BaseEsDO;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 测试EsDO实体类
 * </p>
 *
 * @author yangwei
 * @since 2023/12/7 14:01
 */
@Document(indexName = TestEsDO.indexName)
@Data
public class TestEsDO extends BaseEsDO {

    public final static String indexName = "test_esdo";

    @Field(store = true)
    private String title;

    @Field(store = true)
    private String content;

    @Field(store = true)
    private Boolean isSuccess;

    @Field(store = true)
    private Long someId;

    @Field(store = true,type = FieldType.Date,format = {},pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime someDateTime;


    @Field(store = true,type = FieldType.Date,format = {},pattern = DatePattern.NORM_DATE_PATTERN)
    private LocalDate someDate;
}
