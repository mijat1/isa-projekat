import React from 'react';
import * as ReactBootStrap from "react-bootstrap";
import Header from '../Components/Header';

import { Link } from "react-router-dom";
import GetAuthorisation from "../Funciton/GetAuthorisation"
import Axios from "axios";



class HomePage extends React.Component {



  componentDidMount(){

  }



      hasSpecificRole = (reqRole) => {
      let roles = JSON.parse(localStorage.getItem("keyRole"));

      if (roles === null) return false;

      for (let role of roles) {
        if (role === reqRole) return true;
      }
      return false;
      };

      hasAnyRole = (reqRole) => {
        let roles = JSON.parse(localStorage.getItem("keyRole"));
  
        if (roles === null) return false;

        return true;
        };

    



    
    render() {
      console.log(`Bearer ${localStorage.getItem("keyToken")}`);
      console.log(`Rola ${localStorage.getItem("keyRole")}`);
    return (
      
      <React.Fragment>
     
          <Header/>
          <div >
          <section id="homePageSection" className="d-flex ">
					<div className="container" style={{textAlign: "center"}}>
						<h1>Dobrodošli </h1>

            <Link  to="/login" hidden={this.hasAnyRole()} className="btn-Login-Register">
							Prijava
						</Link>

						<Link  to="/registration" hidden={this.hasAnyRole()} className="btn-Login-Register">
							Registracija
						</Link>
      
					</div>
				
				  </section>

             
        
          </div>
          
       
      </React.Fragment>
    );
  }
}
  
  export default HomePage;