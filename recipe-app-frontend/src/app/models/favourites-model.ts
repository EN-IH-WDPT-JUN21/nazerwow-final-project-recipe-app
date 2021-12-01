export class Favourite {

    public get recipeId(): number {
        return this._recipeId;
    }
    public set recipeId(value: number) {
        this._recipeId = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    constructor(private _id: number,
        private _recipeId: number){};
        
        
}

export interface FavouriteDTO{
    id: number;
    recipeId: number;
}