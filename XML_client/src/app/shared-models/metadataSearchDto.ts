export class MetadataSearchDto {
  constructor(
    public values: string[] = [],
    public preds: string[] = [],
    public operators: string[] = []
  ) {}
}
