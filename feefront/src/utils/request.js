import axios from 'axios';
import { Message } from 'element-ui';
import router from '@/router';
import qs from 'qs';

const baseURL = 'http://localhost:8090';



// request 拦截器
axios.interceptors.request.use(
        config => {
            config.headers.set("token", window.localStorage.getItem("token"));
            return config
        },
        error => {
            return Promise.reject(error)
        }
    )
    // reponse 拦截器
axios.interceptors.response.use(
    response => {
        const { data } = response;
        return data;
    },
    error => {
        if (error.response) {
            switch (error.response.status) {
                case 401:
                    localStorage.removeItem('token');
                    // 获取当前路由信息
                    const currentRoute = router.currentRoute;

                    // 如果当前路径不是登录页面，则重定向到登录页
                    if (currentRoute.path !== "/Login") {
                        router.push("/Login");
                    }
                    break;
            }
        }
        return Promise.reject(error);
    }
);





export const POST = (url, params) => {
    return axios.post(`${baseURL}${url}`, qs.stringify(params), {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-Requested-With': 'XMLHttpRequest',
        },
    });
};

export const POST2 = (url, params) => {
    return axios.post(`${baseURL}${url}`, params, {
        headers: {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest',
        },
    });
};

export const GET = (url, params) => {
    return axios.get(`${baseURL}${url}`, { params });
};

export const PUT = (url, params) => {
    return axios.put(`${baseURL}${url}`, params);
};

export const DELETE = (url, params) => {
    return axios.delete(`${baseURL}${url}`, { params });
};

export const PATCH = (url, params) => {
    return axios.patch(`${baseURL}${url}`, params);
};