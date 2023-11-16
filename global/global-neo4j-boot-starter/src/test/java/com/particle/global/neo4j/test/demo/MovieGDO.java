package com.particle.global.neo4j.test.demo;

import com.particle.global.neo4j.dto.basic.BaseGDO;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 一个示例实体,电影
 * </p>
 *
 * @author yangwei
 * @since 2023/11/14 18:15
 */
@Node("movie")
@Data
public class MovieGDO extends BaseGDO {

    /**
     * 电影名称
     */
    private String name;

    @Relationship(type = "acted", direction = Relationship.Direction.INCOMING)
    private List<ActorGRDO> actors = new ArrayList<>();

    @Relationship(type = "directed", direction = Relationship.Direction.INCOMING)
    private List<PersonGDO> directors = new ArrayList<>();
}
