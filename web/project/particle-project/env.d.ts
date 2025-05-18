/// <reference types="vite/client" />
interface ImportMetaEnv {
    // axios 请求基础地址，如：http://localhost:8080
    readonly VITE_AXIOS_BASE_URL: string | 'getCurrentDomain'
    // 请求地上上下文，最终以 VITE_AXIOS_BASE_URL + VITE_AXIOS_CONTEXT 为接口请求地址
    readonly VITE_AXIOS_CONTEXT: string | ''
}

interface ImportMeta {
    readonly env: ImportMetaEnv
}