import React, { Component } from "react";
import Axios from "axios";
import Header from '../Components/Header';
import GetAuthorisation from "../Funciton/GetAuthorisation";

import UnsuccessfulAlert from "../Components/Alerts/UnsuccessfulAlert";
import SuccessfulAlert from "../Components/Alerts/SuccessfulAlert";
import ChangePasswordModal from "../Components/Modal/ChangePasswordModal";




const API_URL="http://localhost:8080";

class UserProfile extends Component {

    state = {
		id: "",
		email: "",
        name: "",
		surname: "",
		password: "",
		address: "",
		phoneNumber: "",
		nameError: "none",
		surnameError: "none",
		addressError: "none",
		phoneError: "none",
		phoneValidateError: "none",
		hiddenEditInfo: true,
        redirect: false,
		hiddenSuccessfulAlert: true,
		successfulHeader: "",
		successfulMessage: "",
		hiddenUnsuccessfulAlert: true,
		UnsuccessfulHeader: "",
		UnsuccessfulMessage: "",
		isPasswordModalOpened: false,
		hiddenPasswordErrorAlert: true,
		errorPasswordHeader: "",
		errorPasswordMessage: "",
		emptyOldPasswordError: "none",
		emptyNewPasswordError: "none",
		emptyNewPasswordRepeatedError: "none",
		newPasswordAndRepeatedNotSameError: "none",

		
		userAllergens: [],
		hiddenAllergenSuccessfulAlert: true,
		successfulAllergenHeader: "",
		successfulAllergenMessage: "",
		hiddenAllergenErrorAlert: true,
		errorAllergenHeader: "",
		errorAllergenMessage: "",
		openAllergenModal: false,
		patientPoints : "",
		patientPenalties : "",
		patientLoyaltyCategory : "",
		examinationDiscount : "",
		consultationDiscount : "",
		drugDiscount : "",
		showAnotherInformation: false,
		hideButtonForAnotherInformation : false


	};

	constructor(props) {
		super(props);
	}

    hasRole = (requestRole) => {
        let currentRoles = JSON.parse(localStorage.getItem("keyRole"));
  
        if (currentRoles === null) return false;
  
  
        for (let currentRole of currentRoles) {
          if (currentRole === requestRole) return true;
        }
        return false;
      };

	
    handleEmailChange = (event) => {
		this.setState({ email: event.target.value });
	};

	handleNameChange = (event) => {
		this.setState({ name: event.target.value });
	};

	handleSurnameChange = (event) => {
		this.setState({ surname: event.target.value });
	};

	handlePhoneNumberChange = (event) => {
		this.setState({ phoneNumber: event.target.value });
	};

	handleAddressChange = (event) => {
		this.setState({ address: event.target.value });
	};

	handleCloseSuccessfulAlert = () => {
		this.setState({ hiddenSuccessfulAlert: true });
	};

	handleCloseUnsuccessfulAlert= () => {
		this.setState({ hiddenUnsuccessfulAlert: true });
	};


	handleChangePasswordModalOpen = () => {
		this.setState({ hiddenEditInfo: true, 
			isPasswordModalOpened: true });
	};

	handleChangePasswordModalClose = () => {
		this.setState({ isPasswordModalOpened: false });
	};

	handleCloseAlertPassword = () => {
		this.setState({ hiddenPasswordErrorAlert: true });
	};

	handleAllergenModal = () => {
		this.setState({ hiddenEditInfo: true, openAllergenModal: true });
	};


	
	handleAllergensModalClose = () => {
		this.setState({
			
			hiddenAllergenSuccessfulAlert: true,
			successfulAllergenHeader: "",
			successfulAllergenMessage: "",
			hiddenAllergenErrorAlert: true,
			errorAllergenHeader: "",
			errorAllergenMessage: "",
			openAllergenModal: false,
		});
	};

	handleCloseAllergenAlertError = () => {
		this.setState({ hiddenAllergenErrorAlert: true });
	};

