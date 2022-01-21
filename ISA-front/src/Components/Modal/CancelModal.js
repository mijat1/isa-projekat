import React, { Component } from "react";
import { Button, Modal } from "react-bootstrap";

class CancelModal extends Component {
	

	render() {
		return (
			<Modal
				show={this.props.show}
				size="lg"
				dialogClassName="modal-100w-100h"
				aria-labelledby="contained-modal-title-vcenter"
				centered
				onHide={this.props.closeModal}
			>
				<Modal.Header closeButton>
					<Modal.Title id="contained-modal-title-vcenter" >Otkazivanje rezervacije</Modal.Title>
				
				</Modal.Header>
				<Modal.Body>
				<div className="container">
                <p>Da li sigurno želite da otkažete rezervaciju?</p>    
                <p hidden={this.props.name===0}>Ukoliko želite da otkažete rezervaciju morate da platite {this.props.name}% od ukupne cene?</p>
               
                </div>
                

				</Modal.Body>
				<Modal.Footer>
                <Button  onClick={this.props.cancelReservation}>
						{this.props.buttonName}
					</Button>
					<Button onClick={this.props.onCloseModal}>Otkaži</Button>
				</Modal.Footer>
			</Modal>

            
		);
	}
}

export default CancelModal;