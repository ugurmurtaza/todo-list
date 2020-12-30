import { Component, OnInit, TemplateRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { BsModalService } from 'ngx-bootstrap/modal';
import { Item } from 'src/app/common/item.model';
import { Page } from 'src/app/common/page';
import { ItemService } from 'src/app/services/shared/item.service';

declare let alertify:any;

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss'],
})
export class ItemComponent implements OnInit {
  // modalRef: BsModalRef;
  // itemForm: FormGroup;
  addForm: FormGroup;

  item = new Item();
  page = new Page();
  cols = [];
  rows = [];

  constructor(
    private itemService: ItemService,
    // private modalService: BsModalService,
    // private formBuilder: FormBuilder,
    private formBuilder2: FormBuilder
  ) {}

  ngOnInit() {
    this.setPage({ offset: 0, limit: 10 });

    this.addForm = this.formBuilder2.group({
      newItem: [null, []],
    });

    this.cols = [
      //{ prop: 'id', name: 'No', sortable: false},
      //{ prop: 'checked', name: 'Check', sortable: false },
      { prop: 'name', name: 'Item Name' },
      { prop: 'date', name: 'Due Date', sortable: false },
      //{ prop: 'id', name: 'Actions', sortable: false },
    ];

    // this.itemForm = this.formBuilder.group({
    //   name: '',
    //   date: '',
    // });
  }

  submit() {
    this.item.name = this.addForm.value.newItem;
    this.item.date = new Date();
    this.item.checked = false;
    this.itemService.createItem(this.item).subscribe((res) => {
      console.log(res);
      this.setPage({ offset: 0, limit: 10 });
    });
  }

  setPage(pageInfo) {
    this.page.page = pageInfo.offset;
    this.itemService.getAll(this.page).subscribe((pagedData) => {
      this.page.size = pagedData.size;
      this.page.page = pagedData.page;
      this.page.totalElements = pagedData.totalElements;
      this.rows = pagedData.content;
    });
  }

  // updateItem() {
  //   this.itemService.updateItem(this.itemForm.value).subscribe((res) => {
  //     console.log(res);
  //     this.setPage({ offset: 0, limit: 10 });
  //   });
  // }

  deleteItem(item) {
    this.itemService.deleteItem(item).subscribe((res) => {
      console.log(res);
      if (res){
        alertify.success("Item Deleted");
      }
      this.setPage({ offset: 0, limit: 10 });
    });
  }

  // openModal(template: TemplateRef<any>) {
  //   this.modalRef = this.modalService.show(template);
  // }
}
