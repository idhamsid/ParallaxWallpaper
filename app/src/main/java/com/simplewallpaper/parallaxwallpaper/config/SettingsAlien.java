package com.simplewallpaper.parallaxwallpaper.config;


public class SettingsAlien {
    /*
    Panduan dasar Reskin, error, tips dan trik  https://aliendro.id/help-center/dasar-reskin
     */

     /*
      Silahkan upload file Json yang disertakan dalam zip, dan paste di URL_DATA
      lihat panduan dasar untuk meyimpan file json
       */

    /*
    Enkripsi JSON
    Muldai dari Neptunus-18 seluruh SC Alien menggukanan 2 metode pemanggilan JSON, pilih salah satu
    a. Menggunakan URL (https://) URL_DATA ="https://aliendro.id/uploads/demo/walljson/wallpaperV7.json"
    b. Menggunakan metode enkripsi, silahkan enkripsi link Json di https://aliendro.id/Enkripsi
       dan masukan hasilnya di bagian URL_DATA, menggunakan enkripsi harus disertai AplicationID/Packagename.
       Metode ini dugunakan untuk menyembunyikan link Json, sehingga terhindar dari pencurian data.

       PERINGATAN!!!
       Jika Packagename yang di enckripsi beda dengan packagename aplikasi, maka aplikasi tidak bisa digunakan
     */
    public static final String URL_DATA = "https://aliendro.id/uploads/demo/simpleparallax/data.json";
    /*
    Semua SC Aliendroid menggunakan 2 mode pengambilan data/item

    a. ON_OFF_DATA = "0" data akan diambil dari Json yang ada di folder Asset
    b. ON_OFF_DATA = "1" data akan diambil dari internet,
    dengan syarat anda telah mengupload file json dan mengisi  link json di URL_DATA
     */
    public static String ON_OFF_DATA = "1";

    /*
    Iklan di SC Aliendroid bisa menggunakan mode online/remot

    a. ON_OFF_ADS ="0" iklan offline, seluruh pengaturan iklan ada di SettingsAlien.java
    b. ON_OFF_ADS ="1" iklan online/remot, mode ini digunakan untuk meremot iklan seperti mengganti ID iklan,
    mengubaha posisi iklan atau mematikan iklan.

    Mode remots ads bisa digunakan engan syarat anda telah mengupload file json dan mengisi link json di URL_DATA
     */
    public static String ON_OFF_ADS = "1";

    /*
    Iklan di SC Aliendroid menggunakan sistem Switch Ads dan Backup Ads,
    Seluruh iklan sudah menggunakan mediasi (ads network yang tersedia di SC) dan Open bidding facebook
    Anda bisa memilih salah satu Ads network sebagai main ads, daftar ads network :

    a. SELECT_MAIN_ADS="ADMOB" Sistem mediasi dan open bidding
    b. SELECT_MAIN_ADS="APPLOVIN-M" Sistem mediasi dan open bidding
    c. SELECT_MAIN_ADS="APPLOVIN-D" Tanpa mediasi menggunakan Zone ID
    d. SELECT_MAIN_ADS="STARTAPP" Tanpa Mediasi dan Open Bidding, butuh initilze SDK
    e. SELECT_MAIN_ADS="FACEBOOK" waterfall
    f. SELECT_MAIN_ADS="IRON" Sistem mediasi dan open bidding, butuh initilze SDK
    g. SELECT_MAIN_ADS="ALIEN-V" iklan husus dari Aliendroid hanya mendukung Banner 320x50, Interstitial dan Open Ads
       dengan sistem pembayaran per view. hanya butuh initilze SDK. Registrasi https://ad.clickmobile.id/
    h. SELECT_MAIN_ADS="ALIEN-M" iklan husus dari Aliendroid menggunakan sistem mediasi, saat ini hanya menggunakan Smaato
       sebagai mediasi dengan dukungan format iklan Banner, interstitial, rewards dan natives

    */
    public static String SELECT_MAIN_ADS = "ADMOB";

