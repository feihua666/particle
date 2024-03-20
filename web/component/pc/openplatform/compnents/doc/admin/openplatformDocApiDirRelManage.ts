import {
  useCascaderOpenplatformDocDirCompItem,
  useSelectOpenplatformDocApiCompItem
} from "../../openplatformDocCompItem";

export const pageFormItems = [
  useSelectOpenplatformDocApiCompItem({}),

  useCascaderOpenplatformDocDirCompItem({fieldName: 'openplatformDocDirId',label: '接口目录'}),

]
export const addPageFormItems = [

  useSelectOpenplatformDocApiCompItem({required: true}),

  useCascaderOpenplatformDocDirCompItem({fieldName: 'openplatformDocDirId',required: true,label: '接口目录'}),

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

