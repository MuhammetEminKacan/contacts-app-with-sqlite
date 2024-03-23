package com.emin.kacan.kisilerapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.emin.kacan.kisilerapp.DatabaseOperations.Kisiler
import com.emin.kacan.kisilerapp.DatabaseOperations.KisilerDao
import com.emin.kacan.kisilerapp.DatabaseOperations.VeritabaniOlusum
import com.emin.kacan.kisilerapp.R
import com.emin.kacan.kisilerapp.adapter.KisilerAdapter
import com.emin.kacan.kisilerapp.databinding.FragmentAnasayfaBinding
import java.util.Locale

class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
     private val kisilerListesi = ArrayList<Kisiler>()
    private lateinit var adapter: KisilerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAnasayfaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarAnasayfa.title = "ANA SAYFA"
        val vo = VeritabaniOlusum(requireContext())
        val kisilerDao = KisilerDao().kisileriOku(vo)

        binding.fab.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.kisiKayitGecis)
        }
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    ara(newText)
                }
                return true
            }
        })


        binding.rv.layoutManager = LinearLayoutManager(requireContext())


        for (i in kisilerDao ){
            kisilerListesi.add(i)
        }
        adapter = KisilerAdapter(requireContext(),kisilerListesi)
        binding.rv.adapter = adapter


    }

    private fun ara(aramaKelimesi:String){
        if (aramaKelimesi != null){
            val filtreliListe = ArrayList<Kisiler>()
            for (i in kisilerListesi){
                if (i.kisi_ad.lowercase(Locale.ROOT).contains(aramaKelimesi)){
                    filtreliListe.add(i)
                }
            }
        if (filtreliListe.isEmpty()){

        }else{
            adapter.setFilteredList(filtreliListe)
        }
        }
    }








}