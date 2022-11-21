<!-- <template>
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
                  @keyup="searchList"
                  type="text"
                  style="width: 400px; height: 40px; font-size: 25px"
                  placeholder="  검색어를 입력하세요."
                  onfocus="this.placeholder = ''"
                  onblur=" this.placeholder = '  검색어를 입력하세요.'"
                />
                <button class="search-btn" style="width: 40px; height: 40px">검색</button>
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
var locList = [
  "영등포본동",
  "영등포동",
  "여의동",
  "당산1동",
  "당산2동",
  "도림동",
  "문래동",
  "양평1동",
  "양평2동",
  "신길1동",
  "신길2동",
  "신길3동",
  "신길4동",
  "신길5동",
  "신길6동",
  "신길7동",
  "대림1동",
  "대림2동",
];
export default {
  name: "Home",
  data() {
    return {
      text: "",
      findList: [],
    };
  },
  methods: {
    searchList() {
      http.get(`/wish/apartments/${this.text}`).then(({ data }) => console.log(data));
      http.get(`/wish/apartments/${this.text}`).then(({ data }) => console.log(data));
    },
  },
  components: {
    siderbar: () => import("@/components/details/sidebar"),
  },
};

$(document).ready(function () {
  // input필드에 자동완성 기능을 걸어준다
  $("#autoComplete").autocomplete({
    source: findList,
    focus: function (event, ui) {
      return false;
    },
    select: function (event, ui) {},
    minLength: 1,
    delay: 100,
    autoFocus: true,
  });
});
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
  background-color: blue;
  position: absolute;
  border-radius: 40px;
}
</style> -->
