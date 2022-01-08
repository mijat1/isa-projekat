import React, { Component } from "react";
import { Button, CloseButton, Modal } from "react-bootstrap";
import Axios from "axios";
import GetAuthorisation from "../../Funciton/GetAuthorisation";

import { Redirect } from "react-router-dom";
import { confirmAlert } from 'react-confirm-alert';

const API_URL="http://localhost:8080";

class PriceModal extends Component {
	state = {
		modalSize: "lg",
	
    
	};

   

	handleClickOnClose = () => {
		this.setState({
			modalSize: "lg",
		});
		this.props.onCloseModal();
	};




	render() {
        
		return (
			<Modal
				show={this.props.show}
				size={this.state.modalSize}
				centered
                
			>
				<Modal.Header>
					<Modal.Title style={{ marginLeft: "38%" }} >
						{this.props.header}
					</Modal.Title>
				</Modal.Header>
				<Modal.Body>
			
               
					<div>
						

						<table border="1" style={{ width: "100%" }}>
							<tr>
								<th>Usluga</th>
								<th>Cena</th>
							</tr>
							{this.props.prices.map((price) => (
								<tr>
									<td>{price.EntityDTO.name}</td>
									<td>{price.EntityDTO.price}</td>
								</tr>
							))}
						</table>

                       

                        
                        
					</div>
                   
                </Modal.Body>
				<Modal.Footer>
                    <Button onClick={() => this.handleClickOnClose()}>Zatvori</Button>
				</Modal.Footer>
			</Modal>



		);
	}
}

export default PriceModal;
