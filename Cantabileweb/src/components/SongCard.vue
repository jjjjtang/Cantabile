<template>
  <div class="card2">
    <div class="album-container">
      <img :src="cover || defaultCover" alt="Album Cover" />
      <button class="play-button" @click="handleClickPlay">
        ▶
      </button>
    </div>
    <div class="albumtext-container">
      {{ title }}<br />- {{ artist }}
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'
import emitter from '@/utils/eventBus'

const props = defineProps({
  title: String,
  artist: String,
  cover: String,
  src: String
})

const handleClickPlay = () => {
  emitter.emit('playSong', {
    title: props.title,
    artist: props.artist,
    cover: props.cover,
    src: props.src,
  });
  console.log('🎵 emit playSong:', props.title);
  console.log("emit playSong:", props.src)
}
</script>

<style scoped>
.card2 {
  width: 240px;
  height: 260px;
  background-color: #f5eff2;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 16px;
  margin-left: 0px;
  box-sizing: border-box;
  position: relative;
}
.card2:hover {
  background-color: #f8e0ee;
  transition: all 0.2s ease-in-out;
  cursor: pointer;
}
.album-container {
  position: relative;
}
.album-container img {
  width: 200px;
  height: 200px;
  object-fit: cover;
  border-radius: 8px;
}
.play-button {
  position: absolute;
  bottom: 8px;
  right: 8px;
  background-color: rgba(255, 255, 255, 0.8);
  border: none;
  border-radius: 50%;
  width: 32px;
  height: 32px;
  font-size: 18px;
  color: #c6538c;
  cursor: pointer;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
}
.albumtext-container {
  font-size: 12px;
  color: #010b21;
  font-weight: bold;
  text-align: center;
  margin-top: 8px;
}
.play-button:hover {
  background-color: rgba(255, 255, 255, 0.94);
  transition: all 0.2s ease-in-out;

  transform: scale(1.2);


}
</style>
