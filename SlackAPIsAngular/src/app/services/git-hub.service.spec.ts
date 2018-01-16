import { TestBed, getTestBed } from '@angular/core/testing'
import { HttpClientTestingModule,HttpTestingController } from '@angular/common/http/testing';
import { GitHubUser } from '../models/gitHubUser'
import {GitHubService}  from "./git-hub.service";

describe('Git hub service',()=>{
  let testBed:TestBed;
  let service:GitHubService;
  let mockHttp: HttpTestingController;

  beforeEach(()=>{
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [GitHubService]
    });
    testBed = getTestBed();
    service = testBed.get(GitHubService);
    mockHttp = testBed.get(HttpTestingController);
  });

  it('should be created',() => {
    expect(service).toBeTruthy();
  });

  it('should get word object',() => {
    let mockUserName = "testUserName";
    let mockUser:any=
    {
      name: "testName",
      login: mockUserName,
      bio: "testBio",
      public_repos: 10
    }

    service.getUserByUserName(mockUserName).subscribe((user:GitHubUser) =>{
      expect(user.getUserName()).toBe(mockUserName);
    });
    const req = mockHttp.expectOne('http://localhost:8080/gitHub/users/'+mockUserName);
    expect(req.request.method).toBe('GET');
    req.flush(mockUser);
  });

});
