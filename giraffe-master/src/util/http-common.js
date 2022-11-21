import axios from "axios";


export default axios.create({
    // baseURL: "http://172.30.1.49:80/",
    // baseURL: "http://192.168.27.58:80/",
    baseURL: "http://localhost:80/",
    headers: {
        "Content-Type": "application/json",
    }
});