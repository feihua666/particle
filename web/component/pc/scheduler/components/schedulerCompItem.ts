import {list as schedulerTempTaskListApi} from "../api/temptask/admin/schedulerTempTaskAdminApi";

export const useSelectSchedulerTempTaskCompItem = ({required= false,tips = ''})=>{
    return     {
        field: {
            name: 'schedulerTempTaskId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '临时任务',
                required: required,
                tips: tips || undefined
            },
            compProps: {
                dataMethod: schedulerTempTaskListApi
            }
        }
    }
}