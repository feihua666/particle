# 示例应用：博客管理系统

为了让您更快地上手 Particle，我们准备了一个简单的博客管理系统示例。这个示例将带您一步步创建一个完整的博客应用，包括后端接口和前端页面，并演示如何在系统中添加菜单和权限。

## 应用功能

我们要创建的博客管理系统包含以下基本功能：

1. 创建博客文章
2. 查看博客文章列表
3. 查看博客文章详情
4. 编辑博客文章
5. 删除博客文章

## 后端开发步骤

### 1. 创建博客模块

首先，我们需要创建一个博客模块。在 Particle 项目中，每个业务功能都是一个独立的模块。

```bash
# 在 component 目录下创建博客模块
mkdir -p component/blog
```

在博客模块中，我们需要创建以下子模块，遵循 Particle 的四层架构：

```
blog/
├── blog-adapter/              # 适配层（控制器）
├── blog-app/                  # 应用层（业务逻辑）
├── blog-client/               # 客户端接口层（DTO定义）
├── blog-domain/               # 领域层（实体和仓储）
└── blog-infrastructure/       # 基础设施层（数据库操作）
```

### 2. 定义数据实体

在 `blog-domain` 模块中，我们定义博客文章实体，继承 Particle 提供的基础实体类：

```java
// BlogArticle.java
public class BlogArticle extends Entity {
    private BlogArticleId id;     // 实体ID
    private String title;         // 文章标题
    private String content;       // 文章内容
    private String author;        // 作者
    
    // getter 和 setter 方法
}

// BlogArticleId.java
public class BlogArticleId extends Id {
    private Long id;
    
    public static BlogArticleId of(Long id) {
        BlogArticleId blogArticleId = new BlogArticleId();
        blogArticleId.setId(id);
        return blogArticleId;
    }
    
    // getter 和 setter 方法
}
```

### 3. 创建数据库表

在 `blog-infrastructure` 模块中，创建数据库表结构文件：

```sql
-- blog-infrastructure/src/main/resources/db/schema.blog.sql
CREATE TABLE blog_article (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '文章ID',
    title VARCHAR(200) NOT NULL COMMENT '文章标题',
    content TEXT COMMENT '文章内容',
    author VARCHAR(100) COMMENT '作者',
    create_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    create_dept_id BIGINT COMMENT '创建部门ID',
    create_user_id BIGINT COMMENT '创建用户ID',
    update_dept_id BIGINT COMMENT '更新部门ID',
    update_user_id BIGINT COMMENT '更新用户ID'
) COMMENT '博客文章表';
```

### 4. 创建数据对象(DO)和Mapper

在 `blog-infrastructure` 模块中，创建数据对象和 Mapper：

```java
// BlogArticleDO.java
@TableName("blog_article")
public class BlogArticleDO extends BaseDO {
    private String title;
    private String content;
    private String author;
    
    // getter 和 setter 方法
}

// BlogArticleMapper.java
@Mapper
public interface BlogArticleMapper extends BaseMapper<BlogArticleDO> {
}
```

### 5. 创建服务层

在 `blog-infrastructure` 模块中，创建服务层接口和实现：

```java
// IBlogArticleService.java
public interface IBlogArticleService extends IService<BlogArticleDO> {
}

// BlogArticleServiceImpl.java
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticleDO> 
    implements IBlogArticleService {
}
```

### 6. 创建仓储接口和实现

在 `blog-domain` 模块中，创建仓储接口：

```java
// BlogArticleGateway.java
public interface BlogArticleGateway extends Gateway {
    BlogArticle getById(BlogArticleId blogArticleId);
    boolean save(BlogArticle blogArticle);
    boolean update(BlogArticle blogArticle);
    boolean delete(BlogArticleId blogArticleId);
}
```

在 `blog-infrastructure` 模块中，创建仓储实现：

```java
// BlogArticleGatewayImpl.java
@Component
public class BlogArticleGatewayImpl extends AbstractGatewayImpl implements BlogArticleGateway {
    
    @Autowired
    private IBlogArticleService blogArticleService;
    
    @Autowired
    private BlogArticleAppStructMapping blogArticleAppStructMapping;
    
    @Override
    public BlogArticle getById(BlogArticleId blogArticleId) {
        BlogArticleDO blogArticleDO = blogArticleService.getById(blogArticleId.getId());
        return blogArticleAppStructMapping.toBlogArticle(blogArticleDO);
    }
    
    @Override
    public boolean save(BlogArticle blogArticle) {
        BlogArticleDO blogArticleDO = blogArticleAppStructMapping.toBlogArticleDO(blogArticle);
        return blogArticleService.save(blogArticleDO);
    }
    
    @Override
    public boolean update(BlogArticle blogArticle) {
        BlogArticleDO blogArticleDO = blogArticleAppStructMapping.toBlogArticleDO(blogArticle);
        return blogArticleService.updateById(blogArticleDO);
    }
    
    @Override
    public boolean delete(BlogArticleId blogArticleId) {
        return blogArticleService.removeById(blogArticleId.getId());
    }
}
```

