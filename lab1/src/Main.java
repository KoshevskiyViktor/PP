import java.util.Scanner;
import java.lang.Math;
import java.io.IOException;
import java.io.*;
import java.text.*;

public class Main {
    public  static double myPow(double x, int y) {
        double result=1;
        if(y>0){
            for(int i=1; i<=y; i++){
                result*=x;
            }
        }
        else if(y<0){
            for(int i=0; i<=(-y); i++) {
                x /= 10;
            }
            result=x;
        }
        else {
            result=1;
        }
        return result;
    }
    public static double myFunction(double x, double e){
        double y=1;
        double p=1;
        double k=1;
        while(Math.abs(p)>e)
        {
            p=-1*(p*x*x)/((k+1)*(k+2));
            y+=p;
            k+=2;
        }
        return y;
    }public static void main(String[] args) {
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        try {
            System.out.println("Введите x ∈ R: ");
            String line=br.readLine();
            double ourNumber = Double.parseDouble(line);
            System.out.println("Введите натуральное число k: ");
            String secondLine=br.readLine();
            int ourDegree = Integer.parseInt(secondLine);
            ourDegree = -ourDegree;
            double e = myPow(10, ourDegree);
            System.out.println("Результат через стандартные функции: ");
            double result = Math.sin(ourNumber) / ourNumber;
            NumberFormat formatter=NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(3);
            System.out.println(formatter.format(result));
            System.out.println("Мой результат: ");
            double myResult = myFunction(ourNumber, e);
            System.out.println(formatter.format(myResult));
        }
        catch (NumberFormatException e) {
            System.out.println("Не число");
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения с клавиатуры");
        }
    }
}