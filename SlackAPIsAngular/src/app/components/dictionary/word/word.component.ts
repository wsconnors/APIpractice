import { Component, OnInit,Input } from '@angular/core';
import { WordObj } from '../../../models/word-obj'

@Component({
  selector: 'app-word',
  templateUrl: './word.component.html',
  styleUrls: ['./word.component.css']
})
export class WordComponent implements OnInit {

  @Input()
  word:WordObj
  
  constructor() { }

  ngOnInit() {
  }

}
