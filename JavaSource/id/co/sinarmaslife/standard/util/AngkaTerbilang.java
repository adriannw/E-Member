package id.co.sinarmaslife.standard.util;

/**
 * Class yang digunakan untuk terbilang dalam bahasa inggris maupun indonesia
 * contoh : Rp. 2.512.500,- diubah menjadi dua juta lima ratus dua belas ribu lima ratus rupiah 
 * @author Yusuf
 * @since 3 Jan 2006
 */
public class AngkaTerbilang {
    
//    public static void main(String args[]){
//        logger.info(english("111111111111")); //12 digits
//        logger.info(english("115419789113456")); //15 digits
//        logger.info(english("23420450230"));
//        logger.info(english("3202344576"));
//        logger.info(english("784356455"));
//        logger.info(english("98678664"));
//        logger.info(english("5463456"));
//        logger.info(english("878085"));
//        logger.info(english("80504"));
//        logger.info(english("8064"));
//        logger.info(english("109"));
//        logger.info(english("10"));
//        logger.info(english("1"));
//
//        logger.info(english("100000000001")); //12 digits
//        logger.info(english("100100100101")); //12 digits
//        logger.info(english("1001001")); //12 digits
//        logger.info(english("1001010101")); //12 digits
//        logger.info(english("110010101")); //12 digits
//
//        logger.info(indonesian("111111111111")); //12 digits
//        logger.info(indonesian("115419789113")); //12 digits
//        logger.info(indonesian("23420450230"));
//        logger.info(indonesian("3202344576"));
//        logger.info(indonesian("784356455"));
//        logger.info(indonesian("98678664"));
//        logger.info(indonesian("5463456"));
//        logger.info(indonesian("878085"));
//        logger.info(indonesian("80504"));
//        logger.info(indonesian("109"));
//        logger.info(indonesian("10"));
//        logger.info(indonesian("1"));
//
//        logger.info(indonesian("100000000001")); //12 digits
//        logger.info(indonesian("100100100101")); //12 digits
//        logger.info(indonesian("1001001")); //12 digits
//        logger.info(indonesian("1001010101")); //12 digits
//        logger.info(indonesian("110010101")); //12 digits
//        logger.info(indonesianDecimal("8064.53", "01"));
//        logger.info(indonesianDecimal(".15", "02"));
//        logger.info(indonesianDecimal("845", "01"));
//        logger.info(indonesianDecimal("0.15", "02"));
//        logger.info(indonesianDecimal("12312312845.00", "01"));
//    }

    private static String dot(String jumlah){
        int panjang = jumlah.length();

        if(panjang>9){
            jumlah =
                jumlah.substring(0, panjang-9) + "," +
                jumlah.substring(panjang-9, panjang-6) + "," +
                jumlah.substring(panjang-6, panjang-3) + "," +
                jumlah.substring(panjang-3, panjang);
        }else if(panjang>6){
            jumlah =
                jumlah.substring(0, panjang-6) + "," +
                jumlah.substring(panjang-6, panjang-3) + "," +
                jumlah.substring(panjang-3, panjang);
        }else if(panjang>3){
            jumlah =
                jumlah.substring(0, panjang-3) + "," +
                jumlah.substring(panjang-3, panjang);
        }
        return jumlah;
    }

