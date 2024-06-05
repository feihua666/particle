/**
 * logo text 文本
 */
import {ref} from '@nm/vue'
import {defineStore} from '@nm/pinia'
import {get, getRaw, setRaw} from '../tools/StorageTools'

export interface LogoTextStore{
    logoText: string,
}

export const useLogoTextStore = defineStore<LogoTextStore>('logoText', () => {

    const logoTextLocalKey = 'logoTextLocalKey'

    
    const logoText = ref('')

    // 改变是否登录的方法
    function changeLogoText(logoTextValue):void {
        logoText.value = logoTextValue
        setRaw(logoTextLocalKey,logoText.value)
    }

    // 一般从 localStorage 获取
    function loadFromLocal(){
        let logoTextLocal = getRaw(logoTextLocalKey)
        changeLogoText(logoTextLocal)
    }

    return {logoText, changeLogoText, loadFromLocal}
})
