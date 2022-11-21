<template>
  <div>
    <div id="map" class="map"></div>
  </div>
</template>

<script>
export default {
  // props: {
  //   name: {
  //     type: String,
  //     default: "",
  //   },
  //   age: {
  //     type: Number,
  //     default: 0,
  //   },
  // },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addKakaoMapScript();
  },
  methods: {
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
    console.log("map : " + this.$route.params);
  },
};
</script>

<style>
.map {
  width: 100%;
  height: 90vh;
}
</style>
