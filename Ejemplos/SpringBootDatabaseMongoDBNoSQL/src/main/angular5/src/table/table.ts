import {Component, Input, Output} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {Personajes} from '../model/personajes';
import {PersonajesService} from '../services/personajes.service';

/**
 * @title Basic table
 */
@Component({
  selector: 'table-basic',
  styleUrls: ['table.css'],
  templateUrl: 'table.html',
})
export class TableBasicExample {
  displayedColumns = ['nombre', 'edad', 'genero', 'action'];

  public dataSource = new MatTableDataSource<Personajes>();

  public personajesForm: Personajes;

  constructor(private personajesService: PersonajesService){
    this.personajesForm = new Personajes();
    this.personajesForm.nombre = "";
    this.loadPersonajes();
  }

  loadPersonajes(){
    this.personajesService.getPersonajes().subscribe((response) => {
      this.dataSource = new MatTableDataSource<Personajes>(response._embedded.personajes);
    });
  }

  updatePersonaje(item: Personajes){
    this.personajesForm = item;
  }

  removePersonaje(item: Personajes){
    this.personajesService.deletePersonajes(item).subscribe((response) =>{
      this.loadPersonajes();
    });
  }

  acceptButtonDisabled() {
    let disabled = false;
    if (this.personajesForm.nombre == undefined ||
      this.personajesForm.edad == undefined ||
      this.personajesForm.genero == undefined){
      disabled = true;
    }else{
      if (this.personajesForm.nombre.length <= 3 ||
        this.personajesForm.genero.length <= 3){
        disabled = true;
      }
    }
    return disabled;
  }

  reiniciarPersonaje(){
    this.personajesForm = new Personajes();
  }

  enviarPersonaje(){
    if( this.personajesForm._links != undefined){
      this.personajesService.putPersonajes(this.personajesForm).subscribe((response) =>{
        this.loadPersonajes();
      });
    }else{
      this.personajesService.postPersonajes(this.personajesForm).subscribe((response) =>{
        this.loadPersonajes();
      });
    }
  }
}
