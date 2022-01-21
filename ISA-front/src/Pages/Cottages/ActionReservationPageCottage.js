import React, { Component } from "react";
import Axios from "axios";
import Header from '../../Components/Header';
import GetAuthorisation from "../../Funciton/GetAuthorisation";
import {NavLink, Redirect } from "react-router-dom";
import CancelModal from "../../Components/Modal/CancelModal";

const API_URL="http://localhost:8080";

class ActionReservationPageCottage extends Component {
	
  
    
    state = {
        reservations : [],
        showCancelModal:false,
        reservationId:"",
    };

    constructor(props) {
        super(props);
    }
  


  componentDidMount() {

    if (!this.hasRole("ROLE_CLIENT")) {
			this.props.history.push('/login');
    }

    
    Axios.get(API_URL + "/reservation/findAllActionCottageReservationClient", {
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


 
    moveToReservation =() => {

       this.props.history.push("/cottages");
    }


    handleReservation = (reservation) => {
  
        let reservationIdDTO = { id: reservation.Id};
    
        Axios.post(API_URL + "/reservation/fastReservation",reservationIdDTO , {
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
              alert("Uspešno je rezervisana akcija!")
              this.setState({showCancelModal:false});
              Axios.get(API_URL + "/reservation/findAllActionCottageReservationClient", {
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
   
	render() {
	

		return (
      
      <React.Fragment>

      <Header/>
      
         <div className="container">


         <button type="button" class="btn btn-outline-primary btn-lg"
         onClick={() => this.moveToReservation()}
         style={{  marginTop: "2em", marginLeft: "auto",marginRight: "auto" }}
          >
         Nazad
         </button>

         <h1 hidden={this.state.reservations.length === 0} className="text-center  mt-3  " >Akcije!</h1>
         <h1 hidden={this.state.reservations.length !== 0} className="text-center  mt-3 text-danger"  >Nema akcije!</h1>



      
        
    
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

                                        <div>
											<b>Cena na akciji:</b>{" "}
                                         {reservation.EntityDTO.actionPrice }
                                             <b>  din</b>
										</div>
									</td>

                                    <td className="align-middle">
                                    <button
											type="button"
                                            className="btn btn-outline-danger"
											onClick={() => this.handleReservation(reservation)}
											
										>
											Rezerviši odmah
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

export default ActionReservationPageCottage;