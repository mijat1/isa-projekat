import Header from '../Components/Header';
import React, { Component } from "react";
import Axios from "axios";
import { Redirect } from "react-router-dom";
import ModalDialog from '../Components/Modal/ModalDialog';

const API_URL="http://localhost:8080";

class LoginPage extends Component {

    state = {
        errorHeader: "",
		errorMessage: "",
		email: "",
		password: "",
		redirect: false,
		emailError: "none",
		passwordError: "none",
        openModalCantUse:false,
        openModalActivate:false
        



    };

    handleEmailChange = (event) => {
		this.setState({ email: event.target.value });
	};
    handlePasswordChange = (event) => {
		this.setState({ password: event.target.value });
	};
  

    validateForm = () => {
        if (this.state.email === "") {
            this.setState({ emailError: "inline" });
            return false;
        } else if (this.state.password === "") {
            this.setState({ passwordError: "inline" });
            return false;
        }
    
        return true;
    };

    
    handlePasswordModalClose = () => {
        this.setState({ openPasswordModal: false });
    };
    
 

    handleLogin = () => {
       let loginDTO = { username: this.state.email, password: this.state.password };
        console.log(loginDTO);
        Axios.post(API_URL+"/auth/login", loginDTO, { validateStatus: () => true })
        .then((res) => {
            if (res.status === 401) {
                this.setState({ openModalCantUse:true});
            } else if (res.status === 500) {
                alert("Serverska greška");
            }else if(res.status === 403){

                this.setState({ openModalActivate: true});
             
            }
            
            else {
                console.log(res.data);
                localStorage.setItem("keyToken", res.data.accessToken);
                localStorage.setItem("keyRole", JSON.stringify(res.data.roles));
                localStorage.setItem("expireTime", new Date(new Date().getTime() + res.data.expiresIn).getTime());
                console.log(`Bearer ${localStorage.getItem("keyToken")}`);
          
                console.log("aaa");
                console.log(res.data.roles);
              
                this.setState({ redirect: true });
            }
        })
        .catch ((err) => {
			console.log(err);
		});
    };



    handleModalCloseCantUse= () => {
		this.setState({ 
			openModalCantUse: false,
            
		});
	};
    handleModalCloseActive= () => {
		this.setState({ 
			openModalActivate: false,
            
		});
	};

    render() {
        if (this.state.redirect) return <Redirect push to="/" />;
        return(
            <React.Fragment>
                <Header/> 

                <h5 className=" text-center  mb-0 text-uppercase" style={{ marginTop: "2rem" }}>
						Prijava na Doživi avanturu
				</h5>

               <br/>  

               <div class="container" style={{maxWidth: "40%"}}>
                <form>
                    <div class="form-group">
                        <label style={{textAlign:"start !important"}} for="exampleInputEmail1">Email adresa</label>
                        <input type="text" onChange={this.handleEmailChange} value={this.state.email} class="form-control" id="emailAddress" aria-describedby="emailHelp" placeholder="Email adresa"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Lozinka</label>
                        <input type="Password" onChange={this.handlePasswordChange} value={this.state.name} class="form-control" id="password" placeholder="Lozinka"/>
                    </div>
                   
                    
                    
                    <div class="text-center">
                    <button type="button" class="btn btn-primary" style={{width: "20%"}} onClick={this.handleLogin} >Prijavi se</button>
                    </div>

                </form>
            </div>   


            <ModalDialog
				show={this.state.openModalCantUse}
				onCloseModal={this.handleModalCloseCantUse}
				header="Greška"
				text="Email adresa ili lozinka je pogrešna"
			/>
             <ModalDialog
				show={this.state.openModalActivate}
				onCloseModal={this.handleModalCloseActive}
				header="Greška"
				text="Morate aktivirati vaš nalog."
			/>

            </React.Fragment>
        );
    }
}
export default LoginPage;