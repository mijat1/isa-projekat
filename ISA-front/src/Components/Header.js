import React from 'react';
import * as ReactBootStrap from "react-bootstrap";
import {
    BrowserRouter as Router,
    Link
  } from "react-router-dom";






class Header extends React.Component{


      handleLogout = () => {
      localStorage.removeItem("keyToken");
      localStorage.removeItem("keyRole");
      localStorage.removeItem("expireTime");
    };

    hasRole = (requestRole) => {
      let currentRoles = JSON.parse(localStorage.getItem("keyRole"));

      if (currentRoles === null) return false;


      for (let currentRole of currentRoles) {
        if (currentRole === requestRole) return true;
      }
      return false;
    };

    IsLogedIn = () => {
      let currentRoles = JSON.parse(localStorage.getItem("keyRole"));

      if (currentRoles === null) return false;

      
      return true;
    };

    render(){
      
      
        
    
    
          return(
                  
           

                <div>

          


                <ReactBootStrap.Navbar id="header" collapseOnSelect expand="xl" variant="dark">
                <Link to="/">
                <ReactBootStrap.Navbar.Brand  style={{fontSize : "35px", fontStyle:'italic'}}>Doživi avanturu</ReactBootStrap.Navbar.Brand>
                </Link>
                <ReactBootStrap.Navbar.Toggle aria-controls="responsive-navbar-nav" />
                
                <ReactBootStrap.Navbar.Collapse  id="responsive-navbar-nav">
              
                <ReactBootStrap.Nav className="ml-auto" >
                <Link to="/boats">
                <ReactBootStrap.Nav.Link href="#deets" >Brodovi</ReactBootStrap.Nav.Link>
                </Link>

                <Link to="/cottages">
                <ReactBootStrap.Nav.Link href="#deets" >Vikendice</ReactBootStrap.Nav.Link>
                </Link>

                <Link to="/courses">
                <ReactBootStrap.Nav.Link href="#deets" >Časovi pecanja</ReactBootStrap.Nav.Link>
                </Link>
               
                  
                <ReactBootStrap.NavDropdown alignRight title="Korisnik" id="collasible-nav-dropdown">
                    <ReactBootStrap.NavDropdown.Item href="/login" hidden={this.IsLogedIn()}>Prijava</ReactBootStrap.NavDropdown.Item>
                    <ReactBootStrap.NavDropdown.Divider hidden={this.IsLogedIn()} />
                    <ReactBootStrap.NavDropdown.Item href="/registration" hidden={this.IsLogedIn()}>Registracija</ReactBootStrap.NavDropdown.Item>
                    <ReactBootStrap.NavDropdown.Item  href="/userProfile" hidden={!this.IsLogedIn("*")}>Moj profil</ReactBootStrap.NavDropdown.Item>
                    <ReactBootStrap.NavDropdown.Divider hidden={!this.IsLogedIn()} />
                    <ReactBootStrap.NavDropdown.Item onClick={this.handleLogout} href="/login" hidden={!this.IsLogedIn("*")}>Izloguj se</ReactBootStrap.NavDropdown.Item>
                </ReactBootStrap.NavDropdown>
            
            
                </ReactBootStrap.Nav>
            
              </ReactBootStrap.Navbar.Collapse>
            
            </ReactBootStrap.Navbar>
            
            
                    </div>

                  

              )
    }


}

export default Header;