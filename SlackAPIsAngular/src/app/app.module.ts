import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { DictionaryService } from './services/dictionary.service'
import { AppComponent } from './app.component';
import { DictionaryComponent } from './components/dictionary/dictionary.component';
import { WordComponent } from './components/dictionary/word/word.component';
import { GitHubComponent } from './components/git-hub/git-hub.component';
import { GitHubService } from './services/git-hub.service'

@NgModule({
  declarations: [
    AppComponent,
    DictionaryComponent,
    WordComponent,
    GitHubComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [
    DictionaryService,
    GitHubService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
