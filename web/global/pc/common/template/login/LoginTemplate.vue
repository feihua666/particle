<script setup name="LoginTemplate">
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
    <div class="pt-flex-center-all pt-height-100-pc pt-width-100-pc login-bg">
      <!--    login form element  -->
      <slot></slot>
    </div>
</template>

<style scoped>
    .login-bg {
        position:fixed;
        background: url("login-bg.jpg") no-repeat;
        background-size: cover;
    }
</style>
