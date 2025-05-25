<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'

const visible = ref(false)

function openDialog() {
  visible.value = true
}

</script>

<template>
  <div class="Container">
    <div class="ArtistContainer" style="display: flex;margin-top: 30px">
      <div class="ArtistImgContainer" >
        <img src="/zzy/tz.png" alt="Example Album 2" style="width: 70px; height: 70px; object-fit: cover;"/>
      </div>
      <div class="ArtistNameContainer" style ="margin-top: 25px;margin-left:20px;font-size:18px;color:rgba(189,77,131,0.8)">
        <strong>Jiangie</strong>
      </div>
    </div>

    <div class="ArtistContainer" style="display: flex;">
      <div class="ArtistImgContainer" >
        <img src="/zzy/syz.png" alt="Example Album 2" style="width: 70px; height: 70px; object-fit: cover;"/>
      </div>
      <div class="ArtistNameContainer" style ="margin-top: 25px;margin-left:20px;font-size:18px;color:rgba(189,77,131,0.8)">
       <strong>Yingzee Shi</strong>
      </div>
    </div>

    <div class="ArtistContainer" >
      <div class="ArtistImgContainer">
        <img src="/zzy/wxt.png" alt="Example Album 2" style="width: 70px; height: 70px; object-fit: cover;"/>
      </div>
      <div class="ArtistNameContainer" style ="margin-top: 25px;margin-left:20px;font-size:18px;color:rgba(189,77,131,0.8)">
       <strong>Shawting Wang</strong>
      </div>
    </div>

    <div class="ArtistContainer" style="display: flex;">
      <div class="ArtistImgContainer">
        <img src="/zzy/lkq.png" alt="Example Album 2" style="width: 70px; height: 70px; object-fit: cover;"/>
      </div>
      <div class="ArtistNameContainer" style ="margin-top: 25px;margin-left:20px;font-size:18px;color:rgba(189,77,131,0.8)">
        <strong>Katherine Li</strong>
      </div>
    </div>


    <div class="ArtistContainer" style="display: flex;">
      <div class="ArtistImgContainer">
        <img src="/zzy/zzzy.png" alt="Example Album 2" style="width: 70px; height: 70px; object-fit: cover;"/>
      </div>
      <div class="ArtistNameContainer" style ="margin-top: 25px;margin-left:20px;font-size:18px;color:rgba(189,77,131,0.8)">
        <strong>Sean</strong>
      </div>
    </div>

    <el-dialog
      fullscreen
      v-model="visible"
      title="她的歌曲"
      >
      <div>

      </div>


    </el-dialog>







  </div>
</template>

<style scoped>
.Container {
  width: 400px;
  height: 450px;
  background-color: rgb(245, 239, 242);
  margin-left: 25px;
}
.ArtistContainer {
  width: 350px;
  height: 70px;
  background-color: rgba(199,137,199,0.2);
  display: flex;
  margin-top: 10px;
}

.ArtistContainer:hover {
  background: linear-gradient(to top, rgba(243, 127, 243, 0.2), #d9a9d6);
  transition: background-color 0.4s ease-in-out;
  cursor: pointer;
  transform: translateY(-1px);
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);

}

</style>