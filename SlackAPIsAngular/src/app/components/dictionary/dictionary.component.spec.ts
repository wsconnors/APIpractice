import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { DictionaryService } from '../../services/dictionary.service'
import { DictionaryComponent } from './dictionary.component';
import { HttpClientModule } from '@angular/common/http'

describe('GitHubComponent', () => {
  let component: DictionaryComponent;
  let fixture: ComponentFixture<DictionaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      declarations: [ DictionaryComponent ],
      providers: [DictionaryService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DictionaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
