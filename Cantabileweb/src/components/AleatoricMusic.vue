<template>
  <div id="container">
    <header-component />

    <div id="cards-wrapper">
      <div class="card">
        <el-carousel indicator-position="outside" height="440px" style="width: 500px;">
          <el-carousel-item v-for="(image, index) in images" :key="index">
            <img :src="image" alt="Album" class="carousel-image" />
          </el-carousel-item>
        </el-carousel>
      </div>

      <ArtistComponent />
      <MusicListComponent />
    </div>

    <div class="textContainer">热门歌曲</div>
    <div class="allMusicContainer">
      <SongCard
          v-for="(song, index) in hotSongs.slice(0, 5)"
          :key="song.musicId"
          :title="song.title"
          :artist="song.artist"
          :cover="song.cover"
          :src="song.src"
      />
    </div>

    <div class="textContainer">今日推荐</div>
    <div class="allMusicContainer">
      <SongCard
          v-for="(song, index) in hotSongs.slice(5, 10)"
          :key="song.musicId"
          :title="song.title"
          :artist="song.artist"
          :cover="song.cover"
          :src="song.src"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import HeaderComponent from '@/components/HeaderComponent.vue';
import SongCard from '@/components/SongCard.vue';
import ArtistComponent from '@/components/ArtistComponent.vue';
import MusicListComponent from '@/components/MusicListComponent.vue';

const images = ref([
  '/Album/R.png',
  '/Album/Allofme.png',
  '/Album/Liew.png',
  '/zzy/zzytz.png',
]);

const hotSongs = ref([]);

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8081/api/hotMusics');
    hotSongs.value = response.data;
  } catch (error) {
    console.error('获取热门歌曲失败：', error);
  }
});
</script>

<style scoped>
#container {
  width: 100%;
  background-color: rgb(245, 239, 242);
}
#cards-wrapper {
  display: flex;
  margin-top: 25px;
  gap: 24px;
}
.card {
  width: 550px;
  height: 490px;
  background-color: rgba(246, 201, 227, 0.86);
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 16px;
  margin-left: 25px;
  box-sizing: border-box;
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.2);
}
.el-carousel__item img.carousel-image {
  width: 100%;
  height: 440px;
  object-fit: cover;
  border-radius: 8px;
}
.carousel-image {
  width: 600px;
  height: 440px;
  object-fit: cover;
  border-radius: 8px;
}
.textContainer {
  margin-left: 25px;
  font-size: 24px;
  color: #c6538c;
  font-weight: bold;
  margin-top: 25px;
}
.allMusicContainer {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
  margin-left: 25px;
  margin-top: 16px;
}
</style>
