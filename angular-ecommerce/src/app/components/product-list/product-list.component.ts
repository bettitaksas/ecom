import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../services/product.service";
import {Product} from "../../common/product";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];
  currentCategoryId: number = 1;
  searchMode: boolean = false;

  constructor(private productService: ProductService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      () => {
        this.listProducts();
      }
    )
  }

  private listProducts() {
    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    if (this.searchMode) {
      this.handleSearchProducts();
    }

    this.handleListProducts();
  }

  handleListProducts() {

    const hasCategoryId: boolean = this.route
      .snapshot.paramMap.has('id');

    if(hasCategoryId) {
      this.currentCategoryId = +this.route
        .snapshot.paramMap.get('id')!;
    }

    this.productService.getProductList(this.currentCategoryId)
      .subscribe(
        data => {
          console.log(data);
          this.products = data;
        }
      )
  }

  private handleSearchProducts() {
    const theKeyword: string = this.route.snapshot.paramMap.get('keyword')!;
    this.productService.searchProducts(theKeyword)
      .subscribe(
        data => {
          this.products = data;
        }
      );
  }
}
