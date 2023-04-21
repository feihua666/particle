import {nextTick} from 'vue'
import {useCascaderFuncCompItem} from "../../../../func/compnents/funcCompItem";
import {useSelectTenantCompItem} from "../../tenantCompItem";

export const pageFormItems = [
  useCascaderFuncCompItem({fieldName: 'funcId',label: '功能菜单'}),
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
  useSelectTenantCompItem({required: false}),

]
export const addPageFormItems = [
  useCascaderFuncCompItem({fieldName: 'funcId',label: '功能菜单',valueChange: ({form,formData})=>{
      nextTick(()=>{
        if(formData.funcId){
          form.name = formData.funcId.name
        }else {
          form.name = ''
        }
      })
    }}),
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
  useSelectTenantCompItem({required: true}),

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

