import { Component, OnInit } from '@angular/core';
import { ProductClient } from 'src/app/backend/api';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
  constructor(private readonly productClient: ProductClient) {

  }

  categoryId!: number;
  products: any[] = [];

  ngOnInit(): void {
    let categoryIdString = localStorage.getItem('categoryId')!;

    this.categoryId = parseInt(categoryIdString, 10);
    this.productClient.getByCategory(this.categoryId).subscribe(result => {
      this.products = result;
    });
  }
}
