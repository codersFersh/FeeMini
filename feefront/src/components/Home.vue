<template>
  <div class="body">
    <el-container>
      <el-aside :width="asideWidth" :collapse="isAsideCollapse">
        <div>
          <img src="@/assets/费用管理.png" style="margin-top: 20px;">
          <h3>班费管理系统</h3>
        </div>
        <left-Aside :isCollapse="isAsideCollapse" />
      </el-aside>
      <el-container>
        <el-header>
          <el-button class="togbnt" @click="toggleAsideCollapse" style="float:left;
          height: 60px;
          background-color: #B3C0D1;
          border: 1px solid #B3C0D1;
          margin-left: -20px;
          ">
            <i v-if="showIcon" class="el-icon-s-fold"></i>
            <i v-else class="el-icon-s-unfold"></i>
          </el-button>
          <top-Header />
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import topHeader from './topHeader.vue';
import leftAside from './leftAside.vue';

export default {
  components: { topHeader, leftAside },
  name: 'Home',
  data() {
    return {
      asideWidth: '121px',
      isAsideCollapse: false,
      //控制显示的图标
      showIcon: true,      
    };
  },
  methods: {
    toggleAsideCollapse() {
      this.isAsideCollapse = !this.isAsideCollapse;
      this.asideWidth = this.isAsideCollapse ? '70px' : '121px';
      this.showIcon = !this.showIcon;
    },
  },

};
</script>

<style>
.body {
  width: 100%;
  height: 100%;
}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
}

.el-aside {
  background-color: #304156;
  color: white;
  text-align: center;
  height: 100vh;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  height: 80vh;
}

body>.el-container {
  margin-bottom: 40px;
}

.togbnt {
  font-size: 30px !important;
}

.el-menu {
  border-right: solid 1px #D3DCE6;
}

.el-menu-item, .el-submenu__title {
    height: 60px !important;
    line-height: 60px !important;
    list-style: none;
}
</style>
