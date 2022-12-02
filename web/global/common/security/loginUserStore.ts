/**
 * 登录用户
 */
import { ref } from 'vue'
import { defineStore } from 'pinia'
import {anyObj, isEmpty} from '../tools/ObjectTools'
import {get,set} from '../tools/StorageTools.ts'

export interface LoginUserStore{
    // 是否强制登录
    forceLogin: boolean,
    // 是否已经登录
    hasLogin: boolean,
    // hasLogin=true 后登录用户为空
    loginUser: anyObj,
    // 变更是否登录
    changeHasLogin: (value: boolean) => void,
    // 变更登录用户
    changeLoginUser: (user: anyObj) => void,
    // 从 localStorage 加载
    loadFromLocal: () => void,
}

export const useLoginUserStore = defineStore<LoginUserStore>('loginUser', () => {

    const loginUserLocalKey = 'loginUserLocalKey'

    // 强制登录
    const forceLogin = ref(true)
    // 是否已经登录
    const hasLogin = ref(false)

    // 登录用户信息，
    const loginUser = ref({})

    // 改变是否登录的方法
    function changeHasLogin(value):void {
        hasLogin.value = value || false
        if(hasLogin.value == false){
            loginUser.value = {}
            set(loginUserLocalKey,loginUser.value)
        }
    }
    // 改变是否登录的方法
    function changeLoginUser(user):void {
        loginUser.value = user || {}
        hasLogin.value = !isEmpty(loginUser.value)
        set(loginUserLocalKey,loginUser.value)
    }

    // 一般从 localStorage 获取
    function loadFromLocal(){
        let loginUserLocal = get(loginUserLocalKey)
        changeLoginUser(loginUserLocal)
    }
    return { forceLogin, hasLogin, changeHasLogin, changeLoginUser,loadFromLocal}
})
