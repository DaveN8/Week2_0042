package Adaptor

import Interface.CardListener
import Model.Hewan
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.week2_0706012110042.R
import com.example.week2_0706012110042.databinding.CardHewanBinding

class Adapter (val ListData: MutableList<Hewan>, val cardlistener: CardListener):
    RecyclerView.Adapter<Adapter.viewholder>()
{
    class viewholder(itemview: View, val cardlistener: CardListener): RecyclerView.ViewHolder(itemview) {

        val viubain = CardHewanBinding.bind(itemView)

        fun setData(hewan: Hewan, position: Int){
            viubain.textView.text = hewan.nama
            viubain.textView2.text = hewan.jenis
            viubain.textView3.text = hewan.usia.toString()+" Tahun"
            viubain.button.setOnClickListener(){
                cardlistener.OnEditClick(position)
            }
            viubain.button2.setOnClickListener(){
                cardlistener.OnDeleteClick(position)
            }
            viubain.imageButton.setOnClickListener(){
                cardlistener.OnSoundClick(position)
            }
            viubain.imageButton2.setOnClickListener(){
                cardlistener.OnFeedClick(position)
            }
            if(hewan.foto.isNotEmpty()){
                viubain.imageView2.setImageURI(Uri.parse(hewan.foto))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_hewan, parent, false)
        return viewholder(view, cardlistener)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.setData(ListData[position], position)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}