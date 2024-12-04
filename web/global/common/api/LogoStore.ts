/**
 * logo text 文本
 */
import {ref} from '@nm/vue'
import {defineStore} from '@nm/pinia'
import {getRaw, setRaw} from '../tools/StorageTools'

export interface LogoStore {
    logoText: string,
    logoImgUrl: string,
}

export const useLogoStore = defineStore<LogoStore>('logo', () => {

    const logoTextLocalKey = 'logoTextLocalKey'
    const logoImgLocalKey = 'logoImgLocalKey'


    const logo = ref({
        logoText: null,
        logoImgUrl: null
    })

    // 改变logo文本的方法
    function changeLogoText(logoTextValue):void {
        logo.value.logoText = logoTextValue
        setRaw(logoTextLocalKey,logo.value.logoText)
    }

    // 改变logo图片地址的方法
    function changeLogoImgUrl(logoImgUrlValue):void {
        logo.value.logoImgUrl = logoImgUrlValue
        setRaw(logoImgLocalKey,logo.value.logoImgUrl)
    }

    // 一般从 localStorage 获取
    function loadFromLocal(){
        let logoTextLocal = getRaw(logoTextLocalKey)
        changeLogoText(logoTextLocal)

        let logoImgUrlLocal = getRaw(logoImgLocalKey)
        changeLogoImgUrl(logoImgUrlLocal)
    }

    return {logo, changeLogoText,changeLogoImgUrl, loadFromLocal}
})
