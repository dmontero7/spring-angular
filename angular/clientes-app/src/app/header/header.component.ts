import{ Component } from '@angular/core';
import { AuthService } from '../usuarios/auth.service';
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent{
  title = 'App Angular';
  constructor(public authService: AuthService,private router:Router){
  }
  logout():void{
    swal.fire('info',`${this.authService.usuario.nombre}, gracias por usar nuestro servicio`,'info');
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
