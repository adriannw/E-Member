<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/include/taglibs.jsp"%><c:set var="path" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="description" content="<fmt:message key="meta.description" />" />
    <meta name="keywords" content="<fmt:message key="meta.keyword" />" />
    <meta name="author" content="<fmt:message key="main.company" /> | Administered by: Yusuf Sutarko | Original design: Andreas Viklund - http://andreasviklund.com/ | Icons: Paul Jarvis - http://2pt3.com/" />
    <link rel="stylesheet" type="text/css" href="${path}/include/css/general.css" media="screen" title="general" />
    <script type="text/javascript" src="${path}/include/js/links.js"></script>
    <script type="text/javascript" src="${path}/include/js/general.js"></script>
    <script type="text/javascript" src="${path}/include/js/dateOperation.js"></script>

    <link REL="Stylesheet" TYPE="text/css" HREF="${path}/include/js/jscalendar/css/calendar-win2k-1.css" media="screen">
    <script type="text/javascript" src="${path}/include/js/jscalendar/calendar.js"></script>
    <script type="text/javascript" src="${path}/include/js/jscalendar/calendar-en.js"></script>
    <script type="text/javascript" src="${path}/include/js/jscalendar/calendar-setup.js"></script>
 	<script type="text/javascript" src="${path}/include/js/overlib.js"></script>
    <title><fmt:message key="main.title"/></title>
    <decorator:head />

    <script type="text/javascript">
        function seeReleaseNote()
        {
            alert(    '\n30 Juni 2008 - 10:50\n'
                    + '- tambahan produk Ekalink Family Trans Batavia\n'
                    + '\n2 Juni 2008 - 16:00\n'
                    + '- revisi rider PA pada Dana Sejahtera\n'
                    + '\n29 Mei 2008 - 18:40\n'
                    + '- revisi Dana Sejahtera pada rider PA pada mata uang dollar\n'
                    +  '\n28 Mei 2008 - 16:00\n'
                    + '- revisi Dana Sejahtera pada rider PA dan PK\n'
                    + '- revisi Dana Sejahtera pada penambahan nama PP dan usia PP\n'
                    + '\n27 Mei 2008 - 11:15\n'
                    + '- revisi Dana Sejahtera pada rider PK\n'
                    + '\n26 Mei 2008 - 14:30\n'
                    + '- penambahan kata2 Persyaratan underwriting di produk hidup bahagia single \n'
                    + '- FS+G menjadi G+FS dan FS+H menjadi H+FS\n'
                    + '\n21 Mei 2008 - 18:30\n'
                    + '- rider PA pada unit link halaman 3 telah di revisi\n'
                    + '- kinerja investasi bulan desember 2007 telah dimasukkan\n'
                    + '- tiap kali ganti produk akan mereset halaman ke default value\n'
                    + '\n19 Mei 2008 - 08:30\n'
                    + '- rider PA dan TPD pada semua Link telah di revisi\n'
                    + '- default bilangan bulat di ganti menjadi bil pecahan pada stable link dan power save\n'
                    + '- stable link BII telah di keluarkan dari pilihan\n'
                    + '\n12 Mei 2008 - 11:30\n'
                    + '- Butuh user account untuk login \n'
                    + '- Tambahan Log out\n'
                    +  '\n8 Mei 2008 - 15:10\n'
                    + '- Tampilan Login\n'
                    + '- Revisi maksimal 1 milyar pada TPD rider\n'
                    + '- Revisi batasan premi pokok maksimal pada produk2 link\n'
                    + '\n2 Mei 2008 - 16:20\n'
                    + '- Revisi biaya asuransi bulanan manfaat PA pada semua produk ekalink\n'
                    + '- Penambahan data pada Grafik Kinerja NAV rupiah, dollar, syariah\n'
                    + '\n2 Mei 2008 - 10:40\n'
                    + '- Revisi produk Eka Link Plus sesuai email Mb Atik 30 April 2008\n'
                    + '\n30 April 2008 - 14:00\n'
                    + '- Revisi produk Stable Link Ilustrasi\n'
                    + '- Revisi produk Stable Link, menghilangkan pasal\n'
                    + '\n29 April 2008 - 19:40\n'
                    + '- New!!! Produk Stable Link\n'
                    + '- Revisi produk Bahagia sesuai email Mb Neni tgl 29 April 2008\n'
                    + '\n25 April 2008 - 16:15\n'
                    + '- Revisi produk2 link sesuai email Mb Atik tgl 22 April 2008\n'
                    + '- Repricing rate HCP sesuai email Pak Morinof tgl 23 April 2008\n'
                    + '- Revisi produk "Hidup Bahagia" sesuai email Mb Neni tgl 24 & 25 April 2008\n'
                    + '- Penambahan Produk baru "Ekalink 88 Plus" sesuai email Pak Morinof tgl 24 April 2008\n'
                    + '- Repricing rider PK pada produk "Dana Sejahtera (New)" sesuai email Pak Morinof tgl 24 April 2008\n'
                    + '\n17 April 2008 - 17:40\n'
                    + '- Revisi produk Hidup Bahagia pada pembulatan premi (email Mb Neni tgl 16 April 2008)\n'
                    + '- Revisi produk Hidup Bahagia dimana output yg diharapkan FS+H\n'
                    + '\n15 April 2008 - 15:00\n'
                    + '- Revisi produk Super Protection selesai sbg FU dari Pak Achmad di Helpdesk tgl 26 Maret 2008\n'
                    + '- Revisi produk Dana Sejahtera selesai sbg FU dari Pak Morinof di Helpdesk tgl 9 April 2008\n'
                    + '- Revisi produk2 Link selesai sbg FU email Mb Atik tgl 26 Maret 2008\n'
                    + '- Revisi produk Hidup Bahagia selesai sbg FU email Mb Neni tgl 26 Maret 2008\n'
                    + '  kecuali dibagian tipe medis, yg kemungkinan disebabkan pemakaian database testing\n'
                    + '\n25 Maret 2008\n'
                    + '- Revisi produk hidup bahagia selesai sbg FU email Mb Neni tgl 14 Maret 2008\n'
                    + '\n18 Maret 2008\n'
                    + '- Revisi unit link selesai sbg FU email Mb Atik tgl 17 Maret 2008\n'
                    + '- Semua produk power save dimunculkan untuk kategori agency\n'
                    + '\n12 Maret 2008\n'
                    + '- Revisi note selesai sbg FU email Mb Atik tgl 6 Maret 2008\n'
                    + '- Revisi Produk Dana Sejahtera selesai sbg FU email Pak Morinof tgl 10 Maret 2008\n'
                    + '\n06 Maret 2008\n'
                    + '- Produk Bahagia telah selesai di develop\n'
                    + '\n05 Maret 2008\n'
                    + '- Revisi semua unit link pada bagian maksimum UP\n'
                    + '\n04 Maret 2008\n'
                    + '- Revisi semua unit link, sbg FU email mb Atik tanggal 28 Peb'
                    + '  hanya saja bagian maksimum UP belum selesai.\n'
                    + '\n28 Pebruari 2008\n'
                    + '- Revisi validasi kelipatan 10 untuk setiap investasi unit link, FU dari phone by Atik.\n'
                    + '- Pemisahan produk Artha Link dari Eka Link, FU dari email Pak Yohanes Pintoko tgl 27 Pebruari 2008.\n'
                    + '\n27 Pebruari 2008\n'
                    + '- Revisi Dana Sejahtera, sbg FU email Sdr. Morinof tgl 26 Pebruari 2008.\n'
                    + '- Revisi validasi semua Link, sbg FU email Sdri. Atik tgl 25 Pebruari 2008.\n'
                    + '\n22 Pebruari 2008\n'
                    + '- Revisi Dana Sejahtera, sbg FU email Sdr. Morinof tgl 21 Pebruari 2008.\n'
                    + '\n19 Pebruari 2008\n'
                    + '- Ada tambahan produk Ekalink 80, Ekalink 80 Plus dan Ekalink 80 Syariah.\n'
                    + '\n13 Pebruari 2008\n'
                    + '- Ada tambahan produk Eka Link 80.\n'
                    + '\n06 Pebruari 2008\n'
                    + '- Ada tambahan produk Artha/Ekalink 88.\n'
                    );
        }
    </script>
</head>

<body>
<div id="container">

    <decorator:body />
</div>
<script type="text/javascript">
        path = '${path}';

    </script>
</body>
</html>