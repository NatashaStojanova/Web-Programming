import axios from '../custom-axios/axios'
import qs from 'qs'

const IngredientService = {
    fetchIngredients: () => {
        return axios.get("/ingredients");
    },
    fetchIngredientsPaged: (page, pageSize) => {
        return axios.get("/ingredients", {
            headers: {
                'page': page, 'page-size': pageSize
            }
        })
    },

    addIngredient: (ingredient) => {
        const data = {
            ...ingredient,
            ingredientName: ingredient.name
        };
        const formParams = qs.stringify(data);
        return axios.post("/ingredients", formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    },

    deleteIngredient(ingredientID) {
        return axios.delete("/ingredients/" + ingredientID)
    },

    updateIngredient: (ingredient) => {
        const data = {
            ...ingredient,
            name: ingredient.name
        };
        const id = ingredient.id;
        const formParams = qs.stringify(data);
        return axios.patch("/ingredients/" + id, formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    }
};

export default IngredientService;
