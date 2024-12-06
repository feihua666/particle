<template>
    <el-breadcrumb separator="/" v-if="breadcrumbs.length > 0">
        <el-breadcrumb-item><el-icon><House /></el-icon></el-breadcrumb-item>
        <el-breadcrumb-item v-for="(item,index) in breadcrumbs" :key="index">
            <template v-if="index < breadcrumbs.length -1">
                <a :href="item.path" @click.prevent="navigate(item,index)">{{item.name}}</a>
            </template>
            <template v-else>{{item.name}}</template>
        </el-breadcrumb-item>
    </el-breadcrumb>
</template>

<script>
    export default {
        name: 'Breadcrumb',
        mounted() {
            this.logic(this.$route)
        },
        data(){
            return {
                // item={path:'',replace:Boolean,reset:Boolean,name:''}
                breadcrumbs:[]
            }
        },
        methods:{
            navigate(item){
                this.$route.meta.reset = !!item.reset
                this.$route.meta.replace = !!item.replace
                // 点击面包屑，设置目标路由缓存
                this.$route.meta.nextKeepalive = true
                if(item.replace){
                    this.$router.replace(item.path)
                }else{
                    this.$router.push(item.path)
                }
            },
            push(to){
                if(to.meta && to.meta.name && to.meta.breadcrumb !== false){
                    this.breadcrumbs.push(
                        {
                            path:to.fullPath,
                            name:to.meta.name,
                            replace: false,//点击面包屑的时候是路由push还是replace
                            reset:false// 面包屑数据是否清空重置，一般点击一个根路由时设置为重置
                        }
                    )
                }
            },
            logic(to,from){
                // 如果from 为空，mounted触发
                if(!from){
                    // 清空数组
                    this.breadcrumbs.splice(0)
                    this.push(to)
                }else {
                    //暂时没什么作用以后研究 todo
                    // 缓存判断
                    if(from.meta.nextKeepalive){
                        to.meta.keepalive = true
                        from.meta.nextKeepalive = false
                    }
                    //如果去向路由是面包屑中的路径，则将面包屑后面的数据清除
                    let isInclude = false
                    for(let i = 0;i < this.breadcrumbs.length; i++){
                        if(to.fullPath == this.breadcrumbs[i].path){
                            this.breadcrumbs.splice(i)
                            isInclude = true
                            break
                        }
                    }
                    if(!isInclude && to.meta.root){
                        this.breadcrumbs.splice(0)
                    }
                    this.push(to)
                }
            }
        },
        watch:{
            $route(to,from){
                this.logic(to,from)
            }
        }
    }
</script>

