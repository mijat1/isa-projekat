
import './App.css';
import HomePage from "./Pages/HomePage"
import LoginPage from "./Pages/LoginPage"
import RegistrationPage from "./Pages/RegistrationPage"
import ActivationPage from "./Pages/ActivationPage"
import BoatsPage from './Pages/Boats/BoatsPage';
import CottagesPage from './Pages/Cottages/CottagesPage';
import CoursesPage from './Pages/Courses/CoursesPage';
import UserProfilePage from "./Pages/UserProfile";
import BoatFutureReservationsPage from './Pages/Boats/BoatFutureReservationsPage'
import BoatHistoryReservationsPage from './Pages/Boats/BoatHistoryReservationsPage'
import ComplaintUserPage from './Pages/ComplaintUserPage';
import ComplaintUnitPage from './Pages/ComplaintUnitPage';
import ActionReservationPageBoat from './Pages/Boats/ActionReservationPageBoat';
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
        <Route  to ="/boatFutureReservation" path ="/boatFutureReservation"  component={BoatFutureReservationsPage}/>
        <Route  to ="/historyBoatReservation" path ="/historyBoatReservation"  component={BoatHistoryReservationsPage}/>
        <Route  to ="/userProfile" path ="/userProfile"  component={UserProfilePage}/>
        <Route  to ="/userComplaint" path ="/userComplaint"  component={ComplaintUserPage}/>
        <Route  to ="/unitComplaint" path ="/unitComplaint"  component={ComplaintUnitPage}/>
        <Route  to ="/actionBoat" path ="/actionBoat"  component={ActionReservationPageBoat}/>
        </Switch>
    </Router>
     
    </div>
  );
}

export default App;
