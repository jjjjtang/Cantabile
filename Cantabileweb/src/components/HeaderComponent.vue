<script setup>
import { ref, reactive, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { Search } from '@element-plus/icons-vue'
import AIView from "@/views/AIView.vue"
import LoginComponent from "@/components/LoginComponent.vue"
import router from "@/router/index.js"

// 获取 cookie 中 jwt1 的值
function getCookie(name) {
  const match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'))
  if (match) return match[2]
  return null
}

const username = ref('')
const avatarSrc = ref('')

// 获取用户信息
const fetchUserInfo = async () => {
  const jwt = getCookie('jwt1')
  if (!jwt) return

  try {
    const res = await axios.get(`http://localhost:8081/api/getUserById?userId=${jwt}`)
    console.log(jwt)
    const user = res.data
    username.value = user.username
    avatarSrc.value = user.src
  } catch (error) {
    ElMessage.error('获取用户信息失败')
  }
}

// 搜索相关逻辑
const state = reactive({
  fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
  url: 'C:\\Users\\lenovo\\Desktop\\files\\Cantabile\\Cantabileweb\\public\\zzy\\IMG_1054.GIF',
})

const inputText = ref('')
let dialogVisible = ref(false)
let searchDialogVisible = ref(false)
const searchResults = ref([])

const handleSearch = () => {
  if (inputText.value.trim()) {
    searchDialogVisible.value = true
    doSearch(inputText.value)
  } else {
    ElMessage.warning('请输入搜索内容')
  }
}


const doSearch = async (keyword) => {
  try {
    const res = await axios.get(`http://localhost:8081/api/searchMusics?keyword=${encodeURIComponent(keyword)}`)
    console.log(res)
    searchResults.value = res.data
    if (searchResults.value.length === 0) {
      ElMessage.info('未找到相关音乐')
    }
  } catch (error) {
    ElMessage.error('搜索失败，请稍后重试')
  }
}
const logout = () => {
  document.cookie = "jwt1=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/"
  router.currentRoute.value.name === 'home' ? window.location.reload() : router.push({name: 'home'})
  ElMessage({message: '已成功退出登录', type: 'success'})
  window.location.reload()
}

const handleMenuSelect = (key) => {
  switch (key) {
    case '1':
      ElMessage.info('点击了：个人资料');
      break
    case '2':
      ElMessage.info('点击了：音乐歌单');
      break
    case '3':
      ElMessage.info('点击了：AI音乐小助手');
      break
    case '4':
      ElMessage.info('点击了：社区交流');
      break
    case '6':
      ElMessage.info('成功退出登录');
      break
  }
}

const go2PersonPage = () => router.push({name: 'homepage'})
const go2Community = () => router.push({name: 'community'})

onMounted(() => {
  fetchUserInfo()
})

</script>

<template>
  <div id="headContainer">
    <div id="titleContainer">
      <div id="searchBox">
        <el-input
            v-model="inputText"
            placeholder="请输入你想搜索的音乐"
            @keyup.enter="handleSearch"
        >
          <template #prepend>
            <el-icon>
              <Search/>
            </el-icon>
          </template>
        </el-input>
      </div>

      <div id="welcomeContainer">
        <h1 class="animated-text">(｡･∀･)ﾉﾞ嗨，欢迎来到Cantabile</h1>
      </div>

    </div>

    <div id="personContainer">
      <div style="display: flex; align-items: center;">
        <div style="margin-right: 10px; margin-top: 10px; font-family: 'Noto Sans SC'; color: #BC3B5F;">
          <strong>{{ username || '未登录' }}</strong>
        </div>

        <el-popover placement="bottom" trigger="click" width="150">
          <template #reference>
            <el-avatar
                :src="avatarSrc || '/default-avatar.png'"
                style="cursor: pointer"
            />
          </template>

          <el-menu
              default-active="1"
              class="el-menu-vertical-demo"
              @select="handleMenuSelect"
              style="border: none"
          >
            <el-menu-item index="1" @click="go2PersonPage()">个人资料</el-menu-item>
            <el-menu-item index="3" @click="dialogVisible=true">梨梨喵~小助手</el-menu-item>
            <el-menu-item index="4" @click="go2Community()">社区交流</el-menu-item>
            <el-menu-item index="5">
              <login-component></login-component>
            </el-menu-item>
            <el-menu-item index="6" @click="logout">退出登录</el-menu-item>
          </el-menu>
        </el-popover>
      </div>
    </div>
  </div>

  <el-dialog v-model="dialogVisible" width="800px">
    <div style="display: flex">
      <img id="img" src="/zzy/IMG_1054.GIF"/>
      <div style="font-size: 26px; color: #da98b9; font-weight: bold;margin-top: 15px;margin-left: 150px">小猫梨梨</div>
      <div style="font-size: 22px; color: #da98b9; font-weight: bold;margin-top: 17px;margin-left: 30px">=＾● ⋏ ●＾=</div>
    </div>
    <AIView/>
  </el-dialog>

  <el-dialog v-model="searchDialogVisible" fullscreen custom-class="search-music-dialog" show-close>
    <div class="header">
      <div>梨梨喵为你找到以下音乐喵~ 🎵 搜索关键词：<span style="color:rgba(238,139,172,0.84)">{{ inputText }}</span></div>
      <el-button type="text" @click="searchDialogVisible = false" style="color:#f180a6">关闭 ✕</el-button>
    </div>

    <div v-if="searchResults.length > 0" class="result-list">
      <div
          v-for="item in searchResults"
          :key="item.id"
          class="result-item"
      >
        <div>
          <div class="song-name">🎵 歌曲：《{{ item.title }}》</div>
          <div class="artist-name">👤 演唱：{{ item.artist }}</div>
        </div>
        <el-button size="small" type="primary" @click="ElMessage.success(`正在播放：${item.name}`)">立即播放</el-button>
      </div>
    </div>

    <div v-else class="no-results">
      <img src="/zzy/IMG_1054.GIF" style="width: 100px; height: auto;" />
      <div style="margin-top: 20px;">喵呜~没有找到相关的歌曲呢，请换个关键词试试看吧！</div>
    </div>
  </el-dialog>

</template>

<style scoped>
#headContainer {
  display: flex;
  width: 100%;
  height: 100px;
  background-color: #f5eff2;
}

