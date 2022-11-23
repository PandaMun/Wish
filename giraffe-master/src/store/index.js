import Vue from 'vue'
import Vuex from 'vuex'

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
  }
})