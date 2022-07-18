package com.particle.global.mybatis.plus.crud;

import cn.hutool.core.util.ReflectUtil;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;

/**
 * Created by yangwei
 * Created at 2020/11/9 17:24
 */
public class TreeServiceHelperTool {

    /**
     * 根据风节点，设置子节点parentIdx的值包括parentId本身
     * @param child
     * @param parent
     * @param <Po>
     * @return
     */
    public static <Po> Po initParentIdXByParent(Po child, Po parent){
        // 先初始化child
        ((BaseTreeDO) child).setLevel(BaseTreeDO.INIT_LEVEL);
        ((BaseTreeDO) child).setParentId(null);
        String parentIdx = null;
        for (int i = 1; i < BaseTreeDO.MAX_LEVEL; i++) {
            parentIdx = BaseTreeDO.PROPERTY_PARENT_ID + i;
            ReflectUtil.setFieldValue(child,parentIdx,null);
        }

        if (parent != null) {
            ((BaseTreeDO) child).setLevel(((BaseTreeDO) parent).getLevel() + 1);
            if(((BaseTreeDO) child).getLevel() > BaseTreeDO.MAX_LEVEL){
                throw new RuntimeException("最大支持的树深度为" + BaseTreeDO.MAX_LEVEL);
            }
            ((BaseTreeDO) child).setParentId(((BaseTreeDO) parent).getId());
            for (int i = 1; i < BaseTreeDO.MAX_LEVEL; i++) {
                parentIdx = BaseTreeDO.PROPERTY_PARENT_ID + i;
                if (((BaseTreeDO) child).getLevel().equals(i + 1)) {
                    ReflectUtil.setFieldValue(((BaseTreeDO) child),parentIdx,((BaseTreeDO) parent).getId());
                    break;
                }else {
                    ReflectUtil.setFieldValue(child,parentIdx, ReflectUtil.getFieldValue(parent,parentIdx));
                }
            }
        }
        return (Po) child;
    }

    /**
     * 根据父级的level设置查询的parentIdx
     * @param child
     * @param parent
     */
    public static <Po> Po initQueryChildrenParentIdXByParentLevel(Po child, Po parent){
        String parentIdx = BaseTreeDO.PROPERTY_PARENT_ID + ((BaseTreeDO) parent).getLevel();
        ReflectUtil.setFieldValue(child,parentIdx,((BaseTreeDO) parent).getId());
        return child;
    }
}
