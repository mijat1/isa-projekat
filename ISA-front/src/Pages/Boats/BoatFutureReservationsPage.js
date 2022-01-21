import React, { Component } from "react";
import Axios from "axios";
import Header from '../../Components/Header';
import GetAuthorisation from "../../Funciton/GetAuthorisation";
import {NavLink, Redirect } from "react-router-dom";
import CancelModal from "../../Components/Modal/CancelModal";

const API_URL="http://localhost:8080";

class BoatFutureReservationsPage extends Component {
	
  
    
    state = {
        reservations : [],
        showCancelModal:false,
        reservationId:"",
        percentOfCancel:0
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

    handleCancelClick = (reservation) => {
		console.log(reservation);
		
				
					this.setState({
						reservationId: reservation.Id,
						showCancelModal: true,
                        percentOfCancel : reservation.EntityDTO.unit.EntityDTO.percentOfCancel
					});
				
			
			
	};
    handleCancelReservation = () => {
  
        let reservationIdDTO = { id: this.state.reservationId};
    
        Axios.post(API_URL + "/reservation/cancelReservation",reservationIdDTO , {
                validateStatus: () => true,
                headers: { Authorization: GetAuthorisation() },
            })
          .then((res) => {
            if (res.status === 400) {
                alert(res.data);
    
            } else if (res.status === 500) {
    
                alert("Serverska greška")
    
            } 
            else if (res.status === 404) {
    
            
                    alert(res.data);
      
            } else if (res.status === 200) {
              console.log("Success");
              alert("Uspešno je otkazana rezervacija")
              this.setState({showCancelModal:false});
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
               
          })
          .catch((err) => {
            console.log(err);
            alert("Greška")
          
          });
    
        
      
    
      }
      handleCancelModalClose = () => {
        this.setState({ showCancelModal: false });
    };

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
											onClick={() => this.handleCancelClick(reservation)}
											
										>
											Otkaži rezervaciju
										</button>
                                    </td>
								</tr>
							))}
						</tbody>
					</table>
                </div>


                <CancelModal
					buttonName="Potvrdi"
					show={this.state.showCancelModal}
					onCloseModal={this.handleCancelModalClose}
					cancelReservation={this.handleCancelReservation}
					name={this.state.percentOfCancel}
				/>
          
        </div>
        </React.Fragment>
        
		);
	}
}

export default BoatFutureReservationsPage;