<script setup>
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'
import ArticleCardComponent from "@/components/Community/ArticleCardComponent.vue"

// 从 cookie 中提取 jwt1 中的 userId
function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}

const posts = ref([]);
const currentPage = ref(1);
const pageSize = 4;

const totalPages = computed(() => Math.ceil(posts.value.length / pageSize));

const paginatedPosts = computed(() => {
  const start = (currentPage.value - 1) * pageSize;
  return posts.value.slice(start, start + pageSize);
});

const nextPage = () => {
  if (currentPage.value < totalPages.value) currentPage.value++;
};
const prevPage = () => {
  if (currentPage.value > 1) currentPage.value--;
};

onMounted(async () => {
  const userId = getCookie("jwt1");
  try {
    const response = await axios.get(`http://localhost:8081/api/getArticleById?userId=${userId}`);
    posts.value = response.data;
  } catch (error) {
    console.error("获取文章失败：", error);
  }
});
</script>

<template>
  <div id="Container">
    <div id="TextContainer">
      <ArticleCardComponent
          v-for="(post, index) in paginatedPosts"
          :key="index"
          :post="post"
      />
    </div>

    <div id="Pagination">
      <button @click="prevPage" :disabled="currentPage === 1">上一页</button>
      <span>第 {{ currentPage }} 页 / 共 {{ totalPages }} 页</span>
      <button @click="nextPage" :disabled="currentPage === totalPages">下一页</button>
    </div>
  </div>
</template>

<style scoped>
#Container {
  width: 1470px;
  min-height: 100vh;
  background: linear-gradient(to bottom, #FDF1F5, #ea8cba);
  display: flex;
  flex-direction: column;
  align-items: center;
}

#TextContainer {
  width: 86%;
  background: linear-gradient(to bottom, #FDF1F5, #ea8cba);
  margin-top: 70px;
}

#Pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  margin: 30px 0;
  font-family: "Comic Sans MS", cursive, sans-serif;
  font-size: 16px;
}

#Pagination button {
  padding: 8px 16px;
  background-color: #ff8eb4;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  color: white;
  font-weight: bold;
  transition: background-color 0.3s ease;
}

#Pagination button:disabled {
  background-color: #f5c4d1;
  cursor: not-allowed;
}

#Pagination button:hover:not(:disabled) {
  background-color: #ff66a3;
}
</style>
