<template>
  <PtBackendManagementLayout>
    <div class="test-index-container">
      <h1>测试页面导航</h1>
      <div class="test-links-grid">
        <el-card class="test-link-card" v-for="route in testRoutes" :key="route.name">
          <template #header>
            <div class="card-header">
              <span>{{ route.meta.name }}</span>
            </div>
            <el-button 
              class="go-test-btn" 
              type="primary" 
              @click="goToTest(route.path)"
            >
              进入测试
            </el-button>
          </template>
          <div class="test-description">
            <p>路径: {{ route.path }}</p>
            <p>名称: {{ route.meta.name }}</p>
          </div>
        </el-card>
      </div>
    </div>
  </PtBackendManagementLayout>
</template>

<script setup name="TestIndex" lang="ts">
import { useRouter } from 'vue-router'

// 定义测试路由信息
const testRoutes = [
  {
    path: '/test',
    name: 'test',
    meta: {
      root: true,
      code: 'test',
      name: '测试页面',
      keepAlive: true
    }
  },
  {
    path: '/testMap',
    name: 'testMap',
    meta: {
      root: true,
      code: 'testMap',
      name: '测试地图',
      keepAlive: true
    }
  },
  {
    path: '/testDraggable',
    name: 'testDraggable',
    meta: {
      root: true,
      code: 'testDraggable',
      name: '测试拖拽',
      keepAlive: true
    }
  },
  {
    path: '/testTimePicker',
    name: 'testTimePicker',
    meta: {
      root: true,
      code: 'testTimePicker',
      name: '测试时间选择器',
      keepAlive: true
    }
  },
  {
    path: '/testAceEditor',
    name: 'testAceEditor',
    meta: {
      root: true,
      code: 'testAceEditor',
      name: '测试Ace编辑器',
      keepAlive: true
    }
  },
  {
    path: '/testCompAdapter',
    name: 'testCompAdapter',
    meta: {
      root: true,
      code: 'testCompAdapter',
      name: '测试组件适配器',
      keepAlive: true
    }
  },
  {
    path: '/testTinymceEditor',
    name: 'testTinymceEditor',
    meta: {
      root: true,
      code: 'testTinymceEditor',
      name: '测试TinyMCE编辑器',
      keepAlive: true
    }
  },
  {
    path: '/testRealtimeRenderMarkdown',
    name: 'testRealtimeRenderMarkdown',
    meta: {
      root: true,
      code: 'testRealtimeRenderMarkdown',
      name: '测试实时渲染Markdown',
      keepAlive: true
    }
  },
  {
    path: '/testG6Graph',
    name: 'testG6Graph',
    meta: {
      root: true,
      code: 'testG6Graph',
      name: '测试G6图',
      keepAlive: true
    }
  }
];

const router = useRouter()

const goToTest = (path: string) => {
  router.push(path)
}
</script>

<style scoped>
.test-index-container {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.test-links-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  margin-top: 20px;
}

.test-link-card {
  transition: box-shadow 0.3s;
}

.test-link-card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.go-test-btn {
  margin-left: 10px;
}

.test-description {
  padding: 10px 0;
}

.test-description p {
  margin: 5px 0;
  color: #666;
}
</style>