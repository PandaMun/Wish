import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Map from "@/views/Map.vue"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/favorite',
        name: 'Favorite',
        component: () => import("@/views/Favorite.vue"),
    },
    {
        path: '/notice',
        name: 'Notice',
        component: () => import("@/views/Notice.vue"),
    },
    {
        path: '/qna',
        name: 'Qna',
        component: () => import("@/views/Qna.vue"),
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import("@/views/Login.vue"),
    },
    {
        path: '/map',
        component: Map,
        props: true,
    },
    {
        path: '/mypage',
        name: 'MyPage',
        component: () => import("@/views/MyPage.vue"),
    }
]

const router = new VueRouter({
    routes,
    scrollBehavior() {
        document.getElementById('app').scrollIntoView();
    }
})

export default router
