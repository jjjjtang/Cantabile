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
          <svg t="1747664235180" class="icon" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="20" height="20"><path d="M905.177358 115.683019L522.626415 474.807547V128.483019c0-15.215094-18.113208-23.184906-29.222641-12.8L84.769811 499.2c-7.486792 7.003774-7.486792 18.596226 0 25.6l408.392453 383.275472c11.109434 10.384906 29.464151 2.656604 29.464151-12.8V549.192453L905.177358 908.316981c11.109434 10.384906 29.222642 2.656604 29.222642-12.8V128.241509c0-14.973585-18.113208-22.943396-29.222642-12.55849z" fill="#eb7eb4"/></svg>
        </el-button>

        <el-button
            type="primary"
            @click="togglePlay"
            circle
            size="large"
            style="background-color: #eb7eb4; border-color: #eb7eb4;"
        >
          <el-icon v-if="isPlaying" :size="26"><VideoPause /></el-icon>
          <el-icon v-else :size="26"><VideoPlay /></el-icon>
        </el-button>

        <el-button type="text" @click="nextTrack" :disabled="currentIndex === playlist.length - 1" circle>
          <svg t="1747664009825" class="icon" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="26" height="16"><path d="M927.434851 467.27306a71.940103 71.940103 0 0 1 0 89.453611l-213.664799 255.966657-161.663152 192.648589c-33.410385 39.876911-90.261926 11.585859-90.261926-44.726806V63.384619c0-56.312665 56.851542-84.603716 90.261926-44.726805l161.663152 192.648589z" fill="#eb7eb4"/><path d="M546.179251 467.27306a72.748418 72.748418 0 0 1 0 89.453611l-213.934237 255.966657-161.663152 192.648589C137.979793 1045.218828 80.858813 1016.927776 80.858813 960.615111V63.384619C80.858813 7.071955 137.979793-21.219097 171.120739 18.657814l161.663152 192.648589z" fill="#eb7eb4"/></svg>
        </el-button>
      </div>

      <!-- 右侧：状态和播放列表按钮 -->
      <div class="w-[140px] text-right text-white opacity-80 text-sm flex items-center justify-end space-x-2">
        <span>{{ isPlaying ? '播放中' : '已暂停' }}</span>
        <el-button type="text" @click="showPlaylist = true" circle>
          <svg class="icon" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="20" height="20">
            <path d="M419.037 287.953h413.124c17.673 0 32-14.327 32-32s-14.327-32-32-32H419.037c-17.673 0-32 14.327-32 32s14.327 32 32 32zM419.028 543.17h411.608c17.673 0 32-14.327 32-32s-14.327-32-32-32H419.028c-17.673 0-32 14.327-32 32s14.327 32 32 32zM832.161 735.802H419.037c-17.673 0-32 14.327-32 32s14.327 32 32 32h413.124c17.673 0 32-14.327 32-32s-14.327-32-32-32z" fill="#d4237a"></path>
            <path d="M256.037 255.953m-64 0a64 64 0 1 0 128 0 64 64 0 1 0-128 0Z" fill="#d4237a"></path>
            <path d="M256.037 510.787m-64 0a64 64 0 1 0 128 0 64 64 0 1 0-128 0Z" fill="#d4237a"></path>
            <path d="M256.037 767.621m-64 0a64 64 0 1 0 128 0 64 64 0 1 0-128 0Z" fill="#d4237a"></path>
          </svg>
        </el-button>
      </div>
    </el-row>

    <!-- 播放进度条 -->
    <div id="silderContainer">
      <div id="startTime">{{ formatTime(currentTime) }}</div>
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
      <div id="stopTime">{{ formatTime(duration) }}</div>
    </div>

    <el-dialog v-model="showPlaylist" title="播放列表" width="30%">
      <el-scrollbar height="200px">
        <ul class="playlist">
          <li
              v-for="(track, index) in playlist"
              :key="index"
              :class="{ active: index === currentIndex }"
              class="flex justify-between items-center"
          >
        <span @click="selectTrack(index)" class="cursor-pointer flex-1">
          {{ track.title }} - {{ track.artist }}
        </span>
            <el-button
                type="danger"
                circle
                size="small"
                @click.stop="removeTrack(index)"
                style="float: right"
            />

          </li>
        </ul>
      </el-scrollbar>
    </el-dialog>


    <!-- 隐藏音频元素 -->
    <audio
        ref="audio"
        :src="currentTrack.src"
        @timeupdate="updateProgress"
        @ended="onEnded"
    />

  </el-container>
