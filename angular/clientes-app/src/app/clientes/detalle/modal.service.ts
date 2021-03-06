import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModalService {
  modal:boolean = false;
  private _notificarUpload = new EventEmitter<any>();
  constructor() { }
  abrir(){
    this.modal=true;
  }
  cerrar(){
    this.modal = false;
  }

  get notificarUpload() :EventEmitter <any>{
    return this._notificarUpload;
  }
}
