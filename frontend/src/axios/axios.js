import axios from "axios";

const axiosInstance = axios.create({
    baseURL: "http://localhost/api",
    headers: {
        "Content-Type": "application/json"
    }
});

export default axiosInstance;