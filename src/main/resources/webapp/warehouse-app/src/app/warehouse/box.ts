import {Product} from "./product";
import {Location} from "./location";

export class Box {
  id: number;
  description: string;
  product: Product;
  location: Location;
}
