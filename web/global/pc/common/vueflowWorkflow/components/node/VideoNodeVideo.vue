<script setup lang="ts">
import { ref, computed, onUnmounted } from 'vue'

const props = defineProps<{
  src: string
}>()

const videoRef = ref<HTMLVideoElement>()
const isPlaying = ref(false)
const isMuted = ref(false)
const current = ref(0)
const duration = ref(0)
let rafId: number

const progress = computed(() =>
    duration.value ? (current.value / duration.value) * 100 : 0
)

function startProgress() {
  const update = () => {
    current.value = videoRef.value?.currentTime ?? 0
    rafId = requestAnimationFrame(update)
  }
  rafId = requestAnimationFrame(update)
}

function stopProgress() {
  cancelAnimationFrame(rafId)
}

function togglePlay() {
  if (!videoRef.value) return
  if (isPlaying.value) {
    videoRef.value.pause()
  } else {
    videoRef.value.play()
  }
}

function toggleMute() {
  if (!videoRef.value) return
  isMuted.value = !isMuted.value
  videoRef.value.muted = isMuted.value
}

function onPlay() {
  isPlaying.value = true
  startProgress()
}

function onPause() {
  isPlaying.value = false
  stopProgress()
}

function onEnded() {
  isPlaying.value = false
  stopProgress()
}

function onLoadedMetadata() {
  duration.value = videoRef.value?.duration ?? 0
}

function getRatio(e: MouseEvent, el: HTMLElement) {
  const rect = el.getBoundingClientRect()
  return Math.max(0, Math.min(1, (e.clientX - rect.left) / rect.width))
}

function seekTo(ratio: number) {
  if (!videoRef.value || !duration.value) return
  videoRef.value.currentTime = ratio * duration.value
  current.value = videoRef.value.currentTime
}

function onProgressMousedown(e: MouseEvent) {
  e.stopPropagation()
  const el = e.currentTarget as HTMLElement
  seekTo(getRatio(e, el))

  const onMousemove = (e: MouseEvent) => seekTo(getRatio(e, el))
  const onMouseup = () => {
    document.removeEventListener('mousemove', onMousemove)
    document.removeEventListener('mouseup', onMouseup)
  }

  document.addEventListener('mousemove', onMousemove)
  document.addEventListener('mouseup', onMouseup)
}

function formatTime(sec: number) {
  if (!sec || isNaN(sec)) return '0:00'
  const m = Math.floor(sec / 60)
  const s = Math.floor(sec % 60).toString().padStart(2, '0')
  return `${m}:${s}`
}

defineExpose({
  pause: () => videoRef.value?.pause()
})

onUnmounted(() => {
  videoRef.value?.pause()
  stopProgress()
})
</script>

