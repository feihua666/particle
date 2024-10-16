import {useSelectAppCompItem} from "../../openplatformAppCompItem";

export const pageFormItems = [

  useSelectAppCompItem({}),
      {
        field: {
          name: 'limitTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '限制方式',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'open_platform_limit_rule_type'}
          }
        }
      },

]
export const addPageFormItems = [

  useSelectAppCompItem({required: true}),
  {
    field: {
      name: 'limitTypeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '限制方式',
        required: true,
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'open_platform_limit_rule_type'}
      }
    }
  },


  {
    field: {
      name: 'limitCount',
      value: 0,
    },
    element: {
      comp: 'el-input-number',
      formItemProps: {
        label: '限制条数',
        required: true,
        tips: '仅限 限制方式 选择 按调用次数限制、按调用计费次数限制 时有效。注意：填 0 表示额度为0，将不能继续调用'
      },
      compProps: {
      }
    }
  },


  {
    field: {
      name: 'limitFee',
      value: 0,
    },
    element: {
      comp: 'el-input-number',
      formItemProps: {
        label: '限制金额费用（分）',
        required: true,
        tips: '仅限 限制方式 选择 按费用限制 时有效。注意：填 0 表示额度为0，将不能继续调用'
      },
      compProps: {
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
            
          },
          compProps: {
            clearable: true,
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

