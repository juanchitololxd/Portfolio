import {Option} from './options'


export class JConfig { 
  
  constructor(
    public title: string,
    public options: Array<Option> = new Array<Option>()
  ){}
}