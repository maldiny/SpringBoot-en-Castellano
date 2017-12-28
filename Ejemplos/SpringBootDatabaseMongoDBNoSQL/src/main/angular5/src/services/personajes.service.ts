import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Personajes,EmbeddedPersonajes } from "../model/personajes";
import { Pagination } from '../model/pagination';
 
@Injectable()
export class PersonajesService {
   public _embedded: EmbeddedPersonajes;
   public page: Pagination;
   public error;
   public code;
   public message;
   public status;
   constructor(private http: Http) {
   }
 
   getPersonajes(): Observable<PersonajesService> {
      return this.http.get("http://localhost:8080/personajes")
         .map((response: Response) => response.json())
         .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
   }


  postPersonajes(personajes): Observable<PersonajesService>{
    const url = 'http://localhost:8080/personajes';

    return this.http.post(url, personajes)
      .map((response: Response) => response.json())
      .catch(this.error);
  }

  putPersonajes(personajes): Observable<PersonajesService>{
    return this.http.put(personajes._links.personajes.href, personajes)
      .map((response: Response) => response.json())
      .catch(this.error);
  }

  deletePersonajes(personajes): Observable<PersonajesService>{
     return this.http.delete(personajes._links.personajes.href)
      .map((response: Response) => response.json())
      .catch(this.error);

  }
}