    /*
    Anda bisa memilih salah satu Ads network sebagai backup main ads / iklan kedua jika main ads tidak terisi,
    daftar ads network :

    a. SELECT_BACKUP_ADS="ADMOB" Sistem mediasi dan open bidding
    b. SELECT_BACKUP_ADS="APPLOVIN-M" Sistem mediasi dan open bidding
    c. SELECT_BACKUP_ADS="APPLOVIN-D" Tanpa mediasi menggunakan Zone ID
    d. SELECT_BACKUP_ADS="STARTAPP" Tanpa Mediasi dan Open Bidding, butuh backup initilze SDK
    e. SELECT_BACKUP_ADS="FACEBOOK" waterfall
    f. SELECT_BACKUP_ADS="IRON" Sistem mediasi dan open bidding, butuh backup initilze SDK
    g. SELECT_MAIN_ADS="ALIEN-V" iklan husus dari Aliendroid hanya mendukung Banner 320x50, Interstitial dan Open Ads
       dengan sistem pembayaran per view. hanya butuh Placement DI untuk Initialize, banner, interstitial dan open ads
    h. SELECT_MAIN_ADS="ALIEN-M" iklan husus dari Aliendroid menggunakan sistem mediasi, saat ini hanya menggunakan Smaato
       sebagai mediasi dengan dukungan format iklan Banner, interstitial, rewards dan natives
    i. Backup ads bisa di matikan dengan cara mengisi null, SELECT_BACKUP_ADS ="null"
     */

    public static String SELECT_BACKUP_ADS = "APPLOVIN-M";


    /*
    Jika anda menggunakan iklan Admob atau Applovin baik sebagai main ads atau backup ads,
    Silahkan isi ID ads yang ada di string.xml
    Admob = ganti <string name="admobappid">ca-app-pub-3940256099942544~3347511713</string>
    Applovin = ganti <string name="sdk_key_applovin">5UhA2fX7QnsiNAwdmPdJW-QTCSqDx1ssvQm1QC252VUsD0sLAG1-2hpDaqHKsM3ZwH0RlcTNcLKTn-gB_xBeBo</string>
     */

    /*
    Pengaturan isian placement ID :

    a. Seluruh ads network yang digunakan memilik placement ID ads masing-masing,
    silahkan isi sesuai posisi main ads atau backup ads.
    b. Terkecuali iklan StartApp hanya menggunakan APP ID, sehingga cukup isi null pada setiap jenis iklan
    misalnya BACKUP_ADS_INTERTITIAL="null" dan masukan APP ID di INITIALIZE_MAIN_SDK atau INITIALIZE_SDK_BACKUP_ADS
    sesuai posisi main ads atau backup ads.
    c. Semua ads network memiliki tipe iklan natives terkecuali Ironsource
    d. Semua ads network memiliki tipe iklan rewards terkecuali Facebook
    e. Iklan ALIEN-V tidak memiliki unit id banner, interstit maupun open ads, silahkan isi dengan Placemet ID
       disemua isin id iklan seperti MAIN_ADS_INTERTITIAL="PL350911184828"
     */
    public static String MAIN_ADS_INTERTITIAL = "ca-app-pub-3940256099942544/1033173712";
    public static String MAIN_ADS_BANNER = "ca-app-pub-3940256099942544/6300978111";

    public static String BACKUP_ADS_INTERTITIAL = "PL350911184828";
    public static String BACKUP_ADS_BANNER = "PL350911184828";

    public static String MAIN_ADS_NATIVES ="ca-app-pub-3940256099942544/2247696110";
    public static String BACKUP_ADS_NATIVES ="PL350911184828";

