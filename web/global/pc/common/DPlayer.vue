<template>
  <VueDPlayer ref="player" v-bind="$attrs"></VueDPlayer>
</template>

<script>
/**
 * 视频播放组件
 * npm install vue-dplayer -S
 * 依赖 vue-aplyer(一个音乐播放器)
 * 参考：
 * http://dplayer.js.org/guide.html#quick-start
 * 示例：
 *
 */
import VueDPlayer from 'vue-dplayer'
import 'vue-dplayer/dist/vue-dplayer.css'

export default {
  components: {
    'VueDPlayer': VueDPlayer
  },
  name: "DPlayer",
  data(){
    return {

    }
  },
  methods: {
    getInstance() {
      return this.$refs.player
    }
  }
}
</script>

<style scoped>

</style>
