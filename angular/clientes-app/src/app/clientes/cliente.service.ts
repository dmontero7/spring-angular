import { Injectable } from '@angular/core';
import { CLIENTE } from './clientes.json';
import { Cliente } from "./cliente";
import { of, Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor() { }
  //el observable se usa para decirle a todos que el arreglo de cliente puede cambiar para que estén atentos
  getClientes(): Observable<Cliente[]>{
    return of(CLIENTE);
  }
}
