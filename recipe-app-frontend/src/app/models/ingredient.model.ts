export class Ingredient {

    constructor(
        private _name: string,
        private _quantiy: number,
        private _measurement: string,
    ){}

    public get measurement(): string {
        return this._measurement;
    }
    public set measurement(value: string) {
        this._measurement = value;
    }
    public get quantiy(): number {
        return this._quantiy;
    }
    public set quantiy(value: number) {
        this._quantiy = value;
    }
    public get name(): string {
        return this._name;
    }
    public set name(value: string) {
        this._name = value;
    }

}

export interface IngredientDTO {
    recipeId:number;
    name:string;
    quantity:number;
    measurement:string;
}