import {Option} from './Options'


export class JConfig { 
  
  constructor(
    public title: string,
    public options: Array<Option> = new Array<Option>()
  ){}
}