package com.particle.global.trans.helper;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.SimpleCache;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.light.share.constant.ClassAdapterConstants;
import com.particle.global.light.share.trans.anno.*;
import com.particle.global.tool.collection.CollectionTool;
import com.particle.global.trans.GlobalTransAutoConfiguration;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.api.TableNameResolver;
import com.particle.global.trans.api.impl.TableNameTransServiceImpl;
import com.particle.global.trans.result.TransResult;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * 翻译帮助类
 * Created by yangwei
 * Created at 2019/11/25 10:35
 */
@Component
@Slf4j
public class TransHelper {

    private static final String transNotifyThresholdPlaceholder = "particle.notify.trans.threshold";


    /**
     * 注解缓存
     */
    private static final SimpleCache<String, Annotation> ANNOTATION_CACHE = new SimpleCache<>();
    private static final SimpleCache<String, ITransService> TRANS_SERVICE_CACHE = new SimpleCache<>();

    @Autowired(required = false)
    private List<ITransService<?,?>> transServices;

    @Autowired
    @Qualifier(GlobalTransAutoConfiguration.transTaskExecutor)
    private ExecutorService transTaskExecutor;
    /**
     * 翻译时长通知阈值单位ms
     */
    @Value("${"+ transNotifyThresholdPlaceholder +":200}")
    private long transNotifyThreshold = 200;

    @Autowired
    private TableNameResolver tableNameResolver;

    /**
     * 翻译入口
     * @param body
     */
    public Object trans(Object body){
        if (body == null) {
            return body;
        }
        long start = System.currentTimeMillis();
        log.debug("翻译开始:bodyClass={}",body.getClass().getName());

        transObj(body);

        long transDuration = System.currentTimeMillis() - start;
        log.debug("翻译结束:duration={}ms",transDuration);
        if (transDuration > transNotifyThreshold) {
            if (ClassLoaderUtil.isPresent(ClassAdapterConstants.NOTIFY_TOOL_CLASS_NAME)) {
                com.particle.global.notification.notify.NotifyParam notifyParam = com.particle.global.notification.notify.NotifyParam.system();
                notifyParam.setContentType("trans.duration");
                notifyParam.setTitle("trans 翻译执行时长超过阈值");
                notifyParam.setSuggest("您可以通过 "+ transNotifyThresholdPlaceholder +" 配置改变阈值");
                HttpServletRequest request = getRequest();
                String url = Optional.ofNullable(request).map(request1 -> request1.getRequestURI()).orElse("未知");
                notifyParam.setContent(StrUtil.format("trans 翻译执行时长{}ms超过阈值{}ms,className={},url={}"
                        ,transDuration
                        ,transNotifyThreshold
                        ,body.getClass().getName()
                        ,url
                ));
                com.particle.global.notification.notify.NotifyTool.notify(notifyParam);
            }

        }
        return body;
    }

    public Object transObj(Object body) {
        // 翻译支持
        if (!isEmpty(transServices)){
            if (body instanceof PageResponse) {
                List records = ((PageResponse) body).getData();
                if (!isEmpty(records)) {
                    transCollection(records);
                }
            } if (body instanceof MultiResponse) {
                List records = ((MultiResponse) body).getData();
                if (!isEmpty(records)) {
                    transCollection(records);
                }
            } if (body instanceof SingleResponse) {
                Object obj = ((SingleResponse) body).getData();
                if (obj != null) {
                    transCollection(CollectionTool.newArrayList(obj));
                }
            }else if(body instanceof Collection) {
                transCollection((Collection)body);

            }else{
                transCollection(CollectionTool.newArrayList(body));
            }
        }
        return body;
    }
    /**
     * 翻译上下文类
     */
    @Setter
    @Getter
    private class TransContext {
        /**
         * 存储某一个类型对应的所有要翻译的原始值
         */
        private Map<String, Set<Object>> keysMap = new HashMap<>();
        /**
         * 存储某一个类型对应的所有翻译的结果值
         */
        private Map<String, List<TransResult>> transResultMap = new HashMap<>();
        /**
         * 存储所有的翻译元数据
         */
        private List<TransMeta> transMetas = new ArrayList<>();

