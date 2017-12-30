import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { WordObj } from '../models/word-obj'
import 'rxjs/add/operator/map';


@Injectable()
export class DictionaryService {

  constructor(private http:HttpClient) { }

  uri:string = "http://localhost:8080/dictionary/"

  defineWord(word:string): Observable<WordObj>{
    return this.http.get(this.uri+word).map(response=> {
      if(response != null){
        return new WordObj(response);
      }
      return null;
    });
  }
}
