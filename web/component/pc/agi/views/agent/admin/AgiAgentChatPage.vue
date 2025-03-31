<script setup name="AgiAgentChatPage" lang="ts">
/**
 * 智能体对话页面
 */
import {onMounted, getCurrentInstance, ref, nextTick} from 'vue'
import {
  newMarkedWithHl,
  renderMarkdown
} from "../../../../../../global/common/tools/MarkdownMarkedTools";
import {chatStream, detail as agiAgentDetailApi} from "../../../api/agent/admin/agiAgentFrontApi";
import {page as agiAgentChatMessagePageApi} from "../../../api/chat/admin/agiAgentChatMessageFrontApi";
import {copyToClipboard} from "../../../../../../global/common/tools/ClipboardTools";
import {useLoginUserStore} from "../../../../../../global/common/security/loginUserStore";
import AgiAgentChatHistory from "../../../components/chat/admin/AgiAgentChatHistory.vue"
import {v4 as uuidv4} from 'uuid';
const loginUserStore = useLoginUserStore()
const { proxy,appContext } = getCurrentInstance()
// 路由
const router = appContext.config.globalProperties.$router


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  agiAgentId: {
    type: String
  },
  chatId: {
    type: String
  }
})

// 滚动条控制
const renderContainerRef = ref(null)
// 记录上次滚动高度
const lastScrollTop = ref(0)
let isScrolling = false;

// 将滚动条滚动到底部
const scrollToBottom = (isForce = false) => {
  let el = renderContainerRef.value.$el;
  // 如果强制滚动条底部，则直接滚动
  if (isForce) {
    if (isScrolling) {
      return
    }
    isScrolling = true
    el.scrollTo({
      top: el.scrollHeight - el.clientHeight,
      behavior: 'smooth'
    })
    lastScrollTop.value = el.scrollHeight - el.clientHeight
    isScrolling = false
  }
  // 非强制滚动到底部，则判断实际情况
  else{
    // 用户滚动
    if (isUserScrollMethod()) {
      // 用户滚动时，需要将当前的lastScrollTop更新为滚动条底部
      lastScrollTop.value = el.scrollHeight - el.clientHeight
    }
    // 非用户滚动
    else{
      // 是否已经到底部
      const isAtBottom = isAtBottomMethod()
      if (isAtBottom) {
        // 如果已经到底部，则滚动到底部
        scrollToBottom(true)
        if(submitLoading.value){
          // 循环判断，好处是使用 requestAnimationFrame 避免滚动条上下跳动
          requestAnimationFrame(()=>{
            scrollToBottom(isForce)
          })
        }
      }
    }

  }

}
// 判断是否滚动到底部
const isAtBottomMethod = () => {
  let el = renderContainerRef.value.$el
  const isAtBottom = el.scrollTop >= lastScrollTop.value
  return isAtBottom
}
// 判断是否是用户滚动，这和滚动到底部相反，一般认为，滚动条没有在最底部，则是用户干预了滚动
const isUserScrollMethod = () => {
  let el = renderContainerRef.value.$el
  const isUserScroll = el.scrollTop < lastScrollTop.value
  return isUserScroll
}

// 存储智能体
const agiAgent = ref(null)
// 存储历史消息
const historyMessage = ref(null)
// 标识智能体是否加载完成
const agiAgentLoaded = ref(false)
// 标识历史消息是否加载完成
const historyMessageLoaded = ref(false)
// 标识是否已经初始化了历史消息
const hasInitHistoryMessage = ref(false)

// 存储文件上传元素显示状态，以实现切换
const chatActionUploadShow = ref(false)

// 输入内容
const chatInputValue = ref('')

// 使用 marked 库渲染markdown内容
const marked = newMarkedWithHl()
// 定义渲染markdown内容的方法
const renderMarkdownText = (markdownString)=>{
  return renderMarkdown(marked,markdownString);
}
// 存储当前正在回复的消息
const currentReplyMessage = ref('')

