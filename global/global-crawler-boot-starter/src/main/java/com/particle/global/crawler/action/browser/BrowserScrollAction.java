package com.particle.global.crawler.action.browser;

import com.particle.global.crawler.common.constants.ActionType;
import com.particle.global.crawler.runtime.RuntimeContext;

import com.particle.global.crawler.action.ActionResult;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 页面滚动 Action
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
@Data
public class BrowserScrollAction extends BrowserBaseAction {

    public BrowserScrollAction() {
        setType(ActionType.BROWSER_SCROLL);
    }

    /**
     * CSS 选择器
     * 为空时滚动 window，不为空时滚动指定元素
     */
    private String selector;

    /**
     * 滚动方向
     */
    private ScrollDirection direction;

    /**
     * 滚动距离
     * - 数字字符串：按正负值滚动（如 "300"、"-200"）
     * - "top"：滚动到顶部/最左侧
     * - "bottom"：滚动到底部/最右侧
     * - 其他字符串：滚动到指定元素位置
     */
    private String pixels;

    @Override
    public ActionResult doExecute(RuntimeContext context) {
        if (direction == null) {
            direction = ScrollDirection.VERTICAL;
        }
        // 确定滚动目标
        boolean isWindowScroll = selector == null || selector.isEmpty();
        // 解析滚动距离
        ScrollCommand command = parseScrollCommand(pixels);
        if (isWindowScroll) {
            log.info("滚动窗口：selector={},direction={},pixels={}", selector, direction, pixels);
            // Window 滚动
            executeWindowScroll(context, command);
        } else {
            log.info("滚动元素：selector={},direction={},pixels={}", selector, direction, pixels);
            // 元素滚动
            executeElementScroll(context, command);
        }
        return ActionResult.success("滚动成功");
    }

    /**
     * 解析滚动命令
     */
    private ScrollCommand parseScrollCommand(String pixels) {
        ScrollCommand command = new ScrollCommand();

        if (pixels == null || pixels.isEmpty()) {
            command.type = ScrollType.PIXELS;
            command.value = 300;
            return command;
        }

        // 尝试解析为数字
        try {
            command.type = ScrollType.PIXELS;
            command.value = Integer.parseInt(pixels);
            return command;
        } catch (NumberFormatException e) {
            // 不是数字，检查特殊命令
        }

        // 特殊命令
        switch (pixels.toLowerCase()) {
            case "top":
                command.type = ScrollType.TOP;
                break;
            case "bottom":
                command.type = ScrollType.BOTTOM;
                break;
            default:
                // 当作选择器处理
                command.type = ScrollType.ELEMENT;
                command.selector = pixels;
                break;
        }

        return command;
    }

    /**
     * 执行 Window 滚动
     */
    private void executeWindowScroll(RuntimeContext context, ScrollCommand command) {
        String js;
        switch (command.type) {
            case TOP:
                js = direction == ScrollDirection.VERTICAL
                    ? "window.scrollTo(0, 0)"
                    : "window.scrollTo(0, 0)";
                break;
            case BOTTOM:
                js = direction == ScrollDirection.VERTICAL
                    ? "window.scrollTo(0, document.body.scrollHeight)"
                    : "window.scrollTo(document.body.scrollWidth, 0)";
                break;
            case PIXELS:
                if (direction == ScrollDirection.VERTICAL) {
                    js = "window.scrollBy(0, " + command.value + ")";
                } else {
                    js = "window.scrollBy(" + command.value + ", 0)";
                }
                break;
            default:
                throw new RuntimeException("不支持的滚动类型: " + command.type);
        }
        context.getDriver().executeScript(js);
    }

    /**
     * 执行元素滚动
     */
    private void executeElementScroll(RuntimeContext context, ScrollCommand command) {
        String js;
        switch (command.type) {
            case ELEMENT:
                String targetSelector = command.selector != null ? command.selector : selector;
                js = "document.querySelector('" + targetSelector + "').scrollIntoView({behavior: 'smooth'})";
                break;
            case TOP:
                js = "document.querySelector('" + selector + "').scrollTop = 0";
                break;
            case BOTTOM:
                js = "const el = document.querySelector('" + selector + "'); " +
                     "el.scrollTop = el.scrollHeight";
                break;
            case PIXELS:
                if (direction == ScrollDirection.VERTICAL) {
                    js = "const el = document.querySelector('" + selector + "'); " +
                         "el.scrollTop += " + command.value;
                } else {
                    js = "const el = document.querySelector('" + selector + "'); " +
                         "el.scrollLeft += " + command.value;
                }
                break;
            default:
                throw new RuntimeException("不支持的滚动类型: " + command.type);
        }
        context.getDriver().executeScript(js);
    }

    /**
     * 滚动方向枚举
     */
    public enum ScrollDirection {
        /** 垂直滚动 */
        VERTICAL,
        /** 水平滚动 */
        HORIZONTAL
    }

    /**
     * 滚动类型枚举
     */
    private enum ScrollType {
        /** 按像素滚动 */
        PIXELS,
        /** 滚动到顶部/最左侧 */
        TOP,
        /** 滚动到底部/最右侧 */
        BOTTOM,
        /** 滚动到指定元素 */
        ELEMENT
    }

    /**
     * 滚动命令
     */
    private static class ScrollCommand {
        ScrollType type;
        int value;
        String selector;
    }

    public static BrowserScrollAction create(String selector, ScrollDirection direction, String pixels) {
        BrowserScrollAction action = new BrowserScrollAction();
        action.setSelector(selector);
        action.setDirection(direction);
        action.setPixels(pixels);
        return action;
    }

    public static BrowserScrollAction create(ScrollDirection direction, String pixels) {
        return create(null, direction, pixels);
    }

    public static BrowserScrollAction create(String pixels) {
        return create(null, ScrollDirection.VERTICAL, pixels);
    }

    public static BrowserScrollAction createToBottom() {
        return create(null, ScrollDirection.VERTICAL, "bottom");
    }

    public static BrowserScrollAction createToTop() {
        return create(null, ScrollDirection.VERTICAL, "top");
    }

    public static BrowserScrollAction createToElement(String selector) {
        return create(selector, ScrollDirection.VERTICAL, "element");
    }

    public static BrowserScrollAction create() {
        return create("300");
    }
}
