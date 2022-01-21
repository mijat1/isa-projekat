import React, { Component } from "react";
import Axios from "axios";
import ComplaintImage from "../Images/report.png" ;
import Header from '../Components/Header';
import GetAuthorisation from "../Funciton/GetAuthorisation";

const API_URL = "http://localhost:8080"

class ComplaintUserPage extends Component {



  state = {
    complaints : [],
    ownerName : "",   
    complaintId : "",
    showComplaintModal : false,
  


};



  componentDidMount() {

    Axios.get(API_URL + "/complaint/getComplaintsUser", {
			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() },
		})
			.then((res) => {
				if (res.status === 401) {
          this.props.history.push('/login');
				} else {
					this.setState({ complaints: res.data });
					console.log(res.data);
				}
			})
			.catch((err) => {
				console.log(err);
			});



  }





    render() {
	

		return (
      
      <React.Fragment>

      <Header/>

      <h5 className=" text-center  mb-0 text-uppercase" style={{ marginTop: "5rem" }}>
						Žalbe na vlasnike vikendica i instruktore
				</h5>


        <table className="table" style={{ width: "60%", marginTop: "5em", marginLeft: "auto",marginRight: "auto" }}>
						<tbody>
							{this.state.complaints.map((complaint) => (
								<tr id={complaint.Id} key={complaint.Id} >
									<td width="130em">
										<img className="img-fluid" src={ComplaintImage} width="70em" />
									</td>

                  <td>
                                        
                                        <div>  
                                        <b>Email klijenta: </b>{complaint.EntityDTO.email}
                        
                                        </div>
                                        <div>  
                                        <b>Osoba na koju se žali: </b>{complaint.EntityDTO.userName}
                        
                                        </div> 
                                        <div>  
                                        <b>Tip korsnika: </b>{complaint.EntityDTO.profession}
                        
                                        </div> 
                                        <div>  
                                        <b>Tekst žalbe: </b>{complaint.EntityDTO.text}
                        
                                        </div>  

                                        

                                      </td>

                                      <td className="align-middle">
                                      <div style={{ marginLeft: "55%" }}>
                                        <button
                                          type="button"
                                          className="btn btn-outline-secondary btn-block"
                                        >
                                          Reply
                                        </button>
                                      </div>
                                      
                                    </td>
									
									
								</tr>
							))}
						</tbody>
					</table>

     
      </React.Fragment>
        
		);
        }

}
export default ComplaintUserPage;