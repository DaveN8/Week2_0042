package com.example.week2_0706012110042

import Adaptor.Adapter
import Databes.GlobalVar
import Interface.CardListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2_0706012110042.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(), CardListener {

    private lateinit var viubain: ActivityMainBinding
    private val adapter = Adapter(GlobalVar.ListHewan, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viubain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viubain.root)
        supportActionBar?.hide()
        emboh()
        setapRV()
    }

    override fun onResume() {
        super.onResume()
        if (GlobalVar.ListHewan.size == 0 ){
            viubain.recyclerView.visibility = View.VISIBLE
            viubain.textView4.visibility = View.GONE
        }else{
            viubain.recyclerView.visibility = View.GONE
            viubain.textView4.visibility = View.VISIBLE
        }
        adapter.notifyDataSetChanged()
    }

    private fun emboh(){
        val myintent = Intent(this, FormActivity::class.java)
        startActivity(myintent)
    }

    private fun setapRV(){
        val layoutManager = GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false)
        viubain.recyclerView.layoutManager = layoutManager
        viubain.recyclerView.adapter = adapter
    }

    override fun OnEditClick(position: Int) {
        val myintent = Intent(this, FormActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myintent)
    }

    override fun OnDeleteClick(position: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Hapus Hewan")
            .setMessage("Apakah anda yakin ingin menghapus hewan?")
            .setNegativeButton("Batal")
        {dialog, which ->}
            .setPositiveButton("Setuju"){dialog, which ->
                GlobalVar.ListHewan.removeAt(position)
                Toast.makeText(baseContext, "Data berhasil terhapus", Toast.LENGTH_LONG).show()
                onResume()
            }
            .show()
    }

    override fun OnSoundClick(position: Int) {
        if (GlobalVar.ListHewan.get(position).jenis.equals("Ayam")){
            Toast.makeText(baseContext, "BOCK, BOCK, BOCK, BOOOOOOCK", Toast.LENGTH_LONG).show()
        }else if (GlobalVar.ListHewan.get(position).jenis.equals("Kambing")){
            Toast.makeText(baseContext, "Kamu Cantik deh hari ini :)", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(baseContext, "Aku suka gayamu hari ini :)", Toast.LENGTH_LONG).show()
        }
    }

    override fun OnFeedClick(position: Int) {
        if(GlobalVar.ListHewan.get(position).jenis.equals("Ayam")){
            Toast.makeText(baseContext, "Kamu memberi makan biji-bijian")
        }
    }


}