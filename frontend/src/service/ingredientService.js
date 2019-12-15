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
    }
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
    /*updateConsultationTerm : (term) => {
        const data = {
            ...term,
            roomName:term.room.name
        }
        const slotId= term.slotId;
        const formParams = qs.stringify(data);
        return axios.patch("/api/consultations/"+slotId,formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'professorId':'kostadin.mishev'
            }
        });
    }*/
}

export default IngredientService;
