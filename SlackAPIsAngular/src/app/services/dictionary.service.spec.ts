import { TestBed, getTestBed } from '@angular/core/testing'
import { HttpClientTestingModule,HttpTestingController } from '@angular/common/http/testing';

import { WordObj } from '../models/word-obj'
import { DictionaryService } from './dictionary.service'


describe('Dictionary Service tests',() =>{
  let testBed:TestBed;
  let service:DictionaryService;
  let mockHttp: HttpTestingController;

  beforeEach(()=>{
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [DictionaryService]
    });
    testBed = getTestBed();
    service = testBed.get(DictionaryService);
    mockHttp = testBed.get(HttpTestingController);
  });

  it('should be created',() => {
    expect(service).toBeTruthy();
  });

  it('should call defineWord',() => {
    spyOn(service,'defineWord');
    service.defineWord("");
    expect(service.defineWord).toHaveBeenCalledWith("");
  });

  it('should get word object',() => {
    let dummyStr = "testWord";
    let dummyWordObj:any=
    {
      word: dummyStr,
      source: "testSource",
      definitions: [
        {
          definition: "testDefinition",
          examples: [
            "testExample"
          ]
        }
      ]
    }

    service.defineWord(dummyStr).subscribe((wordObj:WordObj) =>{
      expect(wordObj.getWord()).toBe(dummyStr);
    });
    const req = mockHttp.expectOne('http://localhost:8080/dictionary/'+dummyStr);
    expect(req.request.method).toBe('GET');
    req.flush(dummyWordObj);
  });
});