	handleCloseAllergenAlertSuccessful = () => {
		this.setState({ hiddenAllergenSuccessfulAlert: true });
	};


	componentDidMount() {
        console.log(localStorage.getItem("keyRole"));
		if (!this.hasRole("ROLE_CLIENT")) {
			this.setState({ redirect: true });
			this.props.history.push('/login')
		
        } else {


			console.log(GetAuthorisation());
			console.log(localStorage.getItem("keyRole"));
			
			if(this.hasRole("ROLE_CLIENT")){
			Axios.get(API_URL + "/users/client", { validateStatus: () => true, headers: { Authorization : GetAuthorisation()} })
				.then((res) => {
					console.log(res.data);
					if (res.status === 401) {                       
                        this.setState({ redirect: true });				
					} else {

						console.log(res.data.EntityDTO.email)
						console.log(res.data.EntityDTO.name)
						
					
                        this.setState({
							id: res.data.Id,
							email: res.data.EntityDTO.email,
							name: res.data.EntityDTO.name,
							surname: res.data.EntityDTO.surname,
							address: res.data.EntityDTO.address,
							phoneNumber: res.data.EntityDTO.phoneNumber,
							
							
						});
						
						

						console.log(this.state.userAllergens)
						

					}
				})
				.catch((err) => {
					console.log(err);
					console.log("ovaj eror je u pitanju");

				});

            }
			
		}
	}

	

	handleChangeInfo = () => {
		


		let userDTO = {
			name: this.state.name,
			surname: this.state.surname,
			address: this.state.address,
			phoneNumber: this.state.phoneNumber,
		};



		if (this.validateForm(userDTO)){

			console.log(userDTO.name  )

			if(this.hasRole("ROLE_CLIENT")){
			Axios.put(API_URL + "/users/client", userDTO, {
				validateStatus: () => true,
				headers: { Authorization: GetAuthorisation() },
			})
				.then((res) => {
					if (res.status === 400) {
						this.setState({ hiddenUnsuccessfulAlert: false,
							UnsuccessfulHeader: "Bad request", 
							UnsuccessfulMessage: "Pogrešni podaci." });

					} else if (res.status === 500) {

						this.setState({ hiddenUnsuccessfulAlert: false, 
							UnsuccessfulHeader: "Internal server error", 
							UnsuccessfulMessage: "Serverska greška." });

					} else if (res.status === 204) {
						console.log("Success");
						this.setState({
							hiddenSuccessfulAlert: false,
							successfulHeader: "Success",
							successfulMessage: "Uspešno ste ažurirali svoje podatke.",
							hiddenEditInfo: true,
						});
					}
				})
				.catch((err) => {
					console.log(err);
					this.setState({ hiddenUnsuccessfulAlert: false,
						UnsuccessfulHeader: "Error", 
						UnsuccessfulMessage: "Something was wrong" });
				
				});

			}

		}

		
	};


	handleEditInfoClick= () => {
		this.setState({
			hiddenEditInfo: false
		});

		
	};

	validateForm = (userDTO) => {
	
		this.setState({
			nameError: "none",
			surnameError: "none",
			addressError: "none",
			phoneError: "none",
			phoneValidateError: "none",
		});

		const regexPhone = /^([+]?[0-9]{3,6}[-]?[\/]?[0-9]{3,5}[-]?[\/]?[0-9]*)$/;
		console.log(regexPhone.test(userDTO.phoneNumber));
		if (userDTO.name === "") {
			this.setState({ nameError: "initial" });
			return false;
		} else if (userDTO.surname === "") {
			this.setState({ surnameError: "initial" });
			return false;
		} else if (userDTO.address=== "") {
			this.setState({ addressError: "initial" });
			return false;
		} else if (userDTO.phoneNumber === "") {
			this.setState({ phoneError: "initial" });
			return false;
		} else if (regexPhone.test(userDTO.phoneNumber) === false) {
			this.setState({ phoneValidateError: "initial" });
			return false;
		}
		return true;
	};




