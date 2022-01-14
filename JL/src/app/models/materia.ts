export interface MateriaNode {
    name: string;
    children?: MateriaNode[];
    
  }

export interface ExampleFlatNode {
    expandable: boolean;
    name: string;
    level: number;
  }