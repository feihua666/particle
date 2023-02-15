
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
        ]
    },
]
export default LowcodeRoutes