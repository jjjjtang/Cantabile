<script setup>
import { ref } from 'vue'
import { ElDialog } from 'element-plus'

const visible = ref(false)
const currentArtist = ref(null)

const artists = [
  {
    name: 'Jiangie',
    image: '/zzy/tz.png',
    songs: ['夏日微醺', '城市边缘', '她的眼眸', '秋日私语', '风与自由'],
    albums: ['青春纪事', '城市漫游者','钢琴日记']
  },
  {
    name: 'Yingzee Shi',
    image: '/zzy/syz.png',
    songs: ['电子梦境', '霓虹心跳', '未来信号', '太空漫游'],
    albums: ['数字乌托邦', '赛博情书']
  },
  {
    name: 'Shawting Wang',
    image: '/zzy/wxt.png',
    songs: ['街头诗人', '梦想不打烊', '王者归来', '地下通道', '嘻哈态度',],
    albums: ['地下王国', 'Real Talk']
  },
  {
    name: 'Katherine Li',
    image: '/zzy/lkq.png',
    songs: ['月光奏鸣曲', '雨后小调',  '冬日恋歌', '春之圆舞曲'],
    albums: ['四季物语', ]
  },
  {
    name: 'Sean',
    image: '/zzy/zzzy.png',
    songs: ['摇滚不死', '自由之路', '燃烧的青春', '叛逆年代', '最后的呐喊'],
    albums:['我是歌手', 'Live现场2023']
  }
]

function openDialog(artist) {
  currentArtist.value = artist
  visible.value = true
}
</script>

<template>
  <div class="Container">
    <!-- 保持原有艺术家列表结构不变 -->
    <div
        class="ArtistContainer"
        style="display: flex; margin-top: 30px"
        @click="openDialog(artists[0])"
    >
      <div class="ArtistImgContainer">
        <img src="/zzy/tz.png" alt="Example Album 2" style="width: 70px; height: 70px; object-fit: cover;"/>
      </div>
      <div class="ArtistNameContainer" style="margin-top: 25px; margin-left: 20px; font-size: 18px; color: rgba(189,77,131,0.8)">
        <strong>Jiangie</strong>
      </div>
    </div>

    <div
        class="ArtistContainer"
        style="display: flex"
        @click="openDialog(artists[1])"
    >
      <div class="ArtistImgContainer">
        <img src="/zzy/syz.png" alt="Example Album 2" style="width: 70px; height: 70px; object-fit: cover;"/>
      </div>
      <div class="ArtistNameContainer" style="margin-top: 25px; margin-left: 20px; font-size: 18px; color: rgba(189,77,131,0.8)">
        <strong>Yingzee Shi</strong>
      </div>
    </div>

    <div
        class="ArtistContainer"
        @click="openDialog(artists[2])"
    >
      <div class="ArtistImgContainer">
        <img src="/zzy/wxt.png" alt="Example Album 2" style="width: 70px; height: 70px; object-fit: cover;"/>
      </div>
      <div class="ArtistNameContainer" style="margin-top: 25px; margin-left: 20px; font-size: 18px; color: rgba(189,77,131,0.8)">
        <strong>Shawting Wang</strong>
      </div>
    </div>

    <div
        class="ArtistContainer"
        style="display: flex"
        @click="openDialog(artists[3])"
    >
      <div class="ArtistImgContainer">
        <img src="/zzy/lkq.png" alt="Example Album 2" style="width: 70px; height: 70px; object-fit: cover;"/>
      </div>
      <div class="ArtistNameContainer" style="margin-top: 25px; margin-left: 20px; font-size: 18px; color: rgba(189,77,131,0.8)">
        <strong>Katherine Li</strong>
      </div>
    </div>

    <div
        class="ArtistContainer"
        style="display: flex"
        @click="openDialog(artists[4])"
    >
      <div class="ArtistImgContainer">
        <img src="/zzy/zzzy.png" alt="Example Album 2" style="width: 70px; height: 70px; object-fit: cover;"/>
      </div>
      <div class="ArtistNameContainer" style="margin-top: 25px; margin-left: 20px; font-size: 18px; color: rgba(189,77,131,0.8)">
        <strong>Sean</strong>
      </div>
    </div>



    <el-dialog
        v-model="visible"
        :title="currentArtist?.name + '音乐家个人信息'"
        width="600px"
    >
      <div v-if="currentArtist" style="padding: 20px; display: flex;">
        <!-- 左侧信息区域 -->
        <div style="flex: 1; padding-right: 20px;">
          <h2 style="color: rgba(189,77,131,0.8); margin-top: 0;">&ensp;&ensp;&ensp;&ensp;&ensp;{{ currentArtist.name }}</h2>

          <!-- 添加音乐家介绍 -->
          <p style="color: #666; margin: 10px 0 20px 0; font-size: 14px; line-height: 1.5;">
            {{
              currentArtist.name === 'Jiangie' ? '独立音乐人，用温暖的旋律讲述城市故事和青春回忆' :
                  currentArtist.name === 'Yingzee Shi' ? '电子音乐先锋，融合未来科技感与迷幻音效' :
                      currentArtist.name === 'Shawting Wang' ? '嘻哈新锐，用犀利的歌词表达街头态度' :
                          currentArtist.name === 'Katherine Li' ? '古典钢琴家，演绎优雅浪漫的四季乐章' :
                              '摇滚主唱，用充满力量的嗓音呐喊青春与自由'
            }}
          </p>

          <div style="display: flex; gap: 40px;">
            <div>
              <h3 style="color: rgba(189,77,131,0.8); border-bottom: 1px solid #eee; padding-bottom: 5px;">热门歌曲</h3>
              <ul style="list-style: none; padding: 0;">
                <li v-for="(song, index) in currentArtist.songs" :key="index" style="padding: 8px 0; color: #666;">
                  {{ song }}
                </li>
              </ul>
            </div>

            <div>
              <h3 style="color: rgba(189,77,131,0.8); border-bottom: 1px solid #eee; padding-bottom: 5px;">专辑</h3>
              <ul style="list-style: none; padding: 0;">
                <li v-for="(album, index) in currentArtist.albums" :key="index" style="padding: 8px 0; color: #666;">
                  {{ album }}
                </li>
              </ul>
            </div>
          </div>
        </div>

        <!-- 右侧图片区域保持不变 -->
        <div style="width: 250px;">
          <img
              :src="currentArtist.image"
              :alt="currentArtist.name"
              style="width: 100%; height: auto; max-height: 300px; object-fit: cover; border-radius: 8px;"
          />
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<style scoped>
/* 保持原有样式完全不变 */
.Container {
  width: 400px;
  height: 450px;
  background-color: rgb(245, 239, 242);
  margin-left: 25px;
  overflow-y: auto;
}
.ArtistContainer {
  width: 350px;
  height: 70px;
  background-color: rgba(199,137,199,0.2);
  display: flex;
  margin-top: 10px;
  margin-right: auto;
}

.ArtistContainer:hover {
  background: linear-gradient(to top, rgba(243, 127, 243, 0.2), #d9a9d6);
  transition: background-color 0.4s ease-in-out;
  cursor: pointer;
  transform: translateY(-1px);
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
}
</style>