        /**
         * 添加翻译描述信息
         * @param transMeta 翻译元数据，根据各个翻译注解汇总而来
         */
        public void addTransMeta(TransMeta transMeta) {

            if (transMeta.getByFieldValue() != null) {
                if (transMeta.isNotTransWhenExist()) {
                    Object fieldValue = getObjValue(transMeta.getItem(), transMeta.getForFieldName());
                    if (fieldValue != null) {
                        return;
                    }
                }


                HashSet<Object> byFieldValues = new HashSet<>();
                // 对isGroup支持
                if (transMeta.isGroup()) {
                    if (transMeta.byFieldValue instanceof String) {
                        for (String value : ((String) transMeta.byFieldValue).split(",")) {
                            byFieldValues.add(value);
                        }
                    } else if (transMeta.byFieldValue instanceof String[]) {
                        for (String value : ((String[]) transMeta.byFieldValue)) {
                            byFieldValues.add(value);
                        }
                    } else if (transMeta.byFieldValue instanceof Collection) {
                        for (Object value : ((Collection) transMeta.byFieldValue)) {
                            byFieldValues.add(value);
                        }
                    } else {
                        byFieldValues.add(transMeta.byFieldValue);
                    }
                } else {
                    byFieldValues.add(transMeta.byFieldValue);
                }

                String key = getKey(transMeta);
                if (keysMap.containsKey(key)) {
                    keysMap.get(key).addAll(byFieldValues);
                } else {
                    HashSet<Object> objects = new HashSet<>();
                    objects.addAll(byFieldValues);
                    keysMap.put(key, objects);
                }
                transMeta.setByFieldValues(byFieldValues);
                transMetas.add(transMeta);
            }
        }

        /**
         * 添加翻译的结果
         * @param type 翻译类型，如：根据字典id翻译就是一个类型Dict.TRANS_DICT_BY_ID
         * @param results 翻译的结果
         */
        public void addTransResult(String type, List<TransResult> results) {
            if (!isEmpty(results)) {
                if (transResultMap.containsKey(type)) {
                    transResultMap.get(type).addAll(results);
                } else {
                    transResultMap.put(type, results);
                }
            }
        }

        /**
         * 获取批量翻译的结果
         * @param type 翻译类型，如：根据字典id翻译就是一个类型Dict.TRANS_DICT_BY_ID
         * @param key 翻译的原始值，如：字典id就是一个原始值
         * @return
         */
        public TransResult getBatchTransResult(String type, Object key) {
            List<TransResult> transResults = transResultMap.get(type);
            if (!isEmpty(transResults)) {
                return transResults.stream().filter(item -> Objects.equals(key, item.getKey())).findFirst().orElse(null);
            }
            return null;
        }
    }

    /**
     * 翻译描述元数据信息
     */
    @Setter
    @Getter
    public static class TransMeta {
        /**
         * 翻译注解所在的对象
         */
        private Object item;
        /**
         * 翻译的类型
         */
        private String type;

        /**
         * 表名字符串，仅支持{@link TableNameTransServiceImpl 实现的特殊定义}
         * 优先级高于 {@link TransFor#tableNameClass()}
         * 仅type={@link TableNameTransServiceImpl#TRANS_BY_TABLE_NAME}有效
         * @return
         */
        private String tableName;

        /**
         * 表名实体类，仅支持{@link TableNameTransServiceImpl 实现的特殊定义}
         * 如果该类存在mybatis_plus的注解{@link TableName}则使用该注解的表名，如果不存在注解，则使用类名转下划线作为表名
         * 优先经低于 {@link TransFor#tableName()}
         * 仅type={@link TableNameTransServiceImpl#TRANS_BY_TABLE_NAME}有效
         * @return
         */
        private Class tableNameClass;

        /**
         * 根据哪一列翻译，可以写表列名或实体属性名（实体属性名会转为下划线作为列名）
         * 仅type={@link TableNameTransServiceImpl#TRANS_BY_TABLE_NAME}有效
         *
         * @return
         */
        private String tableField = "id";

        /**
         * 提供值的字段
         */
        private String byFieldName;
        /**
         * 字段值
         */
        private Object byFieldValue;

        /**
         * 字段值，目前仅group翻译时使用
         */
        private HashSet<Object> byFieldValues;

        /**
         * 目标字段名
         */
        private String forFieldName;
        /**
         * 目标字段类型
         */
        private Class forFieldNameType;

