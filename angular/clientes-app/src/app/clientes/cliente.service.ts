import { Injectable } from '@angular/core';
import { formatDate } from '@angular/common';
//import { CLIENTE } from './clientes.json';
import { Cliente } from "./cliente";
import { /*of,*/ Observable,throwError } from 'rxjs';
import { HttpClient, HttpHeaders,HttpErrorResponse,HttpRequest,HttpEvent } from '@angular/common/http';
import { retry, map, catchError, tap } from 'rxjs/operators';
import swal from 'sweetalert2';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private urlEndPoint : string ='http://localhost:8080/api/clientes';
  private httpHeaders = new HttpHeaders({'Content-Type':'application/json'});
  constructor(private http : HttpClient,private router: Router) { }
  //el observable se usa para decirle a todos que el arreglo de cliente puede cambiar para que estén atentos
  getClientes(page:number): Observable<Cliente[]>{
    //return of(CLIENTE); así funciona para traer datos de una clase en angular
    //return this.http.get<Cliente[]>(this.urlEndPoint);  Esta linea funciona correctamente haciendo el casting
    //esta otra línea lo hace usando el map, pero es exactamente lo mismo
    //Este bloque de código se borra porque ahora viene paginado
    /*return this.http.get(this.urlEndPoint).pipe(
      tap(response => {
        console.log('primer Tab');
        let clientes = response as Cliente[];
        clientes.forEach(cliente => console.log(cliente.nombre))
      }),
      map( response => {
        let clientes = response as Cliente[];
        return clientes.map(cliente => {
          cliente.nombre = cliente.nombre.toUpperCase();
          cliente.createdAt = formatDate(cliente.createdAt,'fullDate','es');
          return cliente;
        });
      }),
      tap(response => {
        console.log('Segundo tab');
        response.forEach(cliente => console.log(cliente.nombre));
      })
    )*/

    return this.http.get(this.urlEndPoint+'/page/'+page).pipe(
      tap((response:any) => {
        (response.content as Cliente[]).forEach(cliente => console.log(cliente.nombre))
      }),
      map( (response:any) => {
        (response.content as Cliente[]).map(cliente => {
          cliente.nombre = cliente.nombre.toUpperCase();
          cliente.createdAt = formatDate(cliente.createdAt,'fullDate','es');
          return cliente;
        });
        return response;
      })
    )
  }

  public create(cliente: Cliente): Observable<Cliente>{
    return this.http.post<Cliente>(this.urlEndPoint,cliente,{headers:this.httpHeaders}).pipe(
      catchError(e => {
        if(e.status === 400){
          return throwError(e);
        }
        console.error(e.error.msg);
        swal.fire(e.error.msg,e.error.error,'error');
          return throwError(e);
      })
    );
  }

  public getCliente(id:number):Observable<Cliente>{
    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/clientes']);
        console.error(e.error.msg);
        swal.fire(e.error.msg,e.error.error,'error');
        return throwError(e);
      })
    );
  }

  public update(cliente:Cliente) : Observable<Cliente>{
    return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`,cliente,{headers:this.httpHeaders}).pipe(
      catchError(e => {
        if(e.status === 400){
          return throwError(e);
        }
        console.error(e.error.msg);
        swal.fire(e.error.msg,e.error.error,'error');
          return throwError(e);
      })
    );
  }

  public delete(id:number) : Observable<Cliente>{
    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`,{headers:this.httpHeaders}).pipe(
      catchError(e => {
        console.error(e.error.msg);
        swal.fire(e.error.msg,e.error.error,'error');
          return throwError(e);
      })
    );
  }

  public subirFoto(archivo:File, id:number): Observable<HttpEvent<{}>> {
    let formData = new FormData();
    formData.append("file",archivo);
    formData.append("id",id+'');
    const req = new HttpRequest('POST', `${this.urlEndPoint}/upload`,formData, {
      reportProgress: true
    });
    return this.http.request(req);
  }
}
