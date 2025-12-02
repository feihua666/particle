export const pageFormItems = [
    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '表名称',

            },
            compProps: {
                clearable: true,
                placeholder: '模糊匹配',
            }
        }
    },
    {
        field: {
            name: 'comment',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '表注释',
            },
            compProps: {
                clearable: true,
                placeholder: '模糊匹配',
            }
        }
    },

]
// isForAdd 表示是否为添加表单，否则为修改表单
export const useAddPageFormItems = ({isForAdd = true}) => {
    return [

        {
            field: {
                name: 'name',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '表名称',
                    required: true,
                    tips: '请遵循数据库的表名命令规则如：component_user,建议使用 dynamic_table_data_ 前缀',
                },
                compProps: {
                    clearable: true,
                    disabled: !isForAdd,
                }
            }
        },


        {
            field: {
                name: 'comment',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '表注释',
                    required: true,
                    tips: '请填写表注释，如：用户表',
                },
                compProps: {
                    clearable: true,
                }
            }
        },

    ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

export const importDataFormItems = [

    {
        field: {
            name: 'file',
            value: [],
        },
        element: {
            comp: 'PtUpload',
            formItemProps: {
                label: '上传文件',
                required: true,
                displayBlock: true,
            },
            compProps: {
                drag: true,
                autoUpload: false,
                limit: 1,
                dragTip: '将文件拖到此处或<em>点击上传</em>',
                tipTxt: '只能上传一个文件，请严格按照下载的模板进行填写，并保证第一个sheet为导入数据',
                onExceed: (files,uploadFiles)=>{
                    alert('最多只能选择一个文件,如要重新选择，请先删除之前的文件','error')
                }
            }
        }
    },

]