### 7. 创建应用层服务

在 `blog-app` 模块中，创建应用服务：

```java
// BlogArticleApplicationServiceImpl.java
@Service
@Transactional
@CatchAndLog
public class BlogArticleApplicationServiceImpl 
    extends AbstractBaseApplicationServiceImpl 
    implements IBlogArticleApplicationService {
    
    @Autowired
    private BlogArticleCreateCommandExecutor blogArticleCreateCommandExecutor;
    
    @Autowired
    private BlogArticleQueryApplicationServiceImpl blogArticleQueryApplicationServiceImpl;
    
    @Override
    public SingleResponse<BlogArticleVO> create(BlogArticleCreateCommand blogArticleCreateCommand) {
        return blogArticleCreateCommandExecutor.execute(blogArticleCreateCommand);
    }
    
    @Override
    public PageResponse<BlogArticleVO> pageQuery(BlogArticlePageQueryCommand blogArticlePageQueryCommand) {
        return blogArticleQueryApplicationServiceImpl.pageQuery(blogArticlePageQueryCommand);
    }
}
```

### 8. 创建命令执行器

在 `blog-app` 模块中，创建命令执行器：

```java
// BlogArticleCreateCommandExecutor.java
@Component
@Validated
public class BlogArticleCreateCommandExecutor extends AbstractBaseExecutor {
    
    @Autowired
    private BlogArticleGateway blogArticleGateway;
    
    @Autowired
    private BlogArticleAppStructMapping blogArticleAppStructMapping;
    
    public SingleResponse<BlogArticleVO> execute(@Valid BlogArticleCreateCommand command) {
        BlogArticle blogArticle = blogArticleAppStructMapping.toBlogArticle(command);
        // 设置创建人等信息
        fillCommonData(blogArticle);
        boolean save = blogArticleGateway.save(blogArticle);
        if (save) {
            return SingleResponse.of(blogArticleAppStructMapping.toBlogArticleVO(blogArticle));
        }
        return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
    }
}
```

### 9. 创建数据映射

在 `blog-app` 模块中，创建数据映射类：

```java
// BlogArticleAppStructMapping.java
@Mapper
public abstract class BlogArticleAppStructMapping {
    public static BlogArticleAppStructMapping instance = Mappers.getMapper(BlogArticleAppStructMapping.class);
    
    @Mappings({})
    public abstract BlogArticleDO toBlogArticleDO(BlogArticle blogArticle);
    
    @Mappings({})
    public abstract BlogArticle toBlogArticle(BlogArticleDO blogArticleDO);
    
    @Mappings({})
    public abstract BlogArticle toBlogArticle(BlogArticleCreateCommand blogArticleCreateCommand);
    
    @Mappings({})
    public abstract BlogArticleVO toBlogArticleVO(BlogArticle blogArticle);
}
```

### 10. 创建控制器

在 `blog-adapter` 模块中，创建 REST 控制器：

```java
// BlogArticleAdminController.java
@RestController
@RequestMapping("/admin/web/blog/article")
@Tag(name = "博客文章管理")
public class BlogArticleAdminController extends AbstractBaseWebAdapter {
    
    @Autowired
    private IBlogArticleApplicationService blogArticleApplicationService;
    
    @PostMapping("/create")
    @Operation(summary = "创建博客文章")
    @PreAuthorize("hasAuthority('admin:web:blog:article:create')")
    @OpLog(name = "创建博客文章", module = OpLogConstants.Module.blog, type = OpLogConstants.Type.create)
    public SingleResponse<BlogArticleVO> create(@RequestBody @Valid BlogArticleCreateCommand blogArticleCreateCommand) {
        return blogArticleApplicationService.create(blogArticleCreateCommand);
    }
    
    @GetMapping("/page")
    @Operation(summary = "分页查询博客文章")
    @PreAuthorize("hasAuthority('admin:web:blog:article:query')")
    public PageResponse<BlogArticleVO> page(BlogArticlePageQueryCommand blogArticlePageQueryCommand) {
        return blogArticleApplicationService.pageQuery(blogArticlePageQueryCommand);
    }
}
```

### 11. 创建启动模块

创建 `blog-boot-starter` 模块来整合所有组件：