        /**
         * 当翻译结果是一个对象时，可以使用该字段取对象的一个属性值
         * 当翻译结果是一个集合时，可以使用集合中该字段取对象的一个属性值 仅支持List集合
         */
        private String mapValueField;
        /**
         * 仅限于支持{@link TransTool#putThreadLocalTransData(TransTool.ThreadLocalTransData)} 省的再麻烦写一个对象
         */
        private String mapKeyField;

        /**
         * 如果是集合是否转为字符串拼接，仅支持字符串字段
         */
        private boolean join;

        /**
         * 当翻译结果是一个集合时，可以使用的分隔符
         */
        private String mapJoinSeparator;

        /**
         * 是否是一组翻译，如果为true表示翻译的key是一个以英文逗号分隔的，翻译的结果以逗号拼接，如果翻译的结果字段类型不是字符串，以改用集合，只支持key为字符串
         * @return
         */
        boolean group;

        /**
         * 只使用批量翻译，在未实现批量翻译接口，或批量翻译结果为空是，会尝试使用单个翻译，设置为true会提高性能且保存已经实现了对应类型的批量翻译接口
         * @return
         */
        boolean batchOnly;

        /**
         * 当值存在时不翻译
         */
        boolean notTransWhenExist;
    }

    /**
     * 获取表名
     * @param transMeta
     * @return
     */
    private String tableNameResolve (TransMeta transMeta){
        return tableNameResolver.resolve(transMeta.getTableNameClass(), transMeta.getTableName());
    }
    /**
     * 目前以support的type为key，
     * @param transMeta
     * @return
     */
    private String getKey (TransMeta transMeta){
        return transMeta.getType();
    }

    /**
     * 将TransItem注解生成对应元数据
     * @param transItem
     * @param body
     * @return
     */
    private TransMeta newTransMeta (TransItem transItem, Object body){
        TransMeta transMeta = new TransMeta();
        transMeta.setByFieldName(transItem.byFieldName());
        transMeta.setType(transItem.type());

        transMeta.setTableName(transItem.tableName());
        transMeta.setTableNameClass(transItem.tableNameClass());
        transMeta.setTableField(transItem.tableField());

        transMeta.setForFieldName(transItem.forFieldName());
        transMeta.setMapValueField(transItem.mapValueField());
        transMeta.setJoin(transItem.isJoin());
        transMeta.setMapJoinSeparator(transItem.mapJoinSeparator());
        transMeta.setGroup(transItem.isGroup());
        transMeta.setBatchOnly(transItem.batchOnly());
        transMeta.setNotTransWhenExist(transItem.notTransWhenExist());
        transMeta.setItem(body);
        Object fieldValue = ReflectUtil.getFieldValue(body, transItem.byFieldName());
        transMeta.setByFieldValue(fieldValue);

        // 如果不是对应的表解析类型，也就不用获取了
        if (transMeta.getType().equals(TableNameTransServiceImpl.TRANS_BY_TABLE_NAME)) {
            transMeta.setTableName(tableNameResolve(transMeta));
            // 特殊化type,将查询的字段名称和表名、字段列拼接成一个类型
            transMeta.setType(TableNameTransServiceImpl.TRANS_BY_TABLE_NAME + StrUtil.format("{}:{}:{}", transMeta.getMapValueField(), transMeta.getTableName(), transMeta.getTableField()));
        }

        return transMeta;
    }


    /**
     * 将TransBy注解生成对应元数据
     * @param transBy
     * @param forFieldName
     * @param body
     * @return
     */
    private TransMeta newTransMeta (TransBy transBy, String forFieldName, Object body){
        TransMeta transMeta = new TransMeta();
        transMeta.setByFieldName(transBy.byFieldName());
        transMeta.setType(transBy.type());

        transMeta.setTableName(transBy.tableName());
        transMeta.setTableNameClass(transBy.tableNameClass());
        transMeta.setTableField(transBy.tableField());


        transMeta.setForFieldName(forFieldName);
        transMeta.setMapValueField(transBy.mapValueField());
        transMeta.setJoin(transBy.isJoin());
        transMeta.setMapJoinSeparator(transBy.mapJoinSeparator());
        transMeta.setGroup(transBy.isGroup());
        transMeta.setBatchOnly(transBy.batchOnly());
        transMeta.setNotTransWhenExist(transBy.notTransWhenExist());
        transMeta.setItem(body);
        Object fieldValue = ReflectUtil.getFieldValue(body, transBy.byFieldName());
        transMeta.setByFieldValue(fieldValue);

        // 如果不是对应的表解析类型，也就不用获取了
        if (transMeta.getType().equals(TableNameTransServiceImpl.TRANS_BY_TABLE_NAME)) {
            transMeta.setTableName(tableNameResolve(transMeta));
            // 特殊化type,将查询的字段名称和表名、字段列拼接成一个类型
            transMeta.setType(TableNameTransServiceImpl.TRANS_BY_TABLE_NAME + StrUtil.format("{}:{}:{}", transMeta.getMapValueField(), transMeta.getTableName(), transMeta.getTableField()));
        }

        return transMeta;
    }

