import { ReviewInformation } from "./reviewInformation";

export class ServiceInformation{
    ServiceName:string; Description:string; ProviderName:string; ProviderUrl:string; ProviderImageUrl:string;
    ShortServiceName:string;
    CostPerHour:string;
    ProviderId:number;
    ProviderArea:string;
    AverageScore:number;
    ServiceId:number;
    TemplateImageUrl:string;
    Reviews:ReviewInformation[];
}