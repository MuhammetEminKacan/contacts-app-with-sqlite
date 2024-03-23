package com.emin.kacan.kisilerapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.emin.kacan.kisilerapp.DatabaseOperations.KisilerDao
import com.emin.kacan.kisilerapp.DatabaseOperations.VeritabaniOlusum
import com.emin.kacan.kisilerapp.R
import com.emin.kacan.kisilerapp.databinding.FragmentKisiKayitBinding
import com.google.android.material.snackbar.Snackbar

class KisiKayitFragment : Fragment() {
    private lateinit var binding: FragmentKisiKayitBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentKisiKayitBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarKisiKayit.title = "KİŞİ KAYIT"
        val vo = VeritabaniOlusum(requireContext())
        val kisilerDao = KisilerDao()

        binding.btnSave.setOnClickListener {
            val name = binding.editTextNamee.text.toString()
            val phone = binding.editTextPhonee.text.toString()
            kisilerDao.kisiEkle(vo,name,phone)
            Toast.makeText(requireContext(), "kayıt başarılı bir şekilde gerçekleşti", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.kisiKayittanDonus)
        }

    }




}