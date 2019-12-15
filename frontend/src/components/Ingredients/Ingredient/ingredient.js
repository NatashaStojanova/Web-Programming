import React, {Component} from 'react';
import {Link} from "react-router-dom";

class Ingredient extends Component {

    render() {

        return (
            this.slotIngredients()
        );
    }

    slotIngredients() {
        return (
            <tr>
                <td scope="col">{this.props.ingredient.name}</td>
                <td scope="col">{String(this.props.ingredient.spicy)}</td>
                <td scope="col">{String(this.props.ingredient.vegie)}</td>
                <td scope="col">
                    <Link to={"/ingredients/" + this.props.ingredient.id + "/edit"}
                          className="btn btn-sm btn-secondary">
                        <span className="fa fa-edit"/>
                        <span><strong>Edit</strong></span>
                    </Link>
                    <button className="btn btn-sm btn-outline-secondary ">
                        <span className="fa fa-remove"/>
                        <span><strong>Remove</strong></span>
                    </button>
                    <button className="btn btn-sm btn-outline-dark">
                        <span><strong>Details</strong></span>
                    </button>
                </td>
            </tr>
        );
    }
}


export default Ingredient;
