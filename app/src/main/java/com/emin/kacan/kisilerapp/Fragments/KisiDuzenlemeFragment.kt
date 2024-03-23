package com.emin.kacan.kisilerapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.emin.kacan.kisilerapp.DatabaseOperations.KisilerDao
import com.emin.kacan.kisilerapp.DatabaseOperations.VeritabaniOlusum
import com.emin.kacan.kisilerapp.R
import com.emin.kacan.kisilerapp.databinding.FragmentKisiDuzenlemeBinding

class KisiDuzenlemeFragment : Fragment() {
private lateinit var binding: FragmentKisiDuzenlemeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentKisiDuzenlemeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarKisiDuzenleme.title = "KİŞİ DETAY"
        val vo = VeritabaniOlusum(requireContext())
        val kisilerDao =KisilerDao()
        val bundle : KisiDuzenlemeFragmentArgs by navArgs()
        val gelenKisi = bundle.kisi
        val kisi_id = gelenKisi.kisi_no
        binding.editTextName.setText(gelenKisi.kisi_ad)
        binding.editTextPhone.setText(gelenKisi.kisi_tel)
        binding.btnEdit.setOnClickListener {
            val kisi_ad = binding.editTextName.text.toString()
            val kisi_tel = binding.editTextPhone.text.toString()
            kisilerDao.kisiGuncelle(vo,kisi_id,kisi_ad,kisi_tel)
            Toast.makeText(requireContext(), "düzenleme işlemi başarılı", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.kisiDuzenlemedenDonus)
        }
        binding.btnDelete.setOnClickListener {
            kisilerDao.kisiSil(vo,kisi_id)
            Toast.makeText(requireContext(), "silme işlemi başarılı", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.kisiDuzenlemedenDonus)
        }



    }



}