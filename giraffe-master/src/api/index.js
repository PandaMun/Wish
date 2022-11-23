import axios from "axios";


const instance = axios.create({
  baseURL: "http://localhost:80/wish",
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
});

function apiInstance() {
    return instance;
};
function Logout(accessToken) {
  return instance(accessToken);
}

export { apiInstance ,Logout};