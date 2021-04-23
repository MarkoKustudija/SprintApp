import React from "react";
import SprintAxios from "../../apis/SprintAxios";
import {Button, Table, Form, ButtonGroup, Collapse} from 'react-bootstrap';

class Zadaci extends React.Component{
     
    constructor(props){
        super(props);
        
        let zadatak = {
            ime:"",
            zaduzeni:"",
            bodovi:"",
            sprintId:-1,
        };

        this.state = {
            zadatak:zadatak,
            zadaci: [], 
            sprintovi:[],
            showSerach: false,
            search: { ime: "", sprintId: -1 },
            pageNum: 0,
            totalPages: 1,
            sprintSum:""
        };
    }

    componentDidMount(){
        this.getData();
    }
    async getData() {
        await this.getSprintovi();
        await this.getZadaci(0);   
    }
       
    async getZadaci(page){
        let config = {params: {
                pageNo: page
            } };

             //Sledeca 2 if-a su tu zbog search-a
            if(this.state.search.ime != ""){
            config.params.ime = this.state.search.ime;
            }
            if(this.state.search.sprintId != -1){
                config.params.sprintId = this.state.search.sprintId;
            }
            try{
            let result = await SprintAxios.get("/zadaci", config);
            if (result && result.status === 200) {
             const sprintSum = result.headers["sprint-total"]?result.headers["sprint-total"]:"";
             this.setState({
                pageNum: page,
                zadaci: result.data, 
                totalPages: result.headers["total-pages"],
                sprintSum: sprintSum
            });
        }
    } catch(error){
            alert("Nije uspelo dodavanje")
        }
    }

   async getSprintovi(){
       try{
           let result = await SprintAxios.get("/sprintovi");
           if(result && result.status === 200){
               this.setState({
                   sprintovi: result.data,
               });
           }
       }catch(error){
           alert ("Nije uspelo dobavljanje")
       }
    }

    goToEdit(zadatakId){
        this.props.history.push("/zadaci/edit/" + zadatakId);
    }

    async doAdd(){
        try{
            await SprintAxios.post("/zadaci/", this.state.zadatak);
            //bitno je da bi "resetovali" polja za kreiranje nakon kreiranja
            let zadatak = {
                ime: "",
                zaduzeni: "",
                bodovi: 0,
                sprintId: -1,
            };
            this.setState({zadatak:zadatak});
           
            this.getZadaci(0);
        } catch(error){
            alert("Nije uspelo dodavanje");
        }
    }

    async doDelete(zadatakId){
        try{
            await SprintAxios.delete("/zadaci/"+ zadatakId);
            var nextPage
            if(this.state.pageNum == this.state.totalPages -1 && this.state.zadatak.length ==1){
                nextPage = this.state.pageNum -1
            } else {
                nextPage = this.state.pageNum
            }
            await this.getZadaci(nextPage);
        } catch(error){
            alert("Nije uspelo brisanje");
        }
    }
      
    addValueInputChanged(e) {
        let control = e.target;

        let name = control.name;
        let value = control.value;

        let zadatak  = this.state.zadatak;
        zadatak [name] = value;
        
        this.setState({ zadatak:zadatak });
    };

    searchValueInputChanged(e) {
        let control = e.target;

        let name = control.name;
        let value = control.value;

        let search = this.state.search;
        search[name] = value;

        this.setState({ search: search });
    };
    
    doSearch(){
        this.getZadaci(0);
    }
  
    canCreateZadatak(){
        const zadatak = this.state.zadatak
        return zadatak.imeZadatka != "" &&
        (zadatak.bodovi != "" && zadatak.bodovi >= 0 && zadatak.bodovi <= 20 && zadatak.bodovi % 1==0)
           && zadatak.sprintId != -1
    }
    
    async promeniStanje(zadatakId){
        try{
            const ret = await SprintAxios.post(`/zadaci/${zadatakId}/promeni_stanje`);
            var zadaci = this.state.zadaci;
            zadaci.forEach((element, index) => {
                if(element.id === zadatakId){
                    zadaci.splice(index, 1, ret.data);
                    this.setState({zadaci:zadaci});
                }
            });
        } catch (error){
            alert ("Nije moguće promeniti stanje.");
        }
    }

