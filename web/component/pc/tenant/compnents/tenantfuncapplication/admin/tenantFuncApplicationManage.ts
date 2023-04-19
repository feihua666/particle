import {nextTick} from 'vue'

import {list as funcApplicationListApi} from "../../../../func/api/application/admin/funcApplicationAdminApi";
import {useSelectTenantCompItem} from "../../tenantCompItem";

export const pageFormItems = [
  {
    field: {
      name: 'funcApplicationId',
    },
    element: {
      comp: 'PtCascader',
      formItemProps: {
        label: '功能应用',
      },
      compProps: {
        // 加载数据
        dataMethod: () => { return funcApplicationListApi({})},
        dataMethodResultHandleConvertToTree: true,
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
  {
    field: {
      name: 'funcApplicationId',
      valueChange: ({form,formData})=>{
        nextTick(()=>{
          if(formData.funcApplicationId){
            form.name = formData.funcApplicationId.name
          }else {
            form.name = ''
          }
        })
      }
    },
    element: {
      comp: 'PtCascader',
      formItemProps: {
        label: '功能应用',
        required: true
      },
      compProps: {
        // 加载数据
        dataMethod: () => { return funcApplicationListApi({})},
        dataMethodResultHandleConvertToTree: true,
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
        label: '名称',

      },
      compProps: {
        clearable: true,
      }
    }
  },
  useSelectTenantCompItem({required: true}),


  {
    field: {
      name: 'applicationTheme',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '应用主题',

      },
      compProps: {
        clearable: true,
      }
    }
  },


  {
    field: {
      name: 'applicationDefaultRoute',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '默认路由',
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'applicationLogoUrl',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '应用logo',
      },
      compProps: {
        clearable: true,
        placeholder: '以 http 开头'
      }
    }
  },

  {
    field: {
      name: 'applicationIconUrl',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '应用图标',
      },
      compProps: {
        clearable: true,
        placeholder: '以 http 开头'
      }
    }
  },

  {
    field: {
      name: 'configJson',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '额外配置json',
        displayBlock: true
      },
      compProps: {
        type: 'textarea',
        rows: 15
      }
    }
  },

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

