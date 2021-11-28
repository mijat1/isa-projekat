
import './App.css';
import HomePage from "./Pages/HomePage"
import LoginPage from "./Pages/LoginPage"
import RegistrationPage from "./Pages/RegistrationPage"
import ActivationPage from "./Pages/ActivationPage"
import {
  BrowserRouter as Router,
  Route,
  Link,
  Switch
} from "react-router-dom";




function App() {
 
  return (
    <div className="App">
    
    
    <Router>
      
        <Switch>
        <Route exact to ="/"  path ="/" component={HomePage}/>
        <Route  to ="/login" path ="/login"  component={LoginPage}/>
        <Route  to ="/registration" path ="/registration"  component={RegistrationPage}/>
        <Route to="/activeAccount/:id" path ="/activeAccount/:id"  component={ActivationPage}/>
        </Switch>
    </Router>
     
    </div>
  );
}

export default App;
