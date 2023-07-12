import { ServiceInformation } from "./serviceInformation";
import { UserInformation } from "./userInformation";
export class ClientServiceInteraction{
    isAccepted:boolean; hasFinished:boolean;
    id:number; clientId:number; templateId:number;
    startDate:string; endDate:string;
    startTime:string; endTime:string;
    cost:number;
    client:UserInformation; service:ServiceInformation;
} 