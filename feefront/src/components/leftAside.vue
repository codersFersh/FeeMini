<template>
  <div class="left-aside">
    <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
    </el-radio-group>
    <el-menu 
    :default-active="$route.path"
     class="el-menu-vertical-demo"
     @open="handleOpen" 
      :collapse="isCollapse"  
      background-color="#304156"
      active-text-color="#ffd04b"
      router
      >
      <el-menu-item index="/Home/StatManage">
        <i class="el-icon-s-home"></i>
        <span slot="title">首页 </span>
      </el-menu-item>
      <el-menu-item index="/Home/IncomeManage">
        <i class="el-icon-s-finance"></i>
        <span slot="title">收入管理</span>
      </el-menu-item>
      <el-menu-item index="/Home/PayManage">
        <i class="el-icon-s-shop"></i>
        <span slot="title">支出管理</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'leftAside',
  data() {
    return {
      handleOpen: '1',
    }
  },
  props: {
    isCollapse: Boolean,
  },
};
</script>

<style>
.left-aside {
  margin-top: 60px;
  max-width: 121px;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 120px;
  min-height: 400px;
}

.el-menu--collapse {
  width: 69px !important;
}

.el-menu-item {
  color: white !important;
}

.el-menu {
  background-color: #304156;
  border-right: #304156 !important;
}

.el-icon-s-home {
  font-size: 20px !important;
}

.el-icon-s-finance {
  font-size: 20px !important;
}

.el-icon-s-shop {
  font-size: 20px !important;
}

.el-icon-s-data {
  font-size: 20px !important;
}
</style>
