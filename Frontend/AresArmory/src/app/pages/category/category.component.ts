import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryClient } from 'src/app/backend/api';
import { CategoryModel } from 'src/app/components/shared/models/category.model';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {
  constructor(private readonly router: Router,
    private readonly categoryClient: CategoryClient) {

  }

  categories: any[] = []
  role!: string;
  categoryName!: string;
  categoryModel = new CategoryModel();

  ngOnInit(): void {
    this.categoryClient.getAll().subscribe(result => {
      this.categories = result;
    });
    this.role = localStorage.getItem("role")!;
  }

  addCategory(): void {
    this.categoryModel.name = this.categoryName;
    this.categoryClient.add(this.categoryModel).subscribe(() => {

    }, () => { }, () => {
      this.router.navigateByUrl('/home', { skipLocationChange: true }).then(() => {
        this.router.navigate(['/category']);
      });
    });

  }
}
