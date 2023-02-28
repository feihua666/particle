<script setup name="BackendManagementLayout" lang="ts">
// 声明属性
defineProps({
  header: {
    type: Object,
    // 对象或数组的默认值
    // 必须从一个工厂函数返回。
    // 该函数接收组件所接收到的原始 prop 作为参数。
    default(rawProps) {
      return { show: true, attr: {} }
    }
  },
  headerAside: {
    type: Object,
    // 对象或数组的默认值
    // 必须从一个工厂函数返回。
    // 该函数接收组件所接收到的原始 prop 作为参数。
    default(rawProps) {
      // 减 20 ,为 el-header padding值
      return { show: true, attr: {style: {width: 300-20 + 'px'}} }
    }
  },
  headerMain: {
    type: Object,
    // 对象或数组的默认值
    // 必须从一个工厂函数返回。
    // 该函数接收组件所接收到的原始 prop 作为参数。
    default(rawProps) {
      return { show: true, attr: {} }
    }
  },
  aside: {
    type: Object,
    // 对象或数组的默认值
    // 必须从一个工厂函数返回。
    // 该函数接收组件所接收到的原始 prop 作为参数。
    default(rawProps) {
      return { show: true, attr: {style: {width: 300 + 'px'}} }
    }
  },
  main: {
    type: Object,
    // 对象或数组的默认值
    // 必须从一个工厂函数返回。
    // 该函数接收组件所接收到的原始 prop 作为参数。
    default(rawProps) {
      return { show: true, attr: {} }
    }
  },
  footer: {
    type: Object,
    // 对象或数组的默认值
    // 必须从一个工厂函数返回。
    // 该函数接收组件所接收到的原始 prop 作为参数。
    default(rawProps) {
      return { show: true, attr: {} }
    }
  },
  work: {
    type: Object,
    // 对象或数组的默认值
    // 必须从一个工厂函数返回。
    // 该函数接收组件所接收到的原始 prop 作为参数。
    default(rawProps) {
      return { show: true, attr: {} }
    }
  },
})


</script>
<template>
  <el-container class="pt-height-100-pc pt-backend-management-layout">
    <!--  头  -->
    <el-header v-if="header.show" v-bind="header.attr">
      <slot name="header">
        <!-- 头默认布局 -->
        <el-container class="pt-height-100-pc">
          <!-- 头左侧-->
          <el-aside v-if="headerAside.show" v-bind="headerAside.attr" class="pt-flex-align-cross-center">
            <slot name="headerAside"></slot>
          </el-aside>
          <!-- 头工作区-->
          <el-main v-if="headerMain.show" v-bind="headerMain.attr">
            <!-- 默认main 插槽-->
            <slot name="headerMain"></slot>
          </el-main>
        </el-container>
      </slot>
    </el-header>
    <!--  工作区  -->
    <el-container class="pt-height-100-pc" v-if="work.show" v-bind="work.attr">
      <!--  左侧    -->
      <el-aside v-if="aside.show" v-bind="aside.attr">
        <slot name="aside"></slot>
      </el-aside>
      <!--   主工作区   -->
      <el-container v-if="main.show || footer.show">
        <el-main v-if="main.show" v-bind="main.attr">
          <!-- 默认main 插槽-->
          <slot></slot>
        </el-main>
        <!--  工作区底部  -->
        <el-footer v-if="footer.show" v-bind="footer.attr">
          <slot name="footer"></slot>
        </el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>
<style scoped>
.pt-backend-management-layout{
  overflow: hidden;
}
</style>