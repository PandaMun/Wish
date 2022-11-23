
import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from "vuex-persistedstate";
import memberStore from "@/store/modules/memberStore";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    location: null,
    dongCode: null,
  },
  getters: {
  },
  mutations: {
    SHOW_LOCATION(state, payload) {
      state.location = payload;
    },
    STORE_DONGCODE(state, payload) {
      state.dongCode = payload;
    }
  },
  actions: {
  },
  modules: {
    memberStore
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
})
