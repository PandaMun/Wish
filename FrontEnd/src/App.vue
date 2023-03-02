<template>
  <v-app>
    <AppBar></AppBar>

    <v-container class="mt-16 ma-0 pa-0" fluid>
      <router-view></router-view>
    </v-container>

    <!-- <Footer></Footer> -->
  </v-app>
</template>

<script>
import AppBar from "@/components/layout/appbar.vue";
export default {
  name: "App",

  components: {
    AppBar,
    // Footer: () => import("@/components/layout/footer.vue"),
  },
  data: () => ({}),
};
</script>
<style>
#app {
  /* background-image: url("../images/background.jpg"); */
  /* background-size: cover; */
  /* filter: blur(8px); */
  /* -webkit-filter: blur(8px); */
}
</style>
