package com.particle.global.crawler.pipeline;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.particle.global.crawler.action.CrawlAction;
import com.particle.global.crawler.action.browser.*;
import com.particle.global.crawler.action.extract.ExtractAttrAction;
import com.particle.global.crawler.action.extract.ExtractHtmlAction;
import com.particle.global.crawler.action.extract.ExtractTextAction;
import com.particle.global.crawler.action.extract.ExtractTitleAction;
import com.particle.global.crawler.action.flow.*;
import com.particle.global.crawler.action.store.DataStoreAction;
import com.particle.global.crawler.action.store.RawStoreAction;
import com.particle.global.tool.json.JsonTool;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

/**
 * Pipeline 构建器 - 链式 DSL
 * <p>
 * 提供流式 API 构建 Pipeline，是最推荐的 Pipeline 创建方式。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Getter
public class PipelineBuilder {

    private final DefaultPipeline pipeline;

    private PipelineBuilder() {
        this.pipeline = new DefaultPipeline();
    }

    /**
     * 创建新的 PipelineBuilder
     */
    public static PipelineBuilder create() {
        return new PipelineBuilder();
    }

    /**
     * 创建 PipelineBuilder，并设置名称
     */
    public static PipelineBuilder create(String name) {
        PipelineBuilder builder = new PipelineBuilder();
        builder.pipeline.setName(name);
        return builder;
    }
    /**
     * 从 JSON 中创建 Pipeline
     *
     * @param json JSON 字符串
     * @return Pipeline
     * @throws JsonProcessingException JSON 处理异常
     */
    @SneakyThrows
    public static CrawlPipeline fromJson(String json) {
        return JsonTool.getObjectMapper().readValue(json, DefaultPipeline.class);
    }
    /**
     * 设置 Pipeline 名称
     */
    public PipelineBuilder name(String name) {
        pipeline.setName(name);
        return this;
    }

    /**
     * 设置 Pipeline 描述
     */
    public PipelineBuilder description(String description) {
        pipeline.setDescription(description);
        return this;
    }
    /**
     * 设置 Pipeline 描述
     */
    public PipelineBuilder resultDataKeys(List<String> resultDataKeys) {
        pipeline.setResultDataKeys(resultDataKeys);
        return this;
    }


    /**
     * 设置初始变量
     */
    public PipelineBuilder variables(Map<String, Object> variables) {
        pipeline.setVariables(variables);
        return this;
    }

    /**
     * 添加单个变量
     */
    public PipelineBuilder variable(String key, Object value) {
        pipeline.getVariables().put(key, value);
        return this;
    }

    // ==================== 浏览器操作 Actions ====================

    /**
     * 新建标签页
     *
     * @param url 新页面 URL（可选）
     */
    public PipelineBuilder newTab(String url) {
        pipeline.addAction(BrowserNewTabAction.create(url));
        return this;
    }

    /**
     * 新建空白标签页
     */
    public PipelineBuilder newTab() {
        pipeline.addAction(BrowserNewTabAction.create());
        return this;
    }

    /**
     * 关闭当前标签页
     */
    public PipelineBuilder closeTab() {
        pipeline.addAction(BrowserCloseTabAction.create());
        return this;
    }

    /**
     * 打开页面
     *
     * @param url 页面 URL
     */
    public PipelineBuilder open(String url) {
        pipeline.addAction(BrowserOpenAction.create(url));
        return this;
    }

    /**
     * 点击元素
     *
     * @param selector CSS 选择器
     */
    public PipelineBuilder click(String selector) {
        pipeline.addAction(BrowserClickAction.create(selector, 5000));
        return this;
    }

    /**
     * 点击元素
     *
     * @param selector CSS 选择器
     * @param timeout  超时时间（毫秒）
     */
    public PipelineBuilder click(String selector, int timeout) {
        pipeline.addAction(BrowserClickAction.create(selector, timeout));
        return this;
    }

    /**
     * 输入文本
     *
     * @param selector CSS 选择器
     * @param text     要输入的文本
     */
    public PipelineBuilder input(String selector, String text) {
        pipeline.addAction(BrowserInputAction.create(selector, text, 5000));
        return this;
    }

    /**
     * 输入文本
     *
     * @param selector CSS 选择器
     * @param text     要输入的文本
     * @param timeout  超时时间（毫秒）
     */
    public PipelineBuilder input(String selector, String text, int timeout) {
        pipeline.addAction(BrowserInputAction.create(selector, text, timeout));
        return this;
    }

    /**
     * 悬停
     *
     * @param selector CSS 选择器
     */
    public PipelineBuilder hover(String selector) {
        pipeline.addAction(BrowserHoverAction.create(selector));
        return this;
    }

    /**
     * 悬停
     *
     * @param selector CSS 选择器
     * @param timeout  超时时间（毫秒）
     */
    public PipelineBuilder hover(String selector, int timeout) {
        pipeline.addAction(BrowserHoverAction.create(selector, timeout));
        return this;
    }


    /**
     * 滚动页面
     *
     * @param pixels 滚动像素数（正数向下，负数向上）
     */
    public PipelineBuilder scroll(int pixels) {
        pipeline.addAction(BrowserScrollAction.create(String.valueOf(pixels)));
        return this;
    }

    /**
     * 滚动到页面底部
     */
    public PipelineBuilder scrollToBottom() {
        pipeline.addAction(BrowserScrollAction.createToBottom());
        return this;
    }

    /**
     * 关闭浏览器
     */
    public PipelineBuilder close() {
        pipeline.addAction(BrowserCloseAction.create());
        return this;
    }

    /**
     * 按下回车键
     */
    public PipelineBuilder enter(String  selector) {
        pipeline.addAction(BrowserEnterAction.create(selector));
        return this;
    }

    /**
     * 按下指定按键
     *
     * @param key 按键名称（如 "Enter", "Tab", "Escape"）
     */
    public PipelineBuilder pressKey(String key) {
        pipeline.addAction(BrowserPressKeyAction.create(key));
        return this;
    }

    // ==================== 提取 Actions ====================

    /**
     * 提取文本
     *
     * @param selector CSS 选择器
     */
    public PipelineBuilder extractText(String selector) {
        return extractText(selector, null);
    }

    /**
     * 提取文本
     *
     * @param selector CSS 选择器
     * @param as       结果存储的变量名
     */
    public PipelineBuilder extractText(String selector, String as) {
        ExtractTextAction action = ExtractTextAction.create(selector, as);
        pipeline.addAction(action);
        return this;
    }

    /**
     * 提取 HTML
     *
     * @param selector CSS 选择器
     */
    public PipelineBuilder extractHtml(String selector) {
        return extractHtml(selector, null);
    }

    /**
     * 提取 HTML
     *
     * @param selector CSS 选择器
     * @param as       结果存储的变量名
     */
    public PipelineBuilder extractHtml(String selector, String as) {
        ExtractHtmlAction action = ExtractHtmlAction.create(selector);
        action.setResultKey(as);
        pipeline.addAction(action);
        return this;
    }

    /**
     * 提取属性
     *
     * @param selector CSS 选择器
     * @param attr     属性名
     */
    public PipelineBuilder extractAttr(String selector, String attr) {
        return extractAttr(selector, attr, null);
    }

    /**
     * 提取属性
     *
     * @param selector CSS 选择器
     * @param attr     属性名
     * @param as       结果存储的变量名
     */
    public PipelineBuilder extractAttr(String selector, String attr, String as) {
        ExtractAttrAction action = ExtractAttrAction.create(selector, attr);
        action.setResultKey(as);
        pipeline.addAction(action);
        return this;
    }

    /**
     * 提取标题
     *
     * @param as 结果存储的变量名
     */
    public PipelineBuilder extractTitle(String as) {
        ExtractTitleAction action = new ExtractTitleAction();
        action.setResultKey(as);
        pipeline.addAction(action);
        return this;
    }

    // ==================== 流程控制 Actions ====================

    /**
     * 延迟
     *
     * @param millis 延迟时间（毫秒）
     */
    public PipelineBuilder delay(long millis) {
        pipeline.addAction(DelayAction.create(millis));
        return this;
    }

    /**
     * 重试
     *
     * @param maxAttempts 最大重试次数
     * @param delayMs     重试间隔（毫秒）
     * @param actions     要重试的动作列表
     */
    public PipelineBuilder retry(int maxAttempts, long delayMs, CrawlAction... actions) {
        RetryAction retryAction = RetryAction.create(maxAttempts, delayMs);
        retryAction.setActions(java.util.Arrays.asList(actions));
        pipeline.addAction(retryAction);
        return this;
    }

    /**
     * 循环执行
     *
     * @param itemsKey 循环项列表变量名
     * @param actions  循环体中的动作列表
     */
    public PipelineBuilder loop(String itemsKey, CrawlAction... actions) {
        LoopAction loopAction = LoopAction.create(null, itemsKey, java.util.Arrays.asList(actions));
        pipeline.addAction(loopAction);
        return this;
    }

    /**
     * 条件执行
     *
     * @param condition   条件表达式
     * @param trueAction  条件为真时执行的动作
     * @param falseAction 条件为假时执行的动作（可选）
     */
    public PipelineBuilder conditional(String condition, CrawlAction trueAction, CrawlAction falseAction) {
        ConditionalAction conditionalAction = new ConditionalAction();
        conditionalAction.setExpression(condition);
        conditionalAction.setThenActions(trueAction != null ? java.util.Arrays.asList(trueAction) : null);
        conditionalAction.setElseActions(falseAction != null ? java.util.Arrays.asList(falseAction) : null);
        pipeline.addAction(conditionalAction);
        return this;
    }

    /**
     * 条件执行（仅 true 分支）
     *
     * @param condition  条件表达式
     * @param trueAction 条件为真时执行的动作
     */
    public PipelineBuilder conditional(String condition, CrawlAction trueAction) {
        return conditional(condition, trueAction, null);
    }

    /**
     * 退出循环
     */
    public PipelineBuilder breakLoop() {
        pipeline.addAction(new BreakAction());
        return this;
    }

    /**
     * 跳过本次迭代
     */
    public PipelineBuilder continueLoop() {
        pipeline.addAction(new ContinueAction());
        return this;
    }

    /**
     * 存储原始数据
     *
     * @param variableKey 变量名
     */
    public PipelineBuilder storeRaw( String variableKey) {
        pipeline.addAction(RawStoreAction.create( variableKey));
        return this;
    }

    /**
     * 存储结构化数据
     *
     * @param variableKey 变量名
     */
    public PipelineBuilder storeData(String variableKey) {
        pipeline.addAction(DataStoreAction.create(variableKey));
        return this;
    }

    /**
     * 自定义 Action
     *
     * @param action 自定义 Action
     */
    public PipelineBuilder add(CrawlAction action) {
        pipeline.addAction(action);
        return this;
    }

    /**
     * 构建 Pipeline
     */
    public CrawlPipeline build() {
        return pipeline;
    }

}
