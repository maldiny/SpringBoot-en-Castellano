import { Component } from '@angular/core';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import $ from 'jquery';
import {TableBasicExample} from '../table/table';
import {PersonajesService} from '../services/personajes.service';
import {MatTableDataSource} from '@angular/material';
import {Personajes} from '../model/personajes';
import { CdkTableModule } from '@angular/cdk/table';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public datasource = new MatTableDataSource<Personajes>();

  constructor(private personajesService: PersonajesService){
  }

  refreshTable(event):void{
    this.personajesService.getPersonajes().subscribe((response) => {
      this.datasource = new MatTableDataSource<Personajes>(response._embedded.personajes);
    });
  }
}
