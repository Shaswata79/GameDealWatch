import {ListItem} from "./list-item";

export class GameList {

  constructor(public id: string,
              public listName: string,
              public userEmail: string,
              public items: ListItem[]) {
  }

}