// 存储历史对话
const conversationHistory = ref([])
// 存储历史对话添加消息方法
const conversationHistoryAddMessage = (messageType, message)=>{
  let item = {
    messageType: messageType,
    content: 'user' != messageType ? renderMarkdownText(message) : message,
  }
  conversationHistory.value.push(item)
  // 直接return 在修改item变量时不会更新视图
  // return item
  return conversationHistory.value[conversationHistory.value.length - 1]
}
// 存储历史对话添加 user 消息方法
const conversationHistoryAddUserMessage = (message)=>{
  return conversationHistoryAddMessage('user', message)
}
// 存储历史对话添加 assistant 消息方法
const conversationHistoryAddAssistantMessage = (message)=>{
  return conversationHistoryAddMessage('assistant', message)
}
// 加载智能体详情方法
const loadAgiAgentDetail = (agiAgentId:string)=>{
  if (!agiAgentId) {
    agiAgentLoaded.value = true
    initHistoryMessage()
    return
  }
  agiAgentDetailApi({id: agiAgentId}).then(res=>{
    agiAgentLoaded.value = true
    agiAgent.value = res.data
    initHistoryMessage()
  }).finally(()=>{
    agiAgentLoaded.value = true
  })
}
// 加载智能体对话历史消息
const loadAgiAgentChatHistoryMessage = (chatId:string)=>{
  if (!chatId) {
    historyMessageLoaded.value = true
    initHistoryMessage()
    return
  }
  agiAgentChatMessagePageApi({chatId: chatId,orderBy: 'createAt-0'}).then(res=>{
    historyMessageLoaded.value = true
    historyMessage.value = res.data
    initHistoryMessage()
  }).finally(()=>{
    historyMessageLoaded.value = true
  })
}
// 初始化历史消息
const initHistoryMessage = () =>{
  if (hasInitHistoryMessage.value) {
    return
  }
  // 确保 智能体详情和历史消息都加载完毕后，才进行初始化
  if (agiAgentLoaded.value && historyMessageLoaded.value) {
    // 如果有历史消息，直接使用历史消息
    let historyMessageList = historyMessage.value.data.reverse()
    let agiAgentDetail = agiAgent.value.data
    if (historyMessageList && historyMessageList.length > 0) {
      for (let i = 0; i < historyMessageList.length; i++) {
        conversationHistoryAddMessage(historyMessageList[i].messageType, historyMessageList[i].content)
      }
    }
    // 如果没有历史消息，尝试使用智能体的预设对话
    else if (agiAgentDetail) {
      // 开场白
      if(agiAgentDetail.isUsePrologue && agiAgentDetail.prologue){
        conversationHistoryAddAssistantMessage(agiAgentDetail.prologue)
      }
      /**
       * 预设对话
       * 参考后端 {@link com.particle.agi.domain.agent.value.AgiAgentPresetDialogue}
       */
      let presetDialogueJsonStr = agiAgentDetail.presetDialogueJson
      if (presetDialogueJsonStr) {
        let presetDialogueJson = JSON.parse(presetDialogueJsonStr)
        let presetDialogueMessageList = presetDialogueJson.messages
        if (presetDialogueMessageList && presetDialogueMessageList.length > 0) {
          for (let i = 0; i < presetDialogueMessageList.length; i++) {
            conversationHistoryAddMessage(presetDialogueMessageList[i].messageType, presetDialogueMessageList[i].text)
          }
        }
      }
    }
    hasInitHistoryMessage.value = true
    nextTick(()=>{
      scrollToBottom(true)
    })
  }
}

