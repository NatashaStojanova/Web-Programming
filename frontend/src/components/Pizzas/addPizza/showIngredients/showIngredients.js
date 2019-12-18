import React, {Component} from 'react';

class ShowIngredients extends Component {
    render() {
        return (
            this.slotIngredients()
        );
    }

    slotIngredients() {
        return (
            <tr>
                <td scope="col">{this.props.ingredient.name}</td>
                <td scope="col">{String(this.props.ingredient.spicy ? '✔' : '🗙')}</td>
                <td scope="col">{String(this.props.ingredient.vegie ? '✔' : '🗙')}</td>
                <td scope="col">
                    <div className="form-group row">
                        <div className="col-sm-6 col-xl-4">
                            <input type="checkbox"
                                   className="form-control" id="id" name={"id"}/>
                        </div>
                    </div>
                </td>
            </tr>
        )
    }
}

export default ShowIngredients;