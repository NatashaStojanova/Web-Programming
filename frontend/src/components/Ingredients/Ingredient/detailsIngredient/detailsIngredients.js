import React, {Component, useEffect, useState} from "react";
import {Link, useParams} from "react-router-dom";
import axios from "../../../../custom-axios/axios";

const DetailsIngredient = (props) => {


    const {id} = useParams();

    let [pizzaIngredients, setPizza] = useState();

    useEffect(() => {
        axios.get("/ingredients/" + id + "/pizzas").then((data) => {
            const pizzas = data.data.map((pizza, index) => {
                return (
                    <li className="list-group" key={index}>{pizza.name}</li>
                );
            });
            setPizza(pizzas);
        });
    }, []);


    const PizzaBody = () => {
        return (
            <div className="row">
                <div className="col-md-6 font-weight-bold">Pizzas</div>
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
                    {PizzaBody()}
                </div>
            </div>
        </div>
    )
};

export default DetailsIngredient;