<template>
  <div class="pt-vnv">
    <video
        ref="videoRef"
        :src="src"
        class="pt-vnv-video"
        @play="onPlay"
        @pause="onPause"
        @ended="onEnded"
        @loadedmetadata="onLoadedMetadata"
    />

    <!-- 左上角音量按钮 -->
    <button class="pt-vnv-mute-btn nodrag" @click.stop="toggleMute">
      <!-- 有声音 -->
      <svg v-if="!isMuted" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M0 8C0 3.58172 3.58172 0 8 0H16C20.4183 0 24 3.58172 24 8V16C24 20.4183 20.4183 24 16 24H8C3.58172 24 0 20.4183 0 16V8Z" fill="black" fill-opacity="0.7"/>
        <path d="M6.40149 8.66741L7.79948 8.66408L11.0731 6.1747C11.4224 5.95698 11.7808 5.94276 12.1482 6.13205C12.5157 6.32089 12.702 6.61415 12.7072 7.01182V16.9887C12.7072 17.3859 12.5236 17.6791 12.1566 17.8684C11.7892 18.0573 11.4308 18.0428 11.0815 17.8251L7.80718 15.3417L6.4092 15.3457C6.02211 15.3457 5.69199 15.2155 5.41884 14.9552C5.14522 14.6948 5.0056 14.3806 5 14.0127V10.0004C5 9.6325 5.13681 9.31836 5.41043 9.05798C5.68452 8.79804 6.01487 8.66785 6.40149 8.66741ZM8.21692 9.99707L6.40149 10.0004L6.4099 14.0127L8.22532 14.0087L11.3071 16.3728L11.2987 7.62767L8.21692 9.99707Z" fill="#FAFAFA"/>
        <path d="M15.4824 16.7611C15.6249 16.7611 15.7495 16.721 15.8564 16.6409C16.1947 16.4094 16.5063 16.14 16.7913 15.8329C17.0762 15.5257 17.3232 15.1896 17.5325 14.8245C17.7417 14.4595 17.9042 14.0722 18.0199 13.6626C18.1357 13.2531 18.1936 12.8257 18.1936 12.3805C18.1936 11.9443 18.1357 11.5213 18.0199 11.1118C17.9042 10.7022 17.7439 10.3171 17.5391 9.95655C17.3344 9.59595 17.0895 9.26207 16.8046 8.9549C16.5197 8.64773 16.2125 8.3784 15.8831 8.14691C15.7673 8.04897 15.6338 8 15.4824 8C15.3044 8 15.1508 8.06455 15.0217 8.19365C14.8926 8.32275 14.828 8.47634 14.828 8.65441C14.828 8.877 14.9215 9.05507 15.1085 9.18862C15.616 9.54476 16.0389 9.99884 16.3772 10.5509C16.7156 11.1029 16.8847 11.7128 16.8847 12.3805C16.8847 12.71 16.8402 13.0283 16.7512 13.3354C16.6621 13.6426 16.5375 13.932 16.3772 14.2035C16.217 14.4751 16.03 14.7266 15.8163 14.9581C15.6026 15.1896 15.3711 15.3899 15.1218 15.5591L15.1085 15.5724C15.0996 15.5813 15.0907 15.588 15.0818 15.5925C15.0729 15.5969 15.064 15.6036 15.0551 15.6125C14.9037 15.7461 14.828 15.9108 14.828 16.1066C14.828 16.2847 14.8926 16.4383 15.0217 16.5674C15.1508 16.6965 15.3044 16.7611 15.4824 16.7611Z" fill="#FAFAFA"/>
        <path d="M15.5559 11.4256C15.4446 11.163 15.2955 10.9025 15.1085 10.6443C15.0373 10.5464 14.9616 10.4596 14.8814 10.3839C14.8013 10.3082 14.6989 10.2704 14.5743 10.2704C14.414 10.2704 14.2782 10.3283 14.1669 10.444C14.0556 10.5598 14 10.6978 14 10.858C14 10.9382 14.0134 11.0094 14.0401 11.0717C14.0757 11.1696 14.1246 11.2631 14.187 11.3522C14.2493 11.4412 14.3094 11.5347 14.3673 11.6326C14.4251 11.7306 14.4741 11.8396 14.5142 11.9598C14.5542 12.08 14.5743 12.2203 14.5743 12.3805C14.5743 12.5497 14.5542 12.6944 14.5142 12.8146C14.4741 12.9348 14.4251 13.0416 14.3673 13.1351C14.3094 13.2286 14.2493 13.3198 14.187 13.4089C14.1246 13.4979 14.0712 13.5914 14.0267 13.6893H14.0401C14.0134 13.7606 14 13.8318 14 13.903C14 14.0633 14.0556 14.2013 14.1669 14.317C14.2782 14.4328 14.414 14.4907 14.5743 14.4907C14.6989 14.4907 14.8013 14.4528 14.8814 14.3771C14.9616 14.3015 15.0373 14.2146 15.1085 14.1167C15.2866 13.8852 15.4335 13.627 15.5492 13.3421C15.665 13.0572 15.7228 12.7367 15.7228 12.3805C15.7228 12.0066 15.6672 11.6883 15.5559 11.4256Z" fill="#FAFAFA"/>
      </svg>
      <!-- 静音 -->
      <svg v-else width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M0 8C0 3.58172 3.58172 0 8 0H16C20.4183 0 24 3.58172 24 8V16C24 20.4183 20.4183 24 16 24H8C3.58172 24 0 20.4183 0 16V8Z" fill="black" fill-opacity="0.7"/>
        <path d="M6.40149 8.66741L7.79948 8.66408L11.0731 6.1747C11.4224 5.95698 11.7808 5.94276 12.1482 6.13205C12.5157 6.32089 12.702 6.61415 12.7072 7.01182V16.9887C12.7072 17.3859 12.5236 17.6791 12.1566 17.8684C11.7892 18.0573 11.4308 18.0428 11.0815 17.8251L7.80718 15.3417L6.4092 15.3457C6.02211 15.3457 5.69199 15.2155 5.41884 14.9552C5.14522 14.6948 5.0056 14.3806 5 14.0127V10.0004C5 9.6325 5.13681 9.31836 5.41043 9.05798C5.68452 8.79804 6.01487 8.66785 6.40149 8.66741ZM8.21692 9.99707L6.40149 10.0004L6.4099 14.0127L8.22532 14.0087L11.3071 16.3728L11.2987 7.62767L8.21692 9.99707Z" fill="#FAFAFA"/>
        <line x1="14" y1="9" x2="19" y2="15" stroke="#FAFAFA" stroke-width="1.5" stroke-linecap="round"/>
        <line x1="19" y1="9" x2="14" y2="15" stroke="#FAFAFA" stroke-width="1.5" stroke-linecap="round"/>
      </svg>
    </button>

    <!-- 底部控制栏 -->
    <div class="pt-vnv-bar" @click.stop>
      <button class="pt-vnv-play-btn nodrag" @click.stop="togglePlay">
        <svg v-if="!isPlaying" width="16" height="16" viewBox="0 0 16 16" fill="none">
          <path d="M13.4265 6.78499C10.7318 4.6286 8.03701 2.47244 5.34242 0.316373C4.38291 -0.451633 3 0.265857 3 1.53136V14.4686C3 15.7341 4.38291 16.4516 5.34242 15.6836C8.03701 13.5276 10.7318 11.3714 13.4265 9.21502C14.1912 8.60334 14.1912 7.39667 13.4265 6.78499Z" fill="#FAFAFA"/>
        </svg>
        <svg v-else width="16" height="16" viewBox="0 0 16 16" fill="none">
          <rect x="2" y="2" width="4" height="12" rx="1" fill="#FAFAFA"/>
          <rect x="10" y="2" width="4" height="12" rx="1" fill="#FAFAFA"/>
        </svg>
      </button>

      <div
          class="pt-vnv-progress nodrag"
          role="slider"
          tabindex="0"
          :aria-valuenow="progress"
          aria-valuemin="0"
          aria-valuemax="100"
          @mousedown="onProgressMousedown"
      >
        <div class="pt-vnv-track" />
        <div class="pt-vnv-track-played" :style="{ width: progress + '%' }" />
        <div class="pt-vnv-thumb" :style="{ left: progress + '%' }" />
      </div>

      <span class="pt-vnv-time">
        {{ formatTime(current) }} / {{ duration ? formatTime(duration) : '...' }}
      </span>
    </div>
  </div>
