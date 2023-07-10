import { GenericInformation } from "./genericInformation";
import { ReviewInformation } from "./reviewInformation";
import { ServiceBundle } from "./serviceBundle";
import { ServiceInformation } from "./serviceInformation";

export class UserInformation{
    userId:number;
    email:string;
    name:string;
    imageUrl:string;
    gender:string;
    area:GenericInformation;
    birthday:Date;
    description:string;
    serviceRecs:ServiceBundle[];
    reviews:ReviewInformation[];
    services:ServiceInformation[];
    averageScore:number;

}