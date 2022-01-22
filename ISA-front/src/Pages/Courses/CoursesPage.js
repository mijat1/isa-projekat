import React, { Component } from "react";
import Axios from "axios";
import Header from '../../Components/Header';
import GetAuthorisation from "../../Funciton/GetAuthorisation"
import DatePicker from "react-datepicker";
import 'react-confirm-alert/src/react-confirm-alert.css'; 
import { confirmAlert } from 'react-confirm-alert';
import PriceModal from "../../Components/Modal/PriceModal";
import "react-datepicker/dist/react-datepicker.css";
import Services from "../../Components/Modal/Services";

const API_URL = "http://localhost:8080";

class CoursesPage extends Component {



	state = {
		cottages: [],
		services: [],
		selectedBoat:"",
		selectedBoatImage:"",
		selectedBoatName:"",
		selectedBoatAddress:"",
		selectedBoatGrade: "",		
		selectedBoatDescription:"",
		selectedBoatPrice:"",
		selectedDate: new Date(),
		hours: new Date().getHours(),
		minutes: new Date().getMinutes(),
		days:0,
		location:"",
		gradeFrom:"",
		gradeTo:"",
		people:0,
		maxCount: "",
		price: 0,
		startDate:new Date(),
		endDate:new Date(),
		showSearchForm:false,
		showingSearched:false,
		openPriceModal:false,
		openReserveModal:false,
		loggedClient: false,
		isUnitEmpty : false
	};

	handleFormShow = () => {
        this.setState({ showSearchForm: !this.state.showSearchForm });
    };