	changePassword = (oldPassword, newPassword, newPasswordRepeated) => {
		console.log(oldPassword, newPassword, newPasswordRepeated);

		this.setState({
			hiddenEditInfo: true,
			hiddenPasswordErrorAlert: true,
			errorPasswordHeader: "",
			errorPasswordMessage: "",
			emptyOldPasswordError: "none",
			emptyNewPasswordError: "none",
			emptyNewPasswordRepeatedError: "none",
			newPasswordAndRepeatedNotSameError: "none",
			hiddenSuccessAlert: true,
			successHeader: "",
			successMessage: "",


		});

		if (oldPassword === "") {
			this.setState({ emptyOldPasswordError: "initial" });
		} else if (newPassword === "") {
			this.setState({ emptyNewPasswordError: "initial" });
		} else if (newPasswordRepeated === "") {
			this.setState({ emptyNewPasswordRepeatedError: "initial" });
		} else if (newPasswordRepeated !== newPassword) {
			this.setState({ newPasswordAndRepeatedNotSameError: "initial" });
		} else {
			let newPasswordDTO= { oldPassword, newPassword };
			Axios.post(API_URL + "/users/changePassword", newPasswordDTO, {
				validateStatus: () => true,
				headers: { Authorization: GetAuthorisation() },
			})
				.then((res) => {
					 if (res.status === 400) {
						this.setState({
							hiddenPasswordErrorAlert: false,
							errorPasswordHeader: "Invalid new password",
							errorPasswordMessage: "Pogrešna nova lozinka.",
						});
					} else if (res.status === 403) {
						this.setState({
							hiddenPasswordErrorAlert: false,
							errorPasswordHeader: "Bad credentials",
							errorPasswordMessage: "Uneli ste pogrešnu lozinku.",
						});
					} else if (res.status === 500) {
						this.setState({
							hiddenPasswordErrorAlert: false,
							errorPasswordHeader: "Internal server eror",
							errorPasswordMessage: "Serverska greška.",
						});
					} else if (res.status === 204) {
						this.setState({
							
							hiddenSuccessfulAlert: false,
							successfulHeader: "Success",
							successfulMessage: "Uspešno ste promenili lozinku!",
							isPasswordModalOpened: false,
							hiddenEditInfo: true,
						});
					}
					console.log(res);
				})
				.catch((err) => {
					console.log(err);
					this.setState({ hiddenUnsuccessfulAlert: false,
						UnsuccessfulHeader: "Error", 
						UnsuccessfulMessage: "Nešto nije u redu." });
									
				});
		}
	};






	