```xml
<!-- blog-boot-starter/pom.xml -->
<dependencies>
    <dependency>
        <groupId>com.particle</groupId>
        <artifactId>blog-adapter</artifactId>
        <version>${project.version}</version>
    </dependency>
    
    <dependency>
        <groupId>com.particle</groupId>
        <artifactId>blog-app</artifactId>
        <version>${project.version}</version>
    </dependency>
    
    <dependency>
        <groupId>com.particle</groupId>
        <artifactId>blog-domain</artifactId>
        <version>${project.version}</version>
    </dependency>
    
    <dependency>
        <groupId>com.particle</groupId>
        <artifactId>blog-infrastructure</artifactId>
        <version>${project.version}</version>
    </dependency>
</dependencies>
```

### 12. 启动模块

将博客模块添加到启动项目中：

```xml
<!-- 在 particle-project-start/pom.xml 中添加依赖 -->
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>blog-boot-starter</artifactId>
    <version>${project.version}</version>
</dependency>
```

## 系统配置

### 1. 添加数据库脚本

将博客模块的数据库脚本添加到主项目中：

```yaml
# application.yml
spring:
  sql:
    init:
      schema-locations:
        - classpath:db/schema.particle-project.sql
        - classpath:db/schema.blog.sql  # 添加博客模块的表结构脚本
```

### 2. 添加菜单和权限

系统启动后，需要登录系统添加菜单和权限：

1. 使用管理员账号登录系统
2. 进入"系统管理"->"功能菜单"页面
3. 添加博客管理相关菜单：
   - 父菜单：博客管理
   - 子菜单：文章管理
4. 进入"系统管理"->"角色管理"页面
5. 为相应角色分配博客管理权限

## 前端开发步骤

### 1. 创建页面组件

在前端项目中，创建博客管理页面：

```
web/project/particle-project/src/views/blog/
├── ArticleList.vue          # 文章列表页面
├── ArticleForm.vue          # 文章编辑表单
└── ArticleDetail.vue        # 文章详情页面
```

### 2. 实现文章列表页面

```vue
<!-- ArticleList.vue -->
<template>
  <div class="article-list">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>博客文章列表</span>
          <el-button type="primary" @click="handleCreate">
            创建文章
          </el-button>
        </div>
      </template>
      
      <!-- 搜索条件 -->
      <el-form :model="queryParams" inline>
        <el-form-item label="文章标题">
          <el-input v-model="queryParams.title" placeholder="请输入文章标题" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleQuery">搜索</el-button>
          <el-button @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
      
      <!-- 文章列表 -->
      <el-table :data="articleList" v-loading="loading">
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="author" label="作者" />
        <el-table-column prop="createAt" label="创建时间" width="180" />
        <el-table-column label="操作" width="200">
          <template #default="{row}">
            <el-button size="small" @click="handleView(row)">
              查看
            </el-button>
            <el-button size="small" @click="handleEdit(row)">
              编辑
            </el-button>
            <el-button size="small" type="danger" @click="handleDelete(row)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <!-- 分页 -->
      <pagination
        v-show="total>0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
      />
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessageBox, ElMessage } from 'element-plus'
import request from '@/utils/request'

const loading = ref(false)
const articleList = ref([])
const total = ref(0)

const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  title: undefined
})

// 获取文章列表
const getList = async () => {
  loading.value = true
  try {
    const response = await request({
      url: '/admin/web/blog/article/page',
      method: 'get',
      params: queryParams.value
    })
    articleList.value = response.data.records
    total.value = response.data.total
  } catch (error) {
    ElMessage.error('获取文章列表失败')
  } finally {
    loading.value = false
  }
}

// 搜索
const handleQuery = () => {
  queryParams.value.pageNum = 1
  getList()
}

// 重置搜索
const resetQuery = () => {
  queryParams.value = {
    pageNum: 1,
    pageSize: 10,
    title: undefined
  }
  getList()
}

// 创建文章
const handleCreate = () => {
  // 跳转到创建页面
}

// 编辑文章
const handleEdit = (row) => {
  // 跳转到编辑页面
}

// 查看文章
const handleView = (row) => {
  // 跳转到详情页面
}

// 删除文章
const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该文章吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      // 调用删除接口
      ElMessage.success('删除成功')
      getList()
    } catch (error) {
      ElMessage.error('删除失败')
    }
  })
}

onMounted(() => {
  getList()
})
</script>
```

### 3. 实现文章创建表单

