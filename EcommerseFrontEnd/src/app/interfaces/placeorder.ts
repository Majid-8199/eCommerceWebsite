export interface placeOrder{
    userId:number,
    address:string,
    state:string,
    city:string,
    zipcode:number,
    orderDescription:string,
    payment:string,
    upiId:string,
    cardNo:number,
    amount:number,
    totalAmount:number,
    discount:number,
    delivery:number
}