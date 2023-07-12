import { registerLocaleData } from "@angular/common";

export abstract class Utils{
    public static DateToSqlString (date:Date):string{
        var toReturn:string = date.getFullYear().toString();
        toReturn+="-";
        if(date.getMonth()+1 < 10){
            toReturn +="0"+(date.getMonth()+1);
        } else {
            toReturn += (date.getMonth()+1);
        }
        toReturn+="-"
        if(date.getDate() < 10){
            toReturn +="0"+(date.getDate());
        } else {
            toReturn +=(date.getDate());
        }
        return toReturn;
    }

    public static DateToViewString(date:Date):string{
        var toReturn = "";
        if(date.getDate() < 10){
            toReturn +="0"+(date.getDate());
        } else {
            toReturn +=(date.getDate());
        }
        toReturn+="/"
        if(date.getMonth()+1 < 10){
            toReturn +="0"+(date.getMonth()+1);
        } else {
            toReturn += (date.getMonth()+1);
        }
        toReturn +="/";
        toReturn += date.getFullYear().toString();
        return toReturn;
    }

    public static TimeToSqlString (date:Date):string{
        return date.getHours()+":"+date.getMinutes();
    }
}