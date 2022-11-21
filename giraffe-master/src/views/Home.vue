<template>
  <div>
    <div>
      <v-card>
        <v-img
          :aspect-ratio="16 / 9"
          dark
          gradient="to top, rgba(25,32,72,.7), rgba(25,32,72,.0)"
          height="600px"
          src="../images/main.png"
        >
          <v-row class="flex-column">
            <v-col cols="12">
              <h2 class="text-h1">HAPPY HOUSE</h2>
              <div class="search-home">
                <input
                  class="search-txt"
                  id="autoComplete"
                  v-model="text"
                  @input="searchList($event)"
                  type="text"
                  ref="getValue"
                  style="width: 400px; height: 40px; font-size: 25px"
                  placeholder="  검색어를 입력하세요."
                  onfocus="this.placeholder = ''"
                  onblur=" this.placeholder = '  검색어를 입력하세요.'"
                />
                <button class="search-btn" style="width: 40px; height: 40px" @click="moveToMap()">
                  검색
                </button>
                <container class="rel_search">
                  <ul class="search_list"></ul>
                </container>
              </div>
            </v-col>
          </v-row>
        </v-img>
      </v-card>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";

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
      findList: [],
    };
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
        data.map((row) => {
          tmp.push(row.sidoName + " " + row.gugunName + " " + row.dongName);
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
            minLength: 2, // 최소 글자수
            delay: 500, //autocomplete 딜레이 시간(ms)
            //disabled: true, //자동완성 기능 끄기
          });
        }
        this.text = event.target.value;
        console.log(this.text);
      });
    },
    moveToMap() {
      this.value = this.$router.push({ path: "/map", params: this.$refs.getValue.value });
      console.log("here :" + this.$refs.getValue.value);
    },
  },
  components: {
    siderbar: () => import("@/components/details/sidebar"),
  },
};
</script>

<style scoped>
h2 {
  text-align: center;
  font-weight: bold;
  margin-top: 200px;
}
.search-home {
  position: absolute;
  background-color: white;
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 40px;
}
.search-btn {
  background-color: grey;
  position: absolute;
  border-radius: 40px;
}
</style>
