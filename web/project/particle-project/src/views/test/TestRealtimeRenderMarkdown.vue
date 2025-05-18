<script setup name="TestRealtimeRenderMarkdown" lang="ts">

import {ref,computed,nextTick} from "vue";
import { Marked } from 'marked';
import { markedHighlight } from "marked-highlight";
import DOMPurify from 'dompurify';
import Prism from 'prismjs';
import 'prismjs/themes/prism-tomorrow.css';
import 'prismjs/components/prism-bash.min'
import 'prismjs/plugins/toolbar/prism-toolbar.min.css'
import 'prismjs/plugins/toolbar/prism-toolbar.min'
import 'prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min'
import 'prismjs/plugins/show-language/prism-show-language.min';
import {copyToClipboard} from "../../../../../global/common/tools/ClipboardTools.ts";


const marked = new Marked(
    markedHighlight({
      emptyLangClass: '',
      langPrefix: 'language-',
      highlight(code, lang, info) {
        if (lang && Prism.languages[lang]) {
          code = Prism.highlight(code, Prism.languages[lang], lang);
        }
        return code

      }
    })
);
// 自定义 renderer
const renderer = new marked.Renderer()
const rendererCode = renderer.code

renderer.code = function({ text, lang, escaped }) {
  let result =  rendererCode({ text, lang, escaped })
  result = result.replace('<pre><code', '<pre class="language-' + lang + '"><code')

  const codeBlock = `
<div class="pt-chat-code-container">
    <div class="pt-chat-code-container-header">
        <div class="pt-chat-code-container-header-content pt-height-100-pc pt-flex-align-between pt-flex-item-1 pt-flex-align-cross-center">
            <div class="pt-chat-code-container-header-content-left">
                <div class="pt-chat-code-container-header-content-left-lang">${lang || ''}</div>
            </div>
            <div class="pt-chat-code-container-header-content-right">
                <div class="pt-chat-code-container-header-content-right-copy pt-pointer">复制</div>
            </div>
        </div>
    </div>
    <div class="pt-chat-code">${result}</div>
    <div class="pt-chat-code-container-footer">
        <div class="pt-chat-code-container-footer-content pt-height-100-pc pt-flex-align-between pt-flex-item-1 pt-flex-align-cross-center">
            <div class="pt-chat-code-container-footer-content-left">
                <div class="pt-chat-code-container-footer-content-left-lang">${lang || ''}</div>
            </div>
            <div class="pt-chat-code-container-footer-content-right">
                <div class="pt-chat-code-container-footer-content-right-copy pt-pointer">复制</div>
            </div>
        </div>
    </div>
</div>
`

  return codeBlock
};
marked.use({ renderer})

function renderMarkdown( markdownString: string) {
  const rawHtml = marked.parse(markdownString);
  let purify = DOMPurify.sanitize(rawHtml)
  return purify;
}
const input = ref('');
const inputRendered = ref('');
const timerHolder = ref(null);
const currentTimes = ref(0)
const buttonLoadding = ref(false)
const renderMarkdownTestDirectly = ()=> {
  currentTimes.value = input.value.length - 1
  renderMarkdownTest(false)
}
const renderMarkdownTest = (resetCurrentTimes = true)=> {
  if (resetCurrentTimes) {
    currentTimes.value = 0;
  }
  buttonLoadding.value = true

  scrollDoing.value = true

  timerHolder.value = timer((times) => {
    if (currentTimes.value > times) {
      scrollDoing.value = false
      return true
    }
    currentTimes.value = times;
    if (times >= input.value.length) {
      scrollDoing.value = false
      buttonLoadding.value = false
      // Prism.highlightAll();
      return false
    }
    let value = input.value.substring(0, times);
    let valueRendered = renderMarkdown(value)
    inputRendered.value = valueRendered

    const tmpDiv = document.createElement("div");
    tmpDiv.className = 'render-container'
    tmpDiv.innerHTML = inputRendered.value;

    // requestAnimationFrame(()=>{
    //   deepCloneAndUpdate(renderContainerRef.value, tmpDiv)

      scrollToBottom(false)
    // })
    // console.log(value)
    // console.warn(valueRendered)
  }, 30, 0,currentTimes.value)
}
const continueRenderMarkdownTest = () => {
  renderMarkdownTest(false)
}
const stopeRenderMarkdownTest = ()=> {
  clearInterval(timerHolder.value)
  scrollDoing.value = false
  buttonLoadding.value = false
}

