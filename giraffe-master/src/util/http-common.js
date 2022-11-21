import axios from "axios";


export default axios.create({
    baseURL: "http://172.30.1.49:80/",
    headers: {
        "Content-Type": "application/json",
    }
});