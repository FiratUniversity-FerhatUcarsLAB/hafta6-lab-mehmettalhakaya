/*
 * Ad Soyad: MEHMET TALHA KAYA
 * Ogrenci No: 250542009
 * Tarih: 25.11.2025
 * Aciklama: Gorev 2 - Sinema Bileti Fiyati Hesaplama
 *
 * Bu program sinema bileti hesaplamak icin yapilmistir.
 */

import java.util.Scanner;

public class Proje2_SinemaBileti {

    // METOT 1: Olan günün hafta sonu mu olduğunu kontrol eder.
    public static boolean isWeekend(int gun)
    {
        switch (gun)
        {
            case 6:
            case 7:
                return true;
            default:
                return false;
        }
    }

    // METOT 2: Matine olup olmadığını kontrol eder.
    public static boolean isMatinee(int saat)
    {
        if (saat < 12)return true;
        else return false;
    }

    // METOT 3: Temel bilet fiyatını hesapla
    // Hafta içi matine : 45, Hafta içi normal : 65, Hafta sonu matine: 55, Hafta sonu normal: 85
    public static double calculateBasePrice(int gun, int saat)
    {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (weekend == false)
        {
            if (matinee)
            {
                return 45.0;
            } else
            {
                return 65.0;
            }
        }
        else
        {
            if (matinee)
            {
                return 55.0;
            } else
            {
                return 85.0;
            }
        }
    }

    // METOT 4: İndirim oranını hesaplar.
    public static double calculateDiscount(int yas, int meslek, int gun)
    {
        double indirimOrani = 0.0;

        // 65 yaş üstü indirimi (%30)
        if (yas >= 65)
        {
            double aday = 0.30;
            if (aday > indirimOrani)
            {
                indirimOrani = aday;
            }
        }

        // 12 yaş altı indirimi (%25)
        if (yas < 12)
        {
            double aday = 0.25;
            if (aday > indirimOrani)
            {
                indirimOrani = aday;
            }
        }

        // Öğrenci indirimi
        if (meslek == 1)
        {
            if (gun >= 1 && gun <= 4)
            {
                double aday = 0.20;
                if (aday > indirimOrani)
                {
                    indirimOrani = aday;
                }
            }
            else if (gun >= 5 && gun <= 7)
            {
                // Cuma-Pazar %15
                double aday = 0.15;
                if (aday > indirimOrani)
                {
                    indirimOrani = aday;
                }
            }
        }

        // Öğretmen indirimi (sadece Çarşamba %35)
        if (meslek == 2 && gun == 3)
        {
            double aday = 0.35;
            if (aday > indirimOrani)
            {
                indirimOrani = aday;
            }
        }

        return indirimOrani;
    }

    // METOT 5: Film türüne göre ekstra ücret hesaplar.
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: // 2D
                return 0.0;
            case 2: // 3D
                return 25.0;
            case 3: // IMAX
                return 35.0;
            case 4: // 4DX
                return 50.0;
            default:
                return 0.0;
        }
    }

    // METOT 6: Toplam fiyatı hesaplar.
    public static double calculateFinalPrice(double basePrice, double discountRate, double formatExtra) {
        double indirimliFiyat = basePrice - (basePrice * discountRate);
        double toplam = indirimliFiyat + formatExtra;
        return toplam;
    }

    // METOT 7: Gün adı döndürür.
    public static String getGunAdi(int gun) {
        switch (gun) {
            case 1: return "Pazartesi";
            case 2: return "Salı";
            case 3: return "Çarşamba";
            case 4: return "Perşembe";
            case 5: return "Cuma";
            case 6: return "Cumartesi";
            case 7: return "Pazar";
            default: return "Geçersiz gün";
        }
    }

    // METOT 8: Meslek adı döndürür.
    public static String getMeslekAdi(int meslek) {
        switch (meslek) {
            case 1: return "Öğrenci";
            case 2: return "Öğretmen";
            case 3: return "Diğer";
            default: return "Bilinmiyor";
        }
    }

    // METOT 9: Film türü ismi döndürür.
    public static String getFilmTuruAdi(int filmTuru) {
        switch (filmTuru) {
            case 1: return "2D";
            case 2: return "3D";
            case 3: return "IMAX";
            case 4: return "4DX";
            default: return "Bilinmiyor";
        }
    }

    // METOT 10: Bilet bilgilerimizi gösterir.
    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru, double basePrice, double discountRate, double formatExtra, double finalPrice)
    {
        System.out.println("\n===== SINEMA BILETI =====");
        System.out.println("Gün           : " + getGunAdi(gun));
        System.out.println("Saat          : " + saat + ":00");
        System.out.println("Yaş           : " + yas);
        System.out.println("Meslek        : " + getMeslekAdi(meslek));
        System.out.println("Film Türü     : " + getFilmTuruAdi(filmTuru));
        System.out.println("--------------------------");
        System.out.printf("Temel Fiyat   : %.2f TL\n", basePrice);
        System.out.printf("İndirim Oranı : %.0f %%\n", discountRate * 100);
        System.out.printf("Format Ekstra : %.2f TL\n", formatExtra);
        System.out.println("--------------------------");
        System.out.printf("TOPLAM FİYAT  : %.2f TL\n", finalPrice);
        System.out.println("==========================\n");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Gün (1-7)     : ");
        int gun = input.nextInt();

        System.out.print("Saat (8-23)     : ");
        int saat = input.nextInt();

        System.out.print("Yaş     : ");
        int yas = input.nextInt();

        System.out.print("Meslek (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = input.nextInt();

        System.out.print("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuru = input.nextInt();

        double basePrice = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double formatExtra = getFormatExtra(filmTuru);
        double finalPrice = calculateFinalPrice(basePrice, discountRate, formatExtra);

        generateTicketInfo(gun, saat, yas, meslek, filmTuru, basePrice, discountRate, formatExtra, finalPrice);

        input.close();
    }
}
