# particle-project
1. 在打包时去掉 ts查检 package.json 中 "build": "run-p type-check build-only", 改为 "build": "run-p build-only",
2. vite.config.ts 配置 主要解决有的组件或ts在项目（根目录之前）之外，找不到 node_modules内的依赖
```js
const aliasItem = (find) =>{
  return  {
    find: find,
    replacement:  fileURLToPath(new URL('./node_modules/' + find, import.meta.url))
  }
}
...

// https://vitejs.dev/config/
export default defineConfig({
   ...
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
      ]
   },
   ...
})
```
3. src/main.ts 依赖一个自定义的 src/thirdPart.ts 以解决别名也解决不了的问题，主要是提前导入一下
4. 安装依赖尽量使用 npm install，否则可能少包

This template should help get you started developing with Vue 3 in Vite.

## Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur) + [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin).

## Type Support for `.vue` Imports in TS

TypeScript cannot handle type information for `.vue` imports by default, so we replace the `tsc` CLI with `vue-tsc` for type checking. In editors, we need [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin) to make the TypeScript language service aware of `.vue` types.

If the standalone TypeScript plugin doesn't feel fast enough to you, Volar has also implemented a [Take Over Mode](https://github.com/johnsoncodehk/volar/discussions/471#discussioncomment-1361669) that is more performant. You can enable it by the following steps:

1. Disable the built-in TypeScript Extension
    1) Run `Extensions: Show Built-in Extensions` from VSCode's command palette
    2) Find `TypeScript and JavaScript Language Features`, right click and select `Disable (Workspace)`
2. Reload the VSCode window by running `Developer: Reload Window` from the command palette.

## Customize configuration

See [Vite Configuration Reference](https://vitejs.dev/config/).

## Project Setup

```sh
npm install
```

### Compile and Hot-Reload for Development

```sh
npm run dev
```

### Type-Check, Compile and Minify for Production

```sh
npm run build
```

### Run Unit Tests with [Vitest](https://vitest.dev/)

```sh
npm run test:unit
```

### Run End-to-End Tests with [Cypress](https://www.cypress.io/)

```sh
npm run build
npm run test:e2e # or `npm run test:e2e:ci` for headless testing
```

### Lint with [ESLint](https://eslint.org/)

```sh
npm run lint
```
