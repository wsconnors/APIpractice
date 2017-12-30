import { Component, OnInit } from '@angular/core';
import { GitHubService } from '../../services/git-hub.service'
import { GitHubUser } from "../../models/GitHubUser";

@Component({
  selector: 'app-git-hub',
  templateUrl: './git-hub.component.html',
  styleUrls: ['./git-hub.component.css']
})
export class GitHubComponent implements OnInit {

  private user:GitHubUser;
  private start:boolean

  constructor(private gitHubService: GitHubService) {
    this.start = false
  }

  ngOnInit() {
  }

  getUser(textBox:any){
    this.gitHubService.getUserByUserName(textBox.value).subscribe( userResponce =>{
      this.user = userResponce
      this.start = true;
    });
    textBox.value = ""
  }
}
