<script setup lang="ts">

import {ref} from "vue";
import {Snackbar} from "@varlet/ui";
import {findFirst, getStore, getTransaction, openDB} from "../../../../../global/common/tools/IndexedDbTools";

const $alert = (message, type = 'info') => {
  Snackbar[type] ? Snackbar[type](message) : Snackbar(message)
}
const sessionIdReg = /\/chat\/s\/([0-9a-f-]{36})/i;

const DB_NAME = 'deepseek-chat'
const STORE_NAME = 'history-message'

const getSessionIdFromUrl = (): string => {
  return (location.href.match(sessionIdReg) || [])[1] || ''
}
const getConversationFromDB = async (sessionId: string): Promise<any | null> => {
  const db = await openDB(DB_NAME)

  try {
    const transaction = getTransaction(db, STORE_NAME)
    const store = getStore(transaction, STORE_NAME)
    return await findFirst(store, value => value?.data?.chat_session?.id === sessionId)
  } finally {
    db.close()
  }
}

const download = (html: string, filename: string) => {
  const blob = new Blob([html], { type: 'text/html' })
  const url = URL.createObjectURL(blob)
  const link = document.createElement('a')
  link.href = url
  link.download = filename
  link.click()
  URL.revokeObjectURL(url)
}
const exporting = ref(false)
const exportFile = async () => {
  exporting.value = true

  try {
    const sessionId = getSessionIdFromUrl()
    if (!sessionId) {
      $alert('无法获取会话 ID，请确保在 DeepSeek 聊天页面使用')
      return
    }

    const conversation = await getConversationFromDB(sessionId)
    if (!conversation) {
      $alert('未找到对话数据')
      return
    }

    let result = []
    for (let i = 0; i < conversation.data?.chat_messages.length; i++) {
      let msg = conversation.data?.chat_messages[i]
      let content = ''
      if (msg.role === 'USER') {
        content = msg.fragments?.find(f => f.type === 'REQUEST')?.content
        content = '# 我问\n' + content
      }else{
        content = msg.fragments?.find(f => f.type === 'RESPONSE')?.content
        content = '# AI答：\n' + content
      }

      result.push(content)
    }
    let resultStr = result.join('\n')

    // 下载
    const title = conversation.data?.chat_session?.title || '对话记录'
    const timestamp = new Date().toISOString().slice(0, 19).replace(/:/g, '-')
    download(resultStr, `${title}_${timestamp}.md`)

    $alert('导出成功')
  } catch (error: any) {
    console.error(error)
    $alert(`导出失败: ${error.message}`)
  } finally {
    exporting.value = false
  }
}

defineExpose( {
  exportFile
})
</script>
<template>
</template>
<style scoped>
</style>
