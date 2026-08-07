import TestMap from "./TestMap.vue";
import TestDraggable from "./TestDraggable.vue";
import TestTimePicker from "./TestTimePicker.vue";
import TestAceEditor from "./TestAceEditor.vue";
import TestCompAdapter from "./TestCompAdapter.vue";
import TestTinymceEditor from "./TestTinymceEditor.vue";
import TestRealtimeRenderMarkdown from "./TestRealtimeRenderMarkdown.vue";
import TestG6Graph from "./TestG6Graph.vue";
import TestParticle from "./TestParticle.vue";
import TestVueFlowGraph from "./TestVueFlowGraph.vue";
import TestWorkflowEditor from "./TestWorkflowEditor.vue";

let TestRoutes = [
    {
        path: '/testMap',
        name: 'testMap',
        component: TestMap,
        meta: {
            root: true,
            code: 'testMap',
            name: '测试地图',
            keepAlive: true
        }
    },
    {
        path: '/testDraggable',
        name: 'testDraggable',
        component: TestDraggable,
        meta: {
            root: true,
            code: 'testDraggable',
            name: '测试拖拽',
            keepAlive: true
        }
    },
    {
        path: '/testTimePicker',
        name: 'testTimePicker',
        component: TestTimePicker,
        meta: {
            root: true,
            code: 'testTimePicker',
            name: '测试时间选择器',
            keepAlive: true
        }
    },
    {
        path: '/testAceEditor',
        name: 'testAceEditor',
        component: TestAceEditor,
        meta: {
            root: true,
            code: 'testAceEditor',
            name: '测试Ace编辑器',
            keepAlive: true
        }
    },
    {
        path: '/testCompAdapter',
        name: 'testCompAdapter',
        component: TestCompAdapter,
        meta: {
            root: true,
            code: 'testCompAdapter',
            name: '测试组件适配器',
            keepAlive: true
        }
    },
    {
        path: '/testTinymceEditor',
        name: 'testTinymceEditor',
        component: TestTinymceEditor,
        meta: {
            root: true,
            code: 'testTinymceEditor',
            name: '测试TinyMCE编辑器',
            keepAlive: true
        }
    },
    {
        path: '/testRealtimeRenderMarkdown',
        name: 'testRealtimeRenderMarkdown',
        component: TestRealtimeRenderMarkdown,
        meta: {
            root: true,
            code: 'testRealtimeRenderMarkdown',
            name: '测试实时渲染Markdown',
            keepAlive: true
        }
    },
    {
        path: '/testG6Graph',
        name: 'testG6Graph',
        component: TestG6Graph,
        meta: {
            root: true,
            code: 'testG6Graph',
            name: '测试G6图',
            keepAlive: true
        }
    },
    {
        path: '/testParticle',
        name: 'testParticle',
        component: TestParticle,
        meta: {
            root: true,
            code: 'testParticle',
            name: '测试 particle 自定义的组件',
            keepAlive: true
        }
    },
    {
        path: '/testVueFlowGraph',
        name: 'testVueFlowGraph',
        component: TestVueFlowGraph,
        meta: {
            root: true,
            code: 'testVueFlowGraph',
            name: '测试 vueFlow 图编辑',
            keepAlive: true
        }
    },
    {
        path: '/testWorkflowEditor',
        name: 'testWorkflowEditor',
        component: TestWorkflowEditor,
        meta: {
            root: true,
            code: 'testWorkflowEditor',
            name: '测试 partile 工作流编辑器',
            keepAlive: true
        }
    },
]
export default TestRoutes
