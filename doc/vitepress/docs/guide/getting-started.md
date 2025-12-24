# 快速开始

## 在线体验

可以直接在 [演示地址](https://demo.particle.fit) 上进行在线体验。

## 本地运行

### 前置准备

- [Jdk](https://www.java.com/) 17 及以上版本。
- [Mysql](https://www.mysql.com/) 5.7 及以上版本。
- [Node.js](https://nodejs.org/) 18 及以上版本。
- [Maven](https://maven.apache.org/) 3.9 及以上版本。

### 下载源码

``` bash
git clone https://github.com/feihua666/particle.git
```
### 源码导入到 Eclipse

Particle 项目是一个基于 Maven 的多模块项目，可以通过以下步骤将其导入到 Eclipse 中：

1. 打开 Eclipse IDE

2. 选择 **File** -> **Import**

3. 在导入向导中选择 **Maven** -> **Existing Maven Projects**，然后点击 **Next**

4. 在 **Root Directory** 字段中，浏览并选择 Particle 项目的根目录

5. 系统会自动扫描并列出所有可导入的 Maven 项目，您可以选择要导入的模块

6. 点击 **Finish** 完成导入

::: tip 提示
- 如果遇到依赖问题，请确保已正确配置 Maven 的 settings.xml 文件
- 首次导入可能需要较长时间下载依赖包，请耐心等待
:::

### 源码导入到 IntelliJ IDEA

Particle 项目在 IntelliJ IDEA 中的导入方式与 Eclipse 类似，可以通过以下步骤完成：

1. 打开 IntelliJ IDEA

2. 选择 **File** -> **Open**

3. 浏览并选择 Particle 项目的根目录（包含 pom.xml 文件的目录）

4. 在弹出的对话框中选择 **Open as Project**

5. IDEA 会自动识别这是一个 Maven 项目并开始导入

::: tip 提示
- 如果项目导入后出现依赖问题，可以右键点击项目根目录的 pom.xml 文件，选择 **Maven** -> **Reload project**
- 为了获得更好的开发体验，建议在 IDEA 设置中启用 **Always select opened element** 选项
- 可以通过 **File** -> **Project Structure** -> **Modules** 来管理项目的模块
:::

### 新建数据库

Particle 项目使用 MySQL 作为主要数据库，推荐使用 Docker 方式快速启动数据库服务：

1. 进入项目中的 `devops/local/mysql` 目录

2. 执行启动脚本：

```bash
./start-mysql.sh
```

3. 数据库将通过 Docker 启动，默认配置如下：
   - 端口：3306
   - 用户名：root
   - 密码：123456

4. 使用任意 MySQL 客户端连接数据库，创建所需的数据库：

```sql
CREATE DATABASE particle CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

::: tip 提示
- 如果不想使用 Docker，也可以直接安装 MySQL 8.0+ 到本地环境
- 确保数据库字符集设置为 utf8mb4 以支持完整的 Unicode 字符
- 生产环境请务必修改默认密码
:::

### 修改配置文件

Particle 项目的主配置文件位于 `project/particle-project/particle-project-start/src/main/resources/application.yml`，主要配置项包括：

#### 应用基本配置
- `spring.application.name`: 应用名称，设置为 particle-project-start
- `spring.profiles.include`: 包含多个功能配置，如 knife4j、swagger、datasource、mybatis-plus 等

#### 数据源配置
- 使用 dynamic-datasource-spring-boot-starter 实现多数据源支持
- 默认主数据源为 master，使用 MySQL 数据库
- 支持 PostgreSQL 作为备选数据源（主要用于 AI 向量存储）

#### 数据库初始化
- `spring.sql.init.schema-locations`: 定义数据库表结构初始化脚本位置
- `spring.sql.init.data-locations`: 定义初始数据脚本位置
- `spring.sql.init.mode`: 设置为 NEVER，避免意外清除数据

#### 文件上传配置
- `spring.servlet.multipart.max-request-size`: 最大请求大小为 500MB
- `spring.servlet.multipart.max-file-size`: 单个文件最大为 50MB

#### Particle 特有配置
- `particle.session.store-type`: Session 存储方式，使用 JDBC 存储
- `particle.project-info.component.enable.all`: 启用所有后端组件
- `particle.captcha.filter.enabled`: 启用验证码过滤器
- `particle.oss`: 对象存储配置，支持本地存储
- `particle.notification`: 通知配置，支持邮件通知
- `particle.dataquery.openapi.config`: 数据查询开放接口配置

::: tip 提示
- 生产环境请务必修改默认的数据库密码和其他敏感配置
- 根据实际需要启用或禁用相应的功能模块
- 部分配置项使用占位符形式，可通过环境变量或额外的配置文件进行覆盖
:::

#### 完整配置文件

::: details 查看完整配置文件内容
<<< ../../../../project/particle-project/particle-project-start/src/main/resources/application.yml
:::

#### 必要配置修改

- 初始运行，请设置初始化数据库配置为 ALWAYS，系统启动后会自动初始化数据库表。待系统启动完成后可以改回 NEVER，否则重启系统会重新初始化数据库表

```yaml
spring:
  sql:
      # 默认 NEVER，太危险了，因为本机开发有部分是脚本配置依赖数据，比如数据查询和报告模板等，换了一个环境，全部数据没有了，还好通过binlog恢复了
      mode: NEVER # ALWAYS NEVER
```
- 修改数据库连接信息

```yaml
spring:
  datasource:
    dynamic:
      primary: master #设置默认的数据源或者数据源组,默认值即为 master
      strict: false #严格匹配数据源,默认false. true未匹配到指定数据源时抛异常,false使用默认数据源
      datasource:
        master:
          # 数据库连接地址如：jdbc:mysql://localhost/particle_test?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8
          url: ${particle.datasource.master.url}?${particle.datasource.mysql.url-query}
          # 数据库连接用户名
          username: root
          # 数据库连接密码
          password: rootroot
          driver-class-name: ${particle.datasource.mysql.driver} # 3.2.0开始支持SPI可省略此配置
```

#### 项目启动
找到启动文件 project/particle-project/particle-project-start/src/main/java/com/particle/particleproject/ParticleProjectApplication.java 启动项目，出现如下图表示启动成功：

- 项目标识
```text
                      _    _        _       
                     | |  (_)      | |      
  _ __    __ _  _ __ | |_  _   ___ | |  ___ 
 | '_ \  / _` || '__|| __|| | / __|| | / _ \
 | |_) || (_| || |   | |_ | || (__ | ||  __/
 | .__/  \__,_||_|    \__||_| \___||_| \___|
 | |                                        
 |_|                                        
```

- 项目信息
```text
=============================================================
	Application  is running! Project Info:
	lastcommintmessage:	xxxxxxxxx
	author            :	xxxxxxxxx
	name              :	particle-project-start
	builddate         :	xxxxxxxxx
	componentEnable   :	{"all":true}
	version           :	xxxxxxxxx
	branch            :	master
=============================================================
```

- 项目地址
```text
=============================================================
	Application  is running! Access URLs:
	Local Swagger knife4j   :	http://localhost:8080/doc.html
	External Swagger        :	http://x.x.x.x:8080/swagger-ui/index.html
	Local Swagger           :	http://localhost:8080/swagger-ui/index.html
	External                :	http://x.x.x.x:8080
	Local                   :	http://localhost:8080
	External Swagger knife4j:	http://x.x.x.x:8080/doc.html
=============================================================
```
- 打开浏览器，输入：[http://localhost:8080](http://localhost:8080) （默认账户/密码 superadmin/123456） 若能正确展示登录页面，并能成功登录，菜单及页面展示正常，则表明本地运行成功

::: tip 提示
- [particle-project-start](../../../../project/particle-project/particle-project-start) 内已经包含了打包好的前端页面，如想独立运行前端请继续阅读 [前端独立运行](../frontend/standalone.md)
  :::

## 前端运行

Particle 项目前端基于 Vue 3、Vite 和 Element Plus 构建。如果您希望独立运行前端项目而不是使用已打包的前端资源，可以按照以下步骤操作：

### 环境准备

确保您的开发环境中已安装以下软件：

- [Node.js](https://nodejs.org/) 18 及以上版本
- npm 或 yarn 包管理器

### 安装依赖

进入前端项目目录并安装依赖：

```bash
cd web/project/particle-project
npm install
```

### 开发环境运行

使用以下命令启动开发服务器：

```bash
npm run dev
```

默认情况下，开发服务器会在 `http://localhost:5173` 启动。您可以通过修改 `vite.config.ts` 文件中的 server 配置来更改端口和其他设置。

### 构建生产版本

要构建生产版本，运行以下命令：

```bash
npm run build
```

构建产物默认会输出到后端项目的静态资源目录：
`project/particle-project/particle-project-start/src/main/resources/static`

您也可以通过设置环境变量来更改输出目录：

```bash
VITE_BUILD_OUTDIR=./dist npm run build
```

### 其他命令

```bash
# 运行单元测试
npm run test:unit

# 类型检查
npm run type-check

# 代码检查和修复
npm run lint

# 代码格式化
npm run format
```

::: tip 提示
- 前端项目默认配置为访问本地后端服务 `http://localhost:8080`
- 如果后端服务运行在其他地址，请相应修改前端配置
- 开发环境下，前端和后端需要同时运行才能正常使用系统
:::
