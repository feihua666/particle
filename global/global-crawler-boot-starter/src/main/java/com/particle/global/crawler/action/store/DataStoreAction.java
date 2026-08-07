package com.particle.global.crawler.action.store;

import com.particle.global.crawler.action.ActionResult;
import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 结构化数据存储 Action
 * <p>
 * 将提取的结构化数据保存到 DataStorage。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class DataStoreAction extends StoreBaseAction {

    private String variableKey;

    public DataStoreAction() {
        setType(ActionType.STORE_DATA);
    }


    @Override
    public ActionResult doExecute(RuntimeContext context) {
        Object data = context.getVariables().get(variableKey);
        if (data == null) {
            data = context.getVariables();
        }

        context.getDataStorage().save(variableKey, data);

        log.debug("结构化数据已保存: key={}", variableKey);
        return ActionResult.success("数据已保存: " + variableKey);
    }

    public static DataStoreAction create(String variableKey) {
        DataStoreAction action = new DataStoreAction();
        action.setVariableKey(variableKey);
        return action;
    }

}
