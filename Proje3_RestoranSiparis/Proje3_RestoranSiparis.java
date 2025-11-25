/*
 * Ad Soyad: MEHMET TALHA KAYA
 * Ogrenci No: 250542009
 * Tarih: 25.11.2025
 * Aciklama: Gorev 3 - Akıllı Restoran Sipariş Sistemi
 *
 * Bu program akıllı restoranlar için sipariş sistemi oluşturmak için yapılmıştır.
 */

import java.util.Scanner;

public class Proje3_RestoranSiparis {

    static double lastDrinkPrice = 0.0;

    // METOT 1: Ana yemek fiyatları
    public static double getMainDishPrice(int secim)
    {
        switch (secim)
        {
            case 1: // Izgara Tavuk
                return 85.0;
            case 2: // Adana Kebap
                return 120.0;
            case 3: // Levrek
                return 110.0;
            case 4: // Mantı
                return 65.0;
            case 0: // Yok
            default:
                return 0.0;
        }
    }

    // METOT 2: Başlangıç fiyatları
    public static double getAppetizerPrice(int secim)
    {
        switch (secim)
        {
            case 1: // Çorba
                return 25.0;
            case 2: // Humus
                return 45.0;
            case 3: // Sigara Böreği
                return 55.0;
            case 0: // Yok
            default:
                return 0.0;
        }
    }

    // METOT 3: İçecek fiyatları
    public static double getDrinkPrice(int secim)
    {
        double price;
        switch (secim)
        {
            case 1: // Kola
                price = 15.0;
                break;
            case 2: // Ayran
                price = 12.0;
                break;
            case 3: // Taze Meyve Suyu
                price = 35.0;
                break;
            case 4: // Limonata
                price = 25.0;
                break;
            case 0: // Yok
            default:
                price = 0.0;
        }
        lastDrinkPrice = price; // Happy hour indirimi için saklıyoruz.
        return price;
    }

    // METOT 4: Tatlı fiyatları
    public static double getDessertPrice(int secim)
    {
        switch (secim)
        {
            case 1: // Künefe
                return 65.0;
            case 2: // Baklava
                return 55.0;
            case 3: // Sütlaç
                return 35.0;
            case 0: // Yok
            default:
                return 0.0;
        }
    }

    // METOT 5: Combo olup olmadığını kontrol eder.
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar)
    {
        if (anaVar == true && icecekVar == true && tatliVar == true)return true;
        else return false;
    }

    // METOT 6: Happy hour olup olmadığını kontrol eder.
    public static boolean isHappyHour(int saat)
    {
        if ((saat >= 14 && saat <= 17))return true;
        else return false;
    }

    // METOT 7: Tüm indirimleri hesaplar.
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenciHaftaIci, int saat)
    {
        double toplamIndirim = 0.0;

        // 1) Combo indirimi
        if (combo)
        {
            double comboIndirim = tutar * 0.15;
            toplamIndirim = toplamIndirim + comboIndirim;
        }

        // 2) Happy hour içecek indirimi (%20 sadece içecekten)
        if (isHappyHour(saat) && lastDrinkPrice > 0.0)
        {
            double hhIndirim = lastDrinkPrice * 0.20;
            toplamIndirim = toplamIndirim + hhIndirim;
        }

        // Ara toplam (combo + happy hour sonrası)
        double araToplam = tutar - toplamIndirim;

        // 3) Öğrenci (hafta içi) indirimi (%10, araToplam üzerinden)
        if (ogrenciHaftaIci)
        {
            double ogrIndirim = araToplam * 0.10;
            toplamIndirim = toplamIndirim + ogrIndirim;
            araToplam = araToplam - ogrIndirim;
        }

        // 4) 200 TL üzeri ekstra %10
        if (araToplam > 200.0)
        {
            double ekstraIndirim = araToplam * 0.10;
            toplamIndirim = toplamIndirim + ekstraIndirim;
        }

        return toplamIndirim;
    }

    // Metot 8: Bahşiş önerisi yapar.
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    // Metot 9: Gün ismi döndürür.
    public static String getGunAdi(int gun) {
        switch (gun) {
            case 1: return "Pazartesi";
            case 2: return "Salı";
            case 3: return "Çarşamba";
            case 4: return "Perşembe";
            case 5: return "Cuma";
            case 6: return "Cumartesi";
            case 7: return "Pazar";
            default: return "Geçersiz";
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== AKILLI RESTORAN SIPARIS SISTEMI ===");

        System.out.print("Ana Yemek (0-4)  : ");
        int anaSecim = input.nextInt();

        System.out.print("Baslangic (0-3)  : ");
        int baslangicSecim = input.nextInt();

        System.out.print("Icecek (0-4)     : ");
        int icecekSecim = input.nextInt();

        System.out.print("Tatli (0-3)      : ");
        int tatliSecim = input.nextInt();

        System.out.print("Saat (8-23)      : ");
        int saat = input.nextInt();

        System.out.print("Ogrenci misiniz? (E/H): ");
        String ogrStr = input.next();
        boolean ogrenciMi = false;
        if (ogrStr == "E" || ogrStr == "e")ogrenciMi = true;

        System.out.print("Hangi gun? (1-7) : ");
        int gun = input.nextInt();

        // Fiyatları hesapla
        double anaFiyat = getMainDishPrice(anaSecim);
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);
        double icecekFiyat = getDrinkPrice(icecekSecim);
        double tatliFiyat = getDessertPrice(tatliSecim);

        double araToplam = anaFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;

        boolean anaVar = (anaFiyat > 0.0);
        boolean icecekVar = (icecekFiyat > 0.0);
        boolean tatliVar = (tatliFiyat > 0.0);

        boolean comboMu = isComboOrder(anaVar, icecekVar, tatliVar);

        boolean haftaIciMi = (gun >= 1 && gun <= 5);
        boolean ogrenciHaftaIci = (ogrenciMi && haftaIciMi);

        double toplamIndirim = calculateDiscount(araToplam, comboMu, ogrenciHaftaIci, saat);
        double odenecekTutar = araToplam - toplamIndirim;
        double bahsis = calculateServiceTip(odenecekTutar);

        System.out.println("\n=== SIPARIS OZETI ===");
        System.out.printf("Gun              : %s%n", getGunAdi(gun));
        System.out.printf("Saat             : %d:00%n", saat);
        System.out.println("-------------------------------");
        System.out.printf("Ana Yemek        : %.2f TL%n", anaFiyat);
        System.out.printf("Baslangic        : %.2f TL%n", baslangicFiyat);
        System.out.printf("Icecek           : %.2f TL%n", icecekFiyat);
        System.out.printf("Tatli            : %.2f TL%n", tatliFiyat);
        System.out.println("-------------------------------");
        System.out.printf("Ara Toplam       : %.2f TL%n", araToplam);
        System.out.printf("Toplam Indirim   : %.2f TL%n", toplamIndirim);
        System.out.printf("Odenecek Tutar   : %.2f TL%n", odenecekTutar);
        System.out.printf("Bahsis Onerisi   : %.2f TL%n", bahsis);
        System.out.println("===============================\n");

        input.close();
    }
}
