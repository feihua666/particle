<script setup name="LoginPageTemplate">
import loginCodingImg from './login-coding.png'
/**
 * 页面级的登录模板 方便使用
 * 设置了默认的背景图片和垂直水平居中布局
 */

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({

})

</script>
<template>
    <el-row class="pt-height-100-pc pt-width-100-pc">
      <el-col :span="8" class="pt-flex-center-all pt-flex-direction-c pt-height-100-pc" style="background: var(--el-bg-color-page)">
        <PtLogo></PtLogo>
        <br/>
        <h1>
          <el-text class="mx-1" size="large">开箱即用，中后台前端/设计解决方案</el-text>
        </h1>
        <h3>
          <el-text class="mx-1" size="default">多生态支持、功能丰富、高颜值模板</el-text>
        </h3>
        <img :src="loginCodingImg" style="width: 80%">
      </el-col>
      <el-col :span="16" class="pt-flex-center-all pt-height-100-pc">
        <!--    login form element  -->
        <slot></slot>
      </el-col>

    </el-row>
</template>

<style scoped>

</style>
