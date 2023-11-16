package com.particle.global.neo4j.test.demo;

import com.particle.global.neo4j.dto.basic.BaseGDO;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * <p>
 * 一个示例实体,人、导演、演员等
 * </p>
 *
 * @author yangwei
 * @since 2023/11/14 18:15
 */
@Node("person")
@Data
public class PersonGDO extends BaseGDO {

    /**
     * 人员名称
     */
    private String name;
}
