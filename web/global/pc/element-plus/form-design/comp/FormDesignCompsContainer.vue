<script setup name="FormDesignComps" lang="ts">
/**
 * 组件和模板
 */
import {ref, watch} from 'vue'
import PtFormDesignCompsDragWrapper from './FormDesignCompsDragWrapper.vue'
import type { TabsPaneContext } from 'element-plus'
import {particleBuiltInComps} from "./formDesignParticleBuiltInComponents";
import {elementPlusBuiltInComps} from "./formDesignElementPlusBuiltInComponents";
const tabsActiveName = ref('component')
const compsActiveName = ref(['particle','elementPlus'])

</script>
<template>
  <el-tabs v-model="tabsActiveName" class="pt-form-design-comps-tabs">
    <el-tab-pane label="component" name="component">
      <template #label>
        <span class="pt-form-design-comps-tabs-label">
          <el-icon><Operation /></el-icon>
          <span>组件库</span>
        </span>
      </template>
      <slot name="component">
        <el-collapse v-model="compsActiveName">
          <el-collapse-item title="particle内置组件" name="particle">
            <template #title>
              particle内置组件
              <el-tooltip content="建议优先使用 particle内置组件 <br/>满足不了需求时再使用 elementPlus内置组件 或其它组件" raw-content placement="top" effect="light">
                <el-icon style="margin-left: 0.3rem"><InfoFilled /></el-icon>
              </el-tooltip>
            </template>

            <PtFormDesignCompsDragWrapper :options="particleBuiltInComps"></PtFormDesignCompsDragWrapper>
          </el-collapse-item>
          <el-collapse-item title="elementPlus内置组件" name="elementPlus">
            <PtFormDesignCompsDragWrapper :options="elementPlusBuiltInComps"></PtFormDesignCompsDragWrapper>
          </el-collapse-item>
        </el-collapse>
      </slot>
    </el-tab-pane>
    <el-tab-pane name="template">
      <template #label>
        <span class="pt-form-design-comps-tabs-label">
          <el-icon><Grid /></el-icon>
          <span>模板库</span>
        </span>
      </template>
      <slot name="template">ss</slot>
    </el-tab-pane>
  </el-tabs>
</template>

<style scoped>
.pt-form-design-comps-tabs-label .el-icon {
  vertical-align: middle;
}
.pt-form-design-comps-tabs-label span {
  vertical-align: middle;
  margin-left: 4px;
}

</style>
<style>
.pt-form-design-comps-tabs .el-tabs__header {
  margin: 0;
}
</style>