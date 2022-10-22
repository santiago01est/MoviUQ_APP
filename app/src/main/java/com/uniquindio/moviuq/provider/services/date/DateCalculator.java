package com.uniquindio.moviuq.provider.services.date;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/** Clase diseñada apartir de metodos recomendados para el manejo de diferentes formatos para fechas
 * Documentación https://www.campusmvp.es/recursos/post/como-manejar-correctamente-fechas-en-java-el-paquete-java-time.aspx**/

public class DateCalculator {

    public Date Date;
    public String Day;
    public String Month;
    public int MonthValue;
    public String NumberDay;
    public String Year;
    public String TimeZone;
    public String Seconds;
    public String Minutes;
    public String Hours;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DateCalculator(Date date) {

        this.Date = date;

        String DateString = date.toString();



        String[] DateSplitted = DateString.split(" ");

        for (int i = 0; i < DateSplitted.length; i++) {

            switch (i) {
                case 0: {
                    this.Day = DateSplitted[0];
                }
                case 1: {
                    this.Month = DateSplitted[1];

                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    this.MonthValue = localDate.getMonthValue();
                }
                case 2: {
                    this.NumberDay = DateSplitted[2];
                }
                case 3: {
                    String[] hour = DateSplitted[3].split(":");
                    this.Hours = hour[0];
                    this.Minutes = hour[1];
                    this.Seconds = hour[2];
                }
                case 4: {
                    this.TimeZone = DateSplitted[4];
                }
                case 5: {
                    this.Year = DateSplitted[5];
                }
            }
        }
    }

    public DateCalculator() {
    /* EMPTY CONSTRUCTOR FOR USE THE CLASS WITH
     CUSTOM VALUE CALCULATOR (operateCustomDate()) */
    }

    public String getDay() {
        return this.Day;
    }

    public String getMonth() {
        return this.Month;
    }

    public String getDayNumber() {
        return this.NumberDay;
    }

    public int getNumberMonth() {
        return this.MonthValue;
    }
    /** Obtener solo hora de la fecha y darle un formato de hora:minutos
     * metodo usado para ser mostrados en interfaz**/
    public String getCompleteHour() {
       // String CompleteHour = this.Hours + ":" + this.Minutes + ":" + this.Seconds;
        String CompleteHour = this.Hours + ":" + this.Minutes;

        return CompleteHour;
    }

    public String getCompleteDay() {

        //String CompleteDay =  this.getCompleteHour()+ " del " + this.Year + "-" + this.Month + "-" + this.NumberDay + " " + this.getCompleteHour();
        String CompleteDay =  this.getCompleteHour()+ " del " + this.NumberDay+"-" + this.Month;

        return CompleteDay;
    }

    public String getCompleteDayId() {

        //String CompleteDay =  this.getCompleteHour()+ " del " + this.Year + "-" + this.Month + "-" + this.NumberDay + " " + this.getCompleteHour();
        String CompleteDay =  this.getCompleteHourId()+ "-" + this.NumberDay+"-" + this.Month;

        return CompleteDay;
    }
    /** Obtener solo hora de la fecha y darle un formato de hora:minutos:segundos
     * metodo usado para generar id mas precisos**/
    public String getCompleteHourId() {
         String CompleteHour = this.Hours + ":" + this.Minutes + ":" + this.Seconds;

        return CompleteHour;
    }

    public int getIdFechaOrder(){
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String s=dia+""+mes+""+año;
        return Integer.parseInt(s);
    }



    public String getHour() {
        return this.Hours;
    }

    public String getMinute() {
        return this.Minutes;
    }

    public String getSeconds() {
        return this.Seconds;
    }

    public String getTimeZone() {
        return this.TimeZone;
    }

    public String getYear() {
        return this.Year;
    }
}
