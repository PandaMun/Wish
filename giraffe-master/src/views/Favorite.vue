<template>
  <div>
    <div>
      <div class="my-6" style="padding-bottom: 40px">
        <h2 class="text-h3 text-center font-weight-bold mt-5">Favorite</h2>
      </div>
      <template>
        <div id="here" style="display: flex; justify-content: center">
          <v-data-table
            style="width: 40%"
            :headers="headers"
            :items="favorites"
            :items-per-page="10"
            class="elevation-1 hellohello"
            @click:row="write"
            ><template v-slot:[`item.actions`]="{ item }">
              <v-icon small @click.stop="deleteFavorite(item.dongCode)"> mdi-delete </v-icon>
            </template>
          </v-data-table>
        </div>
      </template>
    </div>
  </div>
  <!-- <div>
    <input type="text" v-model="dongCode" id="dongCode" />
    <button class="button" @click="[write(), moveToMap()]">검색</button>
  </div> -->
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
      headers: [
        { text: "시/도", value: "sidoName" },
        { text: "구/군", value: "gugunName" },
        { text: "동", value: "dongName" },
        { text: "삭제", value: "actions", sortable: false },
      ],
    };
  },
  methods: {
    moveToMap() {
      this.$router.push({ path: "/map" });
    },
    write: function (item, row) {
      console.log("value: " + row.dongCode);
      console.log("value: " + item.dongCode);
      // console.log("동코드 : " + document.getElementById("dongCode").value);
      // this.dongCode = document.getElementById("dongCode").value;
      this.dongCode = item.dongCode;
      // this.value = this.$refs.getValue.value;
      // console.log("가기 전 : " + this.$refs.getValue.value);
      this.value = item.sidoName + " " + item.gugunName + " " + item.dongName;
      this.showLocation(this.value);
      console.log("가기 전 dongCode : " + this.dongCode);
      this.storeDongcode(this.dongCode);
      this.moveToMap();
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
    deleteFavorite(dongCode) {
      let url = "/wish/user/interest";
      console.log(dongCode);
      console.log(this.userId);
      http
        .delete(url, {
          data: {
            userId: this.userId,
            dongCode: dongCode,
          },
        })
        .then(({ data }) => {
          console.log(data);
          alert("성공적으로 삭제되었습니다.");
          this.getFavorites();
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
<style>
.hellohello .v-data-table-header tr th {
  font-size: 20px !important;
  text-align: center !important;
}
.hellohello > .v-data-table__wrapper > table > tbody > tr > td {
  font-size: 17px;
  text-align: center !important;
}
</style>
