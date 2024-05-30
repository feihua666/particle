import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueSetupExtend from 'vite-plugin-vue-setup-extend'
const aliasItem = (find) =>{
  return  {
    find: find,
    replacement:  fileURLToPath(new URL('./node_modules/' + find, import.meta.url))
  }
}
// https://vitejs.dev/config/
export default defineConfig({
  base: './',
  plugins: [vue(), vueJsx(),vueSetupExtend()],
  resolve: {
    alias: [
      {
        find:'@',
        replacement:  fileURLToPath(new URL('./src', import.meta.url))
      },
      {
        find:'@nm',
        replacement:  fileURLToPath(new URL('./node_modules', import.meta.url))
      },
      aliasItem('vue'),
      aliasItem('element-plus'),
      aliasItem('vue-router'),
      aliasItem('ace-builds'),
      aliasItem('tinymce/tinymce'),
      aliasItem('@tinymce/tinymce-vue'),
      aliasItem('uuid'),
      aliasItem('axios'),
      aliasItem('vuedraggable'),
      aliasItem('@element-plus/icons-vue'),
      aliasItem('dayjs'),
      aliasItem('clipboard'),
      aliasItem('echarts'),
      aliasItem('vue-echarts'),
    ]
  },
  server: {
    // 设置为 0.0.0.0 或者 true 表示对外开放
    host: true,
    fs: {
      // Allow serving files from one level up to the project root
      strict: false,
    }
  },
  build:{
    outDir: '../../../project/particle-demo/particle-demo-start/src/main/resources/static',
    rollupOptions:{
      external:[]
    }
  }
})
