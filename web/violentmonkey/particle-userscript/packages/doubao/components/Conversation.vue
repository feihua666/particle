<script setup>
import { ref } from 'vue'
import { Snackbar } from '@varlet/ui'
import {reliableClick} from "../../../../../global/common/tools/InteractionTools.ts";
import {delayChain} from "../../../../../global/common/tools/DelayChain.ts";
const $alert = (message, type = 'info') => {
  Snackbar[type] ? Snackbar[type](message) : Snackbar(message)
}
const show = ref(true) // 直接显示也可以（调试方便）

const list = ref([])
const selected = ref([])

// 扫描豆包会话
async function scan() {
  const sideBar = document.querySelector('#flow_chat_sidebar')
  if (!sideBar) return
  const items = sideBar.querySelectorAll('[id^="conversation_"]')
  list.value = Array.from(items).map(el => ({
    id: el.id,
    title: el.innerText.trim(),
    el,
    isRemoved: false
  })).filter(i => i.title != '手机版对话')
  $alert(`已扫描 ${list.value.length} 个对话,注意 手机版对话 已排除，页面未显示的会话扫描不到`)
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
                .querySelectorAll('.items-center')[1]
                ?.querySelector('[class*="chat-item-menu-wrapper"]')
            if (!wrapper) throw new Error('wrapper not found')
            wrapper.style.setProperty("display", "flex", "important")
            wrapper.style.setProperty("visibility", "visible", "important")
            const triggerButton = wrapper.querySelector('button[data-slot="dropdown-menu-trigger"]')
            if (!triggerButton) throw new Error('triggerButton not found')
            reliableClick(triggerButton)
          })
          .step(() => {
            const items = document.querySelectorAll(
                'div[data-radix-popper-content-wrapper] div[data-slot="dropdown-menu-item"]'
            )
            const last = items[items.length - 1]
            if (!last) throw new Error('dropdown item not found')
            reliableClick(last)
          }, 300)
          .step(() => {
            const confirmButton = document.querySelectorAll('div[data-slot="dialog-content"] div[data-slot="dialog-footer"] button')[1]
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
  <var-cell>
  <var-space justify="space-around">
    <var-button type="primary" :auto-loading="true" @click="scan">扫描</var-button>
    <var-button type="warning" :auto-loading="true" @click="toggleAll">全选</var-button>
    <var-button type="danger" :auto-loading="true" @click="removeSelected">删除</var-button>
  </var-space>
  </var-cell>

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
