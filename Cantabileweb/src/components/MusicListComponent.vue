<template>
  <div class="playlist-container">
    <ul v-infinite-scroll="load" class="infinite-list">
      <li v-for="i in count" :key="i" class="infinite-list-item">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;歌曲 {{ i }}
        <svg t="1748078403828" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="9258" data-spm-anchor-id="a313x.search_index.0.i5.527a3a81mq3tY4"
             width="20" height="20"  id="delete">
          <path d="M75.851852 154.244741h872.296296v72.666074H75.851852V154.244741zM829.212444 910.222222H194.787556V299.614815h634.424888V910.222222zM274.090667 837.518222h475.818666V372.318815H274.090667v465.199407zM313.761185 37.925926h396.47763v72.704H313.761185z" fill="#f07ab4" p-id="9259"></path><path d="M379.259259 493.037037h84.271408v227.555556H379.259259v-227.555556z m219.136 0H682.666667v227.555556h-84.271408v-227.555556z" fill="#f07ab4" p-id="9260"></path></svg>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'

const count = ref(10) // 默认显示 10 条
const load = () => {
  count.value += 2 // 每次滚动加载 2 条
}
</script>

<style scoped>
.playlist-container {
  width: 400px;
  height: 400px;
  padding: 12px;
  border-radius: 12px;
  margin-left: -25px;
  background: linear-gradient(145deg, #fce4ec, #f8bbd0); /* 粉色渐变 */
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

/* 列表样式 */
.infinite-list {
  padding: 0;
  margin: 0;
  list-style: none;
}
.infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 8px;
  margin-bottom: 10px;
  color: #c6538c;
  font-weight: 500;
  font-size: 16px;
  transition: background-color 0.2s ease;
}
.infinite-list-item:hover {
  background-color: rgba(255, 255, 255, 0.9);
}
#delete{
  margin-left: 150px
}
#delete:hover{
  cursor: pointer;
  transform: scale(1.2);
  transition: transform 0.2s ease;
}
</style>
