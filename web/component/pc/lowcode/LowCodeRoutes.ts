
const LowCodeRoutes = [
    {
        path: '/admin/lowCodeDataSourceManagePage',
        component: () => import('./views/generator/admin/LowCodeDataSourceManagePage.vue'),
        meta: {
            root: true,
            code:'adminLowCodeDataSourceManagePage',
            name: '低代码数据源管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/lowCodeDataSourceManageAdd',
                component: () => import('./views/generator/admin/LowCodeDataSourceManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminLowCodeDataSourceManageAdd',
                    name: '低代码数据源添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowCodeDataSourceManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowCodeDataSourceManageAdd'
                    }
                }
            },

            {
                path: '/admin/lowCodeDataSourceManageUpdate',
                component: () => import('./views/generator/admin/LowCodeDataSourceManageUpdatePage.vue'),
                props: route => ({ lowCodeDataSourceId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowCodeDataSourceManageUpdate',
                    name: '低代码数据源修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowCodeDataSourceManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowCodeDataSourceManageUpdate'
                    }
                }
            },
        ]
    },
    {
        path: '/admin/lowCodeModelManagePage',
        component: () => import('./views/generator/admin/LowCodeModelManagePage.vue'),
        meta: {
            root: true,
            code:'adminLowCodeModelManagePage',
            name: '低代码模型管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/lowCodeModelManageAdd',
                component: () => import('./views/generator/admin/LowCodeModelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminLowCodeModelManageAdd',
                    name: '低代码模型添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowCodeModelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowCodeModelManageAdd'
                    }
                }
            },

            {
                path: '/admin/lowCodeModelManageUpdate',
                component: () => import('./views/generator/admin/LowCodeModelManageUpdatePage.vue'),
                props: route => ({ lowCodeModelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowCodeModelManageUpdate',
                    name: '低代码模型修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowCodeModelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowCodeModelManageUpdate'
                    }
                }
            },            {
                path: '/admin/lowCodeModelManageLoadModelItemByModelAndDataSource',
                component: () => import('./views/generator/admin/LowCodeModelItemLoadByModelAndDataSourcePage.vue'),
                props: route => ({ lowCodeModelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'lowCodeModelManageLoadModelItemByModelAndDataSource',
                    name: '根据模型装载模型项',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'lowCodeModelManageLoadModelItemByModelAndDataSource'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#lowCodeModelManageLoadModelItemByModelAndDataSource'
                    }
                }
            },
        ]
    },

    {
        path: '/admin/lowCodeModelItemManagePage',
        component: () => import('./views/generator/admin/LowCodeModelItemManagePage.vue'),
        meta: {
            root: true,
            code:'adminLowCodeModelItemManagePage',
            name: '低代码模型项管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/lowCodeModelItemManageAdd',
                component: () => import('./views/generator/admin/LowCodeModelItemManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminLowCodeModelItemManageAdd',
                    name: '低代码模型项添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowCodeModelItemManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowCodeModelItemManageAdd'
                    }
                }
            },

            {
                path: '/admin/lowCodeModelItemManageUpdate',
                component: () => import('./views/generator/admin/LowCodeModelItemManageUpdatePage.vue'),
                props: route => ({ lowCodeModelItemId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminLowCodeModelItemManageUpdate',
                    name: '低代码模型项修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminLowCodeModelItemManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminLowCodeModelItemManageUpdate'
                    }
                }
            },
        ]
    },
]
export default LowCodeRoutes