    /**
	 * @author Yusuf
	 */
    public static String indonesian(String jumlah){

    	if(jumlah==null) return "";

        String angka[] = {
                "nol ", "satu ", "dua ", "tiga ", "empat ", "lima ",
                "enam ", "tujuh ", "delapan ", "sembilan "
        };

        String satuan[] = {
                "",
                "",
                "puluh ",
                "ratus ",
        };

        String tigaan[] = {
                "miliar ",
                "juta ",
                "ribu ",
                ""
        };

        //Validasi dulu dong
        if (jumlah.startsWith("-")) jumlah = jumlah.substring(1,jumlah.length());

        if (jumlah.length()>12){
            return "Maximum number length is 12";
        }else if (!jumlah.matches("\\d+")){
            return "Input must be a number without spaces";
        }else{

            String[] divided = dot(jumlah).split(",");

            String cipiki = "";
            String cipika = "";
            //untuk setiap array yg tadi udah dipisah,
            for(int i=0; i<divided.length; i++){
                int panjang2 = divided[i].length();
                int tambahPanjang = 0;
                //tambahin satuan (puluh, ratus)
                for(int j=1; j<=panjang2; j++){
                    int k = panjang2-j+1;
                    cipiki = divided[i].substring(0,j+tambahPanjang);
                    cipika = divided[i].substring(j+tambahPanjang, divided[i].length());
                    //kalo ada angka 0, apus tuh angka
                    if(cipiki.substring(cipiki.length()-1).equals("0")){
                        cipiki = cipiki.substring(0,cipiki.length()-1);
                        divided[i] = cipiki + cipika;
                        tambahPanjang--;
                    } else{
                        divided[i] = cipiki + satuan[k] + cipika;
                        tambahPanjang += satuan[k].length();
                    }
                }
                //ganti angka menjadi terbilang (1 -> satu, 2 -> dua, ..)
                for(int l=0; l<10; l++){
                    divided[i] = divided[i].replaceAll(String.valueOf(l), angka[l]);
                }
            }

            StringBuffer hasil = new StringBuffer();

            //hasil akhir, digabung lagi array yg tadi udah dipisah,
            //ditambah dengan per tigaan (miliar, juta, ribu)

            int tambah = 0;
            if(jumlah.length()>9)tambah = 0;
            else if(jumlah.length()>6)tambah = 1;
            else if(jumlah.length()>3)tambah = 2;
            else tambah = 3;

            for(int i=0; i<divided.length; i++){
                if(divided[i].equals("")){
                    hasil.append(divided[i]);
                }else{
                    hasil.append(divided[i]+ tigaan[i+tambah]);
                }
            }

            //replace
            return //jumlah + " = " +
            	hasil.toString().
            		replaceAll("satu ratus", "seratus").
            		replaceAll("satu puluh", "sepuluh").
            		replaceAll("satu ribu", "seribu").
            		replaceAll("sepuluh satu", "sebelas").
            		replaceAll("sepuluh dua", "dua belas").
            		replaceAll("sepuluh tiga", "tiga belas").
            		replaceAll("sepuluh empat", "empat belas").
            		replaceAll("sepuluh lima", "lima belas").
            		replaceAll("sepuluh enam", "enam belas").
            		replaceAll("sepuluh tujuh", "tujuh belas").
            		replaceAll("sepuluh delapan", "delapan belas").
            		replaceAll("sepuluh sembilan", "sembilan belas");
        }

    }

    /**
	 * @author Yusuf
	 */
    public static String english(String jumlah){

        String angka[] = {
                "nil ", "one ", "two ", "three ", "four ", "five ",
                "six ", "seven ", "eight ", "nine "
        };

        String satuan[] = {
                "",
                "",
                "puluh ",
                "hundred ",
        };

        String tigaan[] = {
                "billion and ",
                "million and ",
                "thousand and ",
                ""
        };

        //Validasi dulu dong
        if (jumlah.startsWith("-")) jumlah = jumlah.substring(1,jumlah.length());

        if (jumlah.length()>12){
            return "Maximum number length is 12";
        }else if (!jumlah.matches("\\d+")){
            return "Input must be a number without spaces";
        }else{

            String[] divided = dot(jumlah).split(",");

            String cipiki = "";
            String cipika = "";
            //untuk setiap array yg tadi udah dipisah,
            for(int i=0; i<divided.length; i++){
                int panjang2 = divided[i].length();
                int tambahPanjang = 0;
                //tambahin satuan (puluh, ratus)
                for(int j=1; j<=panjang2; j++){
                    int k = panjang2-j+1;
                    cipiki = divided[i].substring(0,j+tambahPanjang);
                    cipika = divided[i].substring(j+tambahPanjang, divided[i].length());
                    //kalo ada angka 0, apus tuh angka
                    if(cipiki.substring(cipiki.length()-1).equals("0")){
                        cipiki = cipiki.substring(0,cipiki.length()-1);
                        divided[i] = cipiki + cipika;
                        tambahPanjang--;
                    } else{
                        divided[i] = cipiki + satuan[k] + cipika;
                        tambahPanjang += satuan[k].length();
                    }
                }
                //ganti angka menjadi terbilang (1 -> satu, 2 -> dua, ..)
                for(int l=0; l<10; l++){
                    divided[i] = divided[i].replaceAll(String.valueOf(l), angka[l]);
                }
            }

            StringBuffer hasil = new StringBuffer();

            //hasil akhir, digabung lagi array yg tadi udah dipisah,
            //ditambah dengan per tigaan (miliar, juta, ribu)

            int tambah = 0;
            if(jumlah.length()>9)tambah = 0;
            else if(jumlah.length()>6)tambah = 1;
            else if(jumlah.length()>3)tambah = 2;
            else tambah = 3;

            for(int i=0; i<divided.length; i++){
                if(divided[i].equals("")){
                    hasil.append(divided[i]);
                }else{
                    hasil.append(divided[i]+ tigaan[i+tambah]);
                }
            }

            //replace
            return //jumlah + " = " +
            	hasil.toString().
        		replaceAll("one puluh", "ten").
        		replaceAll("two puluh", "twenty").
        		replaceAll("three puluh", "thirty").
        		replaceAll("four puluh", "fourty").
        		replaceAll("five puluh", "fifty").
        		replaceAll("six puluh", "sixty").
        		replaceAll("seven puluh", "seventy").
        		replaceAll("eight puluh", "eighty").
        		replaceAll("nine puluh", "ninety").
        		replaceAll("ten one", "eleven").
        		replaceAll("ten two", "twelve").
        		replaceAll("ten three", "thirteen").
        		replaceAll("ten four", "fourteen").
        		replaceAll("ten five", "fifteen").
        		replaceAll("ten six", "sixteen").
        		replaceAll("ten seven", "seventeen").
        		replaceAll("ten eight", "eighteen").
        		replaceAll("ten nine", "nineteen");
        }

    }

