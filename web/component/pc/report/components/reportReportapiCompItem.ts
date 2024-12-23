import {list as reportReportapiListApi} from "../api/reportapi/admin/reportReportApiAdminApi.ts";

export const useCascaderReportReportapiCompItem = ({
                                               fieldName= 'parentId',
                                               required=false,label= '父级',
                                               disableGroup = false,tips = '',
                                               valueChange = () => {}
})=>{
    return         {
        field: {
            name: fieldName,
            valueChange
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required,
                tips: tips

            },
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: reportReportapiListApi,
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}
