import axios from '../custom-axios/axios'
import qs from 'qs'

const PizzaService = {
    fetchPizzas: () => {
        return axios.get("/pizzas");
    },
    fetchPizzasPaged: (page, pageSize) => {
        return axios.get("/pizzas", {
            headers: {
                'page': page, 'page-size': pageSize
            }
        })
    },


    addPizza: (pizza) => {
        const data = {
            ...pizza,
            pizzaName: pizza.name
        };
        const formParams = qs.stringify(data);
        return axios.post("/pizzas", formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    },


}

export default PizzaService;
