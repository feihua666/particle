import {useSelectAppForCurrentUserCompItem} from "../../openplatformAppCompItem";
import {useCascaderOpenapiByOpenplatformAppIdCompItem} from "../../openplatformOpenapiCompItem";
import {useSelectCrmCustomerCompItem} from "../../../../crm/components/crmCompItem";

export const pageFormItems = [
  useSelectAppForCurrentUserCompItem({
    label: '应用',
    valueChange: ({form,newValue}) => {
      if (!newValue) {
        form.openplatformOpenapiId = null
      }
    }
  }),
  useCascaderOpenapiByOpenplatformAppIdCompItem({
    fieldName: 'openplatformOpenapiId',
    label: '开放接口',
    disableGroup: true
  }),
    useSelectCrmCustomerCompItem({fieldName: 'customerId',label: '客户'}),
      {
        field: {
          name: 'executeStatusDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '执行状态',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'open_platform_batch_query_execute_status'}
          }
        }
      },


      {
        field: {
          name: 'traceId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '追踪id',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },
]

export const tableColumns = [
      {
        prop: 'openplatformAppName',
        label: '应用名称',
      },
      {
        prop: 'openplatformOpenapiName',
        label: '接口名称',
      },
      {
        prop: 'customerName',
        label: '客户名称',
      },
      {
        prop: 'executeStatusDictName',
        label: '执行状态',
      },
      {
        prop: 'successCount',
        label: '成功条数',
      },
      {
        prop: 'failCount',
        label: '失败条数',
      },
      {
        prop: 'totalCount',
        label: '总条数',
      },
      {
        prop: 'userNickname',
        label: '用户昵称',
      },
      {
        prop: 'queryAt',
        label: '查询时间',
      },
      {
        prop: 'traceId',
        label: '追踪id',
      },
    {
        prop: 'uploadFileName',
        label: '上传文件名',
    },
      {
        prop: 'remark',
        label: '描述',
      },
    ]