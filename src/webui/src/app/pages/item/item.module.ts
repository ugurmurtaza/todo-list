import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItemComponent } from './item.component';
import { ItemRoutingModule } from './item.routing.module';
import { ItemService } from 'src/app/services/shared/item.service';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { BsModalRef, ModalModule } from 'ngx-bootstrap/modal';
import { ReactiveFormsModule } from '@angular/forms';

import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';


@NgModule({
  declarations: [ItemComponent],
  imports: [
    CommonModule,
    ItemRoutingModule,
    NgxDatatableModule,
    ModalModule.forRoot(),    
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule
  ],
  providers:[ItemService,BsModalRef],
  exports:[
    ModalModule,
    ReactiveFormsModule
   ]
})
export class ItemModule { }