// 回车提交
const onEnter = ()=>{
  submitMethod()
}
// 存储发送按钮状态
const submitLoading = ref(false)
const decoder = new TextDecoder('utf-8');
// 发起对话方法
const submitMethod = () => {
  if (submitLoading.value) {
    return
  }
  if (!chatInputValue.value) {
    return;
  }

  // 请求数据
  let data = {
    agiAgentId: props.agiAgentId,
    chatId: props.chatId,
    message: chatInputValue.value,
  };

  // 将用户的消息添加到对话历史记录中
  conversationHistoryAddUserMessage(chatInputValue.value)
  // 添加一个空的 assistant 消息，以便后续 流式响应时更新动态消息
  let currentMessageItem = conversationHistoryAddAssistantMessage('')
  // 发送之前清空当前回复消息
  currentReplyMessage.value = ''
  // 控制发送按钮状态
  submitLoading.value = true
  // 发送之前将滚动条滚动到最底部
  nextTick(()=>{
    setTimeout(() => {
      scrollToBottom(true)
    })
  })
  // 清空对话输出框
  chatInputValue.value = ''
  // 发起请求
  chatStream(data).then(res => {
    const reader = res.data.getReader()
    reader.read().then(function processText({ done, value }) {
      if (done) {
        submitLoading.value = false
        parseStreamData(currentMessageItem,value)
        scrollToBottom()
        return;
      }
      parseStreamData(currentMessageItem,value)
      scrollToBottom()
      reader.read().then(processText);
    });
  }).catch(error => {
      submitLoading.value = false
  })
}
// 解析流式数据
const parseStreamData = (currentMessageItem,value) => {
  let dataJsonStr = decoder.decode(value)
  let jsonStr = dataJsonStr.replace('data:', '')
  if (jsonStr) {
    jsonStr = jsonStr.trim()
    // 解析 JSON
    let jsonObject = null
    try {
      // 尝试转为json，这里加了try catch，避免异常，因为可能数据会是断的，不是完整的json
      if(jsonStr.startsWith('{') && jsonStr.endsWith('}')){
        jsonObject = JSON.parse(jsonStr)
      }
    } catch (e) {
    }
    if(jsonObject && jsonObject.data && jsonObject.data.results){
      for (let i = 0; i < jsonObject.data.results.length; i++) {
        let output = jsonObject.data.results[i].output
        if(output){
          let text = output.text || ''
          // 这里一般为 assistant
          if (currentMessageItem.messageType == output.messageType) {
            currentReplyMessage.value += text
            currentMessageItem.content = renderMarkdownText(currentReplyMessage.value)
          }
        }
      }

    }
  }
}

// 获取用户或者助手类名称，以显示对应的标识，如：头像等
const getUserOrAssistantClass = (item)=>{
  if(item.messageType == 'user'){
    return 'pt-chat-message-item-content-user pt-flex-direction-rr'
  }else if(item.messageType == 'assistant'){
    return 'pt-chat-message-item-content-assistant pt-flex'
  }
  return ''
}
// 复制代码
const copyCode = (e) => {
  if (e.target.classList.contains('pt-chat-code-container-header-content-right-copy') || e.target.classList.contains('pt-chat-code-container-footer-content-right-copy')) {
    let element = e.target;
    while (element && !element.classList.contains('pt-chat-code-container')) {
      element = element.parentElement;
    }
    const codeElement = element.querySelector('code')
    copyToClipboard(codeElement.textContent,
        () => {e.target.textContent = '复制成功';setTimeout(() => {e.target.textContent = '复制'},1000)},
        () => {e.target.textContent = '复制成功失败，请手动复制';setTimeout(() => {e.target.textContent = '复制'},1000)})
  }
}
/**
 * 核心函数, 对比节点的内容 实现动态更新 markdown 的 div 而不是用 innerHTML 的属性全部刷新
 * 主要解决渲染性能问题，和没必要的渲染，这样已经渲染的结果选中后不会受更新的影响
 * 最主要原因是在实时渲染时点击代码复制不影响复制动态效果
 * 感谢，参考：https://github.com/pldz1/AIGC_Playground/blob/master/samples/sse_markdown/src/module/code-block.js
 */
