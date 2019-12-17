import React from 'react'
import {withRouter} from 'react-router-dom';

const AddPizza = (props) => {
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
                    <label htmlFor="ingredient" className="col-sm-4 offset-sm-1 text-left">Name</label>
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
                    {/*<div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            className="btn btn-warning text-upper">
                            Reset
                        </button>
                    </div>
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            className="btn btn-danger text-upper">
                            Cancel
                        </button>
                    </div>*/}
                </div>
            </form>
        </div>
    )
};

export default withRouter(AddPizza);