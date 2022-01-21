import React, { Component } from "react";
import Axios from "axios";
import Header from '../../Components/Header';
import GetAuthorisation from "../../Funciton/GetAuthorisation";
import ComplaintModal from "../../Components/Modal/ComplaintModal";


import {NavLink, Redirect } from "react-router-dom";



const API_URL="http://localhost:8080";

class CottageHistoryReservationsPage extends Component {
	
  
    
    state = {
        reservations : [],
       

		showComplaintModal: false,
		
		complaint: "",
		selectedUnitId:"",
        profession:"",
		Date : new Date(),
		text : "",
        textUnit : "",
        unitName:"",
        selectedOwnerId: "",
        showComplaintModal1 : false,
        ownerName : ""

    };

    constructor(props) {
        super(props);
    }
  


  componentDidMount() {

    if (!this.hasRole("ROLE_CLIENT")) {
			this.props.history.push('/login');
    }

    
    Axios.get(API_URL + "/reservation/findAllHistoryCottageReservationClient", {
			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() },
		})
			.then((res) => {
				if (res.status === 401) {
                    this.props.history.push('/login');
				} else {
					this.setState({ reservations: res.data });
					console.log(res.data);
				}
			})
			.catch((err) => {
				console.log(err);
			});
	}
      

  hasRole = (requestRole) => {
    let currentRoles = JSON.parse(localStorage.getItem("keyRole"));

    if (currentRoles === null) return false;


    for (let currentRole of currentRoles) {
      if (currentRole === requestRole) return true;
    }
    return false;
  };





  moveToFutureReservation =() => {

       this.props.history.push("/cottageFutureReservation");
    }



	handleSortByPriceAscending =() => {

		console.log("sortiranjeee");
		var resrvationType= "COTTAGE";

		Axios.get(API_URL + "/reservation/historyReservation/sortByPriceAscending/" + resrvationType , {
			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() },
		})
			.then((res) => {
				if (res.status === 401) {
                    this.props.history.push('/login');
				} else {
					this.setState({ reservations: res.data });
					console.log(res.data);
				}
			})
			.catch((err) => {
				console.log(err);
			});
        
    }


	handleSortByPriceDescending =() => {

		var resrvationType= "COTTAGE";

		Axios.get(API_URL + "/reservation/historyReservation/sortByPriceDescending/" + resrvationType , {
			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() },
		})
			.then((res) => {
				if (res.status === 401) {
                    this.props.history.push('/login');
				} else {
					this.setState({ reservations: res.data });
					console.log(res.data);
				}
			})
			.catch((err) => {
				console.log(err);
			});
        
    }

	handleSortByDateAscending =() => {


		console.log("sortiranjeee");
		var resrvationType= "COTTAGE";

		Axios.get(API_URL + "/reservation/historyReservation/sortByDateAscending/" + resrvationType , {
			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() },
		})
			.then((res) => {
				if (res.status === 401) {
                    this.props.history.push('/login');
				} else {
					this.setState({ reservations: res.data });
					console.log(res.data);
				}
			})
			.catch((err) => {
				console.log(err);
			});
        
    }


	handleSortByDateDescending =() => {

		
		var resrvationType= "COTTAGE";

		Axios.get(API_URL + "/reservation/historyReservation/sortByDateDescending/" + resrvationType , {
			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() },
		})
			.then((res) => {
				if (res.status === 401) {
                    this.props.history.push('/login');
				} else {
					this.setState({ reservations: res.data });
					console.log(res.data);
				}
			})
			.catch((err) => {
				console.log(err);
			});
        
    }


	handleSortByDurationAppointmentAscending =() => {

		var resrvationType= "COTTAGE";

		Axios.get(API_URL + "/reservation/historyReservation/sortByDurationAscending/" + resrvationType , {
			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() },
		})
			.then((res) => {
				if (res.status === 401) {
                    this.props.history.push('/login');
				} else {
					this.setState({ reservations: res.data });
					console.log(res.data);
				}
			})
			.catch((err) => {
				console.log(err);
			});

        
    }

	
	handleSortByDurationAppointmentDescending =() => {

		var resrvationType= "COTTAGE";

		Axios.get(API_URL + "/reservation/historyReservation/sortByDurationDescending/" + resrvationType , {
			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() },
		})
			.then((res) => {
				if (res.status === 401) {
                    this.props.history.push('/login');
				} else {
					this.setState({ reservations: res.data });
					console.log(res.data);
				}
			})
			.catch((err) => {
				console.log(err);
			});

        
    }

	handleComplaintClick = (unit) => {
		console.log(unit);
		
				
					this.setState({
						selectedUnitId: unit.Id,
						showComplaintModal: true,
                        unitName : unit.EntityDTO.name
					});
				
			
			
	};


    handleComaplaint = () => {
		let ComplaintUnitDTO = {
			unitId: this.state.selectedUnitId,
			date: new Date(),
			text: this.state.textUnit,
			unitName: "",
			reply: "",
			email: "",
		};

		Axios.post(API_URL+"/complaint/unit", ComplaintUnitDTO, { validateStatus: () => true, headers: { Authorization: GetAuthorisation() } })
			.then((resp) => {
				if (resp.status === 500) {
					this.setState({ hiddenFailAlert: false, failHeader: "Internal server error", failMessage: "Server error." });
				} else if (resp.status === 201) {
					
					
				}
				this.setState({ showComplaintModal: false });
			})
			.catch((err) => {
				console.log(err);
			});
	};

	handleComplaintChange = (event) => {
		this.setState({ textUnit: event.target.value });
	};

	
	handleComplaintModalClose = () => {
		this.setState({ showComplaintModal: false });
	};


    handleComplaintClickOwner = (owner) => {
		console.log(owner);
		
				
					this.setState({
						selectedOwnerId: owner.Id,
						showComplaintModal1: true,
                        ownerName : owner.EntityDTO.name
					});
				
			
			
	};

    handleComaplaintOwner = () => {
		let ComplaintUserDTO = {
			ownerId: this.state.selectedOwnerId,
			date: new Date(),
			text: this.state.text,
			userName:"",
			profession: "",
			reply: "",
			email: "",
		};

		Axios.post(API_URL +"/complaint/user", ComplaintUserDTO, { validateStatus: () => true, headers: { Authorization: GetAuthorisation() } })
			.then((resp) => {
				if (resp.status === 500) {
					alert("Serverska greška")
				} else if (resp.status === 201) {
					
					
				}
				this.setState({ showComplaintModal1: false });
			})
			.catch((err) => {
				console.log(err);
			});
	};

	handleComplaintChange1 = (event) => {
		this.setState({ text: event.target.value });
	};

	
	handleComplaintModalClose1 = () => {
		this.setState({ showComplaintModal1: false });
	};





	render() {
	

		return (
      
      <React.Fragment>

      <Header/>
      
         <div className="container">



         <button type="button" class="btn btn-outline-primary btn-lg"
         onClick={() => this.moveToFutureReservation()}
         style={{  marginTop: "2em", marginLeft: "auto",marginRight: "auto" }}
         >
         Buduće rezervacije 
        </button>


		<div className="container" style={{  marginTop: "2em" }}>

		<div className="dropdown">
			<button className="btn btn-primary btn-lg dropdown-toggle"
				type="button" id="dropdownMenu2"
				data-toggle="dropdown" 
				aria-haspopup="true" 
				aria-expanded="false">
				Sortiraj po
			</button>
			<div className="dropdown-menu" aria-labelledby="dropdownMenu2">
					<button className="dropdown-item" type="button" onClick={this.handleSortByPriceAscending} >Ceni rastuće</button>
					<button className="dropdown-item" type="button" onClick={this.handleSortByPriceDescending} >Ceni opadajuće</button>
					<button className="dropdown-item" type="button" onClick={this.handleSortByDateAscending} >Datumu rastuće</button>
					<button className="dropdown-item" type="button" onClick={this.handleSortByDateDescending} >Datumu opadajuće</button>
					<button className="dropdown-item" type="button" onClick={this.handleSortByDurationAppointmentAscending} >Dužini rezervacije rastuće</button>
					<button className="dropdown-item" type="button" onClick={this.handleSortByDurationAppointmentDescending} >Dužini rezervacije opadajuće</button>
			</div>
			</div>

		</div>	


         <h1 hidden={this.state.reservations.length === 0} className="text-center  mt-3  " >Istorija rezervacija brodova</h1>
         <h1 hidden={this.state.reservations.length !== 0} className="text-center  mt-3 text-danger"  >Nema istorije</h1>


        
   
        <div className="container">
                    
          <table className="table" style={{ width: "100%", marginTop: "5em", marginLeft: "auto",marginRight: "auto" }}>
						<tbody>
							{this.state.reservations.map((reservation) => (
								<tr
									id={reservation.Id}
									key={reservation.Id}
									style={{ cursor: "pointer" }}
								>

                        <td width="150px">  
                   <img className="img-fluid" src={`data:image/jpeg;base64,${reservation.EntityDTO.unit.EntityDTO.image}`} width="300em"  />                                
                                    
                    </td>
                    
									<td>
										<div>
											<b>Početak: </b>{" "}
											{new Date(reservation.EntityDTO.startDateTime).toLocaleDateString("en-UK", {
												day: "2-digit",
                                                month: "2-digit",
                                                year: "numeric",
                                                hour: "2-digit",
                                                minute: "2-digit",
											})}
										</div>
                                        <div>
											<b>Kraj: </b>{" "}
											{new Date(reservation.EntityDTO.endDateTime).toLocaleDateString("en-UK", {
												day: "2-digit",
                                                month: "2-digit",
                                                year: "numeric",
                                                hour: "2-digit",
                                                minute: "2-digit",
											})}
										</div>
                                        <div>
											<b>Naziv: </b>{" "}
											{reservation.EntityDTO.unit.EntityDTO.name}
										</div>
										<div>
											<b>Vlasnik: </b>{" "}
											{reservation.EntityDTO.user.EntityDTO.name}
										</div>
										<div>
											<b>Ukupna cena:</b>{" "}
                                         {reservation.EntityDTO.price }
                                             <b>  din</b>
										</div>
									</td>


									<td className="align-middle">
										<div style={{ marginLeft: "55%" }}>
										<button
											type="button"
											onClick={() => this.handleComplaintClick(reservation.EntityDTO.unit)}
											
											className="btn btn-outline-secondary"
										>
											Žalba na brod
										</button>
										</div>
										
										<div style={{ marginLeft: "55%",marginTop: "1em"  }}>
										<button
											type="button"
											onClick={() => this.handleComplaintClickOwner(reservation.EntityDTO.user)}
											
											className="btn btn-outline-secondary"
										>
											Žalba na vlasnika
										</button>
										</div>	

									</td>

									

                                    
                                    
								</tr>
							))}
						</tbody>
					</table>
                </div>

			
				

          
        </div>


                <ComplaintModal
					buttonName="Pošalji"
					header="Napiši žalbu"
					handleComplaintChange={this.handleComplaintChange}
					show={this.state.showComplaintModal}
					onCloseModal={this.handleComplaintModalClose}
					giveFeedback={this.handleComaplaint}
					name={this.state.unitName}
				/>

              <ComplaintModal
					buttonName="Pošalji"
					header="Napiši žalbu"
					handleComplaintChange={this.handleComplaintChange1}
					show={this.state.showComplaintModal1}
					onCloseModal={this.handleComplaintModalClose1}
					giveFeedback={this.handleComaplaintOwner}
					name={"vlasnika vikendice " + this.state.ownerName}
				/>




        </React.Fragment>
    
		);
	}
}

export default CottageHistoryReservationsPage;