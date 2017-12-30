import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {GitHubUser} from '../models/gitHubUser';
// import {Response} from '@angular/http';
import 'rxjs/add/operator/map';


@Injectable()
export class GitHubService {
  constructor(private http: HttpClient) {
  }


  uri: string = 'http://localhost:8080/gitHub/users/';

  getUserByUserName(userName: string): Observable<any> {
    return this.http.get(this.uri + userName).map(user => {
        if(user != null){
          return new GitHubUser(user);
        }
        return null;
      }
    );
  }

}
