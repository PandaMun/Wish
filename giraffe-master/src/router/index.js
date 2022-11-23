import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Map from "@/views/Map.vue"

Vue.use(VueRouter)

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
    const checkUserInfo = store.getters["memberStore/checkUserInfo"];
    const checkToken = store.getters["memberStore/checkToken"];
    let token = sessionStorage.getItem("access-token");
    console.log("로그인 처리 전", checkUserInfo, token);
  
    if (checkUserInfo != null && token) {
      console.log("토큰 유효성 체크하러 가자!!!!");
      await store.dispatch("memberStore/getUserInfo", token);
    }
    if (!checkToken || checkUserInfo === null) {
      alert("로그인이 필요한 페이지입니다..");
      // next({ name: "login" });
      router.push({ name: "login" });
    } else {
      console.log("로그인 했다!!!!!!!!!!!!!.");
      next();
    }
  };

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
        path: '/signup',
        name: 'signup',
        component: () => import("@/views/Signup.vue"),
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
    },
]

const router = new VueRouter({
    routes,
    mode: "history",
  base: "http://localhost:80/wish",
    scrollBehavior() {
        document.getElementById('app').scrollIntoView();
    }
})

export default router