function deepCloneAndUpdate(div1, div2) {
  // 递归比较和更新 div1 和 div2 的子节点
  function compareAndUpdate(node1, node2) {

    let isIgnore = node1.classList && node1.className === node2.className && (node1.classList.contains('pt-chat-code-container-header-content-right-copy') || node1.classList.contains('pt-chat-code-container-footer-content-right-copy'))
    if (isIgnore) {
      return;
    }

    // 情况 1：node1 是文本节点，更新文本内容
    if (node1 && node1.nodeType === Node.TEXT_NODE && node2.nodeType === Node.TEXT_NODE) {
      if (node1.nodeValue !== node2.nodeValue) {
        // 更新文本内容
        node1.nodeValue = node2.nodeValue;
      }
      return;
    }

    // 情况 2：node1 和 node2 的标签名不同，替换整个节点
    if (!node1 || node1.tagName !== node2.tagName) {
      // 克隆 node2 节点
      const newNode = node2.cloneNode(true);
      if (node1) {
        // 替换旧节点
        node1.parentNode.replaceChild(newNode, node1);
      } else {
        // 如果 node1 不存在，直接新增
        node2.parentNode.appendChild(newNode);
      }
      return;
    }

    // 情况 3：节点的 class 或其他属性更新, 注意对root节点的保护
    if (node1.className !== node2.className) {
      // 3.1 更新 className
      node1.className = node2.className;
    }

    // 3.2 对 id 的更新 注意对root节点的保护
    if (node1.id !== node2.id) {
      node1.id = node2.id;
    }

    //  3.3 对 style 的更新
    if (node1.style.cssText !== node2.style.cssText) {
      node1.style.cssText = node2.style.cssText;
    }

    // 情况 4：递归对比和更新子节点
    const children1 = Array.from(node1.childNodes); // node1 的所有子节点
    const children2 = Array.from(node2.childNodes); // node2 的所有子节点

    // 遍历 node2 的子节点，逐个与 node1 的对应子节点比较
    children2.forEach((child2, index) => {
      const child1 = children1[index];
      if (!child1) {
        // 如果 child1 不存在，直接克隆 child2 并添加到 node1
        const newChild = child2.cloneNode(true);
        node1.appendChild(newChild);
      } else {
        // 如果 child1 存在，递归比较和更新
        compareAndUpdate(child1, child2);
      }
    });

    // 删除 node1 中多余的子节点
    if (children1.length > children2.length) {
      for (let i = children2.length; i < children1.length; i++) {
        node1.removeChild(children1[i]);
      }
    }
  }

  // 从 div2 根节点开始与 div1 比较
  compareAndUpdate(div1, div2);
}
// 自定义指令以实现增量更新
const vCustomHtml = {
  beforeMount(el, binding, vnode) {
    el.innerHTML = binding.value;
  },
  // 在元素被插入到 DOM 前调用
  updated(el, binding, vnode) {
    // 创建虚拟dom以差异化增加更新
    const tmpDiv = document.createElement("div");
    // 由于 deepCloneAndUpdate 需要全部更新，这里className要和 v-custom-html 的类名保持一致
    tmpDiv.className = 'pt-chat-message-item-content-message'
    tmpDiv.innerHTML = binding.value

    deepCloneAndUpdate(el, tmpDiv)
  },
}

// 新建对话
const newChat = ()=>{
  let idChatData = {id: props.agiAgentId,chatId: uuidv4()}
  router.replace({path: '/admin/agiAgentChatPage',query: idChatData})
}

// 挂载
onMounted(() => {
  // 加载智能体详情
  loadAgiAgentDetail(props.agiAgentId)
  // 加载历史对话消息
  loadAgiAgentChatHistoryMessage(props.chatId)
})
</script>
<template>
  <el-container class="pt-height-100-pc pt-width-100-pc">
    <el-container>
      <el-main ref="renderContainerRef" @click="copyCode" class="pt-chat-message-main pt-flex-direction-c pt-flex-align-cross-center">
        <div class="pt-chat-message-header pt-flex-align-between pt-width-100-pc">
          <div class="pt-chat-message-header-left">
            <div class="pt-flex-align-cross-center">
              <el-avatar v-if="agiAgent && agiAgent.data" :src="agiAgent?.data.avatar">
                {{ (agiAgent?.data.name) ? (agiAgent?.data.name).substring(0,1) : 'AI' }}
              </el-avatar>
              <span style="margin-left: 0.8rem;"> {{ agiAgent?.data.name }}</span>
            </div>

          </div>
          <div class="pt-chat-message-header-center">新对话</div>
          <div class="pt-chat-message-header-right">
            <div class="pt-chat-message-header-right-history">

              <el-button type="primary" link icon="ChatSquare" @click="newChat">新建对话</el-button>
              <el-popover placement="bottom" width="600" trigger="click">
                <template #reference>
                  <el-button type="primary" link icon="Clock">历史对话</el-button>
                </template>
                <AgiAgentChatHistory v-if="agiAgent && agiAgent.data" :agiAgentId="agiAgent.data.id"></AgiAgentChatHistory>
              </el-popover>
            </div>
          </div>
        </div>
        <div class="pt-chat-message-container pt-width-100-pc">
          <div class="pt-chat-message-list pt-flex-direction-c">
            <template v-for="item in conversationHistory">
              <div class="pt-chat-message-item">
                <div class="pt-chat-message-item-content" :class="getUserOrAssistantClass(item)">
                  <div class="pt-chat-message-item-content-avatar">
                    <el-avatar v-if="item.messageType == 'user'" :src="loginUserStore.loginUser?.avatar">
                      {{ (loginUserStore.loginUser?.nickname || loginUserStore.loginUser?.username ) ? (loginUserStore.loginUser?.nickname || loginUserStore.loginUser?.username ).substring(0,1) : '我' }}
                    </el-avatar>
                    <el-avatar v-if="item.messageType == 'assistant'" :src="agiAgent?.data.avatar">
                      {{ (agiAgent?.data.name) ? (agiAgent?.data.name).substring(0,1) : 'AI' }}
                    </el-avatar>
                  </div>
                  <template v-if="item.messageType == 'user'">
                    <div class="pt-chat-message-item-content-message" v-text="item.content">
                    </div>
                  </template>
                  <template v-if="item.messageType == 'assistant'">
                    <div class="pt-chat-message-item-content-message" v-custom-html="item.content">
                    </div>
                  </template>
                </div>
              </div>
            </template>
          </div>
        </div>

        <div class="pt-chat-action-container pt-width-100-pc">
          <div v-if="chatActionUploadShow" class="pt-chat-action-container-action-upload-container">
            <PtUpload :drag="true" drag-tip="将文件拖到此处，或<em>点击上传</em>"></PtUpload>
          </div>
          <PtLexicalEditorChatInput v-model="chatInputValue" @enter="onEnter"></PtLexicalEditorChatInput>
          <div class="pt-chat-action-container-action pt-flex-align-between pt-flex-align-end">
            <div class="pt-chat-action-container-action-left pt-flex-align-cross-center"></div>
            <div class="pt-chat-action-container-action-right pt-flex-align-cross-center">

              <PtButton type="default" icon="MessageBox">快捷指令</PtButton>
              <PtButton :type="chatActionUploadShow ? 'primary' : 'default'" icon="Connection" @click="chatActionUploadShow = !chatActionUploadShow">上传附件</PtButton>
              <PtButton type="primary" :disabled="chatInputValue.length <= 0" :loading="submitLoading" icon="Position" @click="submitMethod">发送</PtButton>
            </div>

          </div>

        </div>

      </el-main>
    </el-container>
  </el-container>
  <!-- 子级路由 -->
  <PtRouteViewPopover :level="3"></PtRouteViewPopover>
