<template>
  <div>
    <input type="text" v-model="dongCode" id="dongCode" />
    <button class="button" @click="[write(), moveToMap()]">검색</button>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
import http from "@/util/http-common";
import jwtDecode from "jwt-decode";
export default {
  name: "Favorite",
  components: {
    siderbar: () => import("@/components/details/favorite"),
  },
  data() {
    return {
      dongCode: "",
      value: "",
      userId: "",
      favorites: [],
    };
  },
  methods: {
    moveToMap() {
      this.value = this.$router.push({ path: "/map" });
    },
    write: function () {
      console.log("동코드 : " + document.getElementById("dongCode").value);
      this.dongCode = document.getElementById("dongCode").value;
      this.value = this.$refs.getValue.value;
      console.log("가기 전 : " + this.$refs.getValue.value);
      this.showLocation(this.value);
      console.log("가기 전 dongCode : " + this.dongCode);
      this.storeDongcode(this.dongCode);
    },
    ...mapMutations({
      showLocation: "SHOW_LOCATION",
      storeDongcode: "STORE_DONGCODE",
    }),
    getFavorites() {
      let url = "/wish/user/interest/info/" + this.userId;
      http.get(url).then(({ data }) => {
        console.log(data);
        this.favorites = data;
      });
    },
    deleteFavorite(i) {
      let url = "wish/user/interest/" + this.favorites[i].id;
      http.delete(url).then(({ data }) => {
        console.log(data);
        alert("성공적으로 삭제되었습니다.");
        // this.makeList();
      });
    },
  },
  created() {
    if (sessionStorage.getItem("accessToken") != null) {
      console.log("asdfasdfasdasdfasdf");
      this.userId = jwtDecode(sessionStorage.getItem("accessToken")).sub;
      console.log(jwtDecode(sessionStorage.getItem("accessToken")).sub);
    }
    this.getFavorites();
    // let url = "/wish/user/interest/info/" + this.userId;
    // http.get(url).then(({ data }) => {
    //   console.log(data);
    // });
  },
};
</script>
