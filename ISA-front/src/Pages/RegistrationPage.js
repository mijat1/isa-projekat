import React, { Component } from "react";
import Header from '../Components/Header';
import Axios from "axios";
import { Redirect } from "react-router-dom";

const API_URL="http://localhost:8080"

class RegistrationPage extends Component {

    state = {
        email: "",
		password: "",
		repeatPassword: "",
        name: "",
		surname: "",
		address: "",
		phoneNumber: "",
        emailError: "none",
		passwordError: "none",
		repeatPasswordError: "none",
		repeatPasswordSameError: "none",
		nameError: "none",
		surnameError: "none",
		addressError: "none",
		phoneError: "none",
		emailNotValid: "none",
        redirect:false
		
        



    };

    handleEmailChange = (event) => {
		this.setState({ email: event.target.value });
	};
    handlePasswordChange = (event) => {
		this.setState({ password: event.target.value });
	};
	
	handleRepeatPasswordChange = (event) => {
		this.setState({ repeatPassword: event.target.value });
	};

	handleNameChange = (event) => {
		this.setState({ name: event.target.value });
	};

	handleSurnameChange = (event) => {
		this.setState({ surname: event.target.value });
	};

	handleAddressChange = (event) => {
		this.setState({ address: event.target.value });
	};

	handlePhoneNumberChange = (event) => {
		this.setState({ phoneNumber: event.target.value });
        console.log("Nemanja");
	};

    validateForm = (userDTO) => {
		this.setState({
			emailError: "none",
			emailNotValid: "none",
			nameError: "none",
			surnameError: "none",
			addressError: "none",
			addressNotFoundError: "none",
			phoneError: "none",
			passwordError: "none",
			repeatPasswordError: "none",
			repeatPasswordSameError: "none",
		});

		if (userDTO.email === "") {
			this.setState({ emailError: "initial" });
			return false;
		} else if (!userDTO.email.includes("@")) {
			this.setState({ emailNotValid: "initial" });
			return false;
		} else if (userDTO.name === "") {
			this.setState({ nameError: "initial" });
			return false;
		} else if (userDTO.surname === "") {
			this.setState({ surnameError: "initial" });
			return false;
        } else if (userDTO.address === "") {
			this.setState({ addressError: "initial" });
			return false;    
		} else if (userDTO.phoneNumber === "") {
			this.setState({ phoneError: "initial" });
			return false;
		} else if (userDTO.password === "") {
			this.setState({ passwordError: "initial" });
			return false;
		} else if (this.state.repeatPassword === "") {
			this.setState({ repeatPasswordError: "initial" });
			return false;
		}else if (userDTO.password !== this.state.repeatPassword) {
			this.setState({ repeatPasswordSameError: "initial" });
			return false;
		}
		return true;
	};

    handleSignUp = () => {

           
            let userDTO = {
                email: this.state.email,
                name: this.state.name,
                surname: this.state.surname,
                address: this.state.address,
                phoneNumber: this.state.phoneNumber,
                password: this.state.password,
            };

            if (this.validateForm(userDTO)) {

            console.log(userDTO);
            Axios.post(API_URL+"/reg/signupclient", userDTO, { validateStatus: () => true })
            .then((res) => {
                
                this.setState({ redirect: true });
                
            })
        }

            
    };


    render() {
        if (this.state.redirect) return <Redirect push to="/" />;
        return(
            

        <React.Fragment>
            <Header/>  

            <h5 className=" text-center  mb-0 text-uppercase" style={{ marginTop: "2rem" }}>
						Registracija
					</h5>

            <br/>        

            <div class="container" style={{maxWidth: "40%"}}>
                <form>
                    <div class="form-group">
                        <label style={{textAlign:"start !important"}} for="exampleInputEmail1">Email adresa</label>
                        <input type="text" onChange={this.handleEmailChange} value={this.state.email} class="form-control" id="emailAddress" aria-describedby="emailHelp" placeholder="Email adresa"/>
                    </div>
                    <div className="text-danger" style={{ display: this.state.emailError }}>
										Morate uneti email adresu.
									</div>
									<div className="text-danger" style={{ display: this.state.emailNotValid }}>
										Email adresa nije validna.
									</div>
                    <div class="form-group">
                        <label for="exampleInputName1">Ime</label>
                        <input type="text" onChange={this.handleNameChange} value={this.state.name} class="form-control" id="name" placeholder="Ime"/>
                    </div>
                    <div className="text-danger" style={{ display: this.state.nameError }}>
                                        Morate uneti ime.
									</div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Prezime</label>
                        <input type="text" onChange={this.handleSurnameChange} value={this.state.surname} class="form-control" id="surname" placeholder="Prezime"/>
                    </div>
                    <div className="text-danger" style={{ display: this.state.surnameError }}>
                                        Morate uneti prezime.
									</div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Adresa</label>
                        <input type="text" onChange={this.handleAddressChange} value={this.state.address} class="form-control" id="address" placeholder="Adresa"/>
                    </div>
                    <div className="text-danger" style={{ display: this.state.addressError }}>
                                        Morate uneti adresu.
									</div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Telefon</label>
                        <input type="text" onChange={this.handlePhoneNumberChange} value={this.state.phoneNumber} class="form-control" id="phoneNumber" placeholder="Telefon"/>
                    </div>
                    <div className="text-danger" style={{ display: this.state.phoneError }}>
                                        Morate uneti broj telefona.
									</div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Lozinka</label>
                        <input type="Password" onChange={this.handlePasswordChange} value={this.state.password} class="form-control" id="password" placeholder="Lozinka"/>
                    </div>
                    <div className="text-danger" style={{ display: this.state.passwordError }}>
                                        Morate uneti lozinku.
									</div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Ponovljena lozinka</label>
                        <input type="Password" onChange={this.handleRepeatPasswordChange} value={this.state.repeatPassword} class="form-control" id="passwordRepeat" placeholder="Ponovljena lozinka"/>
                    </div>
                    <div className="text-danger" style={{ display: this.state.repeatPasswordError }}>
                                        Morate uneti lozinku.
									</div>
									<div className="text-danger" style={{ display: this.state.repeatPasswordSameError }}>
										Lozinke nisu iste.
									</div>
                    
                    <div class="text-center">
                    <button type="button" class="btn btn-primary " style={{width: "20%"}} onClick={this.handleSignUp}>Pošalji</button>
                        </div>
                </form>
            </div>  

        </React.Fragment>



        );


    }


}

export default RegistrationPage;