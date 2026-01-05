# 部署指南

Particle 项目支持多种部署方式，可根据实际需求选择最适合的部署方案。本文档将详细介绍各种部署方式及其操作步骤。

## 部署方式概述

Particle 项目支持以下几种部署方式：

1. **一体化部署**：前端资源打包到后端 Jar 包中，统一部署
2. **前后端分离部署**：前端和后端分别部署，通过 API 进行通信

## 一体化部署

一体化部署将前端资源打包到后端 Jar 包中，形成一个独立可运行的应用程序。这种方式适合小型项目或快速部署场景。

### 前端资源打包

在进行一体化部署前，需要先将前端资源打包到后端项目中：

```bash
# 进入 tools 目录
cd tools

# 执行前端构建脚本
./frontendBuild-project-particle-project.sh
```

该脚本会自动执行以下操作：
1. 清理之前的构建产物
2. 构建前端项目
3. 将构建产物复制到后端静态资源目录
4. 准备好用于打包的完整项目

### 后端项目打包

前端资源打包完成后，需要构建后端 Jar 包：

```bash
# 进入项目目录
cd project/particle-project/particle-project-start

# 执行 Maven 打包命令
mvn clean package -DskipTests
```

打包完成后，生成的 Jar 包位于：
```
project/particle-project/particle-project-start/target/particle-project-start.jar
```

### 服务器部署

将打包好的 Jar 包部署到服务器：

```bash
# 复制 Jar 包到服务器（示例）
scp target/particle-project-start.jar user@server:/opt/particle/

# 登录服务器
ssh user@server
```

在服务器上创建自定义配置文件以覆盖 Jar 包中的默认配置：

```bash
# 进入部署目录
cd /opt/particle

# 创建自定义配置文件
vim application.yml
```

在 `application.yml` 中添加需要覆盖的配置项，例如：

```yaml
spring:
  datasource:
    dynamic:
      datasource:
        master:
          url: jdbc:mysql://your-production-db:3306/particle?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8
          username: your_db_username
          password: your_db_password

server:
  port: 8080
```

### 启动应用

使用 `apptool.sh` 脚本启动应用：

```bash
# 使用 apptool.sh 启动应用
./apptool.sh start
```

`apptool.sh` 脚本提供了丰富的应用管理功能：

```bash
# 启动应用
./apptool.sh start

# 停止应用
./apptool.sh stop

# 重启应用
./apptool.sh restart

# 查看应用状态
./apptool.sh status

# 查看应用信息
./apptool.sh info
```

## 前后端分离部署

前后端分离部署方式将前端和后端分别部署在不同的服务器上，通过 API 进行通信。这种方式适合大型项目或需要独立扩展的场景。

### 后端部署

后端部署与一体化部署类似，但不需要打包前端资源：

```bash
# 进入项目目录
cd project/particle-project/particle-project-start

# 执行 Maven 打包命令
mvn clean package -DskipTests
```

将生成的 Jar 包部署到服务器，并创建自定义配置文件：

```bash
# 复制 Jar 包到服务器
scp target/particle-project-start.jar user@backend-server:/opt/particle/

# 登录后端服务器
ssh user@backend-server

# 创建自定义配置文件
vim /opt/particle/application.yml
```

在配置文件中需要特别注意跨域配置：

```yaml
particle:
  web:
    cors:
      enabled: true
      allowed-origins: 
        - http://your-frontend-domain.com
        - https://your-frontend-domain.com
```

启动后端应用：

```bash
./apptool.sh start
```

### 前端部署

前端需要单独构建和部署：

```bash
# 进入前端项目目录
cd web/project/particle-project

# 安装依赖
npm install

# 构建生产版本
npm run build
```

构建完成后，将 `dist` 目录中的内容部署到 Web 服务器（如 Nginx、Apache）：

```bash
# 构建前端项目
npm run build

# 将构建产物复制到 Web 服务器目录
sudo cp -r dist/* /var/www/html/
```

配置 Nginx 反向代理：

```nginx
server {
    listen 80;
    server_name your-frontend-domain.com;

    location / {
        root /var/www/html;
        index index.html;
        try_files $uri $uri/ /index.html;
    }

    location /api {
        proxy_pass http://your-backend-server:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

## 部署最佳实践

### 配置管理

1. **敏感信息处理**
   - 不要在配置文件中硬编码密码等敏感信息
   - 使用环境变量或配置中心管理敏感配置
   - 生产环境配置应与代码库分离

2. **配置文件组织**
   ```
   /opt/particle/
   ├── particle-project-start.jar
   ├── application.yml              # 主配置文件
   ├── application-prod.yml         # 生产环境配置（可选）
   └── logs/                       # 日志目录
   ```

### 监控和日志

1. **日志管理**
   - 配置合理的日志级别
   - 定期清理历史日志
   - 使用 ELK 等工具进行日志分析

2. **健康检查**
   - 启用 Actuator 端点进行健康监控
   - 配置监控告警机制

### 安全考虑

1. **网络安全**
   - 配置防火墙规则
   - 使用 HTTPS 加密传输
   - 限制不必要的端口暴露

2. **应用安全**
   - 定期更新依赖库版本
   - 配置合理的权限控制
   - 启用安全相关配置项

### 性能优化

1. **JVM 调优**
   ```bash
   # 在 apptool.sh 中配置 JVM 参数
   JAVA_OPTS="-Xms512m -Xmx2g -XX:+UseG1GC"
   ```

2. **数据库优化**
   - 配置合适的连接池大小
   - 优化慢查询 SQL
   - 定期维护数据库索引

## 故障排查

### 常见问题

1. **应用无法启动**
   - 检查日志文件：`logs/particle-project-start.log`
   - 确认端口未被占用
   - 验证数据库连接配置

2. **前端资源无法访问**
   - 确认前端资源已正确打包
   - 检查静态资源配置
   - 验证路径映射是否正确

3. **数据库连接失败**
   - 验证数据库服务是否正常运行
   - 检查网络连通性
   - 确认用户名密码是否正确

### 日志查看

由于 `apptool.sh` 脚本本身不提供日志查看功能，您可以使用标准的 Linux 命令来查看日志：

```bash
# 实时查看日志
tail -f logs/particle-project-start.log

# 查看最近 100 行日志
tail -n 100 logs/particle-project-start.log

# 搜索特定关键字
grep "ERROR" logs/particle-project-start.log
```

::: tip 提示
部署前请确保服务器环境满足项目要求：
- JDK 17 或更高版本
- MySQL 5.7 或更高版本
- 网络连通性正常
- 磁盘空间充足
:::