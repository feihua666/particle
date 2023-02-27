
const LowcodeRoutes = [
    {
        path: '/admin/lowcodeDataSourceManagePage',
        component: () => import('./views/generator/admin/LowcodeDataSourceManagePage.vue'),
        meta: {
            root: true,
            code:'adminLowcodeDataSourceManagePage',
            name: '低代码数据源管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/lowcodeDataSourceManageAdd',
                component: () => import('./views/generator/admin/LowcodeDataSourceManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeDataSourceManageAdd',
                    name: '低代码数据源添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeDataSourceManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeDataSourceManageAdd'
                    }
                }
            },

            {
                path: '/admin/lowcodeDataSourceManageUpdate',
                component: () => import('./views/generator/admin/LowcodeDataSourceManageUpdatePage.vue'),
                props: route => ({ lowcodeDataSourceId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeDataSourceManageUpdate',
                    name: '低代码数据源修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeDataSourceManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeDataSourceManageUpdate'
                    }
                }
            },
        ]
    },
    {
        path: '/admin/lowcodeModelManagePage',
        component: () => import('./views/generator/admin/LowcodeModelManagePage.vue'),
        meta: {
            root: true,
            code:'adminLowcodeModelManagePage',
            name: '低代码模型管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/lowcodeModelManageAdd',
                component: () => import('./views/generator/admin/LowcodeModelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeModelManageAdd',
                    name: '低代码模型添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeModelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeModelManageAdd'
                    }
                }
            },

            {
                path: '/admin/lowcodeModelManageUpdate',
                component: () => import('./views/generator/admin/LowcodeModelManageUpdatePage.vue'),
                props: route => ({ lowcodeModelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeModelManageUpdate',
                    name: '低代码模型修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeModelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeModelManageUpdate'
                    }
                }
            },
            {
                path: '/admin/lowcodeModelManageLoadModelItemByModelAndDataSource',
                component: () => import('./views/generator/admin/LowcodeModelItemLoadByModelAndDataSourcePage.vue'),
                props: route => ({ lowcodeModelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'lowcodeModelManageLoadModelItemByModelAndDataSource',
                    name: '加载载模型项',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'lowcodeModelManageLoadModelItemByModelAndDataSource'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#lowcodeModelManageLoadModelItemByModelAndDataSource'
                    }
                }
            },
        ]
    },

    {
        path: '/admin/lowcodeModelItemManagePage',
        component: () => import('./views/generator/admin/LowcodeModelItemManagePage.vue'),
        props: route => ({ lowcodeModelId: route.query.lowcodeModelId }),
        meta: {
            root: true,
            code:'adminLowcodeModelItemManagePage',
            name: '低代码模型项管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/lowcodeModelItemManageAdd',
                component: () => import('./views/generator/admin/LowcodeModelItemManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeModelItemManageAdd',
                    name: '低代码模型项添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeModelItemManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeModelItemManageAdd'
                    }
                }
            },

            {
                path: '/admin/lowcodeModelItemManageUpdate',
                component: () => import('./views/generator/admin/LowcodeModelItemManageUpdatePage.vue'),
                props: route => ({ lowcodeModelItemId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeModelItemManageUpdate',
                    name: '低代码模型项修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeModelItemManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeModelItemManageUpdate'
                    }
                }
            },
        ]
    },
    {
        path: '/admin/lowcodeSegmentTemplateManagePage',
        component: () => import('./views/generator/admin/LowcodeSegmentTemplateManagePage.vue'),
        meta: {
            root: true,
            code:'adminLowcodeSegmentTemplateManagePage',
            name: '低代码片段模板管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/lowcodeSegmentTemplateManageAdd',
                component: () => import('./views/generator/admin/LowcodeSegmentTemplateManageAddPage.vue'),
                props: route => ({ parentLowcodeSegmentTemplateId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeSegmentTemplateManageAdd',
                    name: '低代码片段模板添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeSegmentTemplateManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeSegmentTemplateManageAdd'
                    }
                }
            },

            {
                path: '/admin/lowcodeSegmentTemplateManageUpdate',
                component: () => import('./views/generator/admin/LowcodeSegmentTemplateManageUpdatePage.vue'),
                props: route => ({ lowcodeSegmentTemplateId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeSegmentTemplateManageUpdate',
                    name: '低代码片段模板修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeSegmentTemplateManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeSegmentTemplateManageUpdate'
                    }
                }
            },
            {
                path: '/admin/lowcodeSegmentTemplateManageRenderTest',
                component: () => import('./views/generator/admin/LowcodeSegmentTemplateManageRenderTestPage.vue'),
                props: route => ({ lowcodeSegmentTemplateId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeSegmentTemplateManageRenderTest',
                    name: '低代码片段模板渲染测试',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeSegmentTemplateManageRenderTest'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeSegmentTemplateManageRenderTest'
                    }
                }
            },
            {
                path: '/admin/lowcodeSegmentTemplateManageCopy',
                component: () => import('./views/generator/admin/LowcodeSegmentTemplateManageCopyPage.vue'),
                props: route => ({ lowcodeSegmentTemplateId: route.query.id, parentLowcodeSegmentTemplateId: route.query.parentId }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeSegmentTemplateManageCopy',
                    name: '低代码片段模板复制',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeSegmentTemplateManageCopy'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeSegmentTemplateManageCopy'
                    }
                }
            },
        ]
    },
    {
        path: '/admin/lowcodeSegmentGenManagePage',
        component: () => import('./views/generator/admin/LowcodeSegmentGenManagePage.vue'),
        meta: {
            root: true,
            code:'adminLowcodeSegmentGenManagePage',
            name: '低代码生成管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/lowcodeSegmentGenManageAdd',
                component: () => import('./views/generator/admin/LowcodeSegmentGenManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeSegmentGenManageAdd',
                    name: '低代码生成添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeSegmentGenManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeSegmentGenManageAdd'
                    }
                }
            },

            {
                path: '/admin/lowcodeSegmentGenManageUpdate',
                component: () => import('./views/generator/admin/LowcodeSegmentGenManageUpdatePage.vue'),
                props: route => ({ lowcodeSegmentGenId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeSegmentGenManageUpdate',
                    name: '低代码生成修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeSegmentGenManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeSegmentGenManageUpdate'
                    }
                }
            },
            {
                path: '/admin/lowcodeSegmentGenModuleDesignAndGenerate',
                component: () => import('./views/generator/admin/segmentgen/designandgenerate/LowcodeSegmentGenModuleDesignAndGeneratePage.vue'),
                props: route => ({ lowcodeSegmentGenId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeSegmentGenModuleDesignAndGenerate',
                    name: '模块设计与生成',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeSegmentGenModuleDesignAndGenerate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeSegmentGenModuleDesignAndGenerate'
                    }
                }
            },
            {
                path: '/admin/lowcodeSegmentGenModelServiceDesignAndGenerate',
                component: () => import('./views/generator/admin/segmentgen/designandgenerate/LowcodeSegmentGenModelServiceDesignAndGeneratePage.vue'),
                props: route => ({ lowcodeSegmentGenId: route.query.id ,refrenceSegmentGenId: route.query.refrenceSegmentGenId}),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeSegmentGenModelServiceDesignAndGenerate',
                    name: '模型服务设计与生成',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeSegmentGenModelServiceDesignAndGenerate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeSegmentGenModelServiceDesignAndGenerate'
                    }
                }
            },
            {
                path: '/admin/lowcodeSegmentGenGenericDesignAndGenerate',
                component: () => import('./views/generator/admin/segmentgen/designandgenerate/LowcodeSegmentGenGenericDesignAndGeneratePage.vue'),
                props: route => ({ lowcodeSegmentGenId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowcodeSegmentGenGenericDesignAndGenerate',
                    name: '一般性通用设计与生成',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowcodeSegmentGenGenericDesignAndGenerate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowcodeSegmentGenGenericDesignAndGenerate'
                    }
                }
            },
        ]
    },
    {
        path: '/admin/lowcodeModelItemDesignManagePage',
        component: () => import('./views/generator/admin/LowcodeModelItemDesignManagePage.vue'),
        props: route => ({ lowcodeModelId: route.query.lowcodeModelId }),
        meta: {
            root: true,
            code:'adminLowcodeModelItemDesignManagePage',
            name: '低代码模型项设计',
            keepAlive: true
        },
    },
]
export default LowcodeRoutes