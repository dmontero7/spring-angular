import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import swal from 'sweetalert2';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  titulo:string="Por favor inicia sesión!";
  usuario:Usuario;
  constructor(private authService: AuthService, private router: Router) {
    this.usuario = new Usuario();
  }

  ngOnInit(): void {
    if(this.authService.isAuthenticated()){
      swal.fire('info',`Hola ${this.authService.usuario.nombre} ya estás autenticado`,'info');
      this.router.navigate(['/clientes'])
    }
  }
  login():void{
    console.log(this.usuario);
    if(this.usuario.username == null || this.usuario.password == null){
      swal.fire('Error Login','Usuario o contraseña no pueden estar vacíos','error');
      return;
    }
    this.authService.login(this.usuario).subscribe(response =>{
      console.log(response);
      this.authService.guardarUsuario(response.access_token);
      this.authService.guardarToken(response.access_token);
      let usuario = this.authService.usuario;
      this.router.navigate(['/clientes']);
      swal.fire('Login','Bienvenido '+usuario.nombre,'success');
    },err=>{
      if(err.status == 400){
        swal.fire('Error Login','Usuario o contraseña incorrectas!','error');
      }
    });
  }
}
