import React from 'react'
import {Link, withRouter} from 'react-router-dom';

const AddIngredient = (props) => {
    const onFormSubmit = (e) => {
        e.preventDefault();
        props.history.push('/ingredients');
        props.onSubmit(
            {
                "name": e.target.ingredientName.value,
                "spicy": e.target.isSpicy.checked,
                "vegie": e.target.isVeggie.checked
            }
        );
    };

    return (
        <div className="row">
            <form className="card" onSubmit={onFormSubmit}>
                <h4 className="text-upper text-left">Add Ingredient</h4>
                <div className="form-group row">
                    <label htmlFor="ingredient" className="col-sm-4 offset-sm-1 text-left">Name</label>
                    <div className="col-sm-6">
                        <input type="text"
                               className="form-control" id="ingredient" name={"ingredientName"}
                               placeholder="Ingredient name" required maxLength="50"/>
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
                    <label htmlFor="spicy" className="col-sm-4 offset-sm-1 text-left">Spicy</label>
                    <div className="col-sm-6 col-xl-4">
                        <input type="checkbox"
                               className="form-control" id="spicy" name={"isSpicy"}/>
                    </div>
                </div>
                <div className="form-group row">
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            type="submit"
                            className="btn btn-primary text-upper">
                            Save
                        </button>
                    </div>
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            type="reset"
                            className="btn btn-warning text-upper">
                            Reset
                        </button>
                    </div>
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <Link to={"/ingredients"}
                              className="btn btn-danger text-upper">
                            Cancel
                        </Link>
                    </div>
                </div>
            </form>
        </div>
    )
};

export default withRouter(AddIngredient);