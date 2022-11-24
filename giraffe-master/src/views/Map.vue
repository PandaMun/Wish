<template>
  <div>
    <v-row class="overflow-hidden">
      <v-col class="pa-0 left-map" cols="12" lg="12" xl="12">
        <div v-if="place === null">
          <div id="map" class="map"></div>
        </div>
        <div v-else>
          <div id="map" class="map"></div>
          <!-- {{ move() }} -->
        </div>
      </v-col>
      <v-col class="right-info overflow-auto pa-5" style="height: 93vh; display: none">
        <div v-if="place == null"></div>
        <div v-if="place != null">
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
                <v-btn class="mx-2" fab small color="error" @click="closeSideBar">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
              </div>
            </div>
            <v-divider></v-divider>
            <div>
              <div id="roadview" style="width: 100%; height: 300px"></div>
              <!-- <v-img src="../images/background.png" :aspect-ratio="16 / 9" height="100%"></v-img> -->
              <h4 class="text-h6 font-weight-bold pt-4 pb-4">{{ aptName }}</h4>
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
import jwtDecode from "jwt-decode";
export default {
  data() {
    return {
      map: Object,
      geocoder: null,
      place: this.$store.state.location,
      display: false,
      dc: "",
      aptCode: "",
      headers: [
        { text: "계약일", value: "dealDate" },
        { text: "매매가격(만원)", value: "dealAmount" },
        { text: "층수(층)", value: "floor" },
        { text: "면적(m2)", value: "area" },
      ],
      deals: [],
      info: "",

      dongCode: "",
      aptList: [],
      positions: [],

      userId: "",
    };
  },
  watch: {},
  mounted() {
    if (sessionStorage.getItem("accessToken") != null) {
      console.log("asdfasdfasdasdfasdf");
      this.userId = jwtDecode(sessionStorage.getItem("accessToken")).sub;
      console.log(jwtDecode(sessionStorage.getItem("accessToken")));
    }
    console.log("created userId : " + this.userId);
    // window.kakao && window.kakao.maps ? this.initMap() : this.addKakaoMapScript();
    console.log("mount 후 : " + this.$store.state.location);
    console.log("mount 후 dongCode : " + this.$store.state.dongCode);
    this.dongCode = this.$store.state.dongCode;
    if (window.kakao && window.kakao.maps) {
      console.log("1");
      this.initMap();
      this.getAptListByDong();
    } else {
      console.log("2");
      this.getAptListByDong();
      const script = document.createElement("script");
      /* global kakao */
      // script.src = "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4883376f4eefddd799ae8fdefeedd639";
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4883376f4eefddd799ae8fdefeedd639&libraries=services";
      script.onload = () => kakao.maps.load(this.initMap);

      document.head.appendChild(script);

      // this.makeDeals();
      console.log("map : " + this.$route.params);
    }
  },

  methods: {
    makeDeals() {
      let url = "/wish/apartments/info/" + this.aptCode;
      http.get(url).then(({ data }) => {
        console.log("aptCode : " + this.aptCode);
        console.log(url);
        console.log("아파트 거래내역 호출");
        console.log(data);
        this.deals = data;
      });
    },
    getInfo() {
      let url = "/wish/apartments/info/" + this.aptCode;
      http.get(url).then(({ data }) => {
        console.log(data);
        this.info = data;
      });
    },
    getAptListByDong() {
      let url = "/wish/aptinfo/" + this.dongCode;
      http.get(url).then(({ data }) => {
        console.log(data);
        data.map((row) => {
          let tmp = {
            title: row.apartmentName,
            latlng: new kakao.maps.LatLng(row.lat, row.lng),
            lat: row.lat,
            lng: row.lng,
            aptCode: row.aptCode,
          };
          console.log("tmp : " + tmp.title + " " + tmp.latlng + " " + tmp.aptCode);
          this.positions.push(tmp);
        });
        this.move();
        console.log("after call api : " + this.positions);
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
      const vueInstance = this;
      var nlevel;
      //지도를 담을 영역의 DOM 레퍼런스
      var container = document.getElementById("map");
      //지도를 생성할 때 필요한 기본 옵션
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표
        level: 4, //지도의 레벨(확대, 축소 정도)
      };

      //지도 생성
      this.map = new kakao.maps.Map(container, options);
      console.log(this.map);
      function hello2() {
        return function () {
          console.log("????" + this);
          // 지도 영역정보를 얻어옵니다
          var bounds = this.getBounds();

          // 영역정보의 남서쪽 정보를 얻어옵니다
          var swLatlng = bounds.getSouthWest();

          // 영역정보의 북동쪽 정보를 얻어옵니다
          var neLatlng = bounds.getNorthEast();

          var message = "<p>영역좌표는 남서쪽 위도, 경도는  " + swLatlng.toString() + "이고 <br>";
          message += "북동쪽 위도, 경도는  " + neLatlng.toString() + "입니다 </p>";

          console.log(message);

          var neLat = this.getBounds().getNorthEast().getLat();
          var neLng = this.getBounds().getNorthEast().getLng();
          var swLat = this.getBounds().getSouthWest().getLat();
          var swLng = this.getBounds().getSouthWest().getLng();

          let ML = {
            swLat: swLat,
            swLng: swLng,
            neLat: neLat,
            neLng: neLng,
            mapLevel: options.level,
          };

          let url = "/wish/apartments/list";
          http.post(url, ML).then(({ data }) => {
            console.log("맵 옮기고 난 아파트 : " + data);
            vueInstance.positions = [];
            data.map((row) => {
              let tmp = {
                title: row.apartmentName,
                latlng: new kakao.maps.LatLng(row.lat, row.lng),
                lat: row.lat,
                lng: row.lng,
                aptCode: row.aptCode,
              };
              console.log("tmp : " + tmp.title + " " + tmp.latlng + " " + tmp.aptCode);
              vueInstance.positions.push(tmp);
            });

            var imageSrc =
              "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
            for (var i = 0; i < vueInstance.positions.length; i++) {
              console.log("여긴?? " + vueInstance.positions[i].title);
              // 마커 이미지의 이미지 크기 입니다
              var imageSize = new kakao.maps.Size(24, 35);

              // 마커 이미지를 생성합니다
              var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
              console.log(this);
              // 마커를 생성합니다
              var marker = new kakao.maps.Marker({
                map: vueInstance.map, // 마커를 표시할 지도
                position: vueInstance.positions[i].latlng, // 마커를 표시할 위치
                title: vueInstance.positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                image: markerImage, // 마커 이미지
                clickable: true, // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
                aptCode: vueInstance.positions[i].aptCode,
              });
              console.log("this점맵 : " + vueInstance.map);
              marker.setMap(vueInstance.map);
              // console.log("asdfasdf:" + this.positions);
              function hello(i) {
                return function () {
                  console.log(marker.aptCode);
                  console.log("i: " + i);
                  console.log("vueInstance.positions[i]", vueInstance.positions[i]);
                  vueInstance.aptCode = vueInstance.positions[i].aptCode;
                  vueInstance.aptName = vueInstance.positions[i].title;
                  console.log("여기 aptCode : " + vueInstance.aptCode);
                  // getInfo();
                  vueInstance.makeDeals();
                  vueInstance.openSideBar();
                  var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
                  var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
                  var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

                  var position = new kakao.maps.LatLng(
                    vueInstance.positions[i].lat,
                    vueInstance.positions[i].lng
                  );

                  // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
                  roadviewClient.getNearestPanoId(position, 50, function (panoId) {
                    roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
                  });
                };
              }
              kakao.maps.event.addListener(marker, "click", hello(i));
            }
          });
        };
      }
      kakao.maps.event.addListener(this.map, "dragend", hello2());
    },

    move() {
      const vueInstance = this;
      // 주소-좌표 변환 객체를 생성합니다
      this.geocoder = new kakao.maps.services.Geocoder();

      // 검색한 주소 설정
      var temp = this.map;
      this.place = this.$store.state.location;
      console.log("검색 후 : " + this.place);

      // 검색한 주소로 이동
      this.geocoder.addressSearch(this.place, function (result, status) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          console.log("place 좌표 : " + coords);
          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
            // map: this.map,
            position: coords,
          });

          // 마커 찍기
          marker.setMap(temp);

          kakao.maps.event.addListener(marker, "click", function () {
            let flag;
            http
              .post("/wish/user/interest/check", {
                userId: vueInstance.userId,
                dongCode: vueInstance.$store.state.dongCode,
              })
              .then(({ data }) => {
                console.log("data: " + data);
                if (data === "존재합니다.") {
                  flag = true;
                } else {
                  flag = false;
                }
                console.log("flag : " + flag);
                if (flag == true) {
                  alert("이미 관심지역으로 등록되어있는 지역입니다.");
                  return;
                }
                console.log("마커 선택 " + vueInstance.place);
                var result = confirm(vueInstance.place + " -> 관심 지역으로 등록하시겠습니까?");
                console.log(result);
                if (result) {
                  let url = "/wish/user/interest/";
                  let request = {
                    userId: vueInstance.userId,
                    dongCode: vueInstance.$store.state.dongCode,
                  };
                  console.log("request: " + request.userId);
                  console.log("request: " + request.dongCode);
                  http.post(url, request).then(({ data }) => {});
                  alert("등록이 완료되었습니다.");
                }
              });
          });

          // 인포윈도우로 장소에 대한 설명을 표시합니다
          var infowindow = new kakao.maps.InfoWindow({
            content:
              '<div style="width:150px;text-align:center;padding:6px 0;">' +
              vueInstance.place +
              "</div>",
          });
          infowindow.open(temp, marker);
          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          temp.setCenter(coords);
        }
      });
      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      console.log("this.positions : " + this.positions.length);

      for (var i = 0; i < this.positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        console.log(this);
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: this.positions[i].latlng, // 마커를 표시할 위치
          title: this.positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
          clickable: true, // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
          aptCode: this.positions[i].aptCode,
        });
        marker.setMap(this.map);
        console.log("asdfasdf:" + this.positions);
        function hello(i) {
          return function () {
            console.log(marker.aptCode);
            console.log("i: " + i);
            console.log("vueInstance.positions[i]", vueInstance.positions[i]);
            vueInstance.aptCode = vueInstance.positions[i].aptCode;
            vueInstance.aptName = vueInstance.positions[i].title;
            console.log("여기 aptCode : " + vueInstance.aptCode);
            // getInfo();
            vueInstance.makeDeals();
            vueInstance.openSideBar();
            var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
            var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
            var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

            var position = new kakao.maps.LatLng(
              vueInstance.positions[i].lat,
              vueInstance.positions[i].lng
            );

            // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
            roadviewClient.getNearestPanoId(position, 50, function (panoId) {
              roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
            });
          };
        }
        kakao.maps.event.addListener(marker, "click", hello(i));
        console.log("this.map : " + this.map);
      }
    },
    closeSideBar() {
      console.log("close");
      document.querySelector(".right-info").setAttribute("style", "display:none");
      document.querySelector(".left-map").classList.remove("col-lg-9");
      document.querySelector(".left-map").classList.remove("col-xl-9");
      document.querySelector(".left-map").classList.remove("col-9");
      document.querySelector(".left-map").classList.add("col-lg-12");
      document.querySelector(".left-map").classList.add("col-xl-12");
      document.querySelector(".left-map").classList.add("col-12");
      // this.initMap();
      // this.move();
    },
    openSideBar() {
      console.log("open");
      document.querySelector(".right-info").setAttribute("style", "display:block");
      document.querySelector(".left-map").classList.add("col-lg-9");
      document.querySelector(".left-map").classList.add("col-xl-9");
      document.querySelector(".left-map").classList.add("col-9");
      document.querySelector(".left-map").classList.remove("col-lg-12");
      document.querySelector(".left-map").classList.remove("col-xl-12");
      document.querySelector(".left-map").classList.remove("col-12");
    },
  },
  created() {
    this.makeDeals();
  },

  computed: {
    where() {
      console.log("map : " + this.$store.state.location);
      console.log("맵 dongCode : " + this.$store.state.dongCode);
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
