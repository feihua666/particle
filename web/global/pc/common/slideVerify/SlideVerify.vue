<!-- 验证码前端组件，该代码目前已经写死依赖  后端组件captcha接口-->
<template>
    <div class="slider-verify" onselectstart="return false;">
        <div class="slider-image-box width-100-pc" v-show="showBgImage || showImage" :style="'height:' + this.bgImageHeight + 'px;'" @mouseover="()=>{showImage=true}" @mouseleave="()=>{showImage=false}">
            <!-- 图片加载遮蔽罩 -->
            <div v-if="sliderImageLoading" class="slider-image-loading-box flex-center-all"><div class="slider-image-loading"></div></div>
            <!-- 图片背景层 -->
            <img class="width-100-pc height-100-pc slider-bg-image" :src="bgImageSrc"/>
            <!-- 刷新按钮 -->
            <div v-if="showRefresh" @click="reset" class="slider-verify-refresh-icon pointer"></div>
            <!-- 滑块 -->
            <img :style="computedImageVerifyBlockStyle"  class="slider-verify-block" :src="blockImageSrc"/>
        </div>
        <!-- 滑块操作区域 -->
        <div class="slider-box">
            <div  :class="{'success': validSuccess,'slider-btn': true}" :style="sliderBtnStyleLeft" @mouseover="()=>{showImage=true}" @mouseleave="()=>{showImage=false}" @mousedown="$_moveStart" @dblclick="reset"></div>
            <div class="slider-hit-text" :class="{'error': validError}">{{validError? '验证失败' : hitText}}</div>
            <div class="slider-process" :class="{'error': validError}" :style="sliderProcessStyleWidth"></div>
        </div>
    </div>
</template>
<script>
    export default {
        name: 'SlideVerify',
        props: {
            hitText: {
                type: String,
                default: '拖动滑块验证'
            },
            showRefresh: {
                type: Boolean,
                default: true
            },
            bgImageHeight: {
                type: Number,
                default: 160
            },
            blockImageHeight: {
                type: Number,
                default: 45
            },
            blockImageWidth: {
                type: Number,
                default: 45
            },
            // 是否始终显示图片
            showBgImage:{
                type: Boolean,
                default: false
            },
            purpose: {
                type: String,
                default: '/login'
            }
        },
        computed: {
            // 滑块的左距离
            sliderBtnStyleLeft(){
                return 'left:' + this.moveOffsetX + 'px;'
            },
            // 滑动进度条的宽度
            sliderProcessStyleWidth(){
                return 'width:' + this.moveOffsetX + 'px;'
            },
            computedImageVerifyBlockStyle(){
                return {
                        width: this.blockImageWidth + 'px',
                        height: this.blockImageHeight + 'px',
                        top: this.blockPositionY + 'px',
                        left: this.moveOffsetX + 'px'
                    }
            }

        },
        data() {
            return {
                // 滑块左距离
                moveOffsetX: 0,
                // 滑块最大左距离，动态计算
                moveOffsetMaxX: 0,
                // 按下滑块鼠标位置X
                $_moveStartClientX: null,
                // 按下滑块鼠标位置Y
                $_moveStartClientY: null,
                // 遮罩层，加载
                sliderImageLoading: false,
                // 是否开始滑动，按下表示开始滑动，松开表示结束滑动
                isSlideStart: false,
                // 验证是否成功
                validSuccess: false,
                // 验证是否失败
                validError: false,

                showImage: false,

                // 宽度也是背景图上的宽度，动态计算
                sliderVerifyWidth: 0,
                // 滑块纵位置，后台获取
                blockPositionY: 0,
                // 背景图片
                bgImageSrc: null,
                // 滑块图片地址
                blockImageSrc: null,
                // 滑动开始时间
                slideMoveAt: null
            }
        },
        mounted() {
            this.$_init()
            this.$_initImage()
        },
        methods: {
            $_init(){
                this.sliderVerifyWidth = this.$el.offsetWidth
                // div.slider-box
                let divSliderBox = this.$el.querySelector('div.slider-box')

                this.moveOffsetMaxX = divSliderBox.offsetWidth - divSliderBox.querySelector('div.slider-btn').offsetWidth
                document.addEventListener('mousemove',this.$_move)
                document.addEventListener('mouseup',this.$_moveEnd)

            },
            $_initImage(){
                this.$_loadImage((data)=>{
                    this.blockPositionY = data.slideImageOffsetY
                    this.bgImageSrc = data.bgImageBase64Data
                    this.blockImageSrc = data.slideImageBase64Data
                })
            },
            $_moveStart(e){
                if(this.validSuccess){
                    return
                }
                if (!this.isSlideStart && this.moveOffsetX > 0) {
                    return
                }
                this.slideMoveAt = new Date()
                this.isSlideStart = true;
                this.$_moveStartClientX = e.clientX
            },
            $_move(e){
                if(!this.isSlideStart){
                    return
                }
                // 合理化位置
                let offset = e.clientX - this.$_moveStartClientX
                if (offset < 0) {
                    offset = 0
                }
                if (offset > this.moveOffsetMaxX) {
                    offset = this.moveOffsetMaxX
                }
                this.moveOffsetX = offset
            },
            $_moveEnd(e){
                if(this.validSuccess){
                    return
                }
                if(this.isSlideStart && this.moveOffsetX == 0){
                    return
                }
                this.isSlideStart = false
                let form = {
                    purpose: this.purpose,
                    slideImageOffsetY: this.blockPositionY,
                    slideImageOffsetX: this.moveOffsetX,
                    slideDuration: new Date().getTime() - this.slideMoveAt.getTime()
                }
                this.axios.post('/captcha/slide',form).then((res)=>{
                    this.validSuccess = true
                    this.$emit('success')
                }).catch(error=>{
                    this.validError = true
                    setTimeout(this.reset,500)
                })
            },
            reset(){
                this.isSlideStart = false
                this.validSuccess = false
                this.validError = false
                this.moveOffsetX = 0
                this.slideMoveAt = null
                this.$_initImage()
                this.$emit('refresh')
            },
            // 加载图片
            $_loadImage(callback) {
                this.sliderImageLoading = true
                this.axios.get('/captcha/slide',{params: {purpose: this.purpose}}).then(res => {
                    callback(res.data)
                }).catch((error)=>{
                    this.$message.error('验证码加载失败，请刷新再试')
                })
                    .finally(()=>{
                    this.sliderImageLoading = false
                })

            }
        },
        watch:{
        }
    }
