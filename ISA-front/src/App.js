
import './App.css';
import HomePage from "./Pages/HomePage"
import LoginPage from "./Pages/LoginPage"
import RegistrationPage from "./Pages/RegistrationPage"
import ActivationPage from "./Pages/ActivationPage"
import BoatsPage from './Pages/Boats/BoatsPage';
import CottagesPage from './Pages/Cottages/CottagesPage';
import CoursesPage from './Pages/Courses/CoursesPage';
import UserProfilePage from "./Pages/UserProfile";
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
        <Route to="/activeAccount/:id" path ="/activeAccount/:id"  component={ActivationPage}/>c
        <Route  to ="/boats" path ="/boats"  component={BoatsPage}/>
        <Route  to ="/cottages" path ="/cottages"  component={CottagesPage}/>
        <Route  to ="/courses" path ="/courses"  component={CoursesPage}/>
        <Route  to ="/userProfile" path ="/userProfile"  component={UserProfilePage}/>
        </Switch>
    </Router>
     
    </div>
  );
}

export default App;
