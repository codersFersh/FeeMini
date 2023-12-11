import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/components/Home.vue';
import test from '@/views/testview/test.vue';

Vue.use(VueRouter);

const routes = [{
        path: '/',
        redirect: '/home'
    }, {
        path: '/home',
        component: Home,
        meta: { authRequired: true },
        children: [{
            path: '/test',
            component: () =>
                import ('@/views/testview/test.vue')
        }, ],
    },
    {
        path: '/test',
        name: 'test',
        component: test,
    },
];

const router = new VueRouter({
    routes,
});

// 全局前置守卫
// 每次跳转页面执行该函数
// to, 相当于 this.$route，要跳到某个页面的路由信息
// from, 代表当前页面的信息
// router.beforeEach((to, from, next) => {
//     const token = localStorage.getItem('token');
//     if (to.name !== 'Login' && !token) next({ name: 'Login' });
//     // 如果用户未能验证身份，则 `next` 会被调用两次
//     next();
// });

export default router;