import { Component, OnInit } from '@angular/core';
import { AuthClient } from 'src/app/backend/api';
import { AuthModel } from 'src/app/components/shared/models/auth.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  imagePath: string = '../../../assets/210597d3-7068-4503-8f7e-1edeb82d12b3.jpg';

  authModel: AuthModel;

  constructor(private readonly authClient: AuthClient) {
    this.authModel = new AuthModel();
  }

  ngOnInit(): void {

  }

  register(): void {
    this.authClient.signUp(this.authModel).subscribe(result => {
      console.log(result);
    })
  }
}
