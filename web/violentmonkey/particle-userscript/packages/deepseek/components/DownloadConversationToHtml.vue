<script setup lang="ts">

import {ref} from "vue";
import {Snackbar} from "@varlet/ui";
import {
  findFirst,
  getStore,
  getTransaction,
  openDB
} from "../../../../../global/common/tools/IndexedDbTools";
import {Marked} from "marked";

const $alert = (message, type = 'info') => {
  Snackbar[type] ? Snackbar[type](message) : Snackbar(message)
}
const sessionIdReg = /\/chat\/s\/([0-9a-f-]{36})/i;

const DB_NAME = 'deepseek-chat'
const STORE_NAME = 'history-message'
//模板内容
const templateRoot = `
<div style="display: flex; flex-direction: column; gap: 16px; width: 100%; box-sizing: border-box; padding: 16px;">
  {{content}}
</div>
`
const templateUser = `
    <!-- USER 消息（修复版） -->
    <div style="display: flex; justify-content: flex-end;gap: 12px;">
      <div style="width: 48px"></div>
      <div style="max-width: 100%; background: #3b82f6; color: white; padding: 10px 14px; border-top-left-radius: 18px; border-top-right-radius: 18px; border-bottom-left-radius: 18px; border-bottom-right-radius: 4px; font-size: 14px; line-height: 1.5; word-break: break-word; box-sizing: border-box;">
          <div>{{content}}</div>
      </div>
      <!-- 头像 -->
      <div style="width: 36px; height: 36px; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 14px; font-weight: 500; flex-shrink: 0; background: #3b82f6; color: white;">我</div>
    </div>
`
const templateAssitant = `
    <!-- AI 消息（修复版） -->
    <div style="display: flex; flex-direction: row; align-items: flex-start; gap: 12px;">
      <!-- 头像 -->
      <div style="width: 36px; height: 36px; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 14px; font-weight: 500; flex-shrink: 0; background: #dbeafe; color: #2563eb;">AI</div>
      <div style="max-width: 100%; background: white; color: #1e293b; padding: 10px 14px; border-top-left-radius: 18px; border-top-right-radius: 18px; border-bottom-left-radius: 4px; border-bottom-right-radius: 18px; font-size: 14px; line-height: 1.5; word-break: break-word; box-sizing: border-box;">
          <div>{{content}}</div>
      </div>
      <div style="width: 48px"></div>
   </div>

`

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
const marked =  new Marked()
marked.setOptions({
  breaks: true,      // 将换行符转换为 <br>
  gfm: true,         // 启用 GitHub 风格的 Markdown
})

const renderMarkdown = (markdown: string): string | null => {
  if (!markdown) {
    return ''
  }
  const rawHtml = marked.parse(markdown);

  return rawHtml
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

    // 生成 HTML

    let result = []
    for (let i = 0; i < conversation.data?.chat_messages.length; i++) {
      let msg = conversation.data?.chat_messages[i]
      let content = ''
      if (msg.role === 'USER') {
        content = msg.fragments?.find(f => f.type === 'REQUEST')?.content
        content = templateUser.replace('{{content}}', renderMarkdown(content))
      }else{
        content = msg.fragments?.find(f => f.type === 'RESPONSE')?.content
        content = templateAssitant.replace('{{content}}', renderMarkdown(content))
      }

      result.push(content)
    }
    let resultStr = result.join('')
    resultStr = templateRoot.replace('{{content}}', resultStr)

    // 下载
    const title = conversation.data?.chat_session?.title || '对话记录'
    const timestamp = new Date().toISOString().slice(0, 19).replace(/:/g, '-')
    download(resultStr, `${title}_${timestamp}.html`)

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
