<template>
  <div>
    <v-navigation-drawer
      v-if="!$vuetify.breakpoint.smAndUp"
      v-model="drawer"
      :clipped="$vuetify.breakpoint.lgAndUp"
      app
      color="primary"
      dark
    >
      <v-list color="primary" nav>
        <v-list-item
          v-for="(item, i) in btnItems"
          :key="i"
          :href="item.href"
          :target="item.target"
          :to="item.to"
          link
        >
          <v-list-item-content>
            <v-list-item-title>{{ item.text }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          v-for="(item, i) in barItems"
          :key="i"
          :href="item.href"
          :target="item.target"
          :to="item.to"
          link
        >
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar :clipped-left="$vuetify.breakpoint.lgAndUp" app color="white" elevate-on-scroll flat>
      <v-container :class="{ 'px-0': !$vuetify.breakpoint.smAndUp }">
        <v-row :no-gutters="!$vuetify.breakpoint.smAndUp" align="center" justify="space-between">
          <v-col class="d-flex">
            <v-app-bar-nav-icon
              v-if="!$vuetify.breakpoint.mdAndUp"
              @click.stop="drawer = !drawer"
            />
            <v-toolbar-title
              class="font-weight-bold text-h5 primary--text d-flex justfiy-content-center align-items-center"
              style="cursor: pointer"
              @click="$router.push('/')"
            >
              <v-icon color="green" size="25">mdi-home-outline</v-icon>
              <span>구해줘</span>
              <span class="green--text">홈즈</span>
            </v-toolbar-title>
          </v-col>

          <v-col v-if="$vuetify.breakpoint.mdAndUp" cols="6">
            <v-btn
              v-for="(item, i) in barItems"
              :key="i"
              :to="item.to"
              class="text-capitalize"
              exact
              exact-active-class="accent--text"
              text
              >{{ item.title }}
            </v-btn>
          </v-col>
          <v-col v-if="$vuetify.breakpoint.mdAndUp" class="text-right">
            <v-badge v-if="data" bordered color="error" icon="mdi-lock" overlap>{{
              data.username
            }}</v-badge>
            <v-btn v-if="data" color="danger" to="/mypage" class="ml-3 text-capitalize">
              <v-icon left>mdi-login</v-icon>
              MyPage
            </v-btn>
            <v-btn v-if="data" color="danger" class="ml-3 text-capitalize" @click="onClickLogout()"
              ><v-icon left>mdi-login</v-icon>
              Logout
            </v-btn>
            <v-btn v-else color="danger" to="/login" class="ml-3 text-capitalize"
              ><v-icon left>mdi-login</v-icon>
              Login
            </v-btn>

            <!-- <v-btn
              v-for="(item, i) in btnItems"
              :key="i"
              :color="item.color"
              :href="item.href"
              :outlined="item.outlined"
              :target="item.target"
              :to="item.to"
              class="ml-3 text-capitalize"
            >
              <v-icon left>{{ item.icon }}</v-icon>
              {{ item.text }}
            </v-btn> -->
          </v-col>
        </v-row>
      </v-container>
    </v-app-bar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
const memberStore = "memberStore";
export default {
  name: "appbar",
  data: () => ({
    btnItems: [
      {
        text: "MyPage",
        to: "/mypage",
        target: "_black",
        color: "danger",
        icon: "mdi-login",
      },
      {
        text: "Login",
        to: "/login",
        target: "_black",
        color: "primary",
        icon: "mdi-login",
      },
      {
        text: "Logout",
        to: "/logout",
        target: "_black",
        color: "primary",
        icon: "mdi-logout",
      },
    ],
    barItems: [
      {
        title: "Home",
        to: "/",
      },
      {
        title: "Map",
        to: "/map",
      },
      {
        title: "Notice",
        to: "/notice",
      },
      {
        title: "QnA",
        to: "/qna",
      },
      {
        title: "Favorite",
        to: "/favorite",
      },
    ],
  }),
  computed: {
    ...mapState(memberStore, ["isLogin", "data", "accessToken", "refreshToken"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      console.log(sessionStorage.getItem("accessToken"));
      this.userLogout();
      sessionStorage.removeItem("accessToken"); //저장된 토큰 없애기
      sessionStorage.removeItem("refreshToken"); //저장된 토큰 없애기
      console.log(sessionStorage);
      if (this.$route.path != "/") this.$router.push("/");
    },
  },
};
</script>