    /**
	 * Fungsi yang digunakan untuk mengubah angka menjadi terbilangnya (contoh: Rp.100,- menjadi seratus rupiah)
	 * @author Yusuf
	 * @since 3 Jan 2006
	 * @param jumlah Jumlah uangnya (maximum 12 digit + 2 digit desimal)
	 * @param kurs Satuan kurs (mengikuti format LKU_ID)
	 * @return String hasilnya
	 */
    public static String indonesian(String jumlah, String kurs){

    	if(jumlah==null) return "";

        String angka[] = { "nol ", "satu ", "dua ", "tiga ", "empat ", "lima ", "enam ", "tujuh ", "delapan ", "sembilan " };
        String satuan[] = { "", "", "puluh ", "ratus ", "" };
        String tigaan[] = { "miliar ", "juta ", "ribu ", "" };

        //Validasi dulu dong
        jumlah = jumlah.trim();
        if (jumlah.startsWith("-")) jumlah = jumlah.substring(1,jumlah.length());

        if (!jumlah.matches("^\\d*\\.{0,1}\\d{1,2}$") || (jumlah.indexOf(".")>-1&&jumlah.length()>15) || (jumlah.indexOf(".")==-1&&jumlah.length()>12)){
            return "Format Angka maksimal adalah 12 digit ditambah 2 digit desimal";
        }

        String temp[] = jumlah.split("\\.");
        if(temp.length==0) {
        	temp = new String[] {jumlah};
        }

        String[] divided = dot(temp[0]).split(",");

        String cipiki = "";
        String cipika = "";
        //untuk setiap array yg tadi udah dipisah,
        for(int i=0; i<divided.length; i++){
            int panjang2 = divided[i].length();
            int tambahPanjang = 0;
            //tambahin satuan (puluh, ratus)
            for(int j=1; j<=panjang2; j++){
                int k = panjang2-j+1;
                cipiki = divided[i].substring(0,j+tambahPanjang);
                cipika = divided[i].substring(j+tambahPanjang, divided[i].length());
                //kalo ada angka 0, apus tuh angka
                if(cipiki.substring(cipiki.length()-1).equals("0")){
                    cipiki = cipiki.substring(0,cipiki.length()-1);
                    divided[i] = cipiki + cipika;
                    tambahPanjang--;
                } else{
                    divided[i] = cipiki + satuan[k] + cipika;
                    tambahPanjang += satuan[k].length();
                }
            }
            //ganti angka menjadi terbilang (1 -> satu, 2 -> dua, ..)
            for(int l=0; l<10; l++){
                divided[i] = divided[i].replaceAll(String.valueOf(l), angka[l]);
            }
        }

        StringBuffer hasil = new StringBuffer();

        //hasil akhir, digabung lagi array yg tadi udah dipisah,
        //ditambah dengan per tigaan (miliar, juta, ribu)

        int tambah = 0;
        if(temp[0].length()>9)tambah = 0;
        else if(temp[0].length()>6)tambah = 1;
        else if(temp[0].length()>3)tambah = 2;
        else tambah = 3;

        for(int i=0; i<divided.length; i++){
            if(divided[i].equals("")){
                hasil.append(divided[i]);
            }else{
                hasil.append(divided[i]+ tigaan[i+tambah]);
            }
        }

        //replace
        return (temp[0].equals("")||temp[0].equals("0")?"nol ":"")+
        	hasil.toString().
        		replaceAll("satu ratus", "seratus").
        		replaceAll("satu puluh", "sepuluh").
        		replaceAll("satu ribu", "seribu").
        		replaceAll("sepuluh satu", "sebelas").
        		replaceAll("sepuluh dua", "dua belas").
        		replaceAll("sepuluh tiga", "tiga belas").
        		replaceAll("sepuluh empat", "empat belas").
        		replaceAll("sepuluh lima", "lima belas").
        		replaceAll("sepuluh enam", "enam belas").
        		replaceAll("sepuluh tujuh", "tujuh belas").
        		replaceAll("sepuluh delapan", "delapan belas").
        		replaceAll("sepuluh sembilan", "sembilan belas") + //tambahain desimal dan satuan kurs-nya
        		(temp.length>1?((temp[1].equals("0")||temp[1].equals("00")?"":"koma ")+indonesian(temp[1], "")):"")+ (kurs.equals("01")?"Rupiah":kurs.equals("02")?"US Dollar":"");

    }
}