<template>
  <VueDPlayer ref="player" :options="options"></VueDPlayer>
</template>

<script>
/** 仅是一个示例
 * npm install hls
 * npm install vue-dplayer
 * http://dplayer.js.org/guide.html#hls
 */
import Hls from  'hls.js'
import VueDPlayer from 'vue-dplayer'
import 'vue-dplayer/dist/vue-dplayer.css'

export default {
  components: {
    VueDPlayer
  },
  name: 'DPlayerDemo',
  props: {
    msg: String
  },
  data () {
    return {
      player: null,
      options: {
        video: {
          url: '',
          type: 'customHls',
          customType: {
            customHls: function (video, player) {
              const hls = new Hls();
              hls.loadSource(video.src);
              hls.attachMedia(video);
            },
          }
        },
        contextmenu: [
          {}
        ]
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>