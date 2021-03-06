import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { Region } from './region';
import { ClienteService } from './cliente.service';
import { Router,ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {
  public cliente:Cliente = new Cliente();
  public regiones:Region[];
  public titulo : string = "Crear cliente";
  public errors : string[];

  constructor(private clienteService : ClienteService,
              private router : Router,
              private activatedRoute:ActivatedRoute,private datepipe :DatePipe) { }

  ngOnInit(): void {
    this.cargarCliente();
    this.clienteService.getRegiones().subscribe(
      regiones => this.regiones = regiones
    );
  }
  public cargarCliente():void{
    this.activatedRoute.params.subscribe( params => {
      let id = params['id'];
      if (id){
        this.clienteService.getCliente(id).subscribe(
          cliente => this.cliente = cliente
        )
      }
    });
    this.cliente.createdAt = this.datepipe.transform(this.cliente.createdAt,'dd/MM/yyyy');
    console.log(this.cliente.createdAt);
  }
  public create():void{
    this.clienteService.create(this.cliente).subscribe(
      response => {
        this.router.navigate(['/clientes']);
        swal.fire('Nuevo Cliente',`Cliente ${this.cliente.nombre} creado con éxito`,'success');
      },
      err => {
        this.errors = err.error.msg as string[];
      }
    );
  }
  public update():void{
    this.cliente.facturas = null;
    this.clienteService.update(this.cliente).subscribe(
      response => {
        this.router.navigate(['/clientes']);
        swal.fire('Cliente Actualizado',`Cliente ${this.cliente.nombre} actualizado con éxito`,'success');
      }
    );
  }
  public compararRegion(o1:Region, o2:Region):boolean{
    if(o1 === undefined && o2 === undefined){
      return true;
    }
    return o1 == null || o2 == null ? false : o1.id === o2.id;
  }
}