	componentDidMount() {
		this.state.loggedClient= this.hasRole("ROLE_CLIENT");
		console.log(localStorage.getItem("keyRole"));
		Axios.get(API_URL + "/course/allCourses")

			.then((res) => {
				console.log(res.data);
				this.setState({ cottages: res.data });
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



	handleResetSearch = () => {
		Axios.get(API_URL + "/course/allCourses", {

			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() },
		})
			.then((res) => {
				this.setState({
					cottages: res.data,
					formShowed: false,
					showingSearched: false,
					days:0,
					location:"",
					gradeFrom:"",
					gradeTo:"",
					people:0,
					selectedDate: new Date(),
					hours: new Date().getHours(),
					minutes: new Date().getMinutes(),
					price: 0,
					isUnitEmpty : false
				
					
				});
			})
			.catch((err) => {
				console.log(err);
			});


	};



	moveToActions =() => {

		this.props.history.push("/actionCourse");
	 }
 



	handleModalClose = () => {
		this.setState({ openPriceModal: false });
	};

	handleReservationModalClose = () => {
		this.setState({ openReserveModal: false });
	};

    handleShowPrice = (services) => {
		this.setState({ openPriceModal: true ,services:services});
        console.log("eeeeeeeeeeeeeeeeeeeee");
        console.log(this.state.openPriceModal);
	};


	handleDateChange = (date) => {
		this.setState({ selectedDate: date });
	};

	handleMinutesChange = (event) => {
		if (event.target.value < 0) this.setState({ minutes: 0 });
        else if (event.target.value > 59) this.setState({ minutes: 59 });
		else this.setState({ minutes: event.target.value });
	};

	handleHoursChange = (event) => {
        if (event.target.value < 0) this.setState({ hours: 0 });
		else if (event.target.value > 23) this.setState({ hours: 23 });
		else this.setState({ hours: event.target.value });
	};

	handleDaysChange = (event) => {
     this.setState({ days: event.target.value });
	};

	handlePeopleChange = (event) => {
		this.setState({ people: event.target.value });
	   };

	handleLocationChange = (event) => {
		this.setState({ location: event.target.value });
	};

	handleGradeFromChange = (event) => {
		this.setState({ gradeFrom: event.target.value });
	};

	handleGradeToChange = (event) => {
		this.setState({ gradeTo: event.target.value });
	};

	

	handleOnSelect = (cottage,services)=>{
		if(this.state.showingSearched=== true){

		this.setState({ selectedBoat:cottage.Id,
						selectedBoatImage:cottage.EntityDTO.image,
						selectedBoatName:cottage.EntityDTO.name,
					    selectedBoatAddress:cottage.EntityDTO.address,
						selectedBoatGrade: cottage.EntityDTO.avgGrade,		
						selectedBoatDescription:cottage.EntityDTO.description,
						selectedBoatPrice:cottage.EntityDTO.price,
						services:services,
			           openReserveModal: true
					   });
		console.log(this.state.selectedBoatAddress)	;		
		console.log(this.state.selectedBoatPrice)	;
		console.log(this.state.services);   
		}

	};




	handleSearchClick = () => {
		{

			this.setState({
			
				startDate: new Date(
					this.state.selectedDate.getFullYear(),
					this.state.selectedDate.getMonth(),
					this.state.selectedDate.getDate(),
					this.state.hours,
					this.state.minutes,
					0,
					0
				).getTime(),
				
				hiddenUnsuccessfulAlert: true,
				UnsuccessfulHeader: "",
				UnsuccessfulMessage: "",
			});
			let dateMin = new Date().getTime() + 3600000;
			let startDateSelected= new Date(
				this.state.selectedDate.getFullYear(),
				this.state.selectedDate.getMonth(),
				this.state.selectedDate.getDate(),
				this.state.hours,
				this.state.minutes,
				0,
				0
			).getTime();
			
			let days = this.state.days;
			let people = this.state.people;
			let location = this.state.location;
			let gradeFrom = this.state.gradeFrom;
			let gradeTo = this.state.gradeTo;
			
			if (days === "") days = -1;
			if (people === "") people = -1;
			if (location === "") location = "";
			if (gradeFrom === "") gradeFrom = -1;
			if (gradeTo === "") gradeTo = -1;
			if (startDateSelected <= dateMin) {
				alert("Minimum sat vremena od trenutnog vremena možete rezervisati.")
			} else  if (days<= 0) {
				alert("Morate izabrati broj dana")
			} else  if (people<= 0) {
				alert("Morate uneti broj ljudi")
			} 
			else {
				let endDaree=new Date(
					this.state.selectedDate.getFullYear(),
					this.state.selectedDate.getMonth(),
					this.state.selectedDate.getDate(),
					this.state.hours,
					this.state.minutes,
					0,
					0
				).getTime();
				endDaree=endDaree+24*60*60*1000*this.state.days
			this.setState({endDate:endDaree})
			console.log(this.state.endDate);
			Axios.get(API_URL+ "/course/allCoursesWithFreePeriod", {
				params: {
					DateTime:startDateSelected,
					days: days,
					people: people,
					location: location,
					gradeFrom: gradeFrom,
					gradeTo: gradeTo,
				
				},
				validateStatus: () => true,
				headers: { Authorization: GetAuthorisation() },
			})
				.then((res) => {
					console.log(res.data);
					this.setState({
						cottages: res.data,
						formShowed: false,
						showingSearched: true,
					});
					if(this.state.cottages.length!==0){
						this.setState({ isUnitEmpty: false });
					}else{
						this.setState({isUnitEmpty: true });
					}
				})
				.catch((err) => {
					console.log(err);
				});
		}
	}
	};


	handleSortByPriceAscending = () => {
        

		this.setState({
			
			startDate: new Date(
				this.state.selectedDate.getFullYear(),
				this.state.selectedDate.getMonth(),
				this.state.selectedDate.getDate(),
				this.state.hours,
				this.state.minutes,
				0,
				0
			).getTime(),

			hiddenUnsuccessfulAlert: true,
			UnsuccessfulHeader: "",
			UnsuccessfulMessage: "",
		});

		
	
		let startDateSelected= new Date(
			this.state.selectedDate.getFullYear(),
			this.state.selectedDate.getMonth(),
			this.state.selectedDate.getDate(),
			this.state.hours,
			this.state.minutes,
			0,
			0
		).getTime();

		let days = this.state.days;
		let people = this.state.people;
		let location = this.state.location;
		let gradeFrom = this.state.gradeFrom;
		let gradeTo = this.state.gradeTo;

		
		if (days === "") days = -1;
		if (people === "") people = -1;
		if (location === "") location = "";
		if (gradeFrom === "") gradeFrom = -1;
			if (gradeTo === "") gradeTo = -1;
	    
             console.log("sok");
			Axios.get( API_URL + "/course/getAllFreeCoursesForSelectedDate/SortByPriceAscending"  , {
				params: {
					DateTime:startDateSelected,
					days: days,
					people: people,
					location: location,
					gradeFrom: gradeFrom,
					gradeTo: gradeTo,
				
				},
			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() }},
			)
				.then((res) => {
					 if (res.status === 200) {
						this.setState({ cottages: res.data });
						if(this.state.cottages.length!==0){
							this.setState({ isUnitEmpty: false });
						}else{
							this.setState({isUnitEmpty: true });
						}
						console.log(res.data);
					}
					 if(res.status === 401){
						 this.props.history.push("/login");
                        
                    }
				})
				.catch((err) => {
					console.log(err);
                    this.setState({ hiddenUnsuccessfulAlert: false, 
                        UnsuccessfulHeader: "Error", 
                        UnsuccessfulMessage: "Some error" });
                    
				});
		
	};



	handleSortByPriceDescending = () => {
        

		this.setState({
			
			startDate: new Date(
				this.state.selectedDate.getFullYear(),
				this.state.selectedDate.getMonth(),
				this.state.selectedDate.getDate(),
				this.state.hours,
				this.state.minutes,
				0,
				0
			).getTime(),

			hiddenUnsuccessfulAlert: true,
			UnsuccessfulHeader: "",
			UnsuccessfulMessage: "",
		});

		let days = this.state.days;
		let people = this.state.people;
		let location = this.state.location;
		let gradeFrom = this.state.gradeFrom;
		let gradeTo = this.state.gradeTo;

		
		if (days === "") days = -1;
		if (people === "") people = -1;
		if (location === "") location = "";
		if (gradeFrom === "") gradeFrom = -1;
			if (gradeTo === "") gradeTo = -1;
	
		let startDateSelected= new Date(
			this.state.selectedDate.getFullYear(),
			this.state.selectedDate.getMonth(),
			this.state.selectedDate.getDate(),
			this.state.hours,
			this.state.minutes,
			0,
			0
		).getTime();

		
	    
             console.log("sok");
			Axios.get( API_URL + "/course/getAllFreeCoursesForSelectedDate/SortByPriceDescending" , {
				params: {
					DateTime:startDateSelected,
					days: days,
					people: people,
					location: location,
					gradeFrom: gradeFrom,
					gradeTo: gradeTo,
				
				},
			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() }},
			)
				.then((res) => {
					 if (res.status === 200) {
						this.setState({ cottages: res.data });
						if(this.state.cottages.length!==0){
							this.setState({ isUnitEmpty: false });
						}else{
							this.setState({isUnitEmpty: true });
						}
						console.log(res.data);
					}
					 if(res.status === 401){
						 this.props.history.push("/login");
                        
                    }
				})
				.catch((err) => {
					console.log(err);
                    this.setState({ hiddenUnsuccessfulAlert: false, 
                        UnsuccessfulHeader: "Error", 
                        UnsuccessfulMessage: "Some error" });
                    
				});
		
	};



