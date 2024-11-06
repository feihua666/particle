import cn.hutool.core.util.RuntimeUtil
import org.slf4j.LoggerFactory

def logger = LoggerFactory.getLogger("com.particle.navigation.deploy")
def commitMessage = "autoCommit"
def fileDir = new File("/home/app/particle/app_navs_deploy")
def commands = [
        "git add -A",
        "git commit -m ${commitMessage}",
        "git push origin main"
]
def process = null

for (final def command in commands) {
    try {
        process = RuntimeUtil.exec(
                null,
                fileDir,
                command.split(" ")
        )

        // 获取进程的退出状态
        int exitCode = process.waitFor()

        // 根据 exitCode 判断命令是否执行成功
        if (exitCode == 0) {
            // 正常执行，读取标准输出
            def result = RuntimeUtil.getResult(process)
            logger.info("command={}, result={}", command, result)
        } else {
            // 执行失败，读取错误输出
            def errorResult = RuntimeUtil.getErrorResult(process)
            logger.error("command={}, error={}", command, errorResult)
        }
    } catch (Exception e) {
        logger.error("Error executing command: ${command}", e)
    } finally {
        RuntimeUtil.destroy(process)
    }
}
