export class WordObj {
  private word:string;
  private source:string;
  private definitions:Definition[];

  constructor(json:any){
    this.word = json.word;
    this.source = json.source;
    this.definitions = this.createDefinitions(json.definitions)
  }

  getWord(){
    return this.word;
  }

  getSource(){
    return this.source;
  }

  getDefinitions(){
    return this.definitions;
  }

  createDefinitions(json:any):Definition[]{
    let allDefinitions:Definition[] = [];
    for (let i = 0; i < json.length; i++) {
      allDefinitions.push(new Definition(json[i]))
    }
    return allDefinitions;
  }

}

class Definition {
  private definition: string;
  private examples: string[];

  constructor(json:any){
    this.definition = json.definition
    this.examples = json.examples;
  }

  getDefinition(){
    return this.definition
  }
  getExamples(){
    return this.examples;
  }

}
