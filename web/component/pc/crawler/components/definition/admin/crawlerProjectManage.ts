import {useRemoteSelectUserCompItem} from "../../../../user/components/userCompItem.ts";

export const pageFormItems = [
  {
    field: {
      name: 'name',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '项目名称',

      },
      compProps: {
        clearable: true,
        placeholder: '左前缀匹配'
      }
    }
  },
  useRemoteSelectUserCompItem({
    props: {},
    fieldName: 'userId',
    propUserIdFieldName: 'userId',
    propUserNicknameFieldName: 'userNickname',
    label: '归属用户'
  }),
  {
    field: {
      name: 'isPublic',
    },
    element: {
      comp: 'PtSelect',
      formItemProps: {
        label: '是否公开',
      },
      compProps: {
        dataMethod: () => {
          return {
            data: [
              {id: 'true',name: '公开'},
              {id: 'false',name: '用户私有'},
            ]
          }
        }
      }
    }
  },
]
export const addPageFormItems = [


  {
    field: {
      name: 'name',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '项目名称',
        required: true,
        tips: '项目标识，一个字符串名称，一般用于肉眼可识别',
      },
      compProps: {
        clearable: true,
      }
    }
  },

  {
    field: {
      name: 'remark',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '描述',
        tips: '写点什么'

      },
      compProps: {
        clearable: true,
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
        label: '配置参数json',
        tips: '以json的形式配置参数，以项目维度配置项目级别的全局参数',
        displayBlock: true,
      },
      compProps: {
        type: 'textarea',
        rows: 10,
        clearable: true,
      }
    }
  },


]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

