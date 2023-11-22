import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthClient } from 'src/app/backend/api';
import { LoginModel } from 'src/app/components/shared/models/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  imagePath: string = '../../../assets/79403162-8cd3-4052-a8c8-d69831c48ce4.jpg';
  loginModel: LoginModel;

  horizontalPosition: MatSnackBarHorizontalPosition = 'start';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  constructor(private readonly authClient: AuthClient,
    private readonly snackBarService: MatSnackBar,
    private readonly router: Router) {
    this.loginModel = new LoginModel();
  }

  ngOnInit(): void {

  }

  login(): void {
    this.authClient.login(this.loginModel).subscribe(result => {
      this.snackBarService.open('Login successfully', 'Ok', {
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
      });
      
      localStorage.setItem("role", result.role);
      localStorage.setItem("username", result.username);
      localStorage.setItem("email", result.email);
      this.router.navigate(['/home']);
    },
      () => {
        this.snackBarService.open('Account not found', 'Ok', {
          horizontalPosition: this.horizontalPosition,
          verticalPosition: this.verticalPosition,
        });
      });
  }
}
