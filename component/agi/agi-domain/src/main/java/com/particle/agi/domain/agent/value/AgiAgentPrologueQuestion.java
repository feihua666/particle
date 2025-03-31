package com.particle.agi.domain.agent.value;

import cn.hutool.json.JSONUtil;
import com.particle.common.domain.ValueObjRoot;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 开场白问题
 * </p>
 *
 * @author yangwei
 * @since 2025/2/20 13:12
 */
@Data
public class AgiAgentPrologueQuestion extends ValueObjRoot {

    private List<String> questions;

    public static AgiAgentPrologueQuestion create(List<String> questions) {
        AgiAgentPrologueQuestion agiAgentPrologueQuestion = new AgiAgentPrologueQuestion();
        agiAgentPrologueQuestion.questions = questions;
        return agiAgentPrologueQuestion;
    }

    /**
     * 根据json字符串创建
     * @param jsonStr
     * @return
     */
    public static AgiAgentPrologueQuestion createFromJsonStr(String jsonStr) {
        AgiAgentPrologueQuestion prologueQuestion = JSONUtil.toBean(jsonStr, AgiAgentPrologueQuestion.class);
        return prologueQuestion;
    }

}
