/**
 * 登录用户
 */
import { ref } from 'vue'
import { defineStore } from 'pinia'
import {anyObj, isEmpty} from '../tools/ObjectTools'
import {get, getRaw, set, setRaw} from '../tools/StorageTools.ts'

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
    const loginUserTokenKey = 'loginUserTokenKey'
    const loginUserRefreshTokenKey = 'loginUserRefreshTokenKey'

    // 强制登录
    const forceLogin = ref(true)
    // 是否已经登录
    const hasLogin = ref(false)

    // 登录用户信息，
    const loginUser = ref({})

    // 登录后的 token
    const token = ref('')
    // 用来刷新用的 token
    const refreshToken = ref('')

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
        let loginUserToken = getRaw(loginUserTokenKey)
        let loginUserRefreshToken = getRaw(loginUserRefreshTokenKey)
        changeToken(loginUserToken)
        changeRefreshToken(loginUserRefreshToken)
    }


    function changeToken(tk: string): void{

        token.value = tk
        let loginUserToken = getRaw(loginUserTokenKey)
        if (tk == loginUserToken) {
            return
        }
        setRaw(loginUserTokenKey,token.value)
    }
    function changeRefreshToken(rtk: string): void{

        refreshToken.value = rtk
        let loginUserRefreshToken = getRaw(loginUserRefreshTokenKey)
        if (rtk == loginUserRefreshToken) {
            return
        }
        setRaw(loginUserRefreshTokenKey,refreshToken.value)
    }
    return { forceLogin, hasLogin, changeHasLogin, changeLoginUser,loadFromLocal,token,refreshToken,changeToken,changeRefreshToken}
})
