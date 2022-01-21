import React, { Component } from "react";
import Axios from "axios";
import ComplaintImage from "../Images/report.png" ;
import Header from '../Components/Header';
import GetAuthorisation from "../Funciton/GetAuthorisation";
import ReplyModal from "../Components/Modal/ReplyModal"

const API_URL = "http://localhost:8080"

class ComplaintUnitPage extends Component {



  state = {
    complaints : [],
    unitName : "",
    complaintId : "",
    showComplaintModal : false,
    replyTo:"",
    reply:""


};



  componentDidMount() {

    Axios.get(API_URL + "/complaint/getComplaintsUnit", {
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


handleReplyChange = (event) => {
    this.setState({ reply: event.target.value });
};

handleReplyToUserChange = (event) => {
    this.setState({ replyTo: event.target.value });
};
handleComplaintModalClose = () => {
    this.setState({ showComplaintModal: false });
};

handleReplyComaplaint = () => {
    console.log(this.state.reply);
    console.log(this.state.replyTo);
    console.log(this.state.complaintId);


    let ComplaintUnitDTO = {
        unitId : this.state.complaintId,
        reply : this.state.reply,
        replyToUser : this.state.replyTo,
    
    };
    if(this.state.reply === "" || this.state.replyTo ==="")
    {
        alert("Morate uneti odgovore")
    }else{
    Axios.post(API_URL+"/complaint/replyToUnitComplaint" , ComplaintUnitDTO,  { validateStatus: () => true, headers: { Authorization: GetAuthorisation() } })
        .then((resp) => {
            if (resp.status === 500) {
                this.setState({ hiddenFailAlert: false, failHeader: "Internal server error", failMessage: "Server error." });
            } else if (resp.status === 201) {
                

      Axios.get(API_URL + "/complaint/getComplaintsUnit", {
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
            this.setState({ showComplaintModal: false });
        })
        .catch((err) => {
            console.log(err);
        });
    }
};

handleReplyClick = (complaint) => {
    console.log(complaint);
    this.setState({
        unitName: complaint.EntityDTO.clientName,
        replyTo : complaint.EntityDTO.replayTo,
        reply : complaint.EntityDTO.reply,
        complaintId : complaint.Id,
        showComplaintModal: true,
    });
};



    render() {
	

		return (
      
      <React.Fragment>

      <Header/>

      <h5 className=" text-center  mb-0 text-uppercase" style={{ marginTop: "5rem" }}>
						Žalbe na Entitete
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
                                        <b>Klijent: </b>{complaint.EntityDTO.clientName}
                        
                                        </div>
                                        <div>  
                                        <b>Osoba na koju se žali: </b>{complaint.EntityDTO.unitName}
                        
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
                                          onClick={() => this.handleReplyClick(complaint)}
                                        >
                                          Reply
                                        </button>
                                      </div>
                                      
                                    </td>
									
									
								</tr>
							))}
						</tbody>
					</table>

                    <ReplyModal
					buttonName="Pošalji"
					header="Odgovor na žalbu"
					handleReplyChange={this.handleReplyChange}
                    handleReplyToChange={this.handleReplyToUserChange}
					show={this.state.showComplaintModal}
					onCloseModal={this.handleComplaintModalClose}
					giveFeedback={this.handleReplyComaplaint}
					name={this.state.unitName}
				/>
     
      </React.Fragment>
        
		);
        }

}
export default ComplaintUnitPage;