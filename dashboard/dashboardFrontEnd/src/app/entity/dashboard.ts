export class Dashboard {
  constructor(
    public id: number,
    public endYear: number,
    public cityLng: number,
    public cityLat: number,
    public intensity: number,
    public sector: string,
    public topic: string,
    public insight: string,
    public swot: string,
    public url: string,
    public region: string,
    public startYear: number,
    public impact: number,
    public added: Date,
    public published: Date,
    public city: string,
    public country: string,
    public relevance: number,
    public pestle: string,
    public source: string,
    public title: string,
    public likelihood: number
  ) {}
}
