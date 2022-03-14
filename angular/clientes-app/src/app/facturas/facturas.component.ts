import { Component, OnInit } from '@angular/core';
import { Factura } from './models/factura';
import { Producto } from './models/producto';
import { ItemFactura } from './models/item-factura';
import { FacturaService } from '../facturas/services/factura.service';
import { ClienteService } from '../clientes/cliente.service';
import { ActivatedRoute,Router } from '@angular/router';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, flatMap } from 'rxjs/operators';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import swal from 'sweetalert2';
@Component({
  selector: 'app-facturas',
  templateUrl: './facturas.component.html'
})
export class FacturasComponent implements OnInit {

  titulo: string = "Nueva Factura";
  factura: Factura = new Factura();
  //Elementos para el filtro de productos
  autocompleteControl = new FormControl();
  productosFiltrados: Observable<Producto[]>;
  constructor(private clienteService: ClienteService,
    private activatedRoute: ActivatedRoute,
    private facturaService: FacturaService,
    private router:Router) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let clienteId = +params.get('clienteId');
      this.clienteService.getCliente(clienteId).subscribe(cliente => this.factura.cliente = cliente);
    })
    this.productosFiltrados = this.autocompleteControl.valueChanges.pipe(
      map(value => typeof value === 'string' ? value : value.nombre),
      flatMap(value => value ? this._filter(value) : []),
    );
  }
  private _filter(value: string): Observable<Producto[]> {
    const filterValue = value.toLowerCase();
    return this.facturaService.filtrar(filterValue);
  }
  mostrarNombre(producto?: Producto): string | undefined {
    return producto ? producto.nombre : undefined;
  }
  seleccionarProductos(event: MatAutocompleteSelectedEvent): void {
    let producto = event.option.value as Producto;
    console.log(producto);

    if (this.existe(producto.id)) {
      this.incrementaCantidad(producto.id);
    } else {
      let nuevoItem = new ItemFactura();
      nuevoItem.producto = producto;
      this.factura.items.push(nuevoItem);
    }
    this.autocompleteControl.setValue('');
    event.option.focus();
    event.option.deselect();
  }

  actualizarCantidad(id: number, event: any): void {
    let cantidad = event.target.value as number;
    if(cantidad == 0){
      this.eliminarItemFactura(id);
      return;
    }
    this.factura.items = this.factura.items.map((item: ItemFactura) => {
      if (id === item.producto.id) {
        item.cantidad = cantidad;
      }
      return item;
    });
  }

  existe(id: number): boolean {
    let existe = false;
    this.factura.items.forEach((item: ItemFactura) => {
      if (id === item.producto.id) {
        existe = true;
      }
    });
    return existe;
  }
  incrementaCantidad(id: number): void {
    this.factura.items = this.factura.items.map((item: ItemFactura) => {
      if (id === item.producto.id) {
        ++item.cantidad;
      }
      return item;
    });
  }
  eliminarItemFactura(id:number):void{
    this.factura.items = this.factura.items.filter((item: ItemFactura) => id != item.producto.id);
  }
  create():void{
    console.log(this.factura);
    this.facturaService.create(this.factura).subscribe(factura => {
      swal.fire(this.titulo, `Factura ${factura.descripcion} creada con éxito!`,'success');
      this.router.navigate(['/clientes'])
    });
  }
}
