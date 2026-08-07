<script setup lang="ts">
import type {PropType} from 'vue'
import type {ConversationItem} from "./type";
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 已加载的
  list: {
    type: Array as PropType<ConversationItem[]>,
    default: () => []
  },
})

const selected = defineModel<string[]>('selected', { default: () => [] })
</script>

<template>
  <!-- 列表 -->
  <var-paper height="calc(100% - 44px)" style="overflow-y: auto;">
    <var-checkbox-group v-model="selected">
      <var-cell ripple v-for="(item, index)  in list" :key="item.id"
                :border="index !== list.length - 1"
                :border-offset="0"
                style="padding-top: 0;padding-bottom: 0;"
      >

        <var-checkbox :checked-value="item.id">
          {{1 + index}}. {{ item.title }}
        </var-checkbox>
        <template #extra v-if="item.isRemoved">
          <var-icon name="checkbox-marked-circle" color="var(--color-primary)" />
        </template>

      </var-cell>
    </var-checkbox-group>
  </var-paper>
</template>
