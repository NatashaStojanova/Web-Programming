import React, {useEffect, useState} from "react";
import axios from "../../../custom-axios/axios";
import {Link} from "react-router-dom";

const Pizza = (props) => {
    let [pizzaIngredients, setIngredient] = useState();

    useEffect(() => {
        axios.get("/pizzas/" + props.pizza.id + "/ingredients").then((data) => {
            const ingredients = data.data.map((ingredient, index) => {
                return (
                    <li className="list-group" key={index}>{ingredient.name}</li>
                );
            });
            setIngredient(ingredients);
        });
    }, []);

    const PizzaHeader = () => {
        return (
            <div className="card-header">
                <div className="row">
                    <div className="col-md-6 font-weight-bold">
                        Pizza: {props.pizza.name}
                    </div>
                    <div className="col-md-6 text-right">
                        <button className="btn btn-default" to={"/pizzas/" + props.pizza.id + "/edit"}><i
                            className="fa fa-edit"/>
                        </button>
                        <button onClick={() => props.onDelete(props.pizza.id)}
                                className="btn btn-sm btn-outline-secondary ">
                            <span className="fa fa-trash"/>
                        </button>
                    </div>
                </div>
            </div>
        );
    };

    const PizzaBody = () => {
        return (
            <div className="row">
                <div className="col-md-6 font-weight-bold">Ingredients</div>
                <ul className="list-group">
                    {pizzaIngredients}
                </ul>
            </div>
        );
    };

    return (
        <div className={props.colClass}>
            <div className="card">
                <div className="pizzas">
                    {PizzaHeader()}
                    {PizzaBody()}
                </div>
            </div>
        </div>
    )
};

export default Pizza;