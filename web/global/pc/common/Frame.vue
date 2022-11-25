<template>
    <iframe ref="iframeFrame" :src="localUrl" class="st-width-100-pc st-border-none" :class="{'st-height-100-pc': !computeHeight}" :style="heightStyle"></iframe>
</template>

<script>
/**
 * iframe封装
 */
    export default {
        name: "Frame",
        props: {
            // 页面的地址
            url: {
                type: String,
                required: true
            },
            // 是否计算高度，计算高度是为了让内容的高度撇开iframe，全iframe的高度和内容一样高
            computeHeight:{
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                loading: false,
                localUrl: this.url,
                heightStyle: ''
            }
        },
        mounted(){
            this.initLoading(this.localUrl)
        },
        methods:{
            initLoading (url){
                if(!url){
                    return
                }
                let $this = this
                $this.loading = true
                $this.$emit('loading',$this.loading)
                let iframe = $this.$refs.iframeFrame
                if(iframe.attachEvent){ //IE
                    iframe.attachEvent("onreadystatechange", function() {
                        //此事件在内容没有被载入时候也会被触发，所以我们要判断状态
                        //有时候会比较怪异 readyState状态会跳过 complete 所以我们loaded状态也要判断
                        if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                            //代码能执行到这里说明已经载入成功完毕了
                            //要清除掉事件
                            iframe.detachEvent("onreadystatechange", arguments.callee);
                            //这里是回调函数
                            $this.loading = false
                            $this.$emit('loading',$this.loading)
                            $this.trySetIframeHeight(iframe,10)
                        }
                    });
                }else{
                    iframe.addEventListener("load", function() {
                        //代码能执行到这里说明已经载入成功完毕了
                        this.removeEventListener("load", arguments.call, false);
                        //这里是回调函数
                        $this.loading = false
                        $this.$emit('loading',$this.loading)
                        $this.trySetIframeHeight(iframe,10)
                    }, false);
                }
            },
            refresh(){
                if(!this.localUrl){
                    return
                }
                if(this.localUrl.indexOf('?') >= 0){
                    this.localUrl += '&nocaching=' + new Date().getTime()
                }else {
                    this.localUrl += '?nocaching=' + new Date().getTime()
                }
                this.initLoading(this.localUrl)
            },
            setIframeHeight(iframe) {
                if (iframe) {
                    let iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
                    if (iframeWin.document.body) {
                        iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
                        this.heightStyle = 'height:' + (iframe.height) + 'px'
                    }
                }
            },
            trySetIframeHeight(iframe,times){
                if (!this.computeHeight) {
                    return
                }
                this.setIframeHeight(iframe)
                if(times > 0){
                    setTimeout(()=>{
                        this.trySetIframeHeight(iframe,times - 1)
                    },1000)
                }

            }
        },
        watch:{
            url (url){
                this.localUrl = url
                this.initLoading(url)
            },
        }
    }
</script>

<style scoped>

</style>