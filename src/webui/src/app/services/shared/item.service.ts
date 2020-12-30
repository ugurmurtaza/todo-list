import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ApiService } from "../api.service";
import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
  })
export class ItemService{

    private ITEM_PATH = "/item";

    constructor(private apiService : ApiService){

    }

    getAll(page): Observable<any>{
        return this.apiService.get(this.ITEM_PATH+"/pagination",page).pipe(map(
            res => {
                if (res){
                    return res;
                }
                else
                {
                    console.log(res);
                    return {};
                }
            }
        ));
    }

    createItem(item) : Observable<any>{
        return this.apiService.post(this.ITEM_PATH,item).pipe(map(
            res=>{
                if (res){
                    return res;
                }
                else
                {
                    console.log(res);
                    return {}
                }
            }
        ));
    }

    updateItem(item) : Observable<any>{
        console.log(item);
        return this.apiService.put(this.ITEM_PATH+ '/' + item.id,item).pipe(map(
            res=>{
                if (res){
                    return res;
                }
                else
                {
                    console.log(res);
                    return {}
                }
            }
        ));
    }

    deleteItem(item) : Observable<any>{
        console.log(item);
        return this.apiService.delete(this.ITEM_PATH+ '/'+item.id).pipe(map(
            res=>{
                if (res){
                    console.log("success"+res);
                    return res;
                }
                else
                {
                    console.log("failed"+res);
                    return {}
                }
            }
        ));
    }
}