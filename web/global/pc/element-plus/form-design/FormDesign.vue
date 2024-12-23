<script setup name="FormDesign" lang="ts">
/**
 * 表单设置器
 */

import {provide} from "vue"
import PtFormDesignCompsContainer from './comp/FormDesignCompsContainer.vue'
import PtFormDesignAttrsContainer from './attr/FormDesignAttrsContainer.vue'
import PtFormDesignToolBar from './toolbar/FormDesignToolBar.vue'
import PtFormDesignMain from './main/FormDesignMain.vue'


import {useFormDesign} from "./formDesign"

const {
  formDesignData,
  formDesignDataControl,
  currentFormDesignItemData,
  formDesignHistoryData
} = useFormDesign()

provide('formDesignData',formDesignData)

// 常用方法
provide('formDesignDataControl', formDesignDataControl)

provide('currentFormDesignItemData',currentFormDesignItemData)
provide('formDesignHistoryData',formDesignHistoryData)
provide('permissions',[])


/* 阻止火狐浏览器在 vue-draggable组件时拖动 打开新窗口 */
document.body.ondrop = function(event) {
  event.preventDefault();
  event.stopPropagation();
};


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({

})

// 方法
</script>

<template>
  <el-container class="pt-form-design">
    <!--  左侧  -->
    <el-aside class="pt-form-design-left" width="250px">
      <!--   组件及模板库面板   -->
      <PtFormDesignCompsContainer>
      </PtFormDesignCompsContainer>
    </el-aside>
    <!--  工作区  -->
    <el-container>
      <el-header class="pt-form-design-toolbar-header">
        <PtFormDesignToolBar></PtFormDesignToolBar>
      </el-header>
      <el-main>
        <PtFormDesignMain :options="formDesignData.formDesignItems" class="pt-height-100-pc" :formProps="formDesignData.formDesignFormSettingFormProps"></PtFormDesignMain>
      </el-main>
    </el-container>
    <!--  右侧  -->
    <el-aside class="pt-form-design-right" width="300px">
      <!--   属性面板   -->
      <PtFormDesignAttrsContainer></PtFormDesignAttrsContainer>
    </el-aside>
  </el-container>
</template>
<style scoped>
.pt-form-design-toolbar-header{
  /* 与两边tap调度保持一致 */
  height: 40px;
}
.el-main{
  background: #f1f2f3;
  padding: 20px .6rem;
  overflow-x: hidden;
  overflow-y: auto;
}
.pt-form-design-left,.pt-form-design-right{
  padding: 0 5px;
}
</style>
