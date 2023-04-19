package com.particle.global.light.share.trans.anno;

import com.particle.global.light.share.trans.TransConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一般为vo层使用
 * 标注到类上
 * 可以单独使用或结合 {@link Trans} 使用
 * @author  yangwei
 * Created at 2019/10/9 9:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface TransItem {

    /**
     * 类型
     * TransService 的support方法参数
     * @return
     */
    String type() default TransConstants.defaultTransType;

    /**
     * 表名字符串，仅支持{@link com.particle.global.trans.api.impl.TableNameTransServiceImpl 实现的特殊定义}
     * 优先级高于 {@link TransItem#tableNameClass()}
     * 仅type={@link com.particle.global.trans.api.impl.TableNameTransServiceImpl#TRANS_BY_TABLE_NAME} 即 {@link TransConstants#defaultTransType}有效
     * @return
     */
    String tableName() default "";

    /**
     * 表名实体类，仅支持{@link com.particle.global.trans.api.impl.TableNameTransServiceImpl 实现的特殊定义}
     * 如果该类存在mybatis_plus的注解{@link com.baomidou.mybatisplus.annotation.TableName}则使用该注解的表名，如果不存在注解，则使用类名转下划线作为表名
     * 优先经低于 {@link TransItem#tableName()}
     * 仅type={@link com.particle.global.trans.api.impl.TableNameTransServiceImpl#TRANS_BY_TABLE_NAME} 即 {@link TransConstants#defaultTransType}有效
     * @return
     */
    Class tableNameClass() default Void.class;

    /**
     * 根据哪一列翻译，可以写表列名或实体属性名（实体属性名会转为下划线作为列名）
     * 仅type={@link com.particle.global.trans.api.impl.TableNameTransServiceImpl#TRANS_BY_TABLE_NAME} 即 {@link TransConstants#defaultTransType}有效
     *
     * @return
     */
    String tableField() default "id";
    /**
     * 需要翻译的字段名称
     * 翻译进行时会到该字段名称的get方法的值作为TransService的key
     * @return
     */
    String byFieldName();
    /**
     * 需要被翻译的字段名称
     * 翻译进行时会到标注于字段名称的get方法的值作为TransService的key
     * forFieldName 是目标字段名
     * @return
     */
    String forFieldName();

    /**
     * 当翻译结果是一个对象时，可以使用该字段取对象的一个属性值
     * 当翻译结果是一个集合时，可以使用集合中该字段取对象的一个属性值 仅支持List集合
     * type={@link com.particle.global.trans.api.impl.TableNameTransServiceImpl#TRANS_BY_TABLE_NAME} 即 {@link TransConstants#defaultTransType}必填
     * @return
     */
    String mapValueField() default "";

    /**
     * 如果是集合是否转为字符串拼接，仅支持字符串字段
     * @return
     */
    boolean isJoin()  default true;

    /**
     * 当翻译结果是一个集合时，可以使用的分隔符
     * @return
     */
    String mapJoinSeparator() default "/";

    /**
     * 是否是一组翻译，如果为true表示翻译的key是一个以英文逗号分隔的，翻译的结果以逗号拼接，如果翻译的结果字段类型不是字符串，以改用集合，只支持key为字符串
     * @return
     */
    boolean isGroup() default false;

    /**
     * 只使用批量翻译，在未实现批量翻译接口，或批量翻译结果为空是，会尝试使用单个翻译，设置为true会提高性能且保存已经实现了对应类型的批量翻译接口
     * @return
     */
    boolean batchOnly() default false;

    /**
     * 当值存在时不翻译
     * @return
     */
    boolean notTransWhenExist() default true;
}
