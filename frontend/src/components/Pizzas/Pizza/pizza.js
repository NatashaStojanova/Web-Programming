import React, {Component} from 'react';
import Moment from "react-moment";
import {Link} from "react-router-dom";


class Pizza extends Component {

    render() {

        return (
            <div className={this.props.colClass}>
                <div className="card">
                    <div className="pizzas">
                        {this.cardHeader()}
                        <hr/>
                    </div>
                </div>
            </div>
        );
    }


    cardHeader() {
        return (
            <div className="card-header">
                <div className="row">
                    <div className="col-md-6">
                        {this.props.pizza.name}
                    </div>
                    <div className="col-md-6 text-right">
                        <a href="#" className="btn btn-light" title="Следи">
                            <i className="fa fa-star"></i>
                        </a>
                        {/* <Link className="btn btn-default" to={"/consultations/"+this.props.slotId+"/edit"}><i className="fa fa-pencil"></i></Link>
                        <a onClick={()=>this.props.onDelete(this.props.slotId)} className="btn btn-danger" title="Избриши">
                            <i className="fa fa-trash"></i>
                        </a>*/}
                    </div>

                </div>
            </div>);

    }


    /*slotRoom() {
        return (
            <div className="row">
                <div className="col-md-6 font-weight-bold">Просторија</div>
                <div className="col-md-6">
                    <a href="/Home/Rooms">{this.props.term.room.name}</a>
                </div>
            </div>
        );
    }*/
}


export default Pizza;
