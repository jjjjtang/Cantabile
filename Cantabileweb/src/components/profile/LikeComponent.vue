<template>
  <div id="LikeContainer">
    <div id="HeaderContainer">
      <div class="art-font">Pink Moon</div>
    </div>

    <div id="PictureContainer">
      <el-image src="public/Album/R.png" alt="" style="width: 405px; height: 400px"></el-image>
    </div>

    <div id="MusicContainer">
      <div>
        <div id="MusicNameContainer">
          <strong>后会无期</strong>
        </div>
        <div id="ArtistContainer">許嵩</div>
      </div>

      <div id="LoveBtnContainer">
        <div :class="['love-btn', { liked: isLiked }]" @click="toggleLike">♥</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const isLiked = ref(false)

const toggleLike = () => {
  isLiked.value = !isLiked.value
}
</script>

<style scoped>
#LikeContainer {
  width: 600px;
  height: 720px;
}

#HeaderContainer {
  width: 95%;
  height: 40px;
  padding-top: 20px;
  padding-left: 20px;
  background: linear-gradient(to bottom, #ea99c1, #eaa0c5);
  color: #f2f2f2;
  font-size: 20px;
}

#PictureContainer {
  height: 400px;
}

#MusicContainer {
  height: 100px;
  width: 100%;
  background: linear-gradient(to bottom, #e7c9e8, #f2f2f2);
  display: flex;
  align-items: center;
  justify-content: space-between;
}

/* 可爱艺术字体 */
.art-font {
  font-family: 'Lobster', cursive;
  font-size: 26px;
  color: #f2f2f2;
}

#ArtistContainer {
  padding-top: 5px;
  margin-left: 20px;
  font-size: 18px;
  color: rgba(215, 93, 152, 0.91);
}

#MusicNameContainer {
  padding-top: 5px;
  margin-left: 20px;
  font-size: 22px;
  color: rgba(215, 93, 152, 0.91);
}

#ArtistContainer:hover,
#MusicNameContainer:hover {
  text-decoration: underline;
  cursor: pointer;
}

#LoveBtnContainer {
  margin-right: 40px;
}

.love-btn {
  font-size: 32px;
  color: transparent;
  -webkit-text-stroke: 1px #f78aa0;
  cursor: pointer;
  transition: all 0.3s ease;
  user-select: none;
  transform: scale(1);
}

.love-btn.liked {
  color: #f78aa0;
  -webkit-text-stroke: 0;
  animation: like-pop 0.3s ease;
}

@keyframes like-pop {
  0% {
    transform: scale(1);
  }
  40% {
    transform: scale(1.4);
  }
  100% {
    transform: scale(1);
  }
}
</style>
