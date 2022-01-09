
export interface Option {
  name: string;
  enabled: boolean;
  iconName: string;
  route: string;
  children: Option[];
}
