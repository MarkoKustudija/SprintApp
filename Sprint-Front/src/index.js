import React from "react";
import ReactDOM from "react-dom";
import { Route, Link, HashRouter as Router, Switch, Redirect } from "react-router-dom";
import { Navbar, Nav, Container, Button } from "react-bootstrap";
import Home from "./components/Home";

import EditZadatak from "./components/zadaci/EditZadatak";
import Zadaci from "./components/zadaci/Zadaci";

import Login from './components/authorization/Login';
import NotFound from "./components/NotFound";
import {logout} from './services/auth';

class App extends React.Component {
  render() {
      return (
        <div>
          <Router>
            <Navbar bg="dark" variant="dark" expand>
              <Navbar.Brand as={Link} to="/">
                JWD
              </Navbar.Brand>
              {/*className="mr-auto" podesi ovu grupu Nav Link-ova da se "rasire" sto je vise moguce,
              i zbog toga je dugme Log in/Log out skroz sa leve strane*/}
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/zadaci">
                  Zadaci
                </Nav.Link>
              </Nav>

              {window.localStorage['jwt'] ? 
                  <Button onClick = {()=>logout()}>Log out</Button> :
                  <Nav.Link as={Link} to="/login">Log in</Nav.Link>
              }
            </Navbar>

            <Container style={{marginTop:25}}>
              <Switch>
                <Route exact path="/" component={Home} />
                <Route exact path="/zadaci" component={Zadaci} />
                <Route exact path="/zadaci/edit/:id" component={EditZadatak} />
                <Route exact path="/login" component={Login}/>
                <Route component={NotFound} />
              </Switch>
            </Container>
          </Router>
        </div>
      );
  }
}

ReactDOM.render(<App />, document.querySelector("#root"));
