import memberStore from "../store/modules/memberStore.js";
import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["accessToken"] = sessionStorage.getItem("accessToken");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(token, success, fail) {
  await api.post(`/logout`, { headers: { accessToken: sessionStorage.getItem("accessToken") } }).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout };
