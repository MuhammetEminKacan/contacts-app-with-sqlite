package com.emin.kacan.kisilerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.emin.kacan.kisilerapp.DatabaseOperations.Kisiler
import com.emin.kacan.kisilerapp.Fragments.AnasayfaFragmentDirections
import com.emin.kacan.kisilerapp.databinding.CardTasarimBinding

class KisilerAdapter(var mContext: Context , var kisilerListesi:List<Kisiler>) : RecyclerView.Adapter<KisilerAdapter.cardTasarimTutucu>() {

    inner class cardTasarimTutucu(var binding:CardTasarimBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardTasarimTutucu {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return cardTasarimTutucu(binding)
    }

    fun setFilteredList(kisilerListesi: List<Kisiler>){
        this.kisilerListesi = kisilerListesi
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: cardTasarimTutucu, position: Int) {
        val kisi =kisilerListesi.get(position)
        val t =holder.binding

        t.txtName.text = kisi.kisi_ad
        t.txtPhone.text = kisi.kisi_tel

        t.cardViewSatir.setOnClickListener {
            val gecis =AnasayfaFragmentDirections.kisiDuzenlemeGecis(kisi = kisi)
            Navigation.findNavController(it).navigate(gecis)
        }

    }


    override fun getItemCount(): Int {
        return  kisilerListesi.size
    }
}