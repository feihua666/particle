package com.particle.component.light.share.dict;

/**
 * 一个标识字典项的接口
 * Created by yangwei
 * Created at 2019/12/18 11:23
 */
public interface IDictItem extends IBaseDictEnum {
    /**
     * 字典项编码,默认返回空，因为一般不用，都是通过字典组获取字典项
     * @return
     */
    default public String itemCode(){return null;}

    /**
     * 字典项值
     * @return
     */
    public String itemValue();

    /**
     * 字典组编码
     * @return
     */
    public String groupCode();
}
