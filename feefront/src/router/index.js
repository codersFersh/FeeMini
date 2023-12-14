import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/components/Home.vue';
import test from '@/views/testview/test.vue';
import PayManage from '@/views/pay/PayManage.vue';
import IncomeManage from '@/views/income/IncomeManage.vue';
import IncomeAdd from '@/views/income/IncomeAdd.vue';
import StatManage from '@/views/stat/StatManage.vue';


Vue.use(VueRouter);

const routes = [{
        path: '/',
        redirect: '/Home'
    },
    {
        path: '/Home',
        component: Home,
        meta: { authRequired: true },
        children: [{
                path: 'test',
                component: () =>
                    import ('@/views/testview/test.vue')
            },
            {
                path: 'PayManage',
                component: () =>
                    import ('@/views/pay/PayManage.vue')
            },
            {
                path: 'IncomeManage',
                component: () =>
                    import ('@/views/income/IncomeManage.vue')
            },
            {
                path: 'StatManage',
                component: () =>
                    import ('@/views/stat/StatManage.vue')
            },
        ],
    },
    {
        path: '/test',
        name: 'test',
        component: test,
    },
    {
        path: '/PayManage',
        name: 'PayManage',
        component: PayManage,
    },
    {
        path: '/IncomeManage',
        name: 'IncomeManage',
        component: IncomeManage,
    },
    {
        path: '/StatManage',
        name: 'StatManage',
        component: StatManage,
    },
    {
        path: '/IncomeAdd',
        name: 'IncomeAdd',
        component: IncomeAdd,
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