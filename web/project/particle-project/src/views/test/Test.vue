<template>
  <div>这是一个测试页面首页</div>

<!-- 循环 TestRoutes 方式添加按钮 -->
  <el-button type="primary" v-for="(item, index) in TestRoutes" :key="index" @click="goToTest(item.path)">{{ item.meta.name }}</el-button>

  <div style="min-height: 500px;margin-top: 20px;padding: 20px;">
    <!-- 子路由内容渲染区域 → 必须加 RouterView -->
    <RouterView />
  </div>
</template>
<script setup name="Test"  lang="ts">
import { useRouter } from 'vue-router'
import TestRoutes from "@/views/test/TestRoutes.ts";

TestRoutes
const router = useRouter()

const goToTest = (path: string) => {
  router.push(path)
}
</script>

<style scoped>

</style>