function timer (fn:(times: number)=>any,time: number,times: number,initTimes: number) {
  let _times = initTimes || 0;
  //默认次数为无限次
  if(!times){
    times = 0;
  }
  //默认间隔时间为一秒钟
  if(!time){
    time = 1000;
  }
  let t= setInterval(function () {
    if (fn && typeof fn == "function") {
      const result = fn.call(null,_times);
      if(result === false){
        clearInterval(t);
      }
      _times ++;
      //如果到了执行次数
      if (_times == times) {
        clearInterval(t);
      }
    }else{
      clearInterval(t);
    }
  },time);
  return t;
}


// 滚动条控制
const renderContainerRef = ref(null)
// 记录上次滚动高度
const lastScrollHeight = ref(0)
const lastScrollTop = ref(0)
const scrollDoing = ref(false)
let isScrolling = false;

const scrollToBottom = (isForce = false) => {

  let el = renderContainerRef.value;
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
    lastScrollHeight.value = el.scrollHeight
    isScrolling = false
  }else{
    if (isUserScrollMethod()) {
      lastScrollTop.value = el.scrollHeight - el.clientHeight
    }else{
      const isAtBottom = isAtBottomMethod()
      if (isAtBottom) {
        scrollToBottom(true)
        if(scrollDoing.value){
          requestAnimationFrame(()=>{
            scrollToBottom(isForce)
          })
        }
      }
    }

  }

}
const isAtBottomMethod = () => {
  let el = renderContainerRef.value
  // const isAtBottom = el.scrollTop + el.clientHeight >= el.scrollHeight
  // const isAtBottom = el.scrollTop + el.clientHeight >= lastScrollHeight.value
  const isAtBottom = el.scrollTop >= lastScrollTop.value
  // const isAtBottom = el.scrollHeight - el.clientHeight >= lastScrollTop.value
  // console.log("isAtBottom",isAtBottom,el.scrollTop,el.scrollTop == el.scrollHeight - el.clientHeight,lastScrollTop.value)
  console.log("isAtBottom",el.scrollTop,el.clientHeight,el.scrollHeight)
  return isAtBottom
}
const isUserScrollMethod = () => {
  let el = renderContainerRef.value
  // const isUserScroll = el.scrollTop + el.clientHeight < el.scrollHeight
  // const isUserScroll = el.scrollTop + el.clientHeight < lastScrollHeight.value
  const isUserScroll = el.scrollTop < lastScrollTop.value
  // const isUserScroll = el.scrollHeight - el.clientHeight < lastScrollTop.value
  // console.log("isUserScroll",isUserScroll,el.scrollTop,el.scrollTop == el.scrollHeight - el.clientHeight,lastScrollTop.value)
  console.log("isUserScroll",el.scrollTop,el.clientHeight,el.scrollHeight)
  return isUserScroll
}
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
 * 感谢，参考：https://github.com/pldz1/AIGC_Playground/blob/master/samples/sse_markdown/src/module/code-block.js
 */
/** 核心函数, 对比节点的内容 实现动态更新 markdown 的 div 而不是用 innerHTML 的属性全部刷新 */
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

const vCustomHtml = {
  beforeMount(el, binding, vnode) {
    el.innerHTML = binding.value;
    console.log('Created', el, binding, vnode)
  },
  // 在元素被插入到 DOM 前调用
  updated(el, binding, vnode) {
    const tmpDiv = document.createElement("div");
    tmpDiv.className = 'render-container'
    tmpDiv.innerHTML = binding.value

    // requestAnimationFrame(()=>{
    deepCloneAndUpdate(el, tmpDiv)
    console.log('BeforeMount', el, binding, vnode)
  },
}
</script>
<template>

  <el-input type="textarea" :rows="20" v-model="input" placeholder="请输入markdown文件后点击渲染测试"></el-input>
  <el-button type="primary" :loading="buttonLoadding" @click="renderMarkdownTest">渲染测试</el-button>
  <el-button type="primary" :loading="buttonLoadding" @click="continueRenderMarkdownTest">继续渲染测试</el-button>
  <el-button type="primary" @click="stopeRenderMarkdownTest">停止测试</el-button>
  <el-button type="primary" @click="renderMarkdownTestDirectly">直接渲染</el-button>

  <div>下面为渲染结果：</div>
<!--  <div ref="renderContainerRef" class="render-container" v-html="inputRendered" @click="copyCode"></div>-->
<!--  <div ref="renderContainerRef" class="render-container" @click="copyCode"></div>-->
  <div ref="renderContainerRef" class="render-container" @click="copyCode" v-custom-html="inputRendered"></div>
</template>

<style>
.pt-chat-code-container{
  margin: .5em 0;
  border-top-left-radius: 12px;
  border-top-right-radius: 12px;
}
.pt-chat-code-container .pt-chat-code-container-header{
  position: sticky;
  top: 0;
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
<style scoped>
.render-container{
  max-height: 500px;
  overflow-y: auto;
  border: 1px solid red;
}
</style>
