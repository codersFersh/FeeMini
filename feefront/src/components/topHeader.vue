<template>
  <div class="topHeader">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#B3C0D1">
      <el-col :span="24">
        <el-menu-item index="1" class="menu_end" title="退出登录">
          <a @click="logout" target="_blank"><img src="@/assets/退出.png" /></a>
        </el-menu-item>
      </el-col>
    </el-menu>
  </div>
</template>
<script>
import { Logout } from "@/utils/port";
import router from '@/router';
import { Message } from 'element-ui';
export default {
  name: 'topHeader',
  data() {
    return {
      activeIndex: '1',
    }
  },
  methods: {
    async logout() {
    // 从本地存储中获取token
    const token = window.localStorage.getItem("token");

    try {
        // 调用接口
        await Logout({}, {
            headers: {
                // Authorization: token,
                Token: token,
            },
        });

        // 删除本地存储中的token
        window.localStorage.removeItem("token");

        // 检查当前路由是否是根路由，如果不是才执行重定向
        // if (router.currentRoute.path !== '/') {
        //     router.replace("/");
        // }
        this.$router.push("/");

        Message.success("已退出登录");
    } catch (error) {
        console.error(error);
    }
}


  }
}
</script>
<style>
.topHeader {
  position: absolute;
  top: 0;
  right: 0;
  padding: 0 auto;
  max-width: 70px;
  width: 100%;
  top: 0;
  right: 0;
  border: -0.727;
}

.el-menu.el-menu--horizontal {
  border-bottom: solid 0px #e6e6e6 !important;
}

.menu_end {
  right: 0;
  position: absolute;
  height: 60px;
  /* background-color: rgb(84, 92, 100) !important; */
}

#img-h2 {
  top: -6px;
  left: 50px;
  vertical-align: middle;
  text-align: center;
  position: absolute;
  color: #eee;
}

.el-menu-demo {
  background-color: #B3C0D1 !important;
}
</style>