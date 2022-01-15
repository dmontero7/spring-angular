import { Component, OnInit } from '@angular/core';
import { Cliente } from "./cliente";
import { ClienteService } from './cliente.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html'
})

export class ClientesComponent implements OnInit {

  clientes : Cliente[];
  //agregando el parametro privado aplica inyeccion de dependencias en angular
  constructor(private clienteService : ClienteService) { }

  ngOnInit(): void {
    //se usa el subscribe para inscribir el observador por si hay un cambio en la lista de clientes
  this.clienteService.getClientes().subscribe(
      clientes => this.clientes = clientes
    );
  }

}