	handleSortByGradeAscending = () => {
        

		this.setState({
			
			startDate: new Date(
				this.state.selectedDate.getFullYear(),
				this.state.selectedDate.getMonth(),
				this.state.selectedDate.getDate(),
				this.state.hours,
				this.state.minutes,
				0,
				0
			).getTime(),

			hiddenUnsuccessfulAlert: true,
			UnsuccessfulHeader: "",
			UnsuccessfulMessage: "",
		});

		
	
		let startDateSelected= new Date(
			this.state.selectedDate.getFullYear(),
			this.state.selectedDate.getMonth(),
			this.state.selectedDate.getDate(),
			this.state.hours,
			this.state.minutes,
			0,
			0
		).getTime();

		let days = this.state.days;
		let people = this.state.people;
		let location = this.state.location;
		let gradeFrom = this.state.gradeFrom;
		let gradeTo = this.state.gradeTo;

		
		if (days === "") days = -1;
		if (people === "") people = -1;
		if (location === "") location = "";
		if (gradeFrom === "") gradeFrom = -1;
			if (gradeTo === "") gradeTo = -1;
	    
             console.log("sok");
			Axios.get( API_URL + "/course/getAllFreeCoursesForSelectedDate/SortByGradeAscending" , {
				params: {
					DateTime:startDateSelected,
					days: days,
					people: people,
					location: location,
					gradeFrom: gradeFrom,
					gradeTo: gradeTo,
				
				},
			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() }},
			)
				.then((res) => {
					 if (res.status === 200) {
						this.setState({ cottages: res.data });
						if(this.state.cottages.length!==0){
							this.setState({ isUnitEmpty: false });
						}else{
							this.setState({isUnitEmpty: true });
						}
						console.log(res.data);
					}
					 if(res.status === 401){
						 this.props.history.push("/login");
                        
                    }
				})
				.catch((err) => {
					console.log(err);
                    this.setState({ hiddenUnsuccessfulAlert: false, 
                        UnsuccessfulHeader: "Error", 
                        UnsuccessfulMessage: "Some error" });
                    
				});
		
	};


	handleSortByGradeDescending = () => {
        

		this.setState({
			
			startDate: new Date(
				this.state.selectedDate.getFullYear(),
				this.state.selectedDate.getMonth(),
				this.state.selectedDate.getDate(),
				this.state.hours,
				this.state.minutes,
				0,
				0
			).getTime(),

			hiddenUnsuccessfulAlert: true,
			UnsuccessfulHeader: "",
			UnsuccessfulMessage: "",
		});

		
	
		let startDateSelected= new Date(
			this.state.selectedDate.getFullYear(),
			this.state.selectedDate.getMonth(),
			this.state.selectedDate.getDate(),
			this.state.hours,
			this.state.minutes,
			0,
			0
		).getTime();

		let days = this.state.days;
		let people = this.state.people;
		let location = this.state.location;
		let gradeFrom = this.state.gradeFrom;
		let gradeTo = this.state.gradeTo;

		
		if (days === "") days = -1;
		if (people === "") people = -1;
		if (location === "") location = "";
		if (gradeFrom === "") gradeFrom = -1;
			if (gradeTo === "") gradeTo = -1;
	    
             console.log("sok");
			Axios.get( API_URL + "/course/getAllFreeCoursesForSelectedDate/SortByGradeDescending"   , {
				params: {
					DateTime:startDateSelected,
					days: days,
					people: people,
					location: location,
					gradeFrom: gradeFrom,
					gradeTo: gradeTo,
				
				},
			validateStatus: () => true,
			headers: { Authorization: GetAuthorisation() }},
			)
				.then((res) => {
					 if (res.status === 200) {
						this.setState({ cottages: res.data });
						if(this.state.cottages.length!==0){
							this.setState({ isUnitEmpty: false });
						}else{
							this.setState({isUnitEmpty: true });
						}
						console.log(res.data);
					}
					 if(res.status === 401){
						 this.props.history.push("/login");
                        
                    }
				})
				.catch((err) => {
					console.log(err);
                    this.setState({ hiddenUnsuccessfulAlert: false, 
                        UnsuccessfulHeader: "Error", 
                        UnsuccessfulMessage: "Some error" });
                    
				});
		
	};

	


	render() {


		return (

			<React.Fragment>

				<Header />

				<div id="allBoats" >

					<div className="container" style={{ marginTop: "2em" }} >
						<h2 className=" text-center  mt-2 text-uppercase">Časovi Pecanja</h2>
					
					</div>


					<div style={{ width: "70%", marginTop: "3em", marginLeft: "auto", marginRight: "auto" }} width="100%">


					<button className="btn btn-primary " type="button" onClick={this.handleFormShow} hidden={!this.hasRole("ROLE_CLIENT")}>
                        {this.state.showSearchForm ? "Zatvori pretragu" : "Otvori pretragu"}
                    </button>

						<button hidden={!this.state.showSearchForm} type="button" class="btn btn-outline-primary btn-xl ml-2" onClick={this.handleResetSearch}>

							Poništi pretragu

						</button>

						<button className="btn btn-outline-primary pull-right" type="button" onClick={this.moveToActions}>
                             Akcije
                      </button>
						
						<form className={this.state.showSearchForm ? "form-inline mt-3" : "form-inline mt-3 collapse"} width="100%" id="formCollapse">
						<div className="form-row justify-content-center">
                            <div className="mr-2">
								<div style={{ fontSize: "20px" }}>
                                    Izaberi datum:
								</div>

								<DatePicker
									className="form-control"
									minDate={new Date()}
									onChange={(date) => this.handleDateChange(date)}
									selected={this.state.selectedDate}
								/>
						    </div>
							<div className="mr-2">
								<div style={{ fontSize: "20px" }}>
									Sati:
								</div>
								<input
                                    type="number"
									min="00"
									max="23"
                                    className="form-control"
									onChange={this.handleHoursChange}
									value={this.state.hours}
									style={{ width: "8em" }}
									
								/>
							</div>
							<div className="mr-2">
								<div style={{ fontSize: "20px" }}>
                                    Minuti:
								</div>
								<input
									min="00"
                                    max="59"
                                    type="number"
									className="form-control"
									onChange={this.handleMinutesChange}
									value={this.state.minutes}
									style={{ width: "8em" }}
									
									
								/>
							</div>

							<div className="mr-2">
								<div style={{ fontSize: "20px" }}>
                                    Broj dana:
								</div>
								<input
									min="00"
                                    max="59"
                                    type="number"
									className="form-control"
									onChange={this.handleDaysChange}
									value={this.state.days}
									style={{ width: "8em" }}
									
									
								/>
							</div>

							<div className="mr-2">
								<div style={{ fontSize: "20px" }}>
                                    Broj osoba:
								</div>
								<input
                                    type="number"
									className="form-control"
									onChange={this.handlePeopleChange}
									value={this.state.people}
									style={{ width: "8em" }}
									
									
								/>
							</div>



							<div className="mr-2">
								<div style={{ fontSize: "20px" }}>
                                    Lokacija:
								</div>
								<input
                                    type="text"
									className="form-control "
									onChange={this.handleLocationChange}
									value={this.state.location}
									style={{ width: "8em" }}
									
									
								/>
							</div>

							<div className="mr-2">
								<div style={{ fontSize: "20px" }}>
                                    Ocena od:
								</div>
								<input
                                    min="0"
									max="5"
                                    type="number"
									className="form-control"
									onChange={this.handleGradeFromChange}
									value={this.state.gradeFrom}
									style={{ width: "8em" }}
									
									
								/>
							</div>

							<div className="mr-2">
								<div style={{ fontSize: "20px" }}>
                                    Ocena do:
								</div>
								<input
                                    min="0"
									max="5"
                                    type="number"
									className="form-control"
									onChange={this.handleGradeToChange}
									value={this.state.gradeTo}
									style={{ width: "8em" }}
									
									
								/>
							</div>
							<div className="mr-2" >	
							
								<button
									style={{ background: "#1977cc" , marginTop:"30px"}}
									onClick={this.handleSearchClick}
									className="btn btn-primary btn-x2"
									type="button"
								>
									<i className="icofont-search mr-1"></i>
									Pretraži
								</button>
							</div>	
							</div>
						</form>

						<div className="form-row mt-3" hidden={!this.state.showingSearched}>
								<div className="form-col">
									<div className="dropdown">
										<button
											className="btn btn-primary dropdown-toggle"
											type="button"
											id="dropdownMenu2"
											data-toggle="dropdown"
											aria-haspopup="true"
											aria-expanded="false"
										>
											Sortiraj po
										</button>
										<div className="dropdown-menu" aria-labelledby="dropdownMenu2">
											<button className="dropdown-item" type="button" onClick={this.handleSortByGradeAscending}>
												Oceni rastuće
											</button>
											<button className="dropdown-item" type="button" onClick={this.handleSortByGradeDescending}>
												Oceni opadajuće
											</button>
											<button className="dropdown-item" type="button" onClick={this.handleSortByPriceAscending}>
												Ceni rastuće
											</button>
											<button className="dropdown-item" type="button" onClick={this.handleSortByPriceDescending}>
												Ceni opadajuće
											</button>
										</div>
									</div>
								</div>
								<div className="form-col ml-3">
									<div className={this.props.showingSorted ? "form-group" : "form-group collapse"}>
										<button type="button" className="btn btn-outline-secondary" onClick={this.props.handleResetSort}>
											<i className="icofont-close-line mr-1"></i>Reset criteria
										</button>
									</div>
								</div>
						</div>
						<h5 className=" text-center  mt-3  text-danger" hidden={!this.state.isUnitEmpty}>
						Trenutno nema slobodnih entiteta za izabrane kriterijume.
						</h5>
						<table className={(this.state.loggedClient === true && this.state.showingSearched===true) ? "table table-hover" : "table"} style={{ width: "85%", marginTop: "3rem" }}>
							<tbody>
								{this.state.cottages.map((cottage) => (
									<tr id={cottage.Id} key={cottage.Id} >
										<td width="300em" >
											<div style={{ marginTop: "5%" }}>
												<img className="img-fluid" src={`data:image/jpeg;base64,${cottage.EntityDTO.image}`} width="300em"  />
											</div>
										</td>
										<td width="50%" onClick={() => this.handleOnSelect(cottage,cottage.EntityDTO.services)}> 
											<div style={{ marginTop: "2%" }}>
												<b>Naziv:</b> {cottage.EntityDTO.name}
											</div>
											<div>
												<b>Adresa:</b> {cottage.EntityDTO.address}
											</div>
											<div>
												<b>Ocena:</b> {cottage.EntityDTO.avgGrade}
												<i className="icon-star" style={{ color: "yellow" }}></i>
											</div>
                                            <div>
												<b>Kratak opis:</b> {cottage.EntityDTO.description}
											</div>
											<div>
												<b>Cena:</b> {cottage.EntityDTO.price}
											</div>
										</td>
                                        <td className="align-middle">
											<div>
												<button
													type="button"
													onClick={() => this.handleShowPrice(cottage.EntityDTO.services)}

													className="btn btn-outline-primary btn-block"
												>
													Cenovnik
												</button>
											
											</div>
                                        </td>
									</tr>
								))}
							</tbody>
						</table>





					</div>

					<Services show={this.state.openReserveModal}
					 onCloseModal={this.handleReservationModalClose} 
					 startDate={this.state.startDate}
					 endDate={this.state.endDate}
					 days={this.state.days}
					 prices={this.state.services} 
					 image=   {this.state.selectedBoatImage}
					 name=	{this.state.selectedBoatName}
					 address=   {this.state.selectedBoatAddress}
					 grade=	{this.state.selectedBoatGrade}	
					 description=	{this.state.selectedBoatDescription}
					 price=	{this.state.selectedBoatPrice}
					 boatId={this.state.selectedBoat}  
					 type={"COURSE"}
					  />				
                    <PriceModal show={this.state.openPriceModal} onCloseModal={this.handleModalClose} prices={this.state.services}  header="Cenovnik" />
				</div>

             
			</React.Fragment>


		);
	}
}

export default CoursesPage;
