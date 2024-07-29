
const UserRoutes = [
    {
        path: '/front/stringReplaceToolPage',
        component: () => import('./views/front/StringReplaceToolPage.vue'),
        meta: {
            root: true,
            code:'stringReplaceToolPage',
            name: '字符串替换',
            keepAlive: true
        },
    },
    {
        path: '/front/cronToolPage',
        component: () => import('./components/front/Cron.vue'),
        meta: {
            root: true,
            code:'cronToolPage',
            name: 'cron表达式生成器',
            keepAlive: true
        },
    },
    {
        path: '/front/formDesignerPage',
        component: () => import('./views/front/FormDesignerPage.vue'),
        meta: {
            root: true,
            code:'formDesignerPage',
            name: '表单设计器',
            keepAlive: true
        },
    },
    {
        path: '/front/jsonObjToJsonString',
        component: () => import('./views/front/JsonObjToJsonStringPage.vue'),
        meta: {
            root: true,
            code:'jsonObjToJsonString',
            name: 'json对象转json字符串',
            keepAlive: true
        },
    },
    {
        path: '/front/dictEnumGen',
        component: () => import('./views/front/DictEnumGenPage.vue'),
        meta: {
            root: true,
            code:'dictEnumGen',
            name: '根据字典生成枚举类',
            keepAlive: true
        },
    },
    {
        path: '/front/AddFieldPage',
        component: () => import('./views/front/AddFieldPage.vue'),
        meta: {
            root: true,
            code:'AddFieldPage',
            name: '添加字段',
            keepAlive: true
        },
    },
    {
        path: '/front/BatchGenIdsPage',
        component: () => import('./views/front/BatchGenIdsPage.vue'),
        meta: {
            root: true,
            code:'BatchGenIdsPage',
            name: '批量生成id',
            keepAlive: true
        },
    },
    {
        path: '/front/BatchReplateSqlInsertIdsPage',
        component: () => import('./views/front/BatchReplateSqlInsertIdsPage.vue'),
        meta: {
            root: true,
            code:'BatchReplateSqlInsertIdsPage',
            name: '批量替换sql插入语句id',
            keepAlive: true
        },
    },
    {
        path: '/front/StringMultipleLineCompareToolPage',
        component: () => import('./views/front/StringMultipleLineCompareToolPage.vue'),
        meta: {
            root: true,
            code:'StringMultipleLineCompareToolPage',
            name: '多行文本比较处理',
            keepAlive: true
        },
    },
]
export default UserRoutes