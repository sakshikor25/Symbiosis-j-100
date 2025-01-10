export class Appointmentclass {
  Client: { 
    clientName: string; clientEmail: string 
  } | undefined;
  Appointment: { appointmentId: number; appointmentDate: Date; appointmentTime: string } | undefined;
    Service: { serviceName: string } | undefined;
   }