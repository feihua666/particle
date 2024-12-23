package com.particle.global.neo4j.test.demo;

import com.particle.global.neo4j.dto.basic.BaseGRDO;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

/**
 * <p>
 * 一个示例实体,电影
 * </p>
 *
 * @author yangwei
 * @since 2023/11/14 18:15
 */
@RelationshipProperties
@Data
public class ActorGRDO extends BaseGRDO{

    private final List<String> roles;

    @TargetNode
    private  PersonGDO person;

    public ActorGRDO(PersonGDO person, List<String> roles) {
        this.person = person;
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

}
