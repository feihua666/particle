/**
 * 登录用户
 */
import { ref } from 'vue'
import { defineStore } from 'pinia'
import {isEmpty} from '../tools/ObjectTools.js'

export const useLoginUserStore = defineStore('loginUser', () => {
    // 强制登录
    const forceLogin = ref(true)
    // 是否已经登录
    const hasLogin = ref(false)

    // 登录用户信息，
    const loginUser = ref({})

    // 改变是否登录的方法
    function changeHasLogin(value) {
        hasLogin.value = value || false
        if(hasLogin.value == false){
            loginUser.value = {}
        }
    }
    // 改变是否登录的方法
    function changeLoginUser(user) {
        loginUser.value = user || {}
        if(isEmpty(loginUser.value)){
            hasLogin.value = false
        }
    }

    return { forceLogin, hasLogin, changeHasLogin, changeLoginUser}
})