    /*
       Harap diperhatikan tidak boleh ada dua native dalam 1 halaman yang sama. Silahkan pilih salah satu
       Gunakan Native list dengan mengisi ON_OFF_NATIVE_ON_LIST = "1", atau matikan native dengan mengisi ON_OFF_NATIVE_ON_LIST = "0"
       Jika menggunakan Native Listview isi SWITCH_BANNER_NATIVES ="1"

       INTERVAL_NATIVE adalah jarak munculnya natives didalam list, silahkan isi dengan angka
        */
    public static String ON_OFF_NATIVE_ON_LIST = "1";
    public static int INTERVAL_NATIVE =4;


    /*
    Initilize sdk digunakan untuk iklan selain Admob dan Applovin, sebagai berikut :
    a. StartApp, isi dengan APP ID, contoh INITIALIZE_MAIN_SDK ="12345678"
    b. IronSource, isi dengan APP KEYS, contoh INITIALIZE_MAIN_SDK ="12345678"
    c. Alien-V, isi dengan Placement ID seperti PL350911184828
    isian Initilize sdk disesuaikan dengan posisi iklan sebagai main ads atau backup ads
     */
    public static String INITIALIZE_MAIN_SDK = "8025677";
    public static String INITIALIZE_SDK_BACKUP_ADS = "8025677";

    /*
    Iklan pembuka atau Open App Ads hanya untuk iklan Admob, Applovin Max dan Alien-V
    Jenis iklan ini hanya bisa digunakan sebagai Main Ads.
    a. Hanya untuk SELECT_MAIN_ADS = "ADMOB" atau SELECT_MAIN_ADS ="APPLOVIN-M" atau SELECT_MAIN_ADS ="ALIEN-V"
       tidak berlaku untuk SELECT_BACKUP_ADS.
    b. ALIEN_OPENADS isi dengan Placement ID seperti PL350911184828 atau null jika tidak digunakan
    c. ADMOB_OPENADS = isi dengan unit open ads Admob seperti "ca-app-pub-3940256099942544/3419835294"
    d. MAX_OPENADS = isi dengan unit open ads Applovin Max seperti "asdfg123456"
    e. isi "null" jika tidak ingin menggunakan open ads

     */
    public static String ADMOB_OPENADS = "ca-app-pub-3940256099942544/3419835294";
    public static String ALIEN_OPENADS = "PL350911184828";
    public static String MAX_OPENADS = "abcds1234567";

    /*
    SC Aliendroid menggunakan sistem penyaringan iklan dengan biaya/cpm/klik tinggi untuk iklan ADMOB
    Seperti : Saham, Asuransi dll
    Isian HIGH_PAYING_KEYWORD bisa berupa satu kata kunci atau lebih.
    HIGH_PAYING_KEYWORD1="Asuransi"
    HIGH_PAYING_KEYWORD2="Dekorasi, Saham, Forex"

    Harap diperhatikan, penggunaan mode ini mengakibatkan jarang munculnya iklan
    Sehingga tayangan iklan akan sangat jauh dari permintaan iklan
    Selain itu, penggunaan mode ini tidak menjamin iklan dengan biaya tinggi akan muncul
     */
    public static String HIGH_PAYING_KEYWORD1="Asuransi";
    public static String HIGH_PAYING_KEYWORD2="";
    public static String HIGH_PAYING_KEYWORD3="";
    public static String HIGH_PAYING_KEYWORD4="";
    public static String HIGH_PAYING_KEYWORD5="";


    /*
    SC Aliendroid memiliki keunggulan redirect link untuk mengatasi aplikasi yang di banned/remove oleh google play yang dianggap
    melanggar oleh google plays. Fitur ini hanya bisa digunakan jika anda menggunakan mode remote Ads atau ON_OFF_ADS ="1"
    Fitur ini akan mengalihkan pengguna aplikasi yang di banned/remove ke aplikasi baru yang ada di plasytore
    a. Di SettingsAlien.java biarakan STATUS_APP = "0", jangan di ubah
    b. Jika suatu saat aplikasi di banned/remove isi STATUS_APP = "1" di Json dan isi LINK_REDIRECT="link app baru"
    Maka secara otomatis aplikasi lama tidak bisa digunakan dan langsung membuka halaman playstore aplikasi baru
    */
    public static String STATUS_APP = "0";
    public static String LINK_REDIRECT = "https://play.google.com/store/apps/details?id=com.ad.tesiqkepribadia&hl=en";

