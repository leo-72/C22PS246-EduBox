package com.example.mainuiux.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mainuiux.R


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private val judul = arrayListOf("Literasi VII : Berbagi Kisah, Bertukar Pesan: Inspirasi",
        "Literasi VIII : Persuasi dalam Kata dan Gambar",
        "Literasi IX :  Dunia Kita dalam Cerita","Penguatan Numerasi untuk Ilmu Pengetahuan Alam","Penguatan Numerasi untuk Ilmu Pengetahuan Sosial","Penguatan Numerasi untuk Mata Pelajaran PJOK")

    private val materi = arrayOf("Jakarta, 16 Mei 2020\n" +
            "\n" + "    Bahar, apa kabar?\nPerasaanku tak keruan ketika menuliskan tanggal di ujung atas kertas ini. Hari ini tepat sebulan kita berpisah, dan aku baru sempat menulis surat Sebulan, Bahar, sebulan!\n\n " +
            "   Pagi buta begini, biasanya kita berlari saling mendahului naik bukit di ujung kampung. Setelah sampai di atas, kita menatap matahari bulat terbit dari balik gunung. Kita tertawa sejenak lalu pulang, tetap sambil adu lari. Itu kebiasaan sejak kita masih ingusan.\n\n" +
            "   Tak terhitung memar dan luka di badan, termasuk gigi tanggal beberapa,tetapi kita tetap melakukannya. Hampir setiap hari kita adu lari, termasuk tiga hari setelah kita sama-sama disunat, haha!\n\n" +
            "   Ingatkah kau, kita pulang dengan tersaruk-saruk sambil menahan nyeri,kemudian mendapatkan wejangan panjang di antara wajan raksasa. Kita hanya diam, menunduk dan saling melirik, sementara tangan-tangan kurus kita terus mengaduk gula merah yang mengental.\n\n" +
            "   Jera? Tentu tidak! Keesokan harinya, kita tetap adu lari. Akibatnya, lukaku menganga lagi. Aku digotong ke Puskesmas, dan air mata mengalir tanpa kendali. Sesudahnya, kita sama-sama dimarahi. Haha! Sebenarnya kau lebih beruntung karena tidak tahu apa itu dimarahi. Apa pun perlakuan orang kepadamu, hanya ada senyum di wajahmu.\n\n" +
            "   Oh, ya, Bahar. Dengan siapa kamu lari ke bukit pagi ini? Lukman? Atau Ashari? Mereka juga baik padamu, kan? Sayang sekali, di sini tidak ada bukit. Sebagai gantinya, aku harus naik turun tangga besi yang sudah berkarat di sana sini. Aku harus melakukannya setenang mungkin agar tidak mengganggu tetangga.\n\n" +
            "   Kota yang sering kita saksikan di televisi, ternyata berbeda dengan kota yang kini kuhuni. Aku belum pernah melihat jalan besar yang bersusun, atau gedung tinggi menjulang. Yang kulihat adalah jalan-jalan sempit dengan banyak cabang, dan selokan yang tersumbat.\n\n" +
            "   Di sini tidak ada halaman luas dengan tungku-tungku besar dan wajan mengepul. Om dan Tante memasak berbagai penganan di dapur mungil. Berbagai aroma berganti dengan cepat. Walau api hanya bersumber dari kompor kecil, rasa panas dan pengap yang kurasakan sungguh luar biasa.\n\n" +
            "   Aku belum bisa membantu mereka memasak. Tugasku adalah menata penganan di nampan-nampan, membersihkan dapur, menjaga dua sepupuku kalau mendadak bangun, kemudian siap-siap ke sekolah.\n\n" +
            "   Lain kali akan kuceritakan pengalamanku di sekolah. Aku belum punya teman dekat dan masih salah tingkah dengan semua ini.\n\n" +
            "   Bahar,\n" +
            "Sekian dulu suratku. Aku ingin banyak bercerita, tetapi masih berat rasanya. Setiap kenangan selalu terkait dengan ingatan tentang ayahku yang kini tiada. Sebulan ini aku juga sibuk menyesuaikan diri dengan irama hidup yang jauh berbeda. Jadi maafkan aku terlambat berkirim surat.\n\n" +
            "   Berterima kasihlah kepada adikmu, Ani, yang membacakan suratku ini untukmu. Coba katakan, “Te-ri-ma-ka-sih”. Bisakah?\n\n" +
            "   Bahar, mungkin kau tidak memahami isi suratku ini, tetapi bercerita kepadamu sudah kulakukan sejak dulu. Saat ini aku membayangkan kau menatapku dengan senyum tulus itu. Kini aku sungguh berharap kau sudah menemukan teman berlari. Jangan biarkan bukit itu sepi dan matahari terbit sendirian.\n\n" +
            "Salam,\nSyamsul\n\n" +
            "Catatan:\nTerima kasih, Ani. \nBerikan suratku kepada Bahar untuk disimpan. \nBalaslah suratku, ceritakan apa yang dilakukan Bahar saat membaca suratku.\n" +
            "(Ditulis oleh: Anna Farida)",

        //materi2
        "   Teks persuasif dikenal sebagai teks yang berisi kalimat-kalimat bujukan. Teks ini berfungsi untuk mengajak orang-orang agar mengikuti sebuah ide tertentu. Jenis teks ini banyak ditemukan dalam iklan. Sebagai media yang berfungsi untuk menyampaikan pesan, iklan umumnya mengandung kalimat-kalimat bujukan agar orang-orang tertarik untuk mengenal sebuah produk atau tindakan. Setelah mereka mengenal produk yang dimaksud, diharapkan mereka memiliki keinginan untuk mendapatkan barang yang diiklankan atau melakukan tindakan yang disarankan.",
        //materi3
        "Silahkan membaca cerpen Sampur Merah Dian dibawah ini\n\n\n" +
                "   Sinar mentari belum lama menyapa pucuk pepohonan rindang di tepi lapang. Deretan pintu kelas di sepanjang lorong masih tertutup. Hanya terdengar suara sapu lidi penjaga sekolah yang menggiring helaian daun kering. Namun, Dian sudah mematung di depan majalah dinding sekolahnya. Ia sengaja melewatkan sarapan demi dapat sepuasnya membaca selembar poster yang ditempel Pak Seno di sana. Mata bulatnya menyusuri kata demi kata yang tertera pada poster itu.\n\n" +
                "   Sejak diajak ibunya menonton festival itu beberapa tahun lalu, Dian sangat ingin ikut tampil di sana. Dapat tampil di festival akbar daerahnya itu tentu akan sangat membanggakan. Ia masih ingat bagaimana Pak Sukardi, tetangganya, memberi tahu semua tetangganya bahwa putrinya dapat lulus seleksi penari tahun lalu. Setiap tahun ribuan siswa SMP dan SMA se-Kabupaten Banyuwangi mengikuti seleksi, tetapi yang lolos dan dibutuhkan untuk tampil hanya seribuan. Sayangnya, keinginan Dian itu sepertinya tidak akan pernah dapat terwujud. Kali ini, tidak hanya bapaknya yang akan tidak setuju tapi semua orang.\n\n" +
                "   ”Ian, sedang apa?” suara Kinanti menepuk punggung Dian. “Wah, sudah dibuka ya pendaftarannya?”\n\n" +
                "   “Kamu daftar, Ti?” tanya Dian tanpa melirik gadis berambut ikal di sampingnya.\n\n" +
                "   “Belum tahu. Kalau Marni ikut, aku juga ikut,” jawab Kinanti.\n" +
                "   Suara beberapa siswa lain menghentikan pembicaraan mereka. Dua pelajar SMP itu bergegas menyusuri lorong sekolah menuju kelas mereka di ujung.\n\n" +
                "   Saat hampir semua teman-temannya berhamburan ke luar kelas untuk istirahat, Dian masih memainkan sampur merahnya. Ia kembali mengulang gerakan ngebyar yang baru dipelajari dari Pak Seno, guru seni tarinya. Dikibaskan ujung sampurnya ke dalam dan ke luar.\n" +
                "   “Semangat sekali, Ian!” suara Kinanti menghentikan gerakan tangan Dian. Dian hanya tersenyum dan membereskan sampurnya.\n\n" +
                "   “Ke kantin, yuk? Ajak Marni kepada Kinanti dan Dian yang tiba-tiba muncul. Kinanti dan Dian mengangguk tanda setuju.\n\n" +
                "   Mar, kamu jadi ikut Festival Tari Gandrung Sewu tahun ini?” tanya Kinanti sambil mengunyah ladrang yang dibawa Marni. Aroma bawangnya bercampur dengan semangkuk bakso yang dibeli Marni.\n" +
                "   “Wah, enggak tahu. Capai, panas-panasan gitu,” jawab Marni. “Kamu mau ikut lagi?”\n\n" +
                "   “Kurang tahu juga. Ya sih, capai dan panas, tapi seru. Kita bisa bertemu banyak orang. Malah, kalau beruntung bisa diwawancara dan masuk TV seperti Gita tahun lalu,” jawab Kinanti. Ia masih ingat bagaimana teman sekelasnya itu begitu bangga saat wajah ayunya muncul di sebuah acara televisi nasional meski hanya beberapa menit.\n\n" +
                "   “Wah, betul juga, ya? Tapi, enggak ah,” kata Marni.\n\n" +
                "   “Sudah, ikut saja. Gerakan kalian kan bagus, luwes,” ujar Dian sambil mengambil ladrang di depan Kinanti. Suara renyah ladrang yang digigitnya ikut meramaikan suasana kantin siang itu.\n" +
                "   “He he, terima kasih,” jawab Kinanti sambil menggerakkan tangannya dengan luwes. “Eh, tapi kalau kita lolos nanti kamu datang ke Pantai Marina kan?”\n" +
                "   “Pasti!” jawab Dian pendek.\n\n" +
                "   Sepulang sekolah Dian mampir ke ruang guru mencari Pak Seno. Sudah lama ia ingin menemui Pak Seno untuk menyampaikan niatnya.\n\n" +
                "   Ada apa, Ian? Kok belum pulang?” tanya Pak Seno saat melihat Dian berdiri di samping mejanya.\n\n" +
                "   “Anu Pak. Apa saya benar-benar tidak bisa ikut festival itu?”\n\n" +
                "   Pak Seno pun tersenyum mendengar pertanyaan Dian. “Kan sudah jelas syaratnya seperti apa. Kamu bisa baca pengumumannya di mading sekolah. Jadi, maaf kamu tidak bisa ikut festival itu. Tari gandrung di festival ini kan tidak ditampilkan bersama paju. Kalau zaman dulu mungkin bisa,” jawab Pak Seno sambil berdiri dan membawa tasnya. “Sebentar, sepertinya bapak punya acara lain yang dapat kamu ikuti,” ujar Pak Seno sambil membuka tasnya dan mengeluarkan sebuah kertas. “Coba daftar, semoga kamu bisa lolos dan tampil membawakan tari remo di Surabaya.”\n\n" +
                "   Ya, Pak,” respons Dian pendek. Ia menerima kertas dari Pak Seno, memegangnya seperti memegang angin, membacanya sekilas, dan memasukkannya ke dalam tas. Wajah Dian masih seperti daun layu. Tak ada binar di wajahnya meskipun sudah mendapat informasi itu.\n\n" +
                "   “Kalau kamu memang suka menari, ya menarilah tanpa harus memilih tarian yang ingin kamu tarikan. Kalau kata maestro tari, Didik Nini Thowok, kita harus berkesenian sepenuh hati, total, dan ikhlas,” pesan Pak Seno sambil menepuk pundak Dian. Dian hanya mengangguk meski masih belum dapat memahami kata-kata gurunya tadi.\n\n" +
                "   “Sudah makan, Ian?” suara ibu membuat Dian bergegas melepas sampur merah yang digunakannya latihan di kamar malam itu. Ia sering kali sembunyi-sembunyi menari di dalam kamar.\n\n" +
                "   Dian tak menjawab. Bu Sarni mengajak anak semata wayangnya itu duduk.\n\n" +
                "   “Bapakmu dulu juga penari sebelum jadi pengrajin kerajinan aluminium. Bapak sama ibu sama-sama suka keliling nari reog. Bapakmu biasa jadi Singo Barong atau kadang jadi Warok, sedangkan ibu jadi penari jathil. Bapakmu penari yang hebat, apalagi kalau jadi Singo Barong. Topeng kepala singa yang berat itu seolah-olah begitu ringan kalau Bapak yang menarikan. Sayangnya, kakekmu kurang setuju kalau ibu nikah sama penari. Katanya, penari itu gak punya masa depan,” jelas Bu Sarni dengan seulas senyum di ujung bibirnya.\n\n" +
                "   “Akhirnya, bapakmu merantau sampai ke Banyuwangi sini. Setelah dapat kerja di galerinya Pak Mangun, bapakmu pulang ke Ponorogo melamar ibu. Mungkin, bapakmu kurang setuju kalau kamu suka menari karena itu. Takut, kamu dianggap tidak punya masa depan. Padahal, sekarang kan banyak penari yang sukses, malah bisa pergi tampil ke luar negeri, ya, kan Ian?”\n\n" +
                "   Dian mengangguk. Dari buku yang dibacanya di perpustakaan dan penjelasan Pak Seno di kelas, ia tahu beberapa orang penari Indonesia yang terkenal hingga mancanegara seperti Didik Nini Thowok, Bagong Kussudiardjo, dan Sujana Arja.\n" +
                "   “Kalau ibu, ya, mendukung saja kalau kamu suka menari asal kamu yakin. Kamu yakin senang sekali menari?”\n" +
                "   Dian mengangguk. \n\n" +
                "   “Menari apa pun?” tanya ibu.\n\n" +
                "   Kali ini Dian tak menjawab. Yang ada di pikirannya saat ini hanya tari gandrung. Bahkan, tari remo yang diceritakan Pak Seno saat itu sama sekali tak muncul dalam benaknya.\n\n" +
                "   Ya, sudah, kamu yang paling tahu jawabannya. Cuma itu tadi pesan ibu. Kalau kamu yakin, ibu sama bapak juga ikut yakin sama kamu,” ucap ibu sambil pergi meninggalkan Dian yang masih sibuk mencari jawaban.\n\n" +
                "   Hari seleksi pun tiba. Dian ikut menemani teman-temannya untuk seleksi. Pak Seno serta kedua orang tua Marni dan Kinanti pun ikut. Dian yakin kalau kedua temannya itu akan lolos seleksi seperti tahun kemarin. Namun, seleksi ya tetap seleksi, semua peserta baik yang baru maupun yang sudah pernah tampil harus ikut seleksi.\n\n" +
                "   Seluruh peserta berbaris di lapang menunggu iringan dimainkan. Dian, sama seperti penonton lainnya, hanya dapat menyaksikan dari pinggir lapangan. Suara gending pun terdengar memerintah penari untuk mulai menari mengikuti iramanya. Tanpa sadar, tangan Dian ikut bergerak mengikuti alunan gending. Namun, ia berhasil menahan diri untuk tidak ikut menari. Ia tak mau jadi bahan tontonan orang-orang.\n\n" +
                "   Seperti yang Dian duga, dua temannya itu lolos. Kebahagiaan dan kebanggaan tak hanya terpancar dari wajah kedua temannya, tapi juga orang tua mereka dan Pak Seno. Rasa senang dan sedih bercampur aduk di hati Dian.\n\n" +
                "   Hari itu pun tiba, Dian memasukkan sampur merahnya ke dalam tas. Ia berangkat bersama Marni dan Kinanti ke Pantai Marina tempat Festival Gandrung Sewu diadakan. Pak Seno dan para siswi yang akan menarikan tari khas Banyuwangi itu sudah menunggu. Mereka lantas berganti pakaian dan berias. Beberapa siswi yang sudah berganti pakaian dan dirias wajahnya tampak berlatih gerakan memainkan kipas. Omprog dari kulit kerbau yang berwarna emas menghiasi kepala para penari menambah keanggunan mereka.\n\n" +
                "   Suara gamelan yang riang pun terdengar membahana di seluruh Pantai Marina, mengiringi tarian seribu penari gandrung. Dian mengeluarkan sampur merahnya dan menyampirkannya di bahu. Dian memejamkan matanya dan hanya ada suara gamelan di telinganya. Ia mulai menari. Hanya ada dirinya dan tari gandrung yang riang saat itu.\n\n" +
                "   Senyuman terukir di wajah Dian. Ia paham apa yang disampaikan Pak Seno dulu. Ia juga sudah menemukan jawaban pertanyaan ibunya. Kini, ia hanya ingin menari dan terus menari. Ia tak hanya ingin menarikan tari gandrung yang ia mainkan saat ini atau tari remo yang akan ditampilkan di festival remo di Surabaya nanti, tapi tarian lainnya dengan sepenuh hati, total, dan ikhlas.\n\n" +
                "(Ditulis oleh Fadillah T.A.)",
        //materi4
        "   a. Cara Penggunaan Stopwatch dari Telepon Genggam\n\n" +
                "   Tombol Start ditekan untuk memulai pengukuran waktu. Tombol Pause ditekan untuk menghentikannya. Tombol Reset ditekan untuk menghapus waktu yang sudah terukur.\n\n" +
                "   b. Cara Penggunaan Stopwatch Analog\n\n" +
                "   Ada yang punya dua tombol, yaitu pada bagian tengah dan bagian samping. Bagian tengah untuk memulai dan menghentikan stopwatch. Bagian samping adalah untuk mereset, mengembalikannya ke 0 setelah penggunaan. Jika satu tombol saja maka itu digunakan untuk memulai, menghentikan, dan reset. Stopwatch mempunyai skala dalam lingkaran besar dan skala dalam lingkaran kecil. Satu kali putaran pada lingkaran besar menunjukkan 1 menit. Skala terkecil dapat berbeda-beda. Lingkaran kecil menunjukkan 1 menit untuk 1 skala. Pada stopwatch ini pengukuran maksimal untuk skala menit adalah 30 menit.",
        //materi5
        "1.Apa makna skala 1: 32.000.000 pada peta? Jelaskan pendapatmu\n\n2.Dapatkah kamu menentukan jarak antara ibukota Indonesia dengan ibukota Malaysia menggunakan skala tersebut? jelaskan",
        //materi6
        "1.Apa saja kandungan kandungan gizi yang diperlukan tubuh serta sebutkan kelainan yang timbul akibat gizi salah!\n\n2.Susunlah menu apa saja yang mempunyai gizi yang baik dan termasuk 4 sehat 5 sempurna!")


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemJudul: TextView

        init {
            itemJudul = itemView.findViewById(R.id.rvJudulHome)

            itemView.setOnClickListener {
                val position: Int = getAdapterPosition()
                val context = itemView.context
                val intent = Intent(context, HomeDetailActivity::class.java).apply {
                    putExtra("NUMBER", position)
                    putExtra(HomeDetailActivity.JUDUL, judul[position])
                    putExtra(HomeDetailActivity.MATERI, materi[position])
                }
                context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_home, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemJudul.text = judul[i]
    }

    override fun getItemCount(): Int {
        return judul.size
    }
}