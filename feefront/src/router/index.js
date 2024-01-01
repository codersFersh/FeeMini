import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/components/Home.vue';
import Login from '@/views/login/Login.vue';
import PayManage from '@/views/pay/PayManage.vue';
import PayAdd from '@/views/pay/PayAdd.vue';
import IncomeManage from '@/views/income/IncomeManage.vue';
import IncomeAdd from '@/views/income/IncomeAdd.vue';
import StatManage from '@/views/stat/StatManage.vue';
import Warning from '@/views/stat/Warning.vue';
import DetailManage from '@/views/detail/DetailManage.vue';

Vue.use(VueRouter);

const routes = [{
        path: '/',
        redirect: '/Login',
    },
    {
        path: '/Home',
        component: Home,
        meta: { authRequired: true },
        redirect: '/Home/StatManage',
        children: [{
                path: 'PayManage',
                component: () =>
                    import ('@/views/pay/PayManage.vue'),
            },
            {
                path: 'IncomeManage',
                component: () =>
                    import ('@/views/income/IncomeManage.vue'),
            },
            {
                path: 'DetailManage',
                component: () =>
                    import ('@/views/detail/DetailManage.vue'),
            },
            {
                path: 'StatManage',
                component: () =>
                    import ('@/views/stat/StatManage.vue'),
            },
            {
                path: 'Warning',
                component: () =>
                    import ('@/views/stat/Warning.vue'),
            },
        ],
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
    {
        path: '/PayAdd',
        name: 'PayAdd',
        component: PayAdd,
    },
    {
        path: '/Login',
        name: 'Login',
        component: Login,
    },
    {
        path: '/DetailManage',
        name: 'DetailManage',
        component: DetailManage,
    },
    {
        path: '/Warning',
        name: 'Warning',
        component: Warning,
    },
];

const router = new VueRouter({
    routes,
});


//全局前置守卫
//每次跳转页面执行该函数
//to,相当于this.$rotue,要跳到某个页面的路由信息
//from,代表当前页面的信息
router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('token');

    // 如果未经身份验证，并且不是已经在登录页面，则重定向到登录页面
    if (!token && to.path !== '/Login') {
        // 使用 Vue 实例的 this.$message
        router.app.$message.warning("请先登录");
        return next({ path: '/Login' });
    } else {
        // 如果已经身份验证，或者已经在 Login 页面，继续导航
        next();
    }
});



export default router;