</script>
<style scoped>
.slider-verify{
    /*width: 300px;*/
    height: 40px;
    background-color: #eee;
    position: relative;
}
/* 以下是图片相关 */
.slider-image-box{
    position: absolute;
    padding-bottom: 50px;
    bottom: 0px;
    left: 0px;
}
.slider-image-loading {
    width: 30px; /*先将loading区域变成正方形*/
    height: 30px;
    display: inline-block; /*将loading区域变成行内元素，防止旋转的时候，100%宽度都在旋转*/
    border: 2px solid #f3f3f3; /*设置四周边框大小，并将颜色设置为浅白色*/
    border-top: 2px dashed red; /*将上边框颜色设置为红色高亮，以便旋转的时候能够看到旋转的效果*/
    border-radius: 50%; /*将边框和内容区域都变成圆形*/
    animation: loading 1.5s infinite;
}
@keyframes loading {
    0% {
        transform: rotate(0deg); /*动画起始的时候旋转了0度*/
    }
    100% {
        transform: rotate(360deg); /*动画结束的时候旋转了360度*/
    }
}
/* 图片加载样式 */
.slider-image-loading-box{
    position: absolute;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    background: rgba(255, 255, 255, 0.9);
    z-index: 999;
}
.slider-verify-refresh-icon{
    position: absolute;
    right: 15px;
    top: 15px;
    width: 16px;
    height: 16px;
    z-index: 99;
}
.slider-verify-refresh-icon:hover{
    cursor: pointer;
}
.slider-verify-refresh-icon:hover:before{
    border: 2px solid #7bb7a3;
    border-top: 2px solid transparent;
    transform: rotate(60deg);
}
.slider-verify-refresh-icon:hover:after{
    border-left: 4px solid #7bb7a3;
}
.slider-verify-refresh-icon:before {
    content: "";
    width: 12px;
    height: 12px;
    border: 2px solid #b7b7b7;
    border-top: 2px solid transparent;
    border-radius: 100%;
    position: absolute;
    transform: rotate(45deg);
}
.slider-verify-refresh-icon:after {
    content: "";
    position: absolute;
    border: 4px solid transparent;
    border-left: 4px solid #b7b7b7;
    left: 8px;
    top: -3px;
}
.slider-verify-block{
    position: absolute;
}
/* 以下是滑块相关 */
.slider-btn{
    width: 40px;
    height: 40px;
    border: 1px solid #ccc;
    box-sizing: border-box;
    background-color: #fff;
    cursor: pointer;
    z-index: 2;
    position: absolute;
    top: 0;
    left: 0;
    background-position: center;
    background-repeat: no-repeat;
    background-size: 50% auto;
    background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAACwElEQVRoQ9WZ23XUMBCGZyogJdABSwVk3AChApIKSCpgUwF0QKgAaMD/UgGhAqACQgXiDEfhOF5dxrpsvHrZh7Wt/xtpLhoxHfngI9dPUQAAJyJyVwpY+7513iAAgE9EdOY/shWRa+sHAWyICER0QkS3RHQhIvrbZewBALgkonez2W5E5MKiAMAPIno6eVZXUXpBhAC2RPQ2INYEAcAF3u0GEQI4J6IPEWtficj71EqM43jLzM8OBRHzgRsieh0Rqnta/w8O9QHn3I6ZnwQe+ElEz2uCw/ybqSjUC0IdWn2iOMJNIZJ5AMDqIbKJbO0QWQBdrnEcdU+/6OAT1dvJBKBZ1TtmKLooV9axiehbxABVECYAnbgBRCo8F0OYAdYKsQjACPFKRD4n8kTTlVgMYIDIlg0AmkEUARwAwlR3qY5igLVAVAGsAaIa4LEhmgB4iFQVWuvYUZ9oBmCAyJbSAPSs8WbJWaQpgAFCy+hd6kAUKx6dc1+HYTg1nwdSk2QEpGK8HmaiB3wtV3xDQBsD8/FFRO4bDf//a7oCmQT1UUQULjgy4vWd4Oo1A0iJd859H4YhZNV/MAbx0Wq3CUBOPDOfxo6QNeKrM7G3XnTPq+V7iq8GqLG8h592AOe+kTwk3T9cvIUAaEjTFuLeyFnei9feU8ypTeKLV2DW/3wAcEjxRQBrEr8YYG3iFwF0Fn8tItpUXjxMTpwR/4uZN6lWIYCUwyYzdI4oC5AR/8fH+VR90018dgutXXwSoIH4VG1ftW2m2yp2PzC955rHecu2SZXUzcQHVyBVXDnnViU+BhC65COLeF8i/PY3lPMA0tTy0VoIwN4ln1W8Bwhd8nURH1uBB0XaEvEeQPuiLyfm7yY+GoUAnDnnLplZ2yF60W2+qPY+tHXObZh5V5phcwmsupy2TtD7uWwm7i2g9vtHD/AX4BtFT2Li2V4AAAAASUVORK5CYII=);
}
.slider-btn.success{
    background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3hpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0ZDhlNWY5My05NmI0LTRlNWQtOGFjYi03ZTY4OGYyMTU2ZTYiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NDlBRDI3NjVGMkQ2MTFFNEI5NDBCMjQ2M0ExMDQ1OUYiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NDlBRDI3NjRGMkQ2MTFFNEI5NDBCMjQ2M0ExMDQ1OUYiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDphNWEzMWNhMC1hYmViLTQxNWEtYTEwZS04Y2U5NzRlN2Q4YTEiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NGQ4ZTVmOTMtOTZiNC00ZTVkLThhY2ItN2U2ODhmMjE1NmU2Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+k+sHwwAAASZJREFUeNpi/P//PwMyKD8uZw+kUoDYEYgloMIvgHg/EM/ptHx0EFk9I8wAoEZ+IDUPiIMY8IN1QJwENOgj3ACo5gNAbMBAHLgAxA4gQ5igAnNJ0MwAVTsX7IKyY7L2UNuJAf+AmAmJ78AEDTBiwGYg5gbifCSxFCZoaBMCy4A4GOjnH0D6DpK4IxNSVIHAfSDOAeLraJrjgJp/AwPbHMhejiQnwYRmUzNQ4VQgDQqXK0ia/0I17wJiPmQNTNBEAgMlQIWiQA2vgWw7QppBekGxsAjIiEUSBNnsBDWEAY9mEFgMMgBk00E0iZtA7AHEctDQ58MRuA6wlLgGFMoMpIG1QFeGwAIxGZo8GUhIysmwQGSAZgwHaEZhICIzOaBkJkqyM0CAAQDGx279Jf50AAAAAABJRU5ErkJggg==);

}
.slider-hit-text{
    position: absolute;
    top: 0;
    left: 0;
    height: 40px;
    width: 100%;
    text-align: center;
    line-height: 40px;
    font-size: 14px;
    color: #333;
    z-index: 1;
}
.slider-hit-text.error{
    color: red;
}
.slider-process{
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    background-color: #7ac23c;
}
.slider-process.error{
    background-color: red;
}
</style>
