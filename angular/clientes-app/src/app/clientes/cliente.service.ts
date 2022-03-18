import { Injectable } from '@angular/core';
import { formatDate,DatePipe } from '@angular/common';
//import { CLIENTE } from './clientes.json';
import { Cliente } from "./cliente";
import { Region } from "./region";
import { /*of,*/ Observable,throwError } from 'rxjs';
import { HttpClient,HttpRequest,HttpEvent } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import {URL_BACKEND} from '../config/config';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private urlEndPoint : string =URL_BACKEND+'/api/clientes';
  constructor(private http : HttpClient,private router: Router,public datepipe: DatePipe) { }

  /*private agregarAuthorizationHeader(){
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization','Bearer '+token);
    }
    return this.httpHeaders;
  }
  private isNotAuthorized(e) :boolean{
    if(e.status==401){
      if(this.authService.isAuthenticated()){
        this.authService.logout();
      }
      this.router.navigate(['/login']);
        return true;
    }else if(e.status==403){
      swal.fire('Acceso Denegado',`Lo siento ${this.authService.usuario.nombre} no tienes acceso a este recurso`,'warning');
      this.router.navigate(['/clientes']);
    }
    return false;
  }*/
  getRegiones():Observable<Region[]>{
    return this.http.get<Region[]>(this.urlEndPoint + '/regiones');
    /*.pipe(catchError(e=> {
      //this.isNotAuthorized(e);
      return throwError(e);
    }));*/
  }
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
          cliente.createdAt = this.datepipe.transform(cliente.createdAt,'yyyy-MM-dd');
          return cliente;
        });
        return response;
      })
    )
  }

  public create(cliente: Cliente): Observable<Cliente>{
    return this.http.post<Cliente>(this.urlEndPoint,cliente).pipe(
      catchError(e => {
        if(e.status === 400){
          return throwError(e);
        }
        if(e.error.msg){
          console.error(e.error.msg);
        }
        return throwError(e);
      })
    );
  }

  public getCliente(id:number):Observable<Cliente>{
    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if(e.status != 401 && e.error.msg){
          this.router.navigate(['/clientes']);
          console.error(e.error.msg);
        }
        return throwError(e);
      }));
  }

  public update(cliente:Cliente) : Observable<Cliente>{
    return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`,cliente).pipe(
      catchError(e => {
        if(e.status === 400){
          return throwError(e);
        }
        if(e.error.msg){
          console.error(e.error.msg);
        }
          return throwError(e);
      })
    );
  }

  public delete(id:number) : Observable<Cliente>{
    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if(e.error.msg){
          console.error(e.error.msg);
        }
          return throwError(e);
      })
    );
  }

  public subirFoto(archivo:File, id:number): Observable<HttpEvent<{}>> {
    let formData = new FormData();
    formData.append("file",archivo);
    formData.append("id",id+'');


    const req = new HttpRequest('POST', `${this.urlEndPoint}/upload`,formData, {
      reportProgress: true });
    return this.http.request(req);
  }
}
