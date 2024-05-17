package com.particle.global.mybatis.plus.fill;

import com.particle.global.dto.basic.DO;
import com.particle.global.dto.basic.TreeDO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwei
 * @since 2019/9/23 9:05
 */
@Setter
@Getter
@AllArgsConstructor
public class MpFillConfig {
    private String property;
    private Object value;
    private boolean insert;
    private boolean update;

    private static List<MpFillConfig> config;
    static {
        config = new ArrayList<>();
        config.add(new MpFillConfig(TreeDO.PROPERTY_LEVEL,TreeDO.INIT_LEVEL,true,false));
        config.add(new MpFillConfig(DO.PROPERTY_VERSION,DO.INIT_VERSION,true,false));

    }

    public static List<MpFillConfig> getConfig(Long loginUserId){
        List<MpFillConfig> configDynamic = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        configDynamic.add(new MpFillConfig(DO.PROPERTY_CREATE_BY, loginUserId,true,false));
        configDynamic.add(new MpFillConfig(DO.PROPERTY_CREATE_AT, now,true,false));
        configDynamic.add(new MpFillConfig(DO.PROPERTY_UPDATE_BY, loginUserId,false,true));
        configDynamic.add(new MpFillConfig(DO.PROPERTY_UPDATE_AT,now,true,true));
        configDynamic.addAll(config);
        return configDynamic;
    }
}
