import React, { Component } from "react";
import Axios from "axios";
import { Redirect } from "react-router-dom";

const API_URL="http://localhost:8080"

class ActivationPage extends Component {

    state = {

        redirect:false,


    };


    componentDidMount() {

        const pathParams= window.location.pathname;
        const paramsList= pathParams.split("/");
        const id = paramsList[2];

        Axios.get(API_URL+"/reg/activeAccountClient/" + id)
			.then((res) => {
				console.log(res.data)
				
			})
			.catch((err) => {
				console.log(err);
			});

    }

    handleHomePage = () => {
        this.setState({ redirect: true });
    };

    render() {
        if (this.state.redirect) return <Redirect push to="/" />;
        return(
            <React.Fragment>

                <h5 className=" text-center  mb-0 text-uppercase" style={{ marginTop: "2rem" }}>
						Uspešno ste aktivirali svoj nalog
				</h5>

                <br>
                </br>

                <div class="col text-center">
                <button type="button" class="btn btn-primary" onClick={this.handleHomePage} >Pređi na početnu stranu</button>
                </div>


            </React.Fragment>

           
        );
    }
}
export default ActivationPage;