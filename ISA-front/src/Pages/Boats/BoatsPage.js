import React, { Component } from "react";
import Axios from "axios";
import Header from '../../Components/Header';
import GetAuthorisation from "../../Funciton/GetAuthorisation"

import 'react-confirm-alert/src/react-confirm-alert.css'; 
import { confirmAlert } from 'react-confirm-alert';
import PriceModal from "../../Components/Modal/PriceModal";

const API_URL = "http://localhost:8080";

class BoatsPage extends Component {



	state = {
		boats: [],
		services: [],
		searchName: "",
		searchGradeFrom: "",
		searchGradeTo: "",
		searchDateFrom: "",
		searchDateTo: "",
		searchMan: "",
		
		grade: 1,
		maxCount: "",
		price: 0,
	
		hiddenSuccessfulAlert: true,
		successfulHeader: "",
		successfulMessage: "",
		hiddenUnsuccessfulAlert: true,
		unsuccessfulHeader: "",
		unsuccessfulMessage: "",
		
		openPriceModal:false
	};

    handleFormToogle = () => {
		this.setState({ formShowed: !this.state.formShowed });
	};

	componentDidMount() {
		console.log(localStorage.getItem("keyRole"));
		Axios.get(API_URL + "/boat/allBoats")

			.then((res) => {
				console.log(res.data);
				this.setState({ boats: res.data });
			})
			.catch((err) => {
				console.log(err);
			});

	}


	handleResetSearch = () => {
		Axios.get(API_URL, {

			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() },
		})
			.then((res) => {
				this.setState({
					drugs: res.data,
					formShowed: false,
					showingSearched: false,
					searchDateFrom: "",
					searchDateTo: "",
					searchGradeFrom: "",
					searchGradeTo: "",
					
				});
			})
			.catch((err) => {
				console.log(err);
			});


	};







	handleModalClose = () => {
		this.setState({ openPriceModal: false });
	};

    handleShowPrice = (services) => {
		this.setState({ openPriceModal: true ,services:services});
        console.log("eeeeeeeeeeeeeeeeeeeee");
        console.log(this.state.openPriceModal);
	};


	
	render() {


		return (

			<React.Fragment>

				<Header />

				<div id="allBoats" >

					<div className="container" style={{ marginTop: "2em" }} >
						<h2 className=" text-center  mt-2 text-uppercase">Brodovi</h2>
					
					</div>


					<div style={{ width: "70%", marginTop: "3em", marginLeft: "auto", marginRight: "auto" }} width="100%">


						<button className="btn btn-outline-primary btn-xl" type="button" onClick={this.handleFormToogle}>

							Pretraga
						</button>

						
						<form className={this.state.formShowed ? "form-inline mt-3" : "form-inline mt-3 collapse"} width="100%" id="formCollapse">
							<div className="form-group mb-2" width="100%">
								<input
									placeholder="Datum od"
									className="form-control mr-3"
									style={{ width: "9em" }}
									type="text"
									onChange={this.handleDateFromChange}
									value={this.state.searchDateFrom}
								/>

								<input
									placeholder="Datum do"
									className="form-control mr-3"
									style={{ width: "9em" }}
									type="text"
									onChange={this.handleDateToChange}
									value={this.state.searchDateTo}
								/>

								<input
									placeholder="Lokacija"
									className="form-control mr-3"
									style={{ width: "9em" }}
									type="text"
									onChange={this.handleManChange}
									value={this.state.searchMan}
								/>


								<input
									placeholder="Ocena od"
									className="form-control mr-3"
									style={{ width: "9em" }}
									type="number"
									min="0"
									max="5"
									onChange={this.handleGradeFromChange}
									value={this.state.searchGradeFrom}
								/>
								<input
									placeholder="Ocena do"
									className="form-control mr-3"
									style={{ width: "9em" }}
									type="number"
									min="0"
									max="5"
									onChange={this.handleGradeToChange}
									value={this.state.searchGradeTo}
								/>

								<button
									style={{ background: "#1977cc" }}
									onClick={this.handleSearchClick}
									className="btn btn-primary btn-x2"
									type="button"
								>
									<i className="icofont-search mr-1"></i>
									Pretraži
								</button>
							</div>
						</form>

						<div className={this.state.showingSearched ? "form-group mt-2" : "form-group mt-2 collapse"}>
							<button type="button" className="btn btn-outline-secondary" onClick={this.handleResetSearch}>
								<i className="icofont-close-line mr-1"></i>Reset
							</button>
						</div>


						<table className={"table"} style={{ width: "85%", marginTop: "3rem" }}>
							<tbody>
								{this.state.boats.map((boat) => (
									<tr id={boat.Id} key={boat.Id} >
										<td width="300em" >
											<div style={{ marginTop: "5%" }}>
												<img className="img-fluid" src={`data:image/jpeg;base64,${boat.EntityDTO.image}`} width="300em"  />
											</div>
										</td>
										<td width="50%" > 
											<div style={{ marginTop: "2%" }}>
												<b>Naziv:</b> {boat.EntityDTO.name}
											</div>
											<div>
												<b>Adresa:</b> {boat.EntityDTO.address}
											</div>
											<div>
												<b>Ocena:</b> {boat.EntityDTO.avgGrade}
												<i className="icon-star" style={{ color: "yellow" }}></i>
											</div>
                                            <div>
												<b>Kratak opis:</b> {boat.EntityDTO.description}
											</div>
										</td>
                                        <td className="align-middle">
											<div>
												<button
													type="button"
													onClick={() => this.handleShowPrice(boat.EntityDTO.services)}

													className="btn btn-outline-primary btn-block"
												>
													Cenovnik
												</button>
											
											</div>
                                        </td>
									</tr>
								))}
							</tbody>
						</table>





					</div>


                    <PriceModal show={this.state.openPriceModal} onCloseModal={this.handleModalClose} prices={this.state.services}  header="Cenovnik" />
				</div>

             
			</React.Fragment>


		);
	}
}

export default BoatsPage;
