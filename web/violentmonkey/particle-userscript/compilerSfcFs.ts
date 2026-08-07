/**
 * 该代码由 claude 生成
 * 问题根源： @vue/compiler-sfc 解析 defineProps<EdgeProps>() 泛型时，走自己的文件系统查找，不认识 Vite 的 alias 配置。
 * 解决路径： 通过 plugin-vue 的 script.fs 选项拦截编译器的文件 IO，手动重定向到项目的 node_modules。
 */


import fs from 'node:fs'
import path from 'node:path'
import { fileURLToPath, URL } from 'node:url'

const projectRoot = fileURLToPath(new URL('.', import.meta.url))
const projectNodeModules = path.join(projectRoot, 'node_modules')

function isFile(filePath: string): boolean {
    try {
        return fs.statSync(filePath).isFile()  // 必须是文件，不能是目录
    } catch {
        return false
    }
}

function redirectToProjectModules(file: string): string {
    const nmIndex = file.lastIndexOf('node_modules')
    if (nmIndex !== -1) {
        const relative = file.slice(nmIndex + 'node_modules'.length)
        return path.join(projectNodeModules, relative)
    }
    return file
}

export const compilerSfcFs = {
    fileExists(file: string): boolean {
        if (isFile(file)) return true
        return isFile(redirectToProjectModules(file))
    },
    readFile(file: string): string | undefined {
        if (isFile(file)) return fs.readFileSync(file, 'utf-8')
        const redirected = redirectToProjectModules(file)
        if (isFile(redirected)) return fs.readFileSync(redirected, 'utf-8')
        return undefined
    },
    realpath(file: string): string {
        if (isFile(file)) return fs.realpathSync(file)
        const redirected = redirectToProjectModules(file)
        if (isFile(redirected)) return fs.realpathSync(redirected)
        return file
    }
}
