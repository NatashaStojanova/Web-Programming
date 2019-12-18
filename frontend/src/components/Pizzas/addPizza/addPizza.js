import React from 'react'
import {withRouter} from 'react-router-dom';
import ShowIngredients from "./showIngredients/showIngredients";

const AddPizza = (props) => {
    const ingredients = props.ingredients.map((ingredient, index) => {
        return (
            <ShowIngredients ingredient={ingredient} key={index} onDelete={props.onDelete}
                             colClass={"col-md-6 mt-2 col-sm-6"}/>
        );
    });
    const onFormSubmit = (e) => {
        e.preventDefault();
        props.history.push('/pizzas');
        props.onSubmit(
            {
                "name": e.target.pizzaName.value,
                "description": e.target.pizzaDescription.value,
                "veggie": e.target.isVeggie.checked
            }
        );
    };
    return (
        <div className="row">
            <form className="card" onSubmit={onFormSubmit}>
                <h4 className="text-upper text-left">Add Pizza</h4>
                <div className="form-group row">
                    <label htmlFor="pizza" className="col-sm-4 offset-sm-1 text-left">Name</label>
                    <div className="col-sm-6">
                        <input type="text"
                               className="form-control" id="ingredient" name={"pizzaName"}
                               placeholder="Pizza name" required maxLength="50"/>
                    </div>
                </div>

                <div className="form-group row">
                    <label htmlFor="description" className="col-sm-4 offset-sm-1 text-left">Description</label>
                    <div className="col-sm-6">
                        <input type="text" placeholder="Pizza Description"
                               className="form-control" id="description" name={"pizzaDescription"}/>
                    </div>
                </div>

                <div className="form-group row">
                    <label htmlFor="veggie" className="col-sm-4 offset-sm-1 text-left">Veggie</label>
                    <div className="col-sm-6 col-xl-4">
                        <input type="checkbox"
                               className="form-control" id="veggie" name={"isVeggie"}/>
                    </div>
                </div>
                <div className="form-group row">
                    <div
                        className="offset-sm-1 col-sm-6  text-center">
                        <button
                            type="submit"
                            className="btn btn-success text-upper">
                            Add Pizza
                        </button>
                    </div>
                </div>
            </form>
            <div className="row">
                <h4 className="text-upper text-left">Ingredients</h4>
                <div className="table-responsive">
                    <table className="table tr-history table-striped small">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Spicy</th>
                            <th scope="col">Veggie</th>
                            <th scope="col">Add Ingredient</th>
                        </tr>
                        </thead>
                        <tbody>
                        {ingredients}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>


    )
};

export default withRouter(AddPizza);