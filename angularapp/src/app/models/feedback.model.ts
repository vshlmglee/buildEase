export interface Feedback{
    feedbackId?:number;
    userId?:number;
    materialId?:number;
    category:string;
    feedbackText:string;
    date:Date;
    
    user?:any;
    material?:any
}