</template>

<script lang="ts" setup>
import { ref, computed, watch, onMounted, onUnmounted } from 'vue'
import { VideoPause, VideoPlay } from '@element-plus/icons-vue'
import emitter from "@/utils/eventBus.js"
import { Delete, Edit, Search, Share, Upload } from '@element-plus/icons-vue'



const audio = ref(null)
const isPlaying = ref(false)
const currentIndex = ref(0)
const progress = ref(0)
const currentTime = ref(0)
const duration = ref(0)
const showPlaylist = ref(false)

const playlist = ref([
  { title: '夜曲', artist: '周杰伦', src: '/music/music.mp3', cover: 'music/music/Cover/夜曲.png' },
])

const currentTrack = computed(() => playlist.value[currentIndex.value])

const togglePlay = () => {
  if (!audio.value) return
  isPlaying.value ? audio.value.pause() : audio.value.play()
  isPlaying.value = !isPlaying.value
}

const nextTrack = () => {
  if (currentIndex.value < playlist.value.length - 1) {
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
  isPlaying.value = false;

  // 移除旧的事件监听器（防止多次绑定）
  audio.value.oncanplay = null;

  console.log("playCurrent", currentTrack.value)

  // 等待 canplay 后再播放
  audio.value.oncanplay = async () => {
    try {
      await audio.value.play();
      isPlaying.value = true;
      progress.value = 0;
    } catch (err) {
      console.error('播放失败:', err);
    }
  };

  // 触发 src 的重新加载（其实是 computed currentTrack.src 已变化）
  audio.value.load();  // 强制重新加载新音频资源
};


const selectTrack = (index) => {
  currentIndex.value = index
  showPlaylist.value = false
  playCurrent()
}

const onEnded = () => {
  if (currentIndex.value < playlist.value.length - 1) {
    currentIndex.value++
    playCurrent()
  } else {
    isPlaying.value = false
  }
}

const updateProgress = () => {
  if (audio.value?.duration) {
    currentTime.value = audio.value.currentTime
    duration.value = audio.value.duration
    progress.value = (currentTime.value / duration.value) * 100
  }
}

const onSliderChange = (val) => {
  if (audio.value?.duration) {
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
  if (isPlaying.value) playCurrent()
})

// ✅ 接收歌曲播放事件并更新播放列表
const handleSong = (payload) => {
  const existingIndex = playlist.value.findIndex(p => p.title === payload.title && p.artist === payload.artist)
  if (existingIndex !== -1) {
    currentIndex.value = existingIndex
  } else {
    playlist.value.push(payload)
    currentIndex.value = playlist.value.length - 1
  }
  playCurrent()
}

onMounted(() => {
  emitter.on('playSong', handleSong)
})

onUnmounted(() => {
  emitter.off('playSong', handleSong)
})

const removeTrack = (index) => {
  if (index === currentIndex.value) {
    // 如果删除的是当前播放的曲目
    if (playlist.value.length === 1) {
      // 只剩一首，清空播放
      playlist.value = []
      currentIndex.value = 0
      isPlaying.value = false
      audio.value.pause()
    } else {
      // 播放下一首或上一首
      if (index === playlist.value.length - 1) {
        currentIndex.value--
      }
      playlist.value.splice(index, 1)
      playCurrent()
    }
  } else {
    if (index < currentIndex.value) {
      currentIndex.value--
    }
    playlist.value.splice(index, 1)
  }
}

</script>


<style scoped>
#silderContainer {
  margin-left: 50px;
  margin-top: 30px;
  width: 800px;
}
#stopTime {
  margin-left: 670px;
}
.player-container {
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 160px;
  background: linear-gradient(to right, #f2f2f2, #ea8cba);
  color: rgb(1, 10, 31);
  padding: 20px;
  box-shadow: 0 -1px 15px rgba(51, 8, 61, 0.5);

  z-index: 1000;
}
.player-inner {
  height: 100px;
}
::v-deep(.el-slider__runway) {
  background-color: #ffffff66;
  height: 4px;
}
::v-deep(.el-slider__bar) {
  background-color: #eb7eb4;
}
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
.playlist {
  list-style: none;
  padding: 0;
}
.playlist li {
  padding: 10px;
  cursor: pointer;
}
.playlist li:hover {
  background-color: #fce4ec;
}
.playlist li.active {
  font-weight: bold;
  color: #eb7eb4;
}
</style>