```vue
<!-- ArticleForm.vue -->
<template>
  <div class="article-form">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>{{ isEdit ? '编辑文章' : '创建文章' }}</span>
        </div>
      </template>
      
      <el-form ref="articleFormRef" :model="formData" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="formData.title" placeholder="请输入文章标题" />
        </el-form-item>
        
        <el-form-item label="作者" prop="author">
          <el-input v-model="formData.author" placeholder="请输入作者" />
        </el-form-item>
        
        <el-form-item label="内容" prop="content">
          <el-input 
            v-model="formData.content" 
            type="textarea"
            :rows="15"
            placeholder="请输入文章内容"
          />
        </el-form-item>
        
        <el-form-item>
          <el-button type="primary" @click="submitForm" :loading="submitLoading">
            {{ isEdit ? '更新' : '创建' }}
          </el-button>
          <el-button @click="goBack">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import request from '@/utils/request'

const route = useRoute()
const router = useRouter()

const articleFormRef = ref()
const submitLoading = ref(false)

// 是否为编辑模式
const isEdit = ref(false)

// 表单数据
const formData = reactive({
  title: '',
  content: '',
  author: ''
})

// 表单验证规则
const rules = {
  title: [{ required: true, message: '请输入文章标题', trigger: 'blur' }],
  content: [{ required: true, message: '请输入文章内容', trigger: 'blur' }],
  author: [{ required: true, message: '请输入作者', trigger: 'blur' }]
}

// 提交表单
const submitForm = async () => {
  try {
    await articleFormRef.value.validate()
    submitLoading.value = true
    
    const url = isEdit.value 
      ? `/admin/web/blog/article/update` 
      : `/admin/web/blog/article/create`
      
    const method = isEdit.value ? 'put' : 'post'
    
    await request({
      url,
      method,
      data: formData
    })
    
    ElMessage.success(`${isEdit.value ? '更新' : '创建'}成功`)
    goBack()
  } catch (error) {
    ElMessage.error(`${isEdit.value ? '更新' : '创建'}失败`)
  } finally {
    submitLoading.value = false
  }
}

// 返回列表
const goBack = () => {
  router.push('/blog/article')
}
</script>
```

### 4. 配置路由

在前端路由配置中添加博客管理路由：

```javascript
// router/modules/blog.js
export default {
  path: '/blog',
  component: () => import('@/layout/index.vue'),
  redirect: '/blog/article',
  name: 'Blog',
  meta: { 
    title: '博客管理', 
    icon: 'blog'
  },
  children: [
    {
      path: 'article',
      component: () => import('@/views/blog/ArticleList.vue'),
      name: 'ArticleList',
      meta: { title: '文章管理' }
    },
    {
      path: 'article/create',
      component: () => import('@/views/blog/ArticleForm.vue'),
      name: 'ArticleCreate',
      meta: { title: '创建文章', activeMenu: '/blog/article' },
      hidden: true
    },
    {
      path: 'article/edit/:id',
      component: () => import('@/views/blog/ArticleForm.vue'),
      name: 'ArticleEdit',
      meta: { title: '编辑文章', activeMenu: '/blog/article' },
      hidden: true
    }
  ]
}
```

并将该路由模块添加到主路由配置中：

```javascript
// router/index.js
import blog from './modules/blog'

export const constantRoutes = [
  // ... 其他路由
]

export const asyncRoutes = [
  blog,
  // ... 其他异步路由
]
```

## 运行和测试

### 1. 启动后端服务

```bash
# 编译项目
mvn clean install

# 启动后端服务
cd project/particle-project/particle-project-start
mvn spring-boot:run
```

### 2. 启动前端服务

```bash
# 进入前端目录
cd web/project/particle-project

# 安装依赖
npm install

# 启动前端开发服务器
npm run dev
```

### 3. 配置菜单和权限

1. 启动完成后，使用管理员账号登录系统
2. 进入"系统管理"->"功能菜单"
3. 添加博客管理菜单：
   - 菜单名称：博客管理
   - 菜单路径：/blog
   - 组件路径：Layout
   
   子菜单：
   - 菜单名称：文章管理
   - 菜单路径：/blog/article
   - 组件路径：/blog/article

4. 进入"系统管理"->"角色管理"
5. 为相应角色分配博客管理权限

### 4. 访问应用

完成上述配置后，刷新页面，左侧菜单会出现"博客管理"菜单项，点击即可进入博客管理功能。

## 总结

通过这个博客管理系统示例，您学会了：

1. 如何在 Particle 中创建一个新的业务模块
2. 如何按照 DDD 四层架构组织代码
3. 如何实现基础的 CRUD 功能
4. 如何使用 Particle 提供的基础类简化开发
5. 如何开发前后端交互的页面
6. 如何在系统中添加菜单和权限
7. 如何运行和测试整个应用

这个示例虽然相对简单，但它涵盖了使用 Particle 开发应用的主要流程。您可以在此基础上扩展更多功能，比如文章分类、标签、评论等。