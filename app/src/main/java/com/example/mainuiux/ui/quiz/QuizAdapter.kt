package com.example.mainuiux.ui.quiz

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mainuiux.R

class QuizAdapter : RecyclerView.Adapter<QuizAdapter.ViewHolder>() {
    private val judul = arrayListOf("Kuis Literasi VII : Berbagi Kisah, Bertukar Pesan: Inspirasi",
            "Kuis Literasi VIII : Persuasi dalam Kata dan Gambar",
        "Kuis Literasi IX :  Dunia Kita dalam Cerita","Kuis Penguatan Numerasi untuk Ilmu Pengetahuan Alam","Kuis Penguatan Numerasi untuk Ilmu Pengetahuan Sosial","Kuis Penguatan Numerasi untuk Mata Pelajaran PJOK")

    private val soal = arrayOf("1.Sebutkan unsur kebahasaan surat pribadi\n\n2.Sebutkan dua hal yang membedakan surat pribadi dan surat dinas\n\n" +
            "3.Surat pribadi boleh menggunakan kosakata yang tidak baku. (Setuju/Tidak Setuju)\n\n" +

            "4.Menurut pendapat kalian, apa guna kop pada bagian atas surat dinas?\n\n5.Jika kalian adalah seorang kepala sekolah dan ingin mengajak para guru menghadiri acara makan bersama, jenis surat apa yang kalian pilih? ",
        "1.Apasaja hal yang harus terdapat dalam sebuah iklan?\n\n"+"2.Untuk membuat iklan lebih menarik dan mudah dipahami, pembuat iklan dapat menggunakan bahasa daerah. setujukah kalian dengan pendapat ini? sebutkan alasannya!\n\n"
                + "3.Sebutkan Ciri ciri bahasa iklan yang baik dan benar!",

        "1.Perhatikan penggalan cerita berikut.\n\n"+"Wajah Ranti yang biasanya cerah merona tiba-tiba berubah pucat. Matanya menatap tajam ke arahku. “Cepat hapus!” \n"
                 + "Aku masih belum bereaksi karena kaget. Kenapa ia jadi berubah dan bereaksi seperti itu.\n" +
                "“Kumohon hapus, cukup kamu saja yang tahu,” kini wajahnya memelas. \n" + "“Tapi ....“  “Nanti aku jelaskan, janji. Hapus dulu fotonya, kumohon,” pintanya kembali. Aku langsung menghapus foto itu.\n\n"
                + "Menurut kalian, perasaan apa yang dirasakan oleh Ranti saat itu?\n\n"

                + "2.Perhatikan penggalan cerita berikut\n\n" + "“Ini untukmu,” suara lembut Ranti menyadarkanku yang hampir saja terpejam. Jam kosong seperti siang ini benar-benar waktu yang tepat untuk menikmati hari dengan tidur siang." +
                "“Ini,” ucap Ranti sambil menyerahkan sebuah bungkusan berwarna biru. “Sedikit cokelat dari Ayahku yang pulang dari Malaysia,” jelasnya. Aku menerima cokelat itu sambil berkata, “Terima kasih.”\n\n"
                + "Menurut kalian, sifat Ranti apakah yang ingin disampaikan pengarang pada kutipan cerpen tersebut? ",
    "1.Apa penyumbang terbesar emisi gas rumah kaca dalam sektor ekonomi?\n\n 2.Apa penyumbang emisi terkecil emisi gas rumah kaca dalam sektor ekonomi?",
        "1.Apa makna skala 1: 32.000.000 pada peta? Jelaskan pendapatmu\n\n2.Dapatkah kamu menentukan jarak antara ibukota Indonesia dengan ibukota Malaysia menggunakan skala tersebut? jelaskan",
        "1.Apa saja kandungan kandungan gizi yang diperlukan tubuh serta sebutkan kelainan yang timbul akibat gizi salah!\n\n2.Susunlah menu apa saja yang mempunyai gizi yang baik dan termasuk 4 sehat 5 sempurna!")


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemJudul: TextView

        init {
            itemJudul = itemView.findViewById(R.id.rvJudulKuis)

            itemView.setOnClickListener {
                var position: Int = getAdapterPosition()
                val context = itemView.context
                val intent = Intent(context, QuizDetailActivity::class.java).apply {
                   putExtra("NUMBER", position)
                    putExtra(QuizDetailActivity.JUDUL, judul[position])
                    putExtra(QuizDetailActivity.SOAL, soal[position])
                }
                context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_quiz, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemJudul.text = judul[i]
    }

    override fun getItemCount(): Int {
        return judul.size
    }
}