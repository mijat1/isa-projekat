import React, { Component } from "react";
import Axios from "axios";
import Header from '../../Components/Header';
import GetAuthorisation from "../../Funciton/GetAuthorisation";


import {NavLink, Redirect } from "react-router-dom";



const API_URL="http://localhost:8080";

class BoatHistoryReservationsPage extends Component {
	
  
    
    state = {
        reservations : [],
       

		showComplaintModal: false,
		
		complaint: "",
		unit:"",
        profession:"",
		Date : new Date(),
		text : "",
		

    };

    constructor(props) {
        super(props);
    }
  


  componentDidMount() {

    if (!this.hasRole("ROLE_CLIENT")) {
			this.props.history.push('/login');
    }

    
    Axios.get(API_URL + "/reservation/findAllHistoryBoatReservationClient", {
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

       this.props.history.push("/boatFutureReservation");
    }



	handleSortByPriceAscending =() => {

		console.log("sortiranjeee");
		var resrvationType= "BOAT";

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

		var resrvationType= "BOAT";

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
		var resrvationType= "BOAT";

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

		
		var resrvationType= "BOAT";

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

		var resrvationType= "BOAT";

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

		var resrvationType= "BOAT";

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

	handleComplaintClick = (staff) => {
		console.log(staff);
		
				
					this.setState({
						selectedStaffId: staff.Id,
						showComplaintModal: true,
						StaffName: staff.EntityDTO.name,
						StaffSurame: staff.EntityDTO.surname,
						profession : staff.EntityDTO.profession,
						grade: 0,
					});
				
			
			
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
											onClick={() => this.handleComplaintClick(reservation.EntityDTO.employee)}
											
											className="btn btn-outline-secondary"
										>
											Žalba na brod
										</button>
										</div>
										
										<div style={{ marginLeft: "55%",marginTop: "1em"  }}>
										<button
											type="button"
											onClick={() => this.handleGetGradeClick(reservation.EntityDTO.employee)}
											
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







        </React.Fragment>
    
		);
	}
}

export default BoatHistoryReservationsPage;