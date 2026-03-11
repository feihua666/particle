package com.particle.global.crawler.dag;

import com.particle.global.crawler.tool.CrawlerWebDriverTool;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;
import com.particle.global.dag.runtime.executor.NodeExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

/**
 * 页面抓取节点执行器
 */
public class PageCrawlNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return CrawlerDagConstants.NodeType.PAGE_CRAWL.equals(node.getType());
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
        Map<String, Object> config = node.getConfig();

        String url = (String) config.get("url");
        String selector = (String) config.get("selector");

        try {
            // 创建WebDriver实例
            ChromeOptions options = null;
            options = new ChromeOptions();

            ChromeDriver driver = CrawlerWebDriverTool.Chrome.setUpDriver(
                (String) config.get("driverPath"),
                options
            );


            // 访问页面
            driver.get(url);

            // 等待页面加载
            Thread.sleep(2000);

            // 执行选择器操作
            if (selector != null) {
                var element = driver.findElement(org.openqa.selenium.By.cssSelector(selector));
                String result = element.getText();

                // 存储结果到执行上下文
                context.setVariable(node.getId() + "_result", result);

                return NodeExecutionResult.success(result);
            }

            // 如果没有指定选择器，返回整个页面源码
            String pageSource = driver.getPageSource();
            context.setVariable(node.getId() + "_result", pageSource);

            return NodeExecutionResult.success(pageSource);

        } catch (Exception e) {
            return NodeExecutionResult.failure(e);
        }
    }
}
