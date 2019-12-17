import React from "react";
import Pizza from './Pizza/pizza'
import {Link} from "react-router-dom";

const Pizzas = (props) => {
    const pizzas = props.pizzas.map((pizza, index) => {
        return (
            <Pizza pizza={pizza} key={index} colClass={"col-md-6 mt-2 col-sm-12"}/>
        );
    });
    return (
        <div className="row">
            <div className={"row"}>
                {pizzas}
            </div>
            <Link to={"/pizzas/new"} className="btn btn-outline-secondary">
                <span><strong>Add new Pizza</strong></span>
            </Link>
        </div>

    )
};

export default Pizzas;