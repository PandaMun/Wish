<template>
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
  },
  created() {
    this.makeDeals();
  },
};
</script>
