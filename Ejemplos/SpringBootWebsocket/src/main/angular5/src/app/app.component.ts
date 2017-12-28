import { Component } from '@angular/core';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import $ from 'jquery';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private serverUrl = 'http://localhost:8080/socket'
  private title = 'WebSockets chat';
  private time;
  private timelbl;
  private stompClient;
  private isChecked = true;

  constructor(){
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection(){
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.debug = null;
      that.stompClient.subscribe("/time", (message) => {
        if(message.body) {
          that.time = message.body;
        }
      });
    });
    this.isChecked = true;
    this.timelbl = 'Connected';
  }

  finalizeWebSocketConnection(){
    this.stompClient.disconnect();
    this.isChecked = false;
    this.timelbl = 'Disconnected';
  }

  sendMessage(message){
    this.stompClient.send("/app/send/message" , {}, message);
    $('#input').val('');
  }

  onChange(e){
    if (e.checked == false) {
      this.finalizeWebSocketConnection();
    }else{
      this.initializeWebSocketConnection();
    }
  }

}
