import React from "react";
import { Button, Form } from "react-bootstrap";

import SprintsAxios from "./../../apis/SprintAxios";

class EditZadatak extends React.Component{
    constructor(props){
        super(props);

        let zadatak = {
            ime:"",
            zaduzeni :"",
            bodovi:0,
            stanjeId:-1,
            sprintId:-1
        };
        this.state = {
            zadatak:zadatak,
            sprintovi:[],
            stanja:[]

        };
    }

    componentDidMount(){
        this.getData();
    }
    
    async getData(){
        await this.getSprintovi();
        await this.getStanja();
        await this.getZadaci();

    }

    async getZadaci(){ 
        try{
            let result = await SprintsAxios.get("/zadaci/" + this.props.match.params.id);
            if (result && result.status === 200) {
                this.setState({
                    zadatak:result.data
                });
        }
     }catch(error){
            alert("Nije uspelo dobavljanje")
        }
    }

    async getSprintovi(){
        try{
            let result = await SprintsAxios.get("/sprintovi" );
            if (result && result.status === 200) {
                this.setState({
                    sprintovi: result.data
                });
        }
     }catch(error){
            alert("Nije uspelo dobavljanje");
        }
    }

    async getStanja(){

        try{
            let result = await SprintsAxios.get("/stanja" );
            if (result && result.status === 200) {
                this.setState({
                    stanja: result.data
                });
        }
     }catch(error){
            alert("Nije uspelo dobavljanje");
        }

    }

    async doEdit(){
        try{
            await SprintsAxios.put("/zadaci/" + this.props.match.params.id, this.state.zadatak);
            this.props.history.push("/zadaci");
        }catch(error){
            alert("Nije uspelo dobavljanje");
        }
    }
    
    valueInputChange(event){
        let control = event.target;

        let name = control.name;
        let value = control.value;

        let zadatak = this.state.zadatak;
        zadatak[name] = value;

        this.setState({zadatak:zadatak});
        
    }

    render(){
        return(
        <div>
            <h1> Zadatak</h1>
            <Form>
          <Form.Group>
            <Form.Label>Ime</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChange(event)}
              name="ime"
              value={this.state.zadatak.ime}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Zaduzeni</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChange(event)}
              name="zaduzeni"
              value={this.state.zadatak.zaduzeni}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Bodovi</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChange(event)}
              name="bodovi"
              value={this.state.zadatak.bodovi}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Stanja</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChange(event)}
              name="stanjeId"
              value={this.state.zadatak.stanjeId}
              as="select"
            >
              <option value={-1}></option>
              {this.state.stanja.map((stanje) => {
                return (
                  <option value={stanje.id} key={stanje.id}>
                    {stanje.name}
                  </option>
                );
              })}
            </Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Sprint</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChange(event)}
              name="sprintId"
              value={this.state.zadatak.sprintId}
              as="select"
            >
              <option value={-1}></option>
              {this.state.sprintovi.map((sprint) => {
                return (
                  <option value={sprint.id} key={sprint.id}>
                    {sprint.name}
                  </option>
                );
              })}
            </Form.Control>
          </Form.Group>
          <Button variant="primary" onClick={() => this.doEdit()}>
            Dodaj
          </Button>
        </Form>

      </div>
    );
  }
}
export default EditZadatak;

