package com.particle.global.crawler.action.store;

import com.particle.global.crawler.action.ActionResult;
import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 原始数据存储 Action
 * <p>
 * 将原始 HTML、JSON 等数据保存到 RawStorage。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class RawStoreAction extends StoreBaseAction {

    private String variableKey;

    public RawStoreAction() {
        setType(ActionType.STORE_RAW);
    }

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        Object data = context.getVariables().get(variableKey);
        if (data == null) {
            return ActionResult.fail("变量不存在: " + variableKey);
        }
        context.getRawStorage().save(variableKey, (String) data);

        log.debug("原始数据已保存: key={}", variableKey);
        return ActionResult.success("原始数据已保存: " + variableKey);
    }


    public static RawStoreAction create(String variableKey) {
        RawStoreAction action = new RawStoreAction();
        action.setVariableKey(variableKey);
        return action;
    }

}
