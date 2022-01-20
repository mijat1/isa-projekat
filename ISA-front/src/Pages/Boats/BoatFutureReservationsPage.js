import React, { Component } from "react";
import Axios from "axios";
import Header from '../../Components/Header';
import GetAuthorisation from "../../Funciton/GetAuthorisation";
//import DermatologistAppointmentPicture from "../Images/appointment.png" ;
////import UnsuccessfulAlert from "../Components/Alerts/UnsuccessfulAlert";
//import SuccessfulAlert from "../Components/Alerts/SuccessfulAlert";
import {NavLink, Redirect } from "react-router-dom";

const API_URL="http://localhost:8080";

class BoatFutureReservationsPage extends Component {
	
  
    
    state = {
        reservations : []
    };

    constructor(props) {
        super(props);
    }
  


  componentDidMount() {

    if (!this.hasRole("ROLE_CLIENT")) {
			this.props.history.push('/login');
    }

    
    Axios.get(API_URL + "/reservation/findAllFutureBoatReservationClient", {
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


  
    isAvailableToCanceled =(date) => {

        var reservationDate= new Date(date);
        reservationDate.setDate(reservationDate.getDate() -3);

        if(reservationDate <= new Date()){
            return true;
        }

        return false;
    }
 
    moveToReservationHistory =() => {

       this.props.history.push("/historyBoatReservation");
    }

	render() {
	

		return (
      
      <React.Fragment>

      <Header/>
      
         <div className="container">


         <button type="button" class="btn btn-outline-primary btn-lg"
         onClick={() => this.moveToReservationHistory()}
         style={{  marginTop: "2em", marginLeft: "auto",marginRight: "auto" }}
          >
         Istorija Rezervacija
         </button>

         <h1 hidden={this.state.reservations.length === 0} className="text-center  mt-3  " >Vašae buduće rezervacije!</h1>
         <h1 hidden={this.state.reservations.length !== 0} className="text-center  mt-3 text-danger"  >Nemate rezervacija!</h1>



      
        
    
        <div className="container">
                    
          <table className="table " style={{ width: "80%", marginTop: "5em", marginLeft: "auto",marginRight: "auto" }}>
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
                                    <button
											type="button"
                                            className="btn btn-outline-danger"
											hidden={this.isAvailableToCanceled(new Date(reservation.EntityDTO.startDateTime))}
											onClick={() => this.handleCancelReservation(reservation.Id)}
											
										>
											Otkaži rezervaciju
										</button>
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

export default BoatFutureReservationsPage;