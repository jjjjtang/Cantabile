<template>
  <div class="playlist-container">
    <ul v-infinite-scroll="load" class="infinite-list">
      <li
          v-for="(song, index) in songs"
          :key="index"
          class="infinite-list-item"
      >
        {{ song }}
        <svg
            t="1748078403828"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="9258"
            width="20"
            height="20"
            id="delete"
        >
          <path
              d="M75.851852 154.244741h872.296296v72.666074H75.851852V154.244741zM829.212444 910.222222H194.787556V299.614815h634.424888V910.222222zM274.090667 837.518222h475.818666V372.318815H274.090667v465.199407zM313.761185 37.925926h396.47763v72.704H313.761185z"
              fill="#f07ab4"
              p-id="9259"
          ></path>
          <path
              d="M379.259259 493.037037h84.271408v227.555556H379.259259v-227.555556z m219.136 0H682.666667v227.555556h-84.271408v-227.555556z"
              fill="#f07ab4"
              p-id="9260"
          ></path>
        </svg>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'

// 生成不超过6字符的随机歌曲名（中英文混合）
const generateRandomSongs = (count: number): string[] => {
  const zhWords = ['星河', '夜色', '微光', '心跳', '孤影', '流年', '追梦', '深海', '晨曦', '月影']
  const enWords = ['Love', 'Dream', 'Echo', 'Fire', 'Blue', 'Soul', 'Sky', 'Time']
  const songs: string[] = []

  while (songs.length < count) {
    let title = ''
    while (title.length < 4 + Math.floor(Math.random() * 3)) {
      const isZh = Math.random() < 0.6
      const word = isZh
          ? zhWords[Math.floor(Math.random() * zhWords.length)]
          : enWords[Math.floor(Math.random() * enWords.length)]
      if (title.length + word.length > 6) break
      title += word
    }
    songs.push(title)
  }

  return songs
}

const songs = ref<string[]>(generateRandomSongs(50))

const load = () => {
  // 可扩展滚动加载逻辑
}
</script>

<style scoped>
.playlist-container {
  height: 400px;
  padding: 12px;
  border-radius: 12px;
  margin-left: -25px;
  background: linear-gradient(145deg, #fce4ec, #fcc6d9);
  box-shadow: 0 4px 12px rgba(198, 83, 140, 0.3);
  overflow-y: auto;
  margin-top: 10px;
}

/* 自定义滚动条 */
.playlist-container::-webkit-scrollbar {
  width: 8px;
}
.playlist-container::-webkit-scrollbar-thumb {
  background-color: #f5b7d0;
  border-radius: 4px;
}
.playlist-container::-webkit-scrollbar-track {
  background-color: transparent;
}

.infinite-list {
  padding: 0;
  margin: 0;
  width: 350px;
  list-style: none;
}

.infinite-list-item {
  position: relative; /* 允许绝对定位删除图标 */
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 8px;
  margin-bottom: 10px;
  color: #e55ca0;
  font-weight: 500;
  font-size: 16px;
  transition: background-color 0.2s ease;
  padding-right: 40px; /* 给删除图标留空间 */
}

.infinite-list-item:hover {
  background-color: rgba(255, 255, 255, 0.9);
}

#delete {
  position: absolute;
  right: 16px;
  top: 50%;
  transform: translateY(-50%);
  cursor: pointer;
  transition: transform 0.2s ease;
}

#delete:hover {
  transform: translateY(-50%) scale(1.2);
}
</style>
