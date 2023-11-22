import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryClient, ProductClient } from 'src/app/backend/api';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {
  @Input() name!: string;
  @Input() id!: number;
  @Input() type!: string;
  role!: string;

  constructor(private readonly router: Router,
    private readonly categoryClient: CategoryClient,
    private readonly productClient: ProductClient) {

  }

  ngOnInit(): void {
    this.role = localStorage.getItem('role')!;
  }

  redirectTo(): void {
    localStorage.setItem('categoryId', this.id.toString());
    this.router.navigate(['/product']);
  }

  deleteItem(): void {
    if (this.type == 'category') {
      this.categoryClient.delete(this.id).subscribe(() => {

      }, () => { }, () => {

        this.router.navigateByUrl('/home', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/category']);
        });
      });

    } else {
      this.productClient.delete(this.id).subscribe(() => { }, () => { }, () => {
        this.router.navigateByUrl('/home', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/product']);
        });
      })
    }
  }
}