	render() {

        
		return(

            <React.Fragment>

            <Header/>  

            <div id="userProfilePage" className="container">


					<SuccessfulAlert
						hidden={this.state.hiddenSuccessfulAlert}
						header={this.state.successfulHeader}
						message={this.state.successfulMessage}
						handleCloseAlert={this.handleCloseSuccessfulAlert}    
					/>
					<UnsuccessfulAlert
						hidden={this.state.hiddenUnsuccessfulAlert}
						header={this.state.UnsuccessfulHeader}
						message={this.state.UnsuccessfulMessage}
						handleCloseAlert={this.handleCloseUnsuccessfulAlert}
					/>



	



	
			


            <h4 className=" text-center  mb-0 text-uppercase" style={{ marginTop: "2rem" }}>
						Korisnički profil
			</h4>

            <div className="row mt-5 ">

					<div className="col  container  offset-1 shadow p-3  bg-white ">
                        <h5 className=" text-center text-uppercase">Lični podaci</h5>
                        

						<form id="informationForm" >
								<div className="control-group">
									<div className="form-group controls mb-0 pb-2" >
										<b>Email adresa:</b>
										<input
											readOnly
											placeholder="Email adresa"
											className="form-control-plaintext"
											id="name"
											type="text"
											onChange={this.handleEmailChange}
											value={this.state.email}
										/>
									</div>
								</div>
								<div className="control-group">
									<div className="form-group controls mb-0 pb-2" >
										<b>Ime:</b>
										<input
											readOnly={this.state.hiddenEditInfo}
											className={!this.state.hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
											placeholder="Ime"
											type="text"
											onChange={this.handleNameChange}
											value={this.state.name}
										/>
									</div>
									<div className="text-danger" style={{ display: this.state.nameError }}>
                                    Morate uneti ime.
									</div>
								</div>
								<div className="control-group">
									<div className="form-group controls mb-0 pb-2">
										<b>Prezime:</b>
										<input
											readOnly={this.state.hiddenEditInfo}
											className={!this.state.hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
											placeholder="Prezime"
											type="text"
											onChange={this.handleSurnameChange}
											value={this.state.surname}
										/>
									</div>
									<div className="text-danger" style={{ display: this.state.surnameError }}>
                                    Morate uneti prezime.
									</div>
								</div>
								<div className="control-group">
									
									<div className="form-group controls mb-0 pb-2" >
											<b>Adresa:</b>
											<input
												readOnly={this.state.hiddenEditInfo}
												className={!this.state.hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
												placeholder="Adresa"
												type="text"
												onChange={this.handleAddressChange}
												value={this.state.address}
											/>
									</div>
									
									<div className="text-danger" style={{ display: this.state.addressError }}>
                                    Morate uneti adresu.
									</div>
									
								</div>
								<div className="control-group">
									<div className="form-group controls mb-0 pb-2" >
										<b>Broj telefona:</b>
										<input
											placeholder="Broj telefona"
											readOnly={this.state.hiddenEditInfo}
											className={!this.state.hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
											type="text"
											onChange={this.handlePhoneNumberChange}
											value={this.state.phoneNumber}
										/>
									</div>
									<div className="text-danger" style={{ display: this.state.phoneError }}>
                                        Morate uneti broj telefona.
									</div>
									<div className="text-danger" style={{ display: this.state.phoneValidateError }}>
										Nije dobar format za broj.
									</div>
								</div>
								<div className="form-group text-center" hidden={this.state.hiddenEditInfo}>
									<button
										style={{  marginTop: "15px" }}
										onClick={this.handleChangeInfo}
										className="btn btn-outline-primary btn-xl"
										id="sendMessageButton"
										type="button"
									>
										Izmeni podtake
									</button>
								</div>
								<br />

								<div className="form-group">
										<div className="form-row justify-content-center mr-3">
											
											<div className="mr-2" hidden={!this.state.hiddenEditInfo}>
												<button
													onClick={this.handleEditInfoClick}
													className="btn btn-outline-primary btn-xl"
													id="sendMessageButton"
													type="button"
												>
													Izmena podataka
												</button>
											</div>	

											<div className="mr-2" hidden={!this.state.hiddenEditInfo}>
												<button
													onClick={this.handleChangePasswordModalOpen}
													className="btn btn-outline-primary btn-xl"
													id="sendMessageButton"
													type="button"
												>
													Izmena lozinke
												</button>
											</div>
											

											
																					
										</div>
									
								</div>

							


					</form>
					



				



                    </div>




				

				</div>	
					

            </div>


            

         



			<ChangePasswordModal
					header="Promena lozinke"
					hiddenPasswordErrorAlert={this.state.hiddenPasswordErrorAlert}
					errorPasswordHeader={this.state.errorPasswordHeader}
					errorPasswordMessage={this.state.errorPasswordMessage}
					emptyOldPasswordError={this.state.emptyOldPasswordError}
					emptyNewPasswordError={this.state.emptyNewPasswordError}
					emptyNewPasswordRepeatedError={this.state.emptyNewPasswordRepeatedError}
					newPasswordAndRepeatedNotSameError={this.state.newPasswordAndRepeatedNotSameError}
					show={this.state.isPasswordModalOpened}
					changePassword={this.changePassword}
					onCloseModal={this.handleChangePasswordModalClose}
					handleCloseAlertPassword={this.handleCloseAlertPassword}
				/>

			


            </React.Fragment>


        );
        
	}


}

export default UserProfile;