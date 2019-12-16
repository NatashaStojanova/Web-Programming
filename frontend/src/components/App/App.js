import React, {Component} from 'react';
import './App.css';
import Header from "../Header/header";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Pizzas from "../Pizzas/pizzas";
import Ingredients from "../Ingredients/ingredients";
import PizzaService from "../../service/pizzaService";
import IngredientService from "../../service/ingredientService";
import AddIngredient from "../Ingredients/addIngredient/addIngredient"
import EditIngredient from "../Ingredients/Ingredient/editIngredient/editIngredient"

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            pizzas: [],
            ingredients: []
        }
    }

    componentDidMount() {
        this.loadIngredients();
        this.loadPizzas();
    }

    loadPizzas() {
        PizzaService.fetchPizzas().then(resp => {
            this.setState((prevState) => {
                return {
                    "pizzas": resp.data.content
                }
            });
        });
    }

    loadIngredients() {
        IngredientService.fetchIngredients().then(resp => {
            this.setState((prevState) => {
                return {
                    "ingredients": resp.data.content
                }
            })
        });
    }

    updateIngredient = ((editedIngredient) => {
        IngredientService.updateIngredient(editedIngredient).then((response) => {
            const newIngredient = response.data;
            //update state
            this.setState((prevState) => {
                const newIngRef = prevState.ingredients.filter((item) => {
                    if (item.id === newIngredient.id) {
                        return newIngredient;
                    }
                    return item;
                });
                return {
                    "ingredients": newIngRef
                }
            });
        });
    });

    deleteIngredient = ((id) => {
        IngredientService.deleteIngredient(id).then();
        this.setState((prevState) => {
            const newIngredients = prevState.ingredients.filter((ingredient, index) => {
                return ingredient.id !== id;
            });
            return {"ingredients": newIngredients}
        })
    });

    addNewIngredient = ((newIngredient) => {
        IngredientService.addIngredient(newIngredient).then(resp => {
            const newIngr = resp.data;
            this.setState((prevState) => {
                const newIngredients = prevState.ingredients.map((item) => {
                    return item;
                });
                newIngredients.push(newIngr);
                return {
                    "ingredients": newIngredients
                }
            });
        });
    });


    render() {
        return (
            <div className="App">
                <Router>
                    <Header/>
                    <main role="main" className="mt-3">
                        <div className="container">
                            <Route path={"/"} exact render={() =>
                                <Pizzas pizzas={this.state.pizzas}/>}>
                            </Route>
                            <Route path={"/pizzas"} exact render={() => <Pizzas pizzas={this.state.pizzas}/>}>
                            </Route>
                            <Route path="/ingredients" exact
                                   render={() => <Ingredients ingredients={this.state.ingredients}
                                                              onDelete={this.deleteIngredient}/>}>
                            </Route>
                            <Route path="/ingredients/:id/edit" exact render={() =>
                                <EditIngredient onSubmit={this.updateIngredient}/>}>
                            </Route>
                            <Route path="/ingredients/new" exact
                                   render={() => <AddIngredient onSubmit={this.addNewIngredient}/>}>
                            </Route>

                            <Redirect to={"/"}/>
                        </div>
                    </main>
                </Router>
            </div>
        );
    }
}

export default App;