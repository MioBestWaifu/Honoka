import { ClientServiceInteraction } from "./clientServiceInteraction";

export class ServiceSchedule{
    pendingRequests:ClientServiceInteraction[];
    pendingInstances:ClientServiceInteraction[];
}