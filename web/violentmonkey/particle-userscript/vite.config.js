import {fileURLToPath, URL} from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import monkey, { cdn, util } from 'vite-plugin-monkey'
import fs from 'fs'
import { compilerSfcFs } from './compilerSfcFs'
const aliasItem = (find) =>{
  return  {
    find: find,
    replacement:  fileURLToPath(new URL('./node_modules/' + find, import.meta.url))
  }
}
// 同步读取 package.json
const pkg = JSON.parse(fs.readFileSync('./package.json', 'utf-8'))
export default defineConfig(({mode}) => {
  // 添加脚本只需要修改 scripts 配置
  const scripts = {
    doubao: {
      userscript: {
        name: '豆包会话管理工具',
        description: '豆包会话管理工具，帮助用户批量删除豆包网站的聊天记录。支持多选、逐条删除，同时保留未选中的对话。',
        match: ['https://www.doubao.com/*'],
        icon: `data:image/png;base64,${fs.readFileSync('./src/assets/doubao_avatar.png').toString('base64')}`,
      },
    },
    deepseek: {
      userscript: {
        name: 'deepseek 会话管理工具',
        description: 'deepseek 会话管理工具，帮助用户批量删除 deepseek 网站的聊天记录。支持多选、逐条删除，同时保留未选中的对话。',
        match: ['https://chat.deepseek.com/*'],
        icon: `data:image/png;base64,${fs.readFileSync('./src/assets/deepseek_logo.png').toString('base64')}`,
      },
    },
  }

  return {
    build: {
      outDir: 'release',
      emptyOutDir: false,
      rollupOptions: {
        external: ['@varlet/ui/es/style'],
      }
    },
    plugins: [
      vue({
        script: {
          fs: compilerSfcFs
        }
      }),
      monkey({
        entry: `packages/${mode}/main.js`,
        userscript: {
          author: 'feihua',
          namespace: 'particle',
          version: pkg.version,
          license: 'MIT',
          homepage: 'https://github.com/feihua666/particle',
          supportURL: 'https://github.com/feihua666/particle/issues',
          ...scripts[mode].userscript,
        },
        build: {
          fileName: `particle-userscript-${mode}.user.js`,
          externalGlobals: {
            vue: cdn
                .jsdelivr('Vue', 'dist/vue.global.prod.js')
                .concat(util.dataUrl(';window.Vue=Vue;')),
            '@varlet/ui': cdn
                .jsdelivr('Varlet', 'umd/varlet.js')
                .concat(util.dataUrl(';window.Varlet=Varlet;')),
            '@varlet/touch-emulator': cdn.jsdelivr('VarletTouchEmulator', 'iife.js'),
          },
        }
      })
    ],
    resolve: {
      alias: [
        {
          find:'@',
          replacement:  fileURLToPath(new URL('./src', import.meta.url))
        },
        { find: 'vue', replacement: fileURLToPath(new URL('./node_modules/vue/dist/vue.esm-bundler.js', import.meta.url)) },
        aliasItem('marked'),
        aliasItem('dompurify'),
        aliasItem('highlight.js'),
        aliasItem('marked-highlight'),
        aliasItem('prismjs'),
      ]
    },
  }
})
