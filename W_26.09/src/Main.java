import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Input a odd amount of machines: ");
        int n = in.nextInt();
        int [] idMachines=new int [n];
        System.out.print("Input a id of machines: ");
        for(int i = 0;i < n;++i)
        {
            idMachines[i]=in.nextInt();
        }
        System.out.print("Primitive mass: ");
        for (int i = 0; i < idMachines.length; i++)
        {
            System.out.printf("%2d\t", idMachines[i]);
        }
        System.out.println();
        makePalindrome(idMachines);
        System.out.println();

    }
    public static void makePalindrome(int[] array) {
        int[] counts = new int[256];

        for (int i = 0; i< array.length;++i)
        {
            counts[array[i]]++;
        }
        int unique=0;
        int oddCount = 0;
        int oddElement = 0;

        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            if(count==1)
                unique++;
            if (count % 2 != 0) {
                oddCount++;
                oddElement = i;
            }
        }
        if(unique==0)
        {
            System.out.print("There is no unique machine");
            return;
        }
        int[] result = new int[array.length];
        int left = 0;
        int right = array.length - 1;

        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];

            if (count % 2 == 0) {
                for (int j = 0; j < count / 2; j++) {
                    result[left++] = i;
                    result[right--] = i;
                }
            }
        }

        for(int i = 0;i<oddElement;++i)
            result[left++] = oddElement;
        System.out.print("Palindrom: ");
        for (int i = 0; i < result.length; i++)
        {

            System.out.printf("%2d\t", result[i]);
        }

    }

}