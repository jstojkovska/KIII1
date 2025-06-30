import axiosInstance from "../axios/axios.js";

const categoryRepository = {
    findAll: async () => {
        return await axiosInstance.get("/accommodations/categories")
    }
}
export default categoryRepository;