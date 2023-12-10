<template>
  <div class="body">
    <el-container>
      <el-aside :width="asideWidth" :collapse="isAsideCollapse">
        <el-button @click="toggleAsideCollapse">折叠</el-button>
        <left-Aside :isCollapse="isAsideCollapse" />
      </el-aside>
      <el-container>
        <el-header>
          <top-header />
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
      asideWidth: '201px',
      isAsideCollapse: false,
    };
  },
  methods: {
    toggleAsideCollapse() {
      this.isAsideCollapse = !this.isAsideCollapse;
      this.asideWidth = this.isAsideCollapse ? '70px' : '201px';
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
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    height: 100vh;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    height: 80vh;
  }

  body > .el-container {
    margin-bottom: 40px;
  }
</style>