</template>

<style scoped>
.pt-vnv {
  position: relative;
  height: 100%;
  width: 100%;
  background: #000;
  overflow: hidden;
}

.pt-vnv-video {
  height: 100%;
  width: 100%;
  object-fit: contain;
  display: block;
  cursor: pointer;
}

.pt-vnv-mute-btn {
  position: absolute;
  top: 12px;
  left: 12px;
  cursor: pointer;
  background: none;
  border: none;
  padding: 0;
  display: flex;
  align-items: center;
  opacity: 0;
  transition: opacity 0.2s;
}

.pt-vnv:hover .pt-vnv-mute-btn {
  opacity: 1;
}

.pt-vnv-bar {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 40px;
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 0 12px;
  background: linear-gradient(to top, rgba(0, 0, 0, 0.1), transparent);
  opacity: 0;
  transition: opacity 0.2s;
}

.pt-vnv:hover .pt-vnv-bar {
  opacity: 1;
}

.pt-vnv-play-btn {
  cursor: pointer;
  background: none;
  border: none;
  padding: 0;
  display: flex;
  align-items: center;
  flex-shrink: 0;
}

.pt-vnv-progress {
  position: relative;
  flex: 1;
  height: 16px;
  display: flex;
  align-items: center;
  cursor: pointer;
}

.pt-vnv-track {
  position: absolute;
  height: 4px;
  width: 100%;
  border-radius: 9999px;
  background: rgba(255, 255, 255, 0.3);
}

.pt-vnv-track-played {
  position: absolute;
  height: 4px;
  border-radius: 9999px;
  background: #ffffff;
  pointer-events: none;
}

.pt-vnv-thumb {
  position: absolute;
  width: 12px;
  height: 12px;
  border-radius: 9999px;
  background: #ffffff;
  transform: translateX(-50%);
  pointer-events: none;
  transition: transform 0.1s;
}

.pt-vnv-progress:hover .pt-vnv-thumb {
  transform: translateX(-50%) scale(1.3);
}

.pt-vnv-time {
  color: rgba(255, 255, 255, 0.7);
  font-size: 12px;
  width: 64px;
  text-align: right;
  white-space: nowrap;
  flex-shrink: 0;
}
</style>
