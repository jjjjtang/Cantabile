<template>
  <el-container class="player-container">
    <el-row class="player-inner" align="middle" justify="space-between">
      <!-- 左侧：封面与信息 -->
      <div class="flex items-center space-x-4">
        <el-image
            :src="currentTrack.cover"
            fit="cover"
            style="width: 94px; height: 94px; border-radius: 12px"
        />
        <div>
          <div class="custom-title">{{ currentTrack.title }}</div>
          <div class="custom-artist">{{ currentTrack.artist }}</div>
        </div>

      </div>

      <!-- 中间：控制按钮 -->
      <div class="flex items-center space-x-6">
        <el-button type="text" @click="prevTrack" :disabled="currentIndex === 0" circle>
          <svg t="1747664235180" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="56206" width="20" height="20"><path d="M905.177358 115.683019L522.626415 474.807547V128.483019c0-15.215094-18.113208-23.184906-29.222641-12.8L84.769811 499.2c-7.486792 7.003774-7.486792 18.596226 0 25.6l408.392453 383.275472c11.109434 10.384906 29.464151 2.656604 29.464151-12.8V549.192453L905.177358 908.316981c11.109434 10.384906 29.222642 2.656604 29.222642-12.8V128.241509c0-14.973585-18.113208-22.943396-29.222642-12.55849z" p-id="56207" fill="#eb7eb4"></path></svg>
        </el-button>
        <el-button
            type="primary"
            @click="togglePlay"
            circle
            size="large"
            style="background-color: #eb7eb4; border-color: #eb7eb4;"
        >
          <el-icon is-clickable v-if="isPlaying" :size="26"><VideoPause /></el-icon>
          <el-icon is-clickable v-else :size="26" ><VideoPlay /></el-icon>
        </el-button>

        <el-button type="text" @click="nextTrack" :disabled="currentIndex === playlist.length - 1" circle>
          <svg t="1747664009825" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="28844" width="26" height="16"><path d="M927.434851 467.27306a71.940103 71.940103 0 0 1 0 89.453611l-213.664799 255.966657-161.663152 192.648589c-33.410385 39.876911-90.261926 11.585859-90.261926-44.726806V63.384619c0-56.312665 56.851542-84.603716 90.261926-44.726805l161.663152 192.648589z" fill="#eb7eb4" p-id="28845"></path><path d="M546.179251 467.27306a72.748418 72.748418 0 0 1 0 89.453611l-213.934237 255.966657-161.663152 192.648589C137.979793 1045.218828 80.858813 1016.927776 80.858813 960.615111V63.384619C80.858813 7.071955 137.979793-21.219097 171.120739 18.657814l161.663152 192.648589z" fill="#eb7eb4" p-id="28846"></path></svg>
        </el-button>
      </div>

      <!-- 右侧：空白扩展（可加入音量、模式等） -->
      <div class="w-[100px] text-right text-white opacity-60 text-sm">
        {{ isPlaying ? '播放中' : '已暂停' }}
      </div>
    </el-row>

    <!-- 播放进度条 -->

    <div id="silderContainer">

      <div id="startTime">
        {{ formatTime(currentTime) }}
      </div>

      <div class="absolute left-0 right-0 top-1/2 -translate-y-1/2">
        <el-slider
            v-model="progress"
            :min="0"
            :max="100"
            @change="onSliderChange"
            :show-tooltip="false"
            class="mx-auto"
            style="width: 90%;"
        />
      </div>

      <div id="stopTime">
        {{ formatTime(duration) }}
      </div>


    </div>



    <!-- 隐藏音频元素 -->
    <audio
        ref="audio"
        :src="currentTrack.src"
        @timeupdate="updateProgress"
        @ended="onEnded"
    />
  </el-container>
</template>

<script setup>
import { ref, computed, watch } from 'vue'
import { VideoPause, VideoPlay } from '@element-plus/icons-vue'


const audio = ref(null)
const isPlaying = ref(false)
const currentIndex = ref(0)
const progress = ref(0)
const currentTime = ref(0)
const duration = ref(0)

// 播放列表
const playlist = [
  {
    title: '夜曲',
    artist: '周杰伦',
    src: '/music/music.mp3',
    cover: '/music/cover1.jpg'
  },
  {
    title: '告白气球',
    artist: '周杰伦',
    src: '/music/music.mp3',
    cover: '/music/cover2.jpg'
  },
  {
    title: '夜空中最亮的星',
    artist: '逃跑计划',
    src: '/music/music.mp3',
    cover: '/music/cover3.jpg'
  }
]

const currentTrack = computed(() => playlist[currentIndex.value])

const togglePlay = () => {
  if (!audio.value) return
  if (isPlaying.value) {
    audio.value.pause()
  } else {
    audio.value.play()
  }
  isPlaying.value = !isPlaying.value
}

const nextTrack = () => {
  if (currentIndex.value < playlist.length - 1) {
    currentIndex.value++
    playCurrent()
  }
}

const prevTrack = () => {
  if (currentIndex.value > 0) {
    currentIndex.value--
    playCurrent()
  }
}

const playCurrent = () => {
  isPlaying.value = false
  audio.value.load()
  audio.value.play()
  isPlaying.value = true
  progress.value = 0
}

const onEnded = () => {
  if (currentIndex.value < playlist.length - 1) {
    currentIndex.value++
    playCurrent()
  } else {
    isPlaying.value = false
  }
}

const updateProgress = () => {
  if (audio.value && audio.value.duration) {
    currentTime.value = audio.value.currentTime
    duration.value = audio.value.duration
    progress.value = (currentTime.value / duration.value) * 100
  }
}

const onSliderChange = (val) => {
  if (audio.value && audio.value.duration) {
    const newTime = (val / 100) * audio.value.duration
    audio.value.currentTime = newTime
    currentTime.value = newTime
  }
}

const formatTime = (seconds) => {
  const m = Math.floor(seconds / 60)
  const s = Math.floor(seconds % 60)
  return `${m.toString().padStart(2, '0')}:${s.toString().padStart(2, '0')}`
}

watch(currentIndex, () => {
  if (isPlaying.value) {
    playCurrent()
  }
})
</script>

<style scoped>
#silderContainer{
  margin-left: 50px;
  margin-top: 30px;
  width: 800px;
}
.player-container {
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 160px;
  background: linear-gradient(to right, #f2f2f2, #d9a9d6);
  color: rgb(1, 8, 24);
  padding: 20px;
  box-shadow: 0 -4px 12px rgb(1, 19, 59);
  z-index: 1000;
}

.player-inner {
  height: 100px;
}
#stopTime{
  margin-left: 670px;
}
/* 粉色滑轨和进度 */
::v-deep(.el-slider__runway) {
  background-color: #ffffff66; /* 背景轨道稍微透明 */
  height: 4px;
}

::v-deep(.el-slider__bar) {
  background-color: #eb7eb4; /* 粉色进度条 */
}

/* 粉色滑块按钮 */
::v-deep(.el-slider__button) {
  background-color: #eb7eb4;
  border: none;
  width: 14px;
  height: 14px;
}

.custom-title {
  font-family: 'Playfair Display', serif;
  font-size: 15px;
  color: #131313;
}

.custom-artist {
  font-family: 'Open Sans', sans-serif;
  font-size: 10px;
  color: #171616;
}

</style>
