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

const doSearch = (keyword) => {
  setTimeout(() => {
    searchResults.value = [
      {id: 1, name: '春日旋律', artist: '某倩猪'},
      {id: 2, name: '梦中的歌谣', artist: '梨梨喵'},
      {id: 3, name: '粉色海岸', artist: 'Cantabile'},
    ].filter(item => item.name.includes(keyword) || item.artist.includes(keyword))

    if (searchResults.value.length === 0) {
      ElMessage.info('未找到相关音乐')
    }
  }, 300)
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
        <h1>(｡･∀･)ﾉﾞ嗨，欢迎来到Cantabile</h1>
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
      <div>搜索结果：<span style="color:#f06292">{{ inputText }}</span></div>
      <el-button type="text" @click="searchDialogVisible = false" style="color:#f180a6">关闭 ✕</el-button>
    </div>

    <div class="result-list" v-if="searchResults.length > 0">
      <div
          v-for="item in searchResults"
          :key="item.id"
          class="result-item"
      >
        <div class="song-name">{{ item.name }}</div>
        <div class="artist-name">{{ item.artist }}</div>
        <el-button size="small" type="primary" @click="ElMessage.success(`播放：${item.name}`)">播放</el-button>
      </div>
    </div>

    <div v-else class="no-results">暂无搜索结果哦~</div>
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
  color: #d81b60;
}

.artist-name {
  font-size: 14px;
  color: #f06292;
}

.no-results {
  text-align: center;
  font-size: 18px;
  color: #f48fb1;
  padding: 50px;
}
</style>
