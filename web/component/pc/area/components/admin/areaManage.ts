import {treeQueryComps} from "../../../treeQueryComps";
import {useCascaderAreaCompItem} from "../areaCompItem";

export const pageFormItems = [
    {
        field: {
            name: 'code'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '编码'
            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    {
        field: {
            name: 'name'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '名称'
            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    useCascaderAreaCompItem({}),
    ...treeQueryComps
]

export const userAddPageFormItems = ({locationGeoMapDialogRef})=>{
    return [
        {
            field: {
                name: 'code',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '编码',
                    required: true,
                    title: '编码全局唯一，用来唯一标识一个区域'
                },
                compProps: {
                    clearable: true,
                    placeholder: '编码唯一如：110',
                }
            }
        },
        {
            field: {
                name: 'name'
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '名称',
                    required: true
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'nameSimple'
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '简称'
                },
                compProps: {
                    clearable: true
                }
            }
        },
        {
            field: {
                name: 'typeDictId'
            },
            element: {
                comp: 'PtDictFrontSelect',
                formItemProps: {
                    label: '类型',
                    required: true
                },
                compProps: {
                    clearable: true,
                    // 字典查询
                    dictParam: {groupCode: 'area_type'}
                }
            }
        },
        {
            field: {
                name: 'longitude'
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '经度'
                },
                compProps: {
                    clearable: true,
                    placeholder: '百度地图坐标'
                }
            }
        },
        {
            field: {
                name: 'latitude'
            },
            element: {
                comp: 'PtInput',
                formItemProps: {
                    label: '纬度'
                },
                compProps: {
                    clearable: true,
                    placeholder: '百度地图坐标',
                    appendComp: {
                        comp: 'PtButton',
                        compProps: {
                            icon: 'Location',
                            method: ()=> {
                                locationGeoMapDialogRef.value.open()
                            }
                        }
                    }
                }
            }
        },
        useCascaderAreaCompItem({}),
        {
            field: {
                name: 'seq',
                value: 10
            },
            element: {
                comp: 'el-input-number',
                formItemProps: {
                    label: '排序'
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'remark'
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '描述'
                },
                compProps: {
                    clearable: true,
                }
            }
        },
    ]
}

// 更新和添加一致
export const useUpdatePageFormItems = userAddPageFormItems