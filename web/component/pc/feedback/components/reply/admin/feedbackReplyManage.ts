export const pageFormItems = [
      {
        field: {
          name: 'feedbackUserRateDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '用户评价',
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'feedback_user_rate'}
          }
        }
      },
]
export const addPageFormItems = [
      {
        field: {
          name: 'replyContent',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '回复内容',
            required: true,
            displayBlock: true,
          },
          compProps: {
            type: 'textarea',
            rows: 14,
            clearable: true,
          }
        }
      },
]

