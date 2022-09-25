package com.example.week2_0706012110042

import Databes.GlobalVar
import Model.Ayam
import Model.Hewan
import Model.Kambing
import Model.Sapi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week2_0706012110042.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {

    private lateinit var viubain: ActivityFormBinding
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        viubain = ActivityFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viubain.root)
        GetIntent()
        setuplistener()
    }

    private fun GetIntent(){
        if (intent.getIntExtra("status", 0) == GlobalVar.statusAdd){
            viubain.toolbar2.title = "Tambah Hewan"
        }else if (intent.getIntExtra("status", 0) == GlobalVar.StatusEdit){
            position = intent.getIntExtra("position",-1)
            if (position != -1){
                viubain.toolbar2.title = "Edit Hewan"
                viubain.textInputLayout.editText?.setText(GlobalVar.ListHewan[position].nama)
                viubain.textInputLayout2.editText?.setText(GlobalVar.ListHewan[position].usia.toString())
                if (GlobalVar.ListHewan[position] is Ayam){
                    viubain.radioButton3.isChecked = true
                }else if (GlobalVar.ListHewan[position] is Kambing){
                    viubain.radioButton2.isChecked = true
                }else{
                    viubain.radioButton.isChecked = true
                }
            }
        }

    }

    private fun setuplistener(){
        viubain.toolbar2.setOnClickListener(){
            finish()
        }
        viubain.button3.setOnClickListener(){
try {
    if (viubain.radioButton3.isChecked) {
        val hewan = Ayam(
            viubain.textInputLayout.editText?.text.toString().trim(),
            viubain.radioButton3.text.toString().trim(),
            viubain.textInputLayout2.editText?.text.toString().trim().toInt()
        )
        if (checker(hewan)) {
            if (intent.getIntExtra("status", 0) == GlobalVar.statusAdd) {
                GlobalVar.ListHewan.add(hewan)
                Toast.makeText(baseContext, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                finish()
            } else if (intent.getIntExtra("status", 0) == GlobalVar.StatusEdit) {
                GlobalVar.ListHewan[position] = hewan
                Toast.makeText(baseContext, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    } else if (viubain.radioButton2.isChecked) {
        val hewan = Kambing(
            viubain.textInputLayout.editText?.text.toString().trim(),
            viubain.radioButton2.text.toString().trim(),
            viubain.textInputLayout2.editText?.text.toString().trim().toInt()
        )
        if (checker(hewan)) {
            if (intent.getIntExtra("status", 0) == GlobalVar.statusAdd) {
                GlobalVar.ListHewan.add(hewan)
                Toast.makeText(baseContext, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                finish()
            } else if (intent.getIntExtra("status", 0) == GlobalVar.StatusEdit) {
                GlobalVar.ListHewan[position] = hewan
                Toast.makeText(baseContext, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    } else if (viubain.radioButton.isChecked){
        val hewan = Sapi(
            viubain.textInputLayout.editText?.text.toString().trim(),
            viubain.radioButton.text.toString().trim(),
            viubain.textInputLayout2.editText?.text.toString().trim().toInt()
        )
        if (checker(hewan)) {
            if (intent.getIntExtra("status", 0) == GlobalVar.statusAdd) {
                GlobalVar.ListHewan.add(hewan)
                Toast.makeText(baseContext, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                finish()
            } else if (intent.getIntExtra("status", 0) == GlobalVar.StatusEdit) {
                GlobalVar.ListHewan[position] = hewan
                Toast.makeText(baseContext, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }else{
        viubain.radioButton.error = "Jenis masih kosong"
        viubain.radioButton2.error = "Jenis masih kosong"
        viubain.radioButton3.error = "Jenis masih kosong"
    }
} catch (e: NumberFormatException){
    viubain.textInputLayout2.error = "Usia belum diisi"
}


        }
    }

    private fun checker(hewan: Hewan): Boolean {
        var test = true

        if (hewan.nama.isEmpty()){
            viubain.textInputLayout.error = "Nama masih kosong"
            test = false
        }else{
            viubain.textInputLayout.error = ""
        }

        if (hewan.usia == 0){
            viubain.textInputLayout2.error = "Usia masih kosong"
            test = false
        }else{
            viubain.textInputLayout2.error = ""
        }

        if (hewan.jenis.isEmpty()){
            viubain.radioButton.error = "Jenis masih kosong"
            viubain.radioButton2.error = "Jenis masih kosong"
            viubain.radioButton3.error = "Jenis masih kosong"
            Toast.makeText(baseContext,"Pilih Kategori Hewan", Toast.LENGTH_LONG).show()
            test = false
        }else{
            viubain.radioButton.error = ""
            viubain.radioButton2.error = ""
            viubain.radioButton3.error = ""
        }

        return test
    }

}