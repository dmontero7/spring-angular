import { Component, OnInit } from '@angular/core';
import { Cliente } from "./cliente";
import { ClienteService } from './cliente.service';
import { Router,ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { tap } from 'rxjs/operators';
import { ModalService } from './detalle/modal.service';
import { AuthService } from '../usuarios/auth.service';
import {URL_BACKEND} from '../config/config';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html'
})

export class ClientesComponent implements OnInit {

  clientes : Cliente[];
  paginador : any;
  clienteSeleccionado : Cliente;
  urlBackend : string = URL_BACKEND;
  //agregando el parametro privado aplica inyeccion de dependencias en angular
  constructor(private clienteService : ClienteService,
            private activatedRoute:ActivatedRoute,
            public authService:AuthService,
            public modalService : ModalService) { }

  ngOnInit(): void {

  this.activatedRoute.paramMap.subscribe( params => {
        let page:number = +params.get('page');
        if(!page){
          page = 0;
        }
          //se usa el subscribe para inscribir el observador por si hay un cambio en la lista de clientes
        this.clienteService.getClientes(page).pipe(
          tap((response:any) => {
            console.log('Clientes Component tab');
            (response.content as Cliente[]).forEach(cliente => console.log(cliente.nombre));
          })
        ).subscribe(response => {
           this.clientes = response.content as Cliente[];
           this.paginador = response;
         });
      })

      this.modalService.notificarUpload.subscribe(cliente => {
        this.clientes = this.clientes.map(clienteOriginal => {
          if(cliente.id == clienteOriginal.id){
            clienteOriginal.foto = cliente.foto;
          }
          return clienteOriginal;
        })
      })
  }

  public delete(cliente : Cliente) : void{
    swal.fire({
      title: '¿Estás seguro?',
      text: `¡Está a punto de eliminar el cliente ${cliente.nombre} !`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminarlo!'
      }).then((result) => {
        if (result.isConfirmed) {
          this.clienteService.delete(cliente.id).subscribe(
            response =>{
                this.clientes = this.clientes.filter(  cli => cli != cliente);
                swal.fire('¡Eliminado!',`Se ha borrado el Cliente ${cliente.nombre}`,'success');
            }
          );
        }
      })
  }
  abrirModal(cliente:Cliente){
    this.clienteSeleccionado = cliente;
    this.modalService.abrir();
  }
}
