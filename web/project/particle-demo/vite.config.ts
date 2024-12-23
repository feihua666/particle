import {fileURLToPath, URL} from 'node:url'

import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueDevTools from 'vite-plugin-vue-devtools'

const aliasItem = (find) =>{
  return  {
    find: find,
    replacement:  fileURLToPath(new URL('./node_modules/' + find, import.meta.url))
  }
}
// https://vitejs.dev/config/
export default defineConfig(({ command, mode, isSsrBuild, isPreview }) => {
  let isCommndServe = command === 'serve'
  let isCommndBuild = command !== 'serve'
  // Load env file based on `mode` in the current working directory.
  // Set the third parameter to '' to load all env regardless of the `VITE_` prefix.
  // const env = loadEnv(mode, process.cwd(), '')
  const env = loadEnv(mode, process.cwd())
  return {
    base: env.VITE_BUILD_BASE || './',
    plugins: [vue(), vueJsx(),vueDevTools()],
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
      outDir: env.VITE_BUILD_OUTDIR || '../../../project/particle-demo/particle-demo-start/src/main/resources/static',
      rollupOptions:{
        external:[]
      }
    }
  }
})
