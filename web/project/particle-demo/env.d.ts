/// <reference types="vite/client" />
interface ImportMetaEnv {
    // axios 请求基础地址
    readonly VITE_AXIOS_BASE_URL: string | 'getCurrentDomain'
}

interface ImportMeta {
    readonly env: ImportMetaEnv
}