</template>

<style>
.pt-chat-code-container{
  margin: .5em 0;
  border-top-left-radius: 12px;
  border-top-right-radius: 12px;
}
.pt-chat-code-container .pt-chat-code-container-header{
  position: sticky;
  top: var(--el-main-padding);
}
.pt-chat-code-container .pt-chat-code-container-header,.pt-chat-code-container .pt-chat-code-container-footer{
  background-color: var(--el-bg-color);
  height: 2rem;
  color: #fff;
  font-size: 0.8rem;
}
.pt-chat-code-container .pt-chat-code-container-header .pt-chat-code-container-header-content{
  background-color: #50505a;
  border-top-left-radius: 12px;
  border-top-right-radius: 12px;
  padding: 0 12px;
}
.pt-chat-code-container .pt-chat-code-container-footer .pt-chat-code-container-footer-content{
  background-color: #50505a;
  padding: 0 12px;
}
.pt-chat-code-container pre[class*="language-"] {
  margin: 0;
}
</style>
<style>
.pt-chat-message-item-content-message pre,.pt-chat-message-item-content-message pre code{
  white-space: pre-wrap;
  word-break: break-all;
  margin: 0 !important;
}
</style>
<style scoped>
.pt-chat-message-main {
  position: relative;
  padding-bottom: 0px;
  scroll-behavior: smooth;
}
.pt-chat-message-header{
  min-width: 375px;
  height: 46px;
  min-height: 46px;
  position: sticky;
  top: calc(-1 * var(--el-main-padding));
  background-color: var(--el-bg-color);
}
.pt-chat-message-container{
  flex: 1;
  padding-bottom: 30px;
}
.pt-chat-message-list{
  gap: 30px;
}
.pt-chat-message-item-content{
  gap: 10px;
}
.pt-chat-message-item-content-user{
  padding-left: 48px;
}
.pt-chat-message-item-content-assistant{
  padding-right: 48px;
}


.pt-chat-action-container{
  max-width: 780px;
  border-radius: 16px;
  border: 1px solid var(--el-border-color);
  padding: 8px 12px 2px 16px;
  position: sticky;
  bottom: 0;
  background-color: var(--el-bg-color);
}
.pt-chat-action-container-action{
  margin-top: 2px;
  margin-bottom: 8px;
  position: relative;
}
</style>
