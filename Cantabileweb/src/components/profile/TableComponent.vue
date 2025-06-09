<template>
  <div class="table-wrapper">
    <table class="custom-table">
      <thead>
      <tr>
        <th>&ensp;#</th>
        <th>Title</th>
        <th>Artist</th>
        <th>Like</th>
      </tr>
      </thead>
      <tbody>
      <tr
          v-for="(item, index) in tableData"
          :key="index"
          @mouseenter="hoverIndex = index"
          @mouseleave="hoverIndex = null"
      >
        <td>
          <span v-if="hoverIndex === index" class="play-icon">▶</span>
          <span v-else>&ensp;{{ index + 1 }}</span>
        </td>
        <td>{{ item.date }}</td>
        <td>{{ item.name }}</td>
        <td>
          <div
              class="like-btn"
              :class="{ liked: item.liked }"
              @click="toggleLike(index)"
          >
            ♥
          </div>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
// 表格数据（来自后端）
const tableData = ref([
  { date: '凉凉', name: '杨宗纬&张碧晨', liked: true },
  { date: 'Melody', name: '陶喆', liked: true },
  { date: 'Bad Girl', name: '吴亦凡', liked: true },
  { date: '全是爱', name: '凤凰传奇', liked: true }
])

// 当前悬停的行索引
const hoverIndex = ref<number | null>(null)

// 点赞/取消点赞函数（后续可加接口调用）
const toggleLike = (index: number) => {
  tableData.value[index].liked = !tableData.value[index].liked

  // TODO：可在此处调用 API 通知后端
  // axios.post('/api/like', {
  //   id: tableData.value[index].id,
  //   liked: tableData.value[index].liked
  // })
}
</script>

<style scoped>
.table-wrapper {
  width: 100%;
  overflow-x: auto;
  background-color: transparent;
}

.custom-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 14px;
  color: #2f2f2f;
}

.custom-table thead {
  background-color: rgba(255, 255, 255, 0.1);
}

.custom-table th,
.custom-table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid rgba(255, 255, 255, 0.2);
}

.custom-table tbody tr:nth-child(odd) {
  background-color: rgba(255, 255, 255, 0.05);
}

.custom-table tbody tr:hover {
  background-color: rgba(255, 255, 255, 0.15);
  cursor: pointer;
  transition: background-color 0.3s;
  box-shadow: 0 0 0 1px rgba(215, 144, 180, 0.65);
}

.play-icon {
  margin-left: 5px;
  color: #2f2f2f;
  font-size: 12px;
  transition: color 0.3s;
}

.like-btn {
  font-size: 18px;
  color: transparent;
  -webkit-text-stroke: 1px #888;
  cursor: pointer;
  transition: all 0.3s ease;
  display: inline-block;
  user-select: none;
  transform: scale(1);
}

.like-btn.liked {
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
