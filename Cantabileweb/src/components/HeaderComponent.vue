<script setup>
import { ref } from 'vue'
import {ElMessage, ElMessageBox} from 'element-plus'
import { Search } from '@element-plus/icons-vue'
import AIView from "@/views/AIView.vue";

import { reactive, toRefs } from 'vue'

const state = reactive({
  fits: [
    'fill',
    'contain',
    'cover',
    'none',
    'scale-down',
  ],
  url: 'C:\\Users\\lenovo\\Desktop\\files\\Cantabile\\Cantabileweb\\public\\zzy\\IMG_1054.GIF',
})

const inputText = ref('')  // 使用 ref 创建响应式数据
let dialogVisible = ref(false)

const handleMenuSelect = (key) => {
  switch (key) {
    case '1':
      ElMessage.info('点击了：个人资料')
      break
    case '2':
      ElMessage.info('点击了：音乐歌单')
      break
    case '3':
      ElMessage.info('点击了：AI音乐小助手')
      break
    case '4':
      ElMessage.info('点击了：社区交流')
      break
    case '5':
      ElMessage.info('点击了：注册')
      break
    case '6':
      ElMessage.info('点击了：退出登录')
      break
  }
}

const handleSearch = () => {
  if (inputText.value.trim()) {
    ElMessage.success(`搜索内容：${inputText.value}`)
  } else {
    ElMessage.warning('请输入搜索内容')
  }
}
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
              <Search />
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
        <div style="margin-right: 10px; margin-top: 10px; font-family: 'Noto Sans SC'; color: #e572ec;">
          某倩猪
        </div>

        <el-popover
            placement="bottom"
            trigger="click"
            width="150"
        >
          <template #reference>
            <el-avatar
                src="public/avatar/avatar.png"
                style="cursor: pointer"
            />
          </template>

          <el-menu
              default-active="1"
              class="el-menu-vertical-demo"
              @select="handleMenuSelect"
              style="border: none"
          >
            <el-menu-item index="1">个人资料</el-menu-item>
            <el-menu-item index="2">音乐歌单</el-menu-item>
            <el-menu-item index="3" @click="dialogVisible=true">梨梨喵~小助手</el-menu-item>
            <el-menu-item index="4">社区交流</el-menu-item>
            <el-menu-item index="5">注册</el-menu-item>
            <el-menu-item index="6">退出登录</el-menu-item>
          </el-menu>
        </el-popover>
      </div>
    </div>
  </div>

  <el-dialog
      v-model="dialogVisible"
      width="800px"
  >

    <div style="display: flex">

      <img id="img" src="C:\Users\lenovo\Desktop\files\Cantabile\Cantabileweb\public\zzy\IMG_1054.GIF">
      <div style="font-size: 26px; color: #da98b9; font-weight: bold;margin-top: 15px;margin-left: 150px">小猫梨梨</div>
      <div style="font-size: 22px; color: #da98b9; font-weight: bold;margin-top: 17px;margin-left: 30px">=＾● ⋏ ●＾=</div>


    </div>


    <AIView></AIView>

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
  margin-left: 300px; /* 原来是 320px，左移了 100px */
  margin-top: 20px;
  height: 40px;
  display: flex;
  width: 400px;
}

.el-menu-vertical-demo .el-menu-item {
  padding-left: 10px;
  font-size: 14px;
}
#img{
  width: 70px;
  height: 80px;
  object-fit: contain;
  margin-left: 20px;
}
</style>
