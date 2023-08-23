import {useSelectDataqueryProviderCompItem} from "../../../../dataquery/compnents/dataqueryProviderCompItem";

export const pageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '供应商名称',
            
          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
  useSelectDataqueryProviderCompItem({}),
]
export const addPageFormItems = [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            required: true,
            tips: '一个标识供应商的编码，主要用于程序判断以确定供应商'
          },
          compProps: {
            clearable: true,
            placeholder: '编码，唯一'
          }
        }
      },


      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '供应商名称',
            required: true,
          },
          compProps: {
            clearable: true,
          }
        }
      },


  useSelectDataqueryProviderCompItem({tips: '如果该供应商是通过数据查询服务处理的，可以绑定关系'}),


      {
        field: {
          name: 'remark',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '描述',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

