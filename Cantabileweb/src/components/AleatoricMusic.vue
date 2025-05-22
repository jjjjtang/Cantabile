<template>
  <div id="container">

    <header-component></header-component>


      <div id="cards-wrapper">
        <div class="card">
          <el-carousel indicator-position="outside" height="440px" style="width: 500px;">
            <el-carousel-item v-for="(image, index) in images" :key="index">
              <img :src="image" alt="Blackpink Album" class="carousel-image" />
            </el-carousel-item>
          </el-carousel>
        </div>

    </div>
    <div class="textContainer">
      热门歌曲
    </div>
    <div class="allMusicContainer">
      <song-card></song-card>
      <song-card></song-card>
      <song-card></song-card>
      <song-card></song-card>


    </div>
    <div class="textContainer">
      今日推荐
    </div>
    <div class="allMusicContainer">
      <song-card></song-card>
      <song-card></song-card>
      <song-card></song-card>
      <song-card></song-card>

    </div>

  </div>


</template>

<script setup>
import { ref } from 'vue'
import HeaderComponent from "@/components/HeaderComponent.vue";
import SongCard from "@/components/SongCard.vue";
import {ElMessageBox} from "element-plus";


const images = ref([
  '/Album/R.png',
  '/zzy/zzzy.png',
  '/zzy/zzytz.png',
  '/zzy/syz.png'
])



//opendialog function

</script>

<style scoped>
#container {
  width: 100%;
  height: 800px;
  background-color: rgb(245, 239, 242);
}
#title-wrapper h1 {
  font-size: 24px;
  color: #c6538c;
  font-weight: bold;
}

#cards-wrapper {
  display: flex;
  margin-top: 25px;
  gap: 24px;
}
.card {
  width: 550px;
  height: 450px;
  background-color: #f6c9e3;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 16px;
  margin-left: 25px;
  box-sizing: border-box;
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.2);
}

.album-container img {
  width: 200px;
  height: 200px;
  object-fit: cover;
  border-radius: 12px;
}
.albumtext-container {
  font-size: 14px;
  color: #010b21;
  font-weight: bold;
  text-align: center;
}
#list-panel {
  width: 300px;
  min-height: 400px;
  background-color: #f3d6e1;
  border-radius: 12px;
  padding: 16px;
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
  overflow-y: auto;
  margin-left: 40px;
}
#list-panel h2 {
  font-size: 18px;
  color: #da5898;
  margin-bottom: 12px;
}
#list-panel ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
#list-panel li {
  margin-bottom: 8px;
  font-size: 14px;
  color: #333;
}
.el-carousel__item img.carousel-image {
  width: 100%;
  height: 440px;
  object-fit: cover;
  border-radius: 8px;
}
.allMusicContainer{
  display: flex;
  margin-top: 25px;
}

.textContainer{
  margin-left: 25px;
  font-size: 24px;
  color: #c6538c;
  font-weight: bold;
  margin-top: 25px;
}

</style>
