import { ReviewInformation } from "./reviewInformation";

export class ServiceInformation{
    serviceName:string; description:string; providerName:string; providerUrl:string; providerImageUrl:string;
    shortServiceName:string;
    costPerHour:string;
    providerId:number;
    providerArea:string;
    averageScore:number;
    serviceId:number;
    modality:number;category:number;
    availableDays:boolean[]; availableFroms:string[]; availableTos:string[];
    templateImageUrl:string;
    reviews:ReviewInformation[];
}