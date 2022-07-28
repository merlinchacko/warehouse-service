import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {BoxService} from "../box-service.service";
import {Product} from "../product";
import {ProductService} from "../product-service.service";
import {Location} from "../location";
import {LocationService} from "./location-service.service";

@Component({
  selector: 'app-add-box',
  templateUrl: './add-box.component.html',
  styleUrls: ['./add-box.component.less']
})
export class AddBoxComponent implements OnInit {

  products: Product[];
  locations: Location[];

  constructor(private formBuilder: FormBuilder, private router: Router, private boxService: BoxService, private productService: ProductService, private locationService: LocationService) {
  }

  addForm: FormGroup;

  getProducts() {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    });
  }

  getLocations() {
    this.locationService.getLocations().subscribe(data => {
      this.locations = data;
    });
  }

  ngOnInit() {
    this.getProducts();
    this.getLocations();
    this.addForm = this.formBuilder.group({
      description: ['', Validators.required],
      productId: new FormControl('', Validators.required),
      locationId: new FormControl('', Validators.required)
    });

  }

  onSubmit() {
    this.boxService.addBox(this.addForm.value)
      .subscribe(data => {
        this.router.navigate(['list-boxes']);
      });
  }

  change(e) {
    console.log(e.target.value);
  }

}
