import {useSelectSchedulerTempTaskCompItem} from "../../schedulerCompItem";

export const pageFormItems = [
  useSelectSchedulerTempTaskCompItem({}),
      {
        field: {
          name: 'executeStatusDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '临时任务运行状态',
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'scheduler_temp_task_run_record_status'}
          }
        }
      },

]
export const updatePageFormItems = [

      {
        field: {
          name: 'isAllowRunSwitch',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否允许运行开关',
            required: true,
            tips: '临时任务运行开关，可以通过编辑该配置，来停止任务，停止任务后不可恢复，如再次运行，将会新启动一个运行记录，请关注'
          },
          compProps: {
            inactiveText: '停止运行',
            activeText: '允许运行',
          }
        }
      },

]