    /**
     * 将TransFor注解生成对应元数据
     * @param transFor
     * @param byFieldName
     * @param body
     * @return
     */
    private TransMeta newTransMeta (TransFor transFor, String byFieldName, Object body){
        TransMeta transMeta = new TransMeta();
        transMeta.setByFieldName(byFieldName);
        transMeta.setType(transFor.type());

        transMeta.setTableName(transFor.tableName());
        transMeta.setTableNameClass(transFor.tableNameClass());
        transMeta.setTableField(transFor.tableField());

        transMeta.setForFieldName(transFor.forFieldName());
        transMeta.setMapValueField(transFor.mapValueField());
        transMeta.setJoin(transFor.isJoin());
        transMeta.setMapJoinSeparator(transFor.mapJoinSeparator());
        transMeta.setGroup(transFor.isGroup());
        transMeta.setBatchOnly(transFor.batchOnly());
        transMeta.setNotTransWhenExist(transFor.notTransWhenExist());
        transMeta.setItem(body);
        Object fieldValue = ReflectUtil.getFieldValue(body, byFieldName);
        transMeta.setByFieldValue(fieldValue);

        // 如果不是对应的表解析类型，也就不用获取了
        if (transMeta.getType().equals(TableNameTransServiceImpl.TRANS_BY_TABLE_NAME)) {
            transMeta.setTableName(tableNameResolve(transMeta));
            // 特殊化type,将查询的字段名称和表名、字段列拼接成一个类型
            transMeta.setType(TableNameTransServiceImpl.TRANS_BY_TABLE_NAME + StrUtil.format("{}:{}:{}", transMeta.getMapValueField(), transMeta.getTableName(), transMeta.getTableField()));
        }
        return transMeta;
    }

    /**
     * 所有的转为集合从这里为入口
     * @param c
     */
    private void transCollection (Collection c){
        int transTimesCount = 1;
        for (Object item : c) {
            if (item instanceof Map) {
                break;
            }
            TransTimes transTimes = getAnnotation(item.getClass(), TransTimes.class);
            if (transTimes != null) {
                if (transTimesCount < transTimes.times()) {
                    transTimesCount = transTimes.times();
                    break;
                }
            }
            // 只处理第一条就行
            break;
        }
        for (int i = 0; i < transTimesCount; i++) {
            doTransCollection(c);
        }
    }

