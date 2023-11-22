import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent {
  @Input() name!: string;
  @Input() id!: number;

  constructor(private readonly router: Router) {

  }
  
  redirectTo(): void {
    localStorage.setItem('categoryId', this.id.toString());
    this.router.navigate(['/product']);
  }
}