#searchBox {
  margin-left: 500px;
  margin-top: 20px;
  width: 500px;
}

#welcomeContainer {
  margin-left: 500px;
  width: 500px;
  color: #bd4d83;
}

#personContainer {
  margin-left: 300px;
  margin-top: 20px;
  height: 40px;
  display: flex;
  width: 400px;
}

.el-menu-vertical-demo .el-menu-item {
  padding-left: 10px;
  font-size: 14px;
}

#img {
  width: 70px;
  height: 80px;
  object-fit: contain;
  margin-left: 20px;
}

.search-music-dialog {
  background-color: #fff0f6;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: bold;
  font-size: 18px;
  padding: 20px;
  border-bottom: 1px solid #f8bbd0;
  background-color: #ffebf0;
}

.result-list {
  padding: 20px;
  max-height: calc(100vh - 120px);
  overflow-y: auto;
}

.result-item {
  display: flex;
  justify-content: space-between;
  background-color: #ffe4ec;
  margin-bottom: 12px;
  padding: 15px;
  border-radius: 12px;
  transition: background 0.3s;
}

.result-item:hover {
  background-color: #ffc1d8;
}

.song-name {
  font-size: 16px;
  font-weight: 600;
  color: rgba(9, 0, 0, 0.94);
}

.artist-name {
  font-size: 14px;
  color: rgba(9, 0, 0, 0.97);
}

.no-results {
  text-align: center;
  font-size: 18px;
  color: rgba(9, 0, 0, 0.96);
  padding: 50px;
}
.animated-text {
  display: inline-block;
  animation: jump 1s ease-in-out infinite;
  color: #bd4d83;
}

@keyframes jump {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-10px);
  }
}

</style>
