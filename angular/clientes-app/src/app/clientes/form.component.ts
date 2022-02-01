import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import { Router,ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {
  public cliente:Cliente = new Cliente();
  public titulo : string = "Crear cliente";
  public errors : string[];

  constructor(private clienteService : ClienteService,
              private router : Router,
              private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarCliente();
  }
  public cargarCliente():void{
    this.activatedRoute.params.subscribe( params => {
      let id = params['id'];
      if (id){
        this.clienteService.getCliente(id).subscribe(
          cliente => this.cliente = cliente
        )
      }
    })
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
    this.clienteService.update(this.cliente).subscribe(
      response => {
        this.router.navigate(['/clientes']);
        swal.fire('Cliente Actualizado',`Cliente ${this.cliente.nombre} actualizado con éxito`,'success');
      }
    );
  }
}