    /*
    Interval digunakan untuk jarak munculnya intertitial dari aksi klik suatu item
    isian INTERVAL = 5 artinya iklan akan muncul pada posisi klik ke 6
    isian COUNTER="0" jangan dirubah
   */
    public static int INTERVAL = 5;
    public static int COUNTER = 0;


    /*
    Fitur ini digunakan untuk memfilter iklan dalam aplikasi, sebagai berikut:

    a. CHILD_DIRECT_GDPR = true jika aplikasi 3+, melakukan set true artinya iklan hanya menampilkan jenis ramah keluarga,
    Tayangan iklan akan minim dan harga iklan tidak seperti iklan dewasa / memiliki cpm rendah.
    Akan tetapi Insya Allah hati tenang

    b. CHILD_DIRECT_GDPR = false jika aplikasi mengijinkan seluruh kategori iklan termasuk JUDI, SEX, SARA dll
    yang diperuntukan untuk iklan 18+
    Segala sesuatu muatan iklan seperti tampilan iklan aplikasi BEGO yang fulgar dikarenakan anda mengijinkan tayang
    maka DOSA silahkan tanggung jawab sendiri.

    Fitur ini hanya berlaku untuk Alien Ads Uranus-26
     */
    public static boolean CHILD_DIRECT_GDPR = true;

    /*
    Salah satu faktor limit Admob adalah adanya aplikasi berjalan/bisa dipasang di luar playstore
    Sedangkan aturan penayangan Admob dan FAN aplikasi harus live di playstore atau di market yang tekah di ijinkan
    tidak termasuk APKMirror, Aptoide, APKPure  dan lainnya.
    Untuk melindungi aplikasi bisa terpasang dari selain Playstore silahkan isi PROTECT_APP = true
    dan isi BASE_64_LICENSE_KEY , silahkan ikuti panduan dibawah
    https://www.youtube.com/watch?v=HqRze7sGH6s&t=7s
     */
    public static boolean PROTECT_APP = false;
    public static String BASE_64_LICENSE_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4eX/58nIRKOXLJHHiQuLjBjjk7lV2ffsxhzE45nyPCZVKOBpELLmOPCxWIkWw8gOPhxYU2vwga+txkIubAG6UftGfVz/4t8siqguYkmUrfmhGYgMOVMdkq1X69vKRUsauv4u1mEi6AWJKOPO/Lbp+vvr6IOMu70N5Yyzhkp3nNCWvUz6N6SalrDjNkGWyiZQH0Ibq3Ccqhj9VugdAVmTMlqpi9k1SsoGvj07WbJMYXzqjrq3DxFnYNg6WezbmQG6dv49loffImcct0Bz1A37ixWqMbkchcG/YZUh5vXJnH5w3/fruf6sl5qmYqz6eZMUPmbIWsorydLvhShRERgktQIDAQAB";

    /*
    Onesignal digunakan untuk mengirim pesan keseluruh pengguna aplikasi, Daftar https://onesignal.com/
    silahkan ikuti panduan https://documentation.onesignal.com/docs/generate-a-google-server-api-key
    atau bisa cek video https://www.youtube.com/watch?v=yPlFAvTR-Vo&t=13s
    masukan Api Key ke ONE_SIGNAL_API_KEY, dan jika tida digunakan silahkan isi null
     */
    public static String ONE_SIGNAL_API_KEY ="535dc774-9fe3-44ae-839e-09e4133aebe9";

    public static String MODE_DIALOG_EXIT = "1" ; // 1 = Dengan Banner Ads, 2 = Dengan More App
}

