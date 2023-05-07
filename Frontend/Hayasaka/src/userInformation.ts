import { AreaInformation } from "./areaInformation";
import { ReviewInformation } from "./reviewInformation";
import { ServiceBundle } from "./serviceBundle";
import { ServiceInformation } from "./serviceInformation";

export class UserInformation{
    Id:number;
    Email:string;
    Name:string;
    ImageUrl:string;
    Gender:string;
    Area:AreaInformation;
    Birthday:Date;
    Description:string;
    ServiceRecs:ServiceBundle[];
    Reviews:ReviewInformation[];

}