    /**
     * 执行翻译
     * @param c
     */
    private void doTransCollection (Collection c){
        TransContext transContext = new TransContext();
        c.stream().forEach(item -> {
            // 填充翻译元数据，到上下文中
            transPojo(item, transContext);
        });
        // 批量翻译并将翻译结果放到context中
        Map<String, Future<List>> cfMap = new HashMap<>();
        for (Map.Entry<String, Set<Object>> stringSetEntry : transContext.keysMap.entrySet()) {
            ITransService iTransServiceByType = getITransServiceByType(stringSetEntry.getKey(), true);
            if (iTransServiceByType != null) {
                // 根据值批量翻译
                Future<List> submit = transTaskExecutor.submit(() -> iTransServiceByType.transBatch(stringSetEntry.getKey(), stringSetEntry.getValue()));
                cfMap.put(stringSetEntry.getKey(), submit);
            }
        }
        if (!cfMap.isEmpty()) {
            cfMap.entrySet().forEach(stringCompletableFutureEntry -> {
                try {
                    transContext.addTransResult(stringCompletableFutureEntry.getKey(), stringCompletableFutureEntry.getValue().get());
                } catch (InterruptedException e) {
                    log.error("异步获指翻译异常 key={}", stringCompletableFutureEntry.getKey(), e);
                } catch (ExecutionException e) {
                    log.error("异步获指翻译异常 key={}", stringCompletableFutureEntry.getKey(), e);
                }
            });
        }

        CountDownLatch countDownLatch = new CountDownLatch(transContext.getTransMetas().size());
        // 设置目标值 改为异步执行
        for (TransMeta transMeta : transContext.getTransMetas()) {
            transTaskExecutor.execute(() -> {
                try {
                    doTrans(transMeta, transContext);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("翻译在设置目标值等待时异常", e);
        }
        // 翻译field 注解支持
        c.stream().forEach(item -> transFieldSupport(item));

    }
    /**
     * {@link TransField} 支持
     * @param body
     */
    private void transFieldSupport (Object body){
        if (body == null) {
            return;
        }
        if (body instanceof Map) {
            return;
        }

        Class<?> bodyClass = body.getClass();
        for (Field field : ReflectUtil.getFields(bodyClass)) {
            // 翻译字段本身
            TransField transField = getAnnotation(field, TransField.class);
            if (transField != null) {
                //  每一个transField单独翻译
                trans(ReflectUtil.getFieldValue(body, field));
            }
        }
    }
    /**
     * 填充翻译元数据，到上下文中
     * @param body
     * @param transContext
     */
    private void transPojo (Object body, TransContext transContext){
        // 只翻译vo实体
        if (body == null) {
            return;
        }
        // threadLocal
        handleTransItemThreadLocal(body, transContext);
        // map不处理
        if (body instanceof Map) {

            return;
        }
        Class<?> bodyClass = body.getClass();

        // @Trans
        handleTransAnnotion(body, transContext);
        // @TransItem
        handleTransItemAnnotion(body, transContext);
        for (Field field : ReflectUtil.getFields(bodyClass)) {
            // @TransBy
            handleTransByAnnotion(field, body, transContext);
            // @TransFor
            handleTransForAnnotion(field, body, transContext);
        }

    }
    /**
     * trans 支持
     * @param body
     */
    private void handleTransAnnotion (Object body, TransContext transContext){
        Trans trans = getAnnotation(body.getClass(), Trans.class);
        if (trans != null) {
            if (trans.value() != null) {
                for (TransItem transItem : trans.value()) {
                    transContext.addTransMeta(newTransMeta(transItem, body));
                }
            }
        }
    }

    /**
     * transItem 支持
     * @param body
     */
    private void handleTransItemAnnotion (Object body, TransContext transContext){
        TransItem transItem = getAnnotation(body.getClass(), TransItem.class);
        if (transItem != null) {
            transContext.addTransMeta(newTransMeta(transItem, body));
        }

    }

    /**
     * threadLocal支持
     * @param body
     * @param transContext
     */
    private void handleTransItemThreadLocal(Object body, TransContext transContext) {
        // 支持从threadLocal中获取
        TransTool.ThreadLocalTransData threadLocalTransData = TransTool.fetchThreadLocalTransData();
        if (threadLocalTransData != null) {
            List<TransMeta> transMetaList = threadLocalTransData.getTransMetaList();
            if (CollectionUtil.isNotEmpty(transMetaList)) {
                for (TransMeta transMeta : transMetaList) {
                    Object item = transMeta.getItem();
                    if (item == null) {
                        transMeta.setItem(body);
                    }
                    Object byFieldValue = transMeta.getByFieldValue();
                    if (byFieldValue == null) {
                        transMeta.setByFieldValue(getObjValue(body,transMeta.getByFieldName()));
                    }
                    transContext.addTransMeta(transMeta);
                }
            }
        }
    }

    /**
     * TransBy 支持
     * @param field
     * @param body
     */
    private void handleTransByAnnotion (Field field, Object body, TransContext transContext){
        TransBy transBy = getAnnotation(field, TransBy.class);
        if (transBy != null) {
            transContext.addTransMeta(newTransMeta(transBy, field.getName(), body));
        }
    }

    /**
     * transFor 支持
     * @param field
     * @param body
     */
    private void handleTransForAnnotion (Field field, Object body, TransContext transContext){
        TransFor transFor = getAnnotation(field, TransFor.class);
        if (transFor != null) {
            transContext.addTransMeta(newTransMeta(transFor, field.getName(), body));
        }
    }

    /**
     * 真正翻译处理
     * @param transMeta
     * @param transContext
     */
    private void doTrans (TransMeta transMeta, TransContext transContext){
        log.trace("doTrans: type={},byFieldName={},forFieldName={},mapValueField={},jsJoin={}", transMeta.getType(), transMeta.getByFieldName(), transMeta.forFieldName, transMeta.getMapValueField(), transMeta.isJoin());
        if (transMeta.byFieldValue == null) {
            return;
        }
        // 批量翻译的结果中有值，则使用批量翻译的结果
        if (transMeta.isGroup()) {
            HashSet<Object> byFieldValues = transMeta.getByFieldValues();
            List<TransResult> transResults = new ArrayList<>(byFieldValues.size());
            for (Object byFieldValue : byFieldValues) {
                TransResult transResult = transContext.getBatchTransResult(transMeta.getType(), byFieldValue);
                if (transResult != null) {
                    transResults.add(transResult);
                }
            }
            setFieldValueByGroup(transMeta, transResults);

            return;
        }
        Object fieldValue = transMeta.getByFieldValue();
        TransResult transResult = getTransResult(transMeta.getType(), transMeta.isBatchOnly(), transContext, fieldValue);
        if (transResult == null) {
            // 翻译字段有值，但没翻译出结果来，比如  根据用户id翻译昵称，有用户id但没有昵称，这一般认为这条数据不存在，所以警告一下，如果是间接翻译的，也不是什么错误信息
            log.warn("在翻译设置值时，获取为空，可能的原因是数据不匹配,type={},fieldValue={}", transMeta.getType(), fieldValue);
        } else {
            setFieldValue(transMeta, transResult);
        }

    }

    /**
     * 根据翻译值进行翻译
     * @param type 翻译类型
     * @param isBatchOnly
     * @param transContext
     * @param byFieldValue
     * @return
     */
    private TransResult getTransResult (String type,boolean isBatchOnly, TransContext transContext, Object
            byFieldValue){
        TransResult transResult = transContext.getBatchTransResult(type, byFieldValue);
        if (transResult != null) {
            return transResult;
        } else if (isBatchOnly) {
            return null;
        }


        // 单个支持
        ITransService iTransServiceByType = getITransServiceByType(type, false);

        // 如果批量支持不再单个支持处理，因为上面已经调用批量，没有返回数据，因此认为单个也不会有数据
        if (iTransServiceByType.supportBatch(type)) {
            return null;
        }

        if (iTransServiceByType != null) {
            if (byFieldValue != null) {
                transResult = iTransServiceByType.trans(type, byFieldValue);
            }
        }
        return transResult;
    }

    /**
     * 获取翻译的值
     * @param transMeta
     * @param transResult
     * @return
     */
    public static Object getTransFormatValue (TransMeta transMeta, TransResult transResult){
        Object transValue = transResult.getTransValue();
        // 这里不作任何判断，请谨慎使用
        if (!StrUtil.isEmpty(transMeta.getMapValueField())) {
            if (transValue instanceof Collection) {
                transValue = ((Collection) transValue).stream().map(obj -> getObjValue(obj, transMeta.getMapValueField())).filter(Objects::nonNull).collect(Collectors.toList());
                if (transMeta.isJoin()) {
                    transValue = ((Collection) transValue).stream().collect(Collectors.joining(transMeta.getMapJoinSeparator()));
                }
            } else {
                transValue = getObjValue(transValue, transMeta.getMapValueField());
            }
        }
        return transValue;
    }

    /**
     * 获取对象的属性值
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getObjValue (Object obj, String fieldName){
        if (obj instanceof Map) {
            return ((Map) obj).get(fieldName);
        }
        return ReflectUtil.getFieldValue(obj, fieldName);
    }

    /**
     * 设置翻译值
     * @param transMeta
     * @param transResult
     */
    public static void setFieldValue (TransMeta transMeta, TransResult transResult){
        Object transValue = getTransFormatValue(transMeta, transResult);
        Object fieldValue = transValue;
        // 这里假设集合中的类型都是一致的，理论上应该也是一致的
        // Class forFieldNameType = transMeta.getForFieldNameType();
        Object item = transMeta.getItem();
        // if (forFieldNameType == null) {
        //     Field field = ReflectUtil.getField(item.getClass(), transMeta.getForFieldName());
        //     forFieldNameType = field.getType();
        // }
        if (item instanceof Map) {
            ((Map) item).put(transMeta.getForFieldName(), fieldValue);
        }else {
            ReflectUtil.setFieldValue(item, transMeta.getForFieldName(), fieldValue);
        }
    }
    /**
     * 设置翻译值
     * @param transMeta
     * @param transResults
     */
    private void setFieldValueByGroup (TransMeta transMeta, List < TransResult > transResults){
        if (isEmpty(transResults)) {
            return;
        }
        Set<Object> transValues = new HashSet<>(transResults.size());
        for (TransResult transResult : transResults) {
            Object transValue = getTransFormatValue(transMeta, transResult);
            if (transValue != null) {
                transValues.add(transValue);
            }
        }
        if (!isEmpty(transValues)) {
            Object fieldValue = transValues;
            // 这里假设集合中的类型都是一致的，理论上应该也是一致的
            Class forFieldNameType = transMeta.getForFieldNameType();
            Object item1 = transMeta.getItem();
            if (forFieldNameType == null) {
                Field field = ReflectUtil.getField(item1.getClass(), transMeta.getForFieldName());
                forFieldNameType = field == null ? null : field.getType();
            }
            if (forFieldNameType != null) {
                if (forFieldNameType.isAssignableFrom(String.class)) {
                    fieldValue = transValues.stream().filter(Objects::nonNull).map(item -> item.toString()).collect(Collectors.joining(transMeta.getMapJoinSeparator()));
                } else if (forFieldNameType.isAssignableFrom(String[].class)) {
                    fieldValue = transValues.stream().filter(Objects::nonNull).map(item -> item.toString()).collect(Collectors.toList());
                }
            }else {
                // 按字符串处理
                fieldValue = transValues.stream().filter(Objects::nonNull).map(item -> item.toString()).collect(Collectors.joining(transMeta.getMapJoinSeparator()));
            }

            if (item1 instanceof Map) {
                ((Map) item1).put(transMeta.getForFieldName(), fieldValue);
            }else {
                ReflectUtil.setFieldValue(item1, transMeta.getForFieldName(), fieldValue);
            }
        }


    }
    /**
     * 获取支持的翻译类
     * @param type
     * @return
     */
    private ITransService getITransServiceByType (String type,boolean isBatch){
        ITransService iTransService = TRANS_SERVICE_CACHE.get(type + isBatch);
        if (iTransService != null) {
            return iTransService;
        }
        if (!isEmpty(transServices)) {
            iTransService = transServices.stream().filter(iTransServiceItem -> isBatch ? iTransServiceItem.supportBatch(type) : iTransServiceItem.support(type)).findFirst().orElse(null);
            if (iTransService == null) {
                log.warn("can not find iTransService for trans type={} isBatch={} ",type,isBatch);
                return null;
            }
            return TRANS_SERVICE_CACHE.put(type + isBatch, iTransService);
        }
        return null;
    }

    /**
     * 获取注解，加了缓存
     * @param field
     * @param annotationType
     * @param <A>
     * @return
     */
    private  <A extends Annotation > A getAnnotation(AnnotatedElement field, Class < A > annotationType) {
        String key = null;
        if (field instanceof Field) {
            key = ((Field) field).getDeclaringClass().getName() + ((Field) field).getName();
        } else if (field instanceof Class) {
            key = ((Class) field).getName();
        } else {
            key = field.getClass() + field.toString();
        }
        key += annotationType.getName();
        A a = (A) ANNOTATION_CACHE.get(key);
        if (a != null) {
            if (a == none) {
                return null;
            }
            return a;
        }
        a = AnnotationUtil.getAnnotation(field, annotationType);
        if (a == null) {
            // null 也缓存
            ANNOTATION_CACHE.put(key, none);
            return null;
        }
        return (A) ANNOTATION_CACHE.put(key, a);
    }

    /**
     * spring环境中获取当前请求对象
     *
     * @return
     */
    private static HttpServletRequest getRequest () {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {

        }
        return null;
    }

    private static final Annotation none = new Annotation() {
        @Override
        public Class<? extends Annotation> annotationType() {
            return null;
        }
    };
}
