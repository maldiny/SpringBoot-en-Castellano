import { DatePipe } from '@angular/common';
import { Component, OnInit, EventEmitter, ViewChild, Output } from '@angular/core';
import { Personajes } from '../model/personajes';
import {PersonajesService} from '../services/personajes.service';

@Component({
  selector: 'personajes-form',
  templateUrl: 'personajesForm.component.html',
  styleUrls: ['personajesForm.component.css']
})
export class PersonajesForm implements OnInit {

  public personajesForm: Personajes;

  @Output() refreshTable = new EventEmitter();

  constructor(private personajesService: PersonajesService) {
    this.personajesForm = new Personajes();
    this.personajesForm.nombre = "";
  }

  ngOnInit() {
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

  enviarPersonaje(){
    this.personajesService.postPersonajes(this.personajesForm).subscribe((response) =>{
      if (response.status == '201'){
        // this.snackBar.open('Support saved succesfully: [ ' + result.message + ' ]', 'OK', {
        //   duration: 4000,
        //   extraClasses: ['success-snackbar']
        // });
        console.log("Success" + JSON.stringify(response));
        this.refreshTable.emit(response);
      }else{
        // this.snackBar.open('Error saving the support: [ ' + result.message + ' ]', 'KO', {
        //         duration: 4000,
        //         extraClasses: ['success-snackbar']
        //       });

        console.log("Error" + JSON.stringify(response));
        this.refreshTable.emit(response);
      }

    });
  }
}
