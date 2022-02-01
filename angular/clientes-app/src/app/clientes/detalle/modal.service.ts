import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModalService {
  modal:boolean = false;
  constructor() { }
  abrir(){
    this.modal=true;
  }
  cerrar(){
    this.modal = false;
  }
}
