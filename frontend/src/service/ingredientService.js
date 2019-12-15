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
    /* addConsultationTerm: (term) => {
         const data = {
             ...term,
             roomName:term.room.name
         }
         const formParams = qs.stringify(data);
         return axios.post("/api/consultations",formParams, {
             headers: {
                 'Content-Type': 'application/x-www-form-urlencoded',
                 'professorId':'kostadin.mishev'
             }
         });
     },*/
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
}

export default IngredientService;
