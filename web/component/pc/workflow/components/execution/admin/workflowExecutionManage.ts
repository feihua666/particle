import {
  useSelectWorkflowDefinitionCompItem,
  useSelectWorkflowDefinitionHistoryCompItem, useSelectWorkflowProjectCompItem
} from "../../workflowCompItem.ts";

export const pageFormItems = [

  useSelectWorkflowProjectCompItem({}),
  useSelectWorkflowDefinitionCompItem({required: true}),
  useSelectWorkflowDefinitionHistoryCompItem({}),
      {
        field: {
          name: 'statusDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '执行状态',
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'workflow_execution_status'}
          }
        }
      },
      {
        field: {
          name: 'triggerTypeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '触发方式',

          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'workflow_execution_trigger_type'}
          }
        }
      },
      {
        field: {
          name: 'nodeId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '当前执行节点ID',

          },
          compProps: {
            clearable: true,
          }
        }
      },
]
