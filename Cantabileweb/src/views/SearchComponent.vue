<script setup>
import { ref, watch } from 'vue'
import { ElMessage } from 'element-plus'

const props = defineProps({
  keyword: String,
  visible: Boolean,
})

const emit = defineEmits(['update:visible'])

const searchResults = ref([])

watch(() => props.visible, (val) => {
  if (val) {
    doSearch(props.keyword)
  }
})

function doSearch(keyword) {
  if (!keyword) {
    searchResults.value = []
    return
  }
  // 模拟异步搜索结果
  setTimeout(() => {
    searchResults.value = [
      { id: 1, name: '樱花树下', artist: '某倩猪' },
      { id: 2, name: '粉色回忆', artist: '梨梨喵' },
      { id: 3, name: '浪漫旋律', artist: 'Cantabile' },
    ].filter(item => item.name.includes(keyword) || item.artist.includes(keyword))
    if (searchResults.value.length === 0) {
      ElMessage.info('没有找到相关音乐')
    }
  }, 500)
}

function close() {
  emit('update:visible', false)
}

function playMusic(item) {
  ElMessage.success(`播放歌曲: ${item.name} - ${item.artist}`)
}
</script>

<template>
  <el-dialog
      :visible.sync="props.visible"
      fullscreen
      custom-class="search-music-dialog"
      show-close
      @close="close"
  >
    <div class="header">
      <div class="keyword-text">搜索结果：<span>{{ props.keyword }}</span></div>
      <el-button type="text" @click="close">关闭 ✕</el-button>
    </div>

    <div class="result-list" v-if="searchResults.length > 0">
      <div
          class="result-item"
          v-for="item in searchResults"
          :key="item.id"
          @click="playMusic(item)"
      >
        <div class="song-name">{{ item.name }}</div>
        <div class="artist-name">{{ item.artist }}</div>
        <el-button size="mini" type="primary" @click.stop="playMusic(item)">播放</el-button>
      </div>
    </div>

    <div v-else class="no-results">暂无搜索结果</div>
  </el-dialog>
</template>

<style scoped>
.search-music-dialog {
  font-family: "Comic Sans MS", cursive, sans-serif;
  background-color: #fff0f5;
  color: #cc2b5e;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 20px;
  border-bottom: 2px dashed #ffb6c1;
  background-color: #ffe6f0;
  font-weight: bold;
  font-size: 18px;
}

.keyword-text span {
  color: #ff4d88;
}

.result-list {
  padding: 20px;
  max-height: calc(100vh - 80px);
  overflow-y: auto;
}

.result-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: #fff4fb;
  margin-bottom: 12px;
  padding: 10px 15px;
  border-radius: 12px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.result-item:hover {
  background-color: #ffc6da;
}

.song-name {
  font-size: 16px;
  font-weight: 600;
  color: #cc2b5e;
  flex: 1;
}

.artist-name {
  font-size: 14px;
  color: #e572ec;
  margin-left: 10px;
  flex-shrink: 0;
}

.no-results {
  padding: 40px;
  text-align: center;
  font-size: 18px;
  color: #e572ec;
}
</style>
