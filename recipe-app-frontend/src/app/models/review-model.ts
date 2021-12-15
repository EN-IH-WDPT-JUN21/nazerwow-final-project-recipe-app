export interface ReviewDTO {
    id?: number;
    title: string;
    content: string;
    recipeId: number;
    userId: number;
    ratingId:number;
}

export interface ReviewResponse {
    title:string;
    content:string;
    name: string;
    rating: number
}