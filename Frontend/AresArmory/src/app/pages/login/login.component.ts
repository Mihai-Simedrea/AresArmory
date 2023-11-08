import { Component, OnInit } from '@angular/core';
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

  constructor(private readonly authClient: AuthClient) {
    this.loginModel = new LoginModel();
  }

  ngOnInit(): void {

  }

  login(): void {
    console.log(this.loginModel);
    
    this.authClient.login(this.loginModel).subscribe(result => {
      console.log(result);
    },
    () => {
      console.log('error');
    });
  }
}
