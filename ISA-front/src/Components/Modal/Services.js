import React, { Component } from 'react';
import { Button, Modal } from 'react-bootstrap';
import Axios from 'axios';

import DatePicker from "react-datepicker";
import { useState } from 'react';
import GetAuthorisation from "../../Funciton/GetAuthorisation";
import { Redirect } from "react-router-dom";
const API_URL="http://localhost:8080";


class Services extends Component {
   
    state = {
   
        servicesE:[],
        modalSize:'lg',    
       
  
    }
  
  
   

    componentDidMount() {

    }

    handleCheck = (event) => {
        var updatedList = [...this.state.servicesE];
        var index = updatedList.indexOf(event.target.value)
        if (event.target.checked) {
          updatedList = [...this.state.servicesE, event.target.value];
        } else {
          updatedList.splice(index, 1);
        }
       this.setState({servicesE:updatedList});
      
       console.log(this.state.servicesE)
      };
   

  

    handleCreateReservation = () =>{
        let newReservationDTO = {
            startDateTime:this.props.startDate,
            days:this.props.days,
            unitId:this.props.boatId,
            listServices:this.state.servicesE
        }
      console.log(newReservationDTO)
      if(this.props.type ==="COTTAGE"){
        Axios
        .post(API_URL + "/reservation/createCottageReservaton", newReservationDTO, {
            validateStatus: () => true,
            headers: { Authorization: GetAuthorisation() },})
        .then((res) =>{
            console.log(res.status)
            if (res.status === 201) {
                alert("Uspešno izvršena rezervacija.");
                this.handleClickOnClose();
            }else if (res.status === 400) {
                alert(res.data);
            }else{
                alert("Serverska Greška!");
            }
        }).catch((err) => {
        });
    }else   if(this.props.type ==="BOAT"){
        Axios
        .post(API_URL + "/reservation/createBoatReservaton", newReservationDTO, {
            validateStatus: () => true,
            headers: { Authorization: GetAuthorisation() },})
        .then((res) =>{
            console.log(res.status)
            if (res.status === 201) {
                alert("Uspešno izvršena rezervacija.");
                this.handleClickOnClose();
            }else if (res.status === 400) {
                alert(res.data);
            }else{
                alert("Serverska Greška!");
            }
        }).catch((err) => {
        });
    } else  if(this.props.type ==="COURSE"){
        Axios
        .post(API_URL + "/reservation/createCourseReservaton", newReservationDTO, {
            validateStatus: () => true,
            headers: { Authorization: GetAuthorisation() },})
        .then((res) =>{
            console.log(res.status)
            if (res.status === 201) {
                alert("Uspešno izvršena rezervacija.");
                this.handleClickOnClose();
            }else if (res.status === 400) {
                alert(res.data);
            }else{
                alert("Serverska Greška!");
            }
        }).catch((err) => {
        });
    }

}



    handleClickOnClose = () => {
        this.setState({
          servicesE:[]
        });
        
        this.props.onCloseModal();
    }

 


  

 


  



    render() { 
      
       
        return ( 
            <Modal
                show = {this.props.show}
                size='lg'
                centered
                >
                <Modal.Header >
                    <Modal.Title style={{marginLeft:'37%'}} >
                        Kreiranje Rezervacije
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                <p className="mt-2 text-uppercase" style={{ fontSize: "1.5em" }}>
								Datum rezervacije:{" "}
								{new Date(this.props.startDate).toLocaleDateString("en-US", {
									day: "2-digit",
									month: "2-digit",
									year: "numeric",
									hour: "2-digit",
									minute: "2-digit",
								})}
                                {" "}
                                {new Date(this.props.endDate).toLocaleDateString("en-US", {
									day: "2-digit",
									month: "2-digit",
									year: "numeric",
									hour: "2-digit",
									minute: "2-digit",
								})}
							</p>
                <table className= "table" style={{ width: "100%", marginTop: "3rem" }} hidden={!this.props.show}>
							<tbody>
									<tr id={this.props.boatId} key={this.props.boatId} >
										<td width="300em" >
											<div style={{ marginTop: "5%" }}>
												<img className="img-fluid" src={`data:image/jpeg;base64,${this.props.image}`} width="300em"  />
											</div>
										</td>
										<td width="50%" > 
											<div style={{ marginTop: "2%" }}>
												<b>Naziv:</b> {this.props.name}
											</div>
											<div>
												<b>Adresa:</b> {this.props.address}
											</div>
											<div>
												<b>Ocena:</b> {this.props.grade}
												<i className="icon-star" style={{ color: "yellow" }}></i>
											</div>
                                            <div>
												<b>Kratak opis:</b> {this.props.description}
											</div>
											<div>
												<b>Cena:</b> {this.props.price}
											</div>
										</td>
									</tr>
								
							</tbody>
						</table>
                        <div className="checkList">
    <div className="title">Dodatne usluge:</div>
    <div className="list-container">
      {this.props.prices.map((item, index) => (
         <div key={index}>
             <span>{item.EntityDTO.name}</span>
             <span>{" "}</span>
             <span>{item.EntityDTO.price}</span>
             <span>{" "}</span>
             <input value={item.Id} type="checkbox" onChange={this.handleCheck}/>
           
         </div>
      ))}
    </div>
  </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={() => this.handleCreateReservation()}>Rezerviši</Button>
                    <Button onClick={() => this.handleClickOnClose()}>Zatvori</Button>
                </Modal.Footer>
            </Modal>
         );
    }
}
 
export default Services;