    render() {
        return (
          <div>
            <h1>Zadaci</h1>
            {/*Deo za ADD*/}
            {window.localStorage['role']=="ROLE_ADMIN"?
            <Form>
              <Form.Group>
                <Form.Label>Ime</Form.Label>
                <Form.Control
                  onChange={(event) => this.addValueInputChange(event)}
                  name="imeZadatka"
                  value={this.state.zadatak.imeZadatka}
                  as="input"
                ></Form.Control>
              </Form.Group>
              <Form.Group>
                <Form.Label>Zaduzeni</Form.Label>
                <Form.Control
                  onChange={(event) => this.addValueInputChange(event)}
                  name="zaduzeni"
                  value={this.state.zadatak.zaduzeni}
                  as="input"
                ></Form.Control>
              </Form.Group>
              <Form.Group>
                <Form.Label>Bodovi</Form.Label>
                <Form.Control
                  onChange={(event) => this.addValueInputChange(event)}
                  name="bodovi"
                  value={this.state.zadatak.bodovi}
                  as="input"
                  type="number"
                  min = "0"
                  step = "1"
                ></Form.Control>
              </Form.Group>
              <Form.Group>
                <Form.Label>Sprint</Form.Label>
                <Form.Control
                  onChange={(event) => this.addValueInputChange(event)}
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
              <Button disabled = {!this.canCreateTask()} variant="primary" onClick={() => this.doAdd()}>
                Dodaj
              </Button>
            </Form>:null}
    
            {/*Deo za Search*/}
            <Form.Group style={{marginTop:35}}>
              <Form.Check type="checkbox" label="Show search form" onClick={(event) => this.setState({showSearch: event.target.checked})}/>
            </Form.Group>
            <Collapse in={this.state.showSearch}>
            <Form style={{marginTop:10}}>
              <Form.Group>
                <Form.Label>Ime zadatka </Form.Label>
                <Form.Control
                  value={this.state.search.ime}
                  name="imeZadatka"
                  as="input"
                  onChange={(e) => this.searchValueInputChange(e)}
                ></Form.Control>
              </Form.Group>
              <Form.Group>
                <Form.Label>Sprint</Form.Label>
                <Form.Control
                  onChange={(event) => this.searchValueInputChange(event)}
                  name="sprintId"
                  value={this.state.search.sprintId}
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
              <Button onClick={() => this.doSearch()}>Search</Button>
            </Form>
            </Collapse>
    
            {/*Deo za prikaz Zadatka*/}
            <ButtonGroup style={{ marginTop: 25, float:"right"}}>
              <Button 
                style={{ margin: 3, width: 90}}
                disabled={this.state.pageNo==0} onClick={()=>this.getZadaci(this.state.pageNo-1)}>
                Prthodna
              </Button>
              <Button
                style={{ margin: 3, width: 90}}
                disabled={this.state.pageNo==this.state.totalPages-1} onClick={()=>this.getZadaci(this.state.pageNo+1)}>
                Sledeca
              </Button>
            </ButtonGroup>
    
            <Table bordered striped style={{ marginTop: 5 }}>
              <thead className="thead-dark">
                <tr>
                  <th>Ime </th>
                  <th>Zaduzeni</th>
                  <th>Bodovi</th>
                  <th>Stanje</th>
                  <th>Sprint</th>
                  <th colSpan={2}>Actions</th>
                </tr>
              </thead>
              <tbody>
                {this.state.zadaci.map((zadatak) => {
                  return (
                    <tr key={zadatak.id}>
                      <td>{zadatak.imeZadatka}</td>
                      <td>{zadatak.zaduzeni}</td>
                      <td>{zadatak.bodovi}</td>
                      <td>{zadatak.stanjeIme}</td>
                      <td>{zadatak.sprintIme}</td>
                      <td>
                          
                        <Button
                          disabled={zadatak.stanjeId === 3}
                          variant="info"
                          onClick={() => this.changeState(zadatak.id)}
                        >
                          Preci na sledece stanje
                        </Button>
                        {window.localStorage['role']=="ROLE_ADMIN"?
                        [<Button
                          variant="warning"
                          onClick={() => this.goToEdit(zadatak.id)}
                          style={{ marginLeft: 5 }}
                        >
                          Edit
                        </Button>,
    
                        <Button
                          variant="danger"
                          onClick={() => this.doDelete(zadatak.id)}
                          style={{ marginLeft: 5 }}
                        >
                          Delete
                        </Button>]:null}
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </Table>
            <h2 hidden={this.state.sprintSum == ""}>Suma bodova za sprint je {this.state.sprintSum}</h2>
          </div>
        );
      }
}

export default Zadaci;