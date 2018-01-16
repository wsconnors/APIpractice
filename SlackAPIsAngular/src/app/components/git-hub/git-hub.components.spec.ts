import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { GitHubService } from '../../services/git-hub.service'
import { GitHubComponent } from './git-hub.component';
import { HttpClientModule } from '@angular/common/http'

describe('GitHubComponent', () => {
  let component: GitHubComponent;
  let fixture: ComponentFixture<GitHubComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      declarations: [ GitHubComponent ],
      providers: [GitHubService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GitHubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
