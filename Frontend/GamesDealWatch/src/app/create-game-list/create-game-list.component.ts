import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountService} from "../services/account.service";
import {Account} from "../model/account";
import {GamelistService} from "../services/gamelist.service";

@Component({
  selector: 'app-create-game-list',
  templateUrl: './create-game-list.component.html',
  styleUrl: './create-game-list.component.css'
})
export class CreateGameListComponent implements OnInit{

  gameListCreationForm: FormGroup = new FormGroup({});

  constructor(private gamelistService: GamelistService,
              private formBuilder: FormBuilder) {}

  ngOnInit(): void {

    this.gameListCreationForm = this.formBuilder.group({
      name: ['']
    });

  }

  submitForm(){
    this.gamelistService.createNewList(this.gameListCreationForm.value.name);
  }

}
