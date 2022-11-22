<template>
  <div>
    <v-row class="overflow-hidden">
      <v-col class="pa-0 left-map" cols="9" lg="9" xl="9"> <div id="map" class="map"></div></v-col>
      <v-col class="right-info overflow-auto pa-5" cols="3" lg="3" xl="3" style="height: 94vh">
        <div>
          <div>
            <div
              style="
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding-bottom: 10px;
              "
            >
              <h4 class="text-h5 font-weight-bold pt-4 pb-4">아파트 상세 정보</h4>
              <div>
                <v-btn class="mx-2" fab small color="error">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
              </div>
            </div>
            <v-divider></v-divider>
            <div>
              <v-img src="../images/background.png" :aspect-ratio="16 / 9" height="100%"></v-img>
              <h4 class="text-h6 font-weight-bold pt-4 pb-4">반포 자이 2차 아파트</h4>
              <v-divider></v-divider>
              <h4 class="text-h5 font-weight-bold pt-4 pb-4">거래내역</h4>
              <template>
                <v-data-table
                  font-size="1px"
                  :headers="headers"
                  :items="deals"
                  :items-per-page="5"
                  class="elevation-1"
                ></v-data-table>
              </template>
            </div>
          </div>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  data() {
    return {
      display: false,
      aptCode: "11110000000001",
      headers: [
        { text: "계약일", value: "dealDate" },
        { text: "매매가격(만원)", value: "dealAmount" },
        { text: "층수(층)", value: "floor" },
        { text: "면적(m2)", value: "area" },
      ],
      deals: [],
      infos: [],
    };
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addKakaoMapScript();
  },
  methods: {
    makeDeals() {
      let url = "/wish/apartments/info/" + this.aptCode;
      http.get(url).then(({ data }) => {
        console.log(data);
        this.deals = data;
      });
    },
    getInfo() {
      let url = "/wish/apartments/info/" + this.aptCode;
      http.get(url).then(({ data }) => {
        console.log(data);
        this.infos = data;
      });
    },
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4883376f4eefddd799ae8fdefeedd639";
      document.head.appendChild(script);
    },
    initMap() {
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(35.190801118503, 126.82393787615513), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };

      var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    },
  },
  created() {
    this.makeDeals();
    console.log("map : " + this.$route.params);
  },
  components: {
    // siderbar: () => import("@/components/details/sidebar"),
  },
};
</script>

<style>
.map {
  width: 100%;
  height: 94vh;
}
</style>
