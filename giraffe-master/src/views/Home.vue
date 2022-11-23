<template>
  <div>
    <div>
      <v-row
        class="flex-column"
        align="center"
        justify="center"
        style="margin-top: 20vh; margin-bottom: 20vh"
      >
        <v-col
          cols="12"
          style="background-color: white; padding-top: 100px; padding-bottom: 100px; width: 1000px"
        >
          <v-row justify="center" class="mt-500">
            <h1 style="font-size: 50px; padding-bottom: 20px">어떤 집을 찾고 계세요?</h1>
            <!-- <v-img src="../images/happyhouse-removebg-preview.png" max-width="300"> </v-img> -->
          </v-row>
          <v-row justify="center" align="center" style="margin-left: 20px">
            <v-icon large>mdi-magnify</v-icon>
            <input
              class="search-txt"
              id="autoComplete"
              v-model="text"
              @input="searchList($event)"
              type="text"
              ref="getValue"
              style="
                width: 600px;
                height: 60px;
                font-size: 20px;

                border: 2px solid grey;
              "
              placeholder="  검색어를 입력하세요."
              onfocus="this.placeholder = ''"
              onblur=" this.placeholder = '  검색어를 입력하세요.'"
            />
            <input type="text" v-model="dongCode" id="dongCode" hidden />
            <button class="button" @click="[write(), moveToMap()]">검색</button>
            <container class="rel_search">
              <ul class="search_list"></ul>
            </container>
          </v-row>
        </v-col>
      </v-row>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapMutations } from "vuex";

// var findList = [];
$(function () {
  $("#autoComplete").autocomplete({
    source: function (request, response) {
      $.ajax({
        type: "get",
        contentType: "application/x-www-form-urlencoded; charset=euc-kr",
        url: "http://192.168.27.58:80/wish/dongs/" + Home.text,
        dataType: "json",
        success: function (data) {
          // 서버에서 json 데이터 response 후 목록 추가
          response(
            console.log(data),
            $.map(data, function (item) {
              return {
                label: item + "label",
                value: item,
                test: item + "test",
              };
            })
          );
        },
      });
    },
    select: function (event, ui) {
      console.log(ui);
      console.log(ui.item.label);
      console.log(ui.item.value);
      console.log(ui.item.test);
    },
    focus: function (event, ui) {
      return false;
    },
    minLength: 1,
    autoFocus: true,
    classes: {
      "ui-autocomplete": "highlight",
    },
    delay: 500,
    position: { my: "right top", at: "right bottom" },
    close: function (event) {
      console.log(event);
    },
  });
});

export default {
  name: "Home",
  data() {
    return {
      text: "",
      searchItem: "",
      value: "",
      findList: [],
      dongCode: "",
      sang: "",
    };
  },
  mounted() {
    this.sang = "";
    console.log(this.$store.state);
  },
  methods: {
    searchList(event) {
      // debugger;
      console.log("search");
      this.text = event.target.value;
      console.log("event value : " + event.target.value);
      if (this.text.length < 2) {
        return;
      }
      http.get(`/wish/dongs/${this.text}`).then(({ data }) => {
        let tmp = [];
        console.log(data);
        data.map((row) => {
          let o = {
            label: row.sidoName + " " + row.gugunName + " " + row.dongName,
            value: row.dongCode,
          };
          tmp.push(o);
        });
        this.findList = tmp;
        console.log(this.findList);
        if (this.findList.length > 0) {
          $("#autoComplete").autocomplete({
            //오토 컴플릿트 시작
            source: this.findList,
            focus: function (event, ui) {
              // 방향키로 자동완성단어 선택 가능하게 만들어줌
              return false;
            },
            select: function (event, ui) {
              // place the person.given_name value into the textfield called 'select_origin'...
              $("#autoComplete").val(ui.item.label);
              // this.dongCode = ui.item.value;
              // $("#dongCode").val(ui.item.value);
              $("#dongCode").attr("value", ui.item.value);
              console.log("label : " + ui.item.label);
              console.log("value : " + ui.item.value);
              return false;
            },
            minLength: 2, // 최소 글자수
            delay: 500, //autocomplete 딜레이 시간(ms)
            //disabled: true, //자동완성 기능 끄기
          });
        }
        this.text = event.target.value;
      });
    },
    moveToMap() {
      this.value = this.$router.push({ path: "/map", params: this.$refs.getValue.value });
    },
    ...mapMutations({
      showLocation: "SHOW_LOCATION",
      storeDongcode: "STORE_DONGCODE",
    }),

    write: function () {
      console.log("동코드 : " + document.getElementById("dongCode").value);
      this.dongCode = document.getElementById("dongCode").value;
      this.value = this.$refs.getValue.value;
      console.log("가기 전 : " + this.$refs.getValue.value);
      this.showLocation(this.value);
      console.log("가기 전 dongCode : " + this.dongCode);
      this.storeDongcode(this.dongCode);
    },
  },
  components: {
    siderbar: () => import("@/components/details/sidebar"),
    Footer: () => import("@/components/layout/footer.vue"),
  },
};
</script>

<style>
h2 {
  text-align: center;
  font-weight: bold;
  margin-top: 200px;
}
.search-home {
  /* position: absolute; */
  background-color: white;
  /* top: 55%; */
  /* left: 50%; */
  transform: translate(-50%, -50%);
  border-radius: 40px;
}
.button {
  background-color: #555555; /* Green */
  border: none;
  color: white;
  padding: 15px 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  height: 60px;
}
</style>
