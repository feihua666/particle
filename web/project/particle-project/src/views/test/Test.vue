<template>
  <el-container class="pt-height-100-pc pt-width-100-pc">
    <el-aside width="200px">

      <div>这是一个测试页面首页</div>

      <!-- 循环 TestRoutes 方式添加按钮 -->
      <el-button type="primary" v-for="(item, index) in TestRoutes" :key="index" @click="goToTest(item.path)">{{ item.meta.name }}</el-button>

    </el-aside>
    <el-main>

      <div class="pt-height-100-pc pt-width-100-pc" style="border: red solid 1px;margin: -1px;">
        <!-- 子路由内容渲染区域 → 必须加 RouterView -->
        <RouterView />
      </div>
    </el-main>
  </el-container>

</template>
<script setup name="Test"  lang="ts">
import { useRouter } from 'vue-router'
import TestRoutes from "@/views/test/TestRoutes.ts";


const router = useRouter()

const goToTest = (path: string) => {
  router.push(path)
}
</script>

<style scoped>

</style>
