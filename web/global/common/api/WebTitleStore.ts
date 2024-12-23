/**
 * logo text 文本
 */
import {ref} from '@nm/vue'
import {defineStore} from '@nm/pinia'
import {getRaw, setRaw} from '../tools/StorageTools'

export interface WebTitleStore{
    webTitle: string,
}

export const useWebTitleStore = defineStore<WebTitleStore>('webTitle', () => {

    const webTitleLocalKey = 'webTitleLocalKey'


    const webTitle = ref('')

    // 改变是否登录的方法
    function changeWebTitle(webTitlevalue):void {
        webTitle.value = webTitlevalue
        setRaw(webTitleLocalKey,webTitle.value)
    }

    // 一般从 localStorage 获取
    function loadFromLocal(){
        let webTitleLocal = getRaw(webTitleLocalKey)
        changeWebTitle(webTitleLocal)
    }

    return {webTitle, changeWebTitle, loadFromLocal}
})
