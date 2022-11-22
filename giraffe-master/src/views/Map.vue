<template>
  <div>
    <v-row class="overflow-hidden">
      <v-col class="pa-0 left-map" cols="9" lg="9" xl="9"> <div id="map" class="map"></div></v-col>
      <v-col class="right-info overflow-auto pa-5" cols="3" lg="3" xl="3" style="height: 94vh">
        <div>
          <div>
            <div style="display: flex; justify-content: space-between; align-items: center; padding-bottom: 10px">
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
      map: null,
      geocoder: null,

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
      script.src = "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4883376f4eefddd799ae8fdefeedd639";
      document.head.appendChild(script);
    },
    initMap() {
      //지도를 담을 영역의 DOM 레퍼런스
      var container = document.getElementById("map");
      //지도를 생성할 때 필요한 기본 옵션
      var options = {
        center: new kakao.maps.LatLng(35.190801118503, 126.82393787615513), //지도의 중심좌표
        level: 3, //지도의 레벨(확대, 축소 정도)
      };

      //지도 생성
      this.map = new kakao.maps.Map(container, options);
    },

    move() {
      // 주소-좌표 변환 객체를 생성합니다
      this.geocoder = new kakao.maps.services.Geocoder();

      // 주소로 좌표를 검색합니다
      geocoder.addressSearch("제주특별자치도 제주시 첨단로 242", function (result, status) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
            map: map,
            position: coords,
          });

          // 인포윈도우로 장소에 대한 설명을 표시합니다
          var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>',
          });
          infowindow.open(map, marker);

          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          this.map.setCenter(coords);
        }
      });
    },
  },
  created() {
    if (window.kakao && window.kakao.maps) {
      console.log("1");
      this.initMap();
    } else {
      console.log("2");
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4883376f4eefddd799ae8fdefeedd639";
      document.head.appendChild(script);

      this.makeDeals();
      console.log("map : " + this.$route.params);
    }
    // this.initMap();
    // this.addKakaoMapScript();
    // window.kakao && window.kakao.maps ? this.initMap() : this.addKakaoMapScript();
  },

  computed: {
    where() {
      console.log("map : " + this.$store.state.location);
      // console.log(process.env.VUE_APP_KAKAOMAP_KEY);
      return this.$store.state.location;
    },
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
