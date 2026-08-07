<script setup lang="ts">
import { ref, computed } from 'vue'
import Conversation from '../../components/Conversation.vue'
import type {ConversationItem} from "../../components/type.ts";
import {Snackbar} from "@varlet/ui";
import {delayChain} from "../../../../../global/common/tools/DelayChain";
import {reliableClick} from "../../../../../global/common/tools/InteractionTools";
// 👇 接收父组件 v-model
const props = defineProps({
  show: Boolean
})

const $alert = (message, type = 'info') => {
  Snackbar[type] ? Snackbar[type](message) : Snackbar(message)
}
const emit = defineEmits(['update:show'])

// 👇 双向绑定
const computedShow = computed({
  get: () => props.show,
  set: val => emit('update:show', val)
})

const list = ref<ConversationItem[]>([])
const selected = ref<ConversationItem[]>([])

// 扫描会话
async function scan() {
  const sideBar = document.querySelector('.ds-scroll-area.ds-scroll-area--show-on-focus-within')
  if (!sideBar) return
  const items = sideBar.querySelectorAll('a[href^="/a/chat/"]')
  list.value = Array.from(items).map(el => ({
    id: el.href,
    title: el.innerText.trim(),
    el,
    isRemoved: false
  }))
  $alert(`已扫描 ${list.value.length} 个对话,页面未显示的会话扫描不到`)
}

// 全选 / 反选
async function toggleAll() {
  if (!list.value.length) {
    $alert('请先扫描会话')
    return
  }
  if (selected.value.length === list.value.length) {
    selected.value = []
  } else {
    selected.value = list.value.map(i => i.id)
  }
}

// 删除
async function removeSelected() {
  const targets = list.value.filter(i =>
      selected.value.includes(i.id)
  )

  if (!targets.length) {
    $alert('没有选中的会话')
    return
  }
  for (const item of targets) {
    try {
      await delayChain(item)
          .step((target) => {
            const wrapper = target.el
                .querySelectorAll(':scope > div')[2]
            if (!wrapper) throw new Error('wrapper not found')
            wrapper.style.setProperty("display", "flex", "important")
            const triggerButton = wrapper.querySelector(':scope > div.ds-icon-button')
            if (!triggerButton) throw new Error('triggerButton not found')
            reliableClick(triggerButton)
          })
          .step(() => {
            const items = document.querySelectorAll(
                'div.ds-floating-container div.ds-floating-position-wrapper div.ds-dropdown-menu div.ds-dropdown-menu-option'
            )
            const last = items[items.length - 1]
            if (!last) throw new Error('dropdown item not found')
            reliableClick(last)
          }, 300)
          .step(() => {
            const confirmButton = document.querySelectorAll(
                'div.ds-modal div.ds-modal-content div.ds-modal-content__footer button'
            )[1]
            if (!confirmButton) throw new Error('confirmButton not found')
            reliableClick(confirmButton)
          }, 200)
          .run()
      item.isRemoved = true
    } catch (e) {
      $alert(`删除 ${item.id} 失败`, 'error')
      console.error(`删除 ${item.id} 失败`, e)
    }
  }

  list.value = list.value.filter(x => !selected.value.includes(x.id))
  selected.value = []
}
</script>
<template>
  <var-popup
      v-model:show="computedShow"
      position="right"
      :lock-scroll="false"
      style="display: flex; flex-direction: column;"
  >
    <var-app-bar title="会话批量删除" />
    <var-paper style="
      width: 320px;
      flex: 1;
    ">
      <var-cell>
        <var-space justify="space-around">
          <var-button type="primary" :auto-loading="true" @click="scan">扫描</var-button>
          <var-button type="warning" :auto-loading="true" @click="toggleAll">全选</var-button>
          <var-button type="danger" :auto-loading="true" @click="removeSelected">删除</var-button>
        </var-space>
      </var-cell>
      <Conversation :list="list" v-model:selected="selected" />
    </var-paper>
  </var-popup>
</template>
<style scoped>
</style>
