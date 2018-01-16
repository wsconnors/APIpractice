import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { DictionaryComponent } from './components/dictionary/dictionary.component';
import { WordComponent } from './components/dictionary/word/word.component';
import { GitHubComponent } from './components/git-hub/git-hub.component';
import { GitHubService } from './services/git-hub.service'
import { DictionaryService } from './services/dictionary.service'
import { HttpClientModule } from '@angular/common/http';


describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [DictionaryService,GitHubService],
      declarations: [
        AppComponent,WordComponent,GitHubComponent,DictionaryComponent
      ],imports: [HttpClientModule]
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
  it(`should have as title 'app'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('app');
  }));
  // it('should render title in a h1 tag', async(() => {
  //   const fixture = TestBed.createComponent(AppComponent);
  //   fixture.detectChanges();
  //   const compiled = fixture.debugElement.nativeElement;
  //   expect(compiled.querySelector('h1').textContent).toContain('Welcome to app!');
  // }));
});
