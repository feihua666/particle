const adminComponentDependencyAdminRoutes = [
    {
        path: '/admin/adminComponentDependencyManagePage',
        component: () => import('./AdminComponentDependencyManagePage.vue'),
        meta: {
            root: true,
            code:'adminAdminComponentDependencyManagePage',
            name: '组件依赖关系管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/adminComponentDependencyManageAdd',
                component: () => import('./AdminComponentDependencyManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAdminComponentDependencyManageAdd',
                    name: '组件依赖关系添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAdminComponentDependencyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAdminComponentDependencyManageAdd'
                    }
                }
            },

            {
                path: '/admin/adminComponentDependencyManageUpdate',
                component: () => import('./AdminComponentDependencyManageUpdatePage.vue'),
                props: route => ({ adminComponentDependencyId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminAdminComponentDependencyManageUpdate',
                    name: '组件依赖关系修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAdminComponentDependencyManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAdminComponentDependencyManageUpdate'
                    }
                }
            },
            {
                path: '/admin/adminComponentDependencyManageDependComponentAssignComponent',
                component: () => import('./AdminComponentDependencyManageDependComponentAssignComponentPage.vue'),
                props: route => ({ dependComponentId: route.query.dependComponentId, dependComponentName: route.query.dependComponentName }),
                meta: {
                    showInDrawer: true,
                    code:'adminComponentDependencyManageDependComponentAssignComponent',
                    name: '依赖组件分配源组件',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/adminComponentDependencyManageComponentAssignDependComponent',
                component: () => import('./AdminComponentDependencyManageComponentAssignDependComponentPage.vue'),
                props: route => ({ componentId: route.query.componentId, componentName: route.query.componentName }),
                meta: {
                    showInDrawer: true,
                    code:'adminComponentDependencyManageComponentAssignDependComponent',
                    name: '源组件分配依赖组件',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/adminComponentDependencyManageDeleteByDependComponentId',
                component: () => import('./AdminComponentDependencyManageDeleteByDependComponentIdPage.vue'),
                props: route => ({ dependComponentId: route.query.dependComponentId, dependComponentName: route.query.dependComponentName }),
                meta: {
                    showInDrawer: true,
                    code:'adminComponentDependencyManageDeleteByDependComponentId',
                    name: '清空依赖组件源组件',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
            {
                path: '/admin/adminComponentDependencyManageDeleteByComponentId',
                component: () => import('./AdminComponentDependencyManageDeleteByComponentIdPage.vue'),
                props: route => ({ componentId: route.query.componentId, componentName: route.query.componentName }),
                meta: {
                    showInDrawer: true,
                    code:'adminComponentDependencyManageDeleteByComponentId',
                    name: '清空源组件依赖组件',
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '.pt-route-view-popover-drawer-footer'
                    }
                }
            },
        ]
    },
]
export default adminComponentDependencyAdminRoutes
