import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Account} from "../model/account";
import {AccountService} from "../services/account.service";

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.css'
})
export class CreateAccountComponent implements OnInit{

  accountCreationForm: FormGroup = new FormGroup({});

  constructor(private accountService: AccountService,
              private formBuilder: FormBuilder) {}

  ngOnInit(): void {

    this.accountCreationForm = this.formBuilder.group({
      name: [''],
      email: [''],
      username: [''],
      password: ['']
    });

  }

  submitForm(){
    let newAccount: Account = new Account(this.accountCreationForm.value.name,
                                      this.accountCreationForm.value.email,
                                      this.accountCreationForm.value.username,
                                      this.accountCreationForm.value.password);
    console.log(newAccount);
    this.accountService.createAccountInBackend(newAccount);
  }

}
