/*
 * Ad Soyad: MEHMET TALHA KAYA
 * Ogrenci No: 250542009
 * Tarih: 25.11.2025
 * Aciklama: Gorev 1 - Ogrenci Not Sistemi
 *
 * Bu program ogrenci not sistemi icin yapilmistir.
 */

import java.util.Scanner;

public class Proje1_NotSistemi {

    // METOT 1: Vize, final ve odevlerden alınan notlarla ortalamayı hesaplar.
    public static double calculateAverage(double vizeNot, double finalNot, double odevNot)
    {
        double Ortalama = vizeNot * 0.3 + finalNot * 0.4 + odevNot * 0.3;
        return Ortalama;
    }

    // METOT 2: Ortalamaya gore gecip, kalma durumunu kontrol eder.
    public static boolean isPassingGrade(double ortalama)
    {
        if (ortalama >= 50)return true;
        else return false;
    }

    // METOT 3: Ortalamaya gore harf notu belirler.
    public static char getLetterGrade(double ortalama)
    {
        if (ortalama >= 90) return 'A';
        else if (ortalama < 90 && ortalama >= 80) return 'B';
        else if (ortalama < 80 && ortalama >= 70) return 'C';
        else if (ortalama < 70 && ortalama >= 60) return 'D';
        else if (ortalama < 60 && ortalama >= 50) return 'E';
        else return 'F';
    }

    // METOT 4: Ortalama, vize, final ve odev notlarina gore onur listesine girip giremedigini kontrol eder.
    public static boolean isHonorList(double ortalama, double vizeNot, double finalNot, double odevNot)
    {
        if (ortalama >= 85 && vizeNot >= 70 && finalNot >= 70 && odevNot >= 70)return true;
        else return false;
    }

    // METOT 5: Ortalamaya gore butunleme hakki olup olmadigini kontrol eder.
    public static boolean hasRetakeRight(double ortalama)
    {
        if (ortalama >= 40 && ortalama < 50) return true;
        else return false;
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Vize notu giriniz : ");
        double vizeNot = input.nextDouble();
        System.out.print("Final notu giriniz : ");
        double finalNot = input.nextDouble();
        System.out.print("Ödev notu giriniz : ");
        double odevNot = input.nextDouble();

        System.out.println("\n==== Öğrenci Not Raporu ====");
        System.out.printf("Vize Notu     : %.1f\n", vizeNot);
        System.out.printf("Final Notu    : %.1f\n", finalNot);
        System.out.printf("Ödev Notu     : %.1f\n", odevNot);
        System.out.println("---------------------------");

        double ortalama = calculateAverage(vizeNot, finalNot, odevNot);
        char harfNotu = getLetterGrade(ortalama);

        String passingGrade = "KALDI", honorList = "HAYIR", retakeRight = "YOK";
        if (isPassingGrade(ortalama) == true)passingGrade = "GECTI";
        if (isHonorList(ortalama, vizeNot, finalNot, odevNot) == true)honorList = "EVET";
        if (hasRetakeRight(ortalama) == true)retakeRight = "VAR";

        System.out.printf("Ortalama : %.1f\n", ortalama);
        System.out.printf("Harf Notu : %c\n", harfNotu);
        System.out.printf("Durum : %s\n", passingGrade);
        System.out.printf("Onur Listesi : %s\n", honorList);
        System.out.printf("Butunleme : %s\n", retakeRight);

        input.close();
    }
}
