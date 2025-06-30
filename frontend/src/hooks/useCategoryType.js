// import {useEffect, useState} from "react";
// import categoryRepository from "../repository/categoryRepository.js";
//
// const useCategoryType = () => {
//     const [categories, setCategories] = useState([])
//
//     useEffect(() => {
//         categoryRepository
//             .findAll()
//             .then((response) => {
//                 setCategories(response.data)
//             })
//             .catch((error) => console.log(error))
//     }, []);
//     return categories
// }
// export default useCategoryType;

const useCategoryType = () => {
    return {
        categories: ["ROOM", "HOUSE", "FLAT", "APARTMENT", "HOTEL", "MOTEL"]
    };
};

export default useCategoryType;
