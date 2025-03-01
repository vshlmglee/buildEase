export interface MaterialRequest{
    // material:any;
    // materialName: any;
    // materialRequestId?:number;
    // userId:number;
    // materialId:number;
    // quantity:number;
    // urgencyLevel:string;
    // requestDate:Date;
    // preferredDeliveryDate:Date;
    // timeSlot:string;
    // status:string;
    // deliveryAddress:string;
    // contactNumber:string;
    // comments:string;

    materialName?:any;
    material:any;
    user:any;
    materialId:any;
    status:string;
    requestDate:Date;

    quantity:number;
    urgencyLevel:string;

    preferredDeliveryDate:Date;
    timeSlot:string;
    deliveryAddress:string;
    contactNumber:string;
    comments:string;

    materialRequestId?:number; //added
}