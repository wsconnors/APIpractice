import { Component, OnInit } from '@angular/core';
import { DictionaryService } from '../../services/dictionary.service'
import { WordObj } from '../../models/word-obj'

@Component({
  selector: 'app-dictionary',
  templateUrl: './dictionary.component.html',
  styleUrls: ['./dictionary.component.css']
})
export class DictionaryComponent {

  word:WordObj;
  start:boolean;

  constructor(private dictionary: DictionaryService) {
    this.start = false;
  }

  searchWord(textBox:any){
    this.dictionary.defineWord(textBox.value).subscribe( obj => {
      this.word = obj;
      this.start = true;
    });
    textBox.value = "";
  }
}
