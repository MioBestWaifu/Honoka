import { ServiceBundle } from "./serviceBundle";
import { ServiceInformation } from "./serviceInformation";

export class UserInformation{
    Id:number;
    Email:string;
    Username:string;
    ImageUrl:string;
    Birthday:Date;
    Description:string;
    ServiceRecs:ServiceBundle[];
}