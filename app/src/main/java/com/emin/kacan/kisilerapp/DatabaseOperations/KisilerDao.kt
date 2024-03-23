package com.emin.kacan.kisilerapp.DatabaseOperations

import android.annotation.SuppressLint
import android.content.ContentValues

class KisilerDao {
    fun kisiEkle(vo:VeritabaniOlusum,kisi_ad:String,kisi_tel:String){
        val db = vo.writableDatabase
        val values = ContentValues()

        values.put("kisi_ad",kisi_ad)
        values.put("kisi_tel",kisi_tel)
        db.insertOrThrow("kisiler",null,values)
        db.close()
    }

    @SuppressLint("Range")
    fun kisileriOku(vo: VeritabaniOlusum) : ArrayList<Kisiler> {
      val kisilerListesi = ArrayList<Kisiler>()
      val db = vo.writableDatabase
      val cursor =db.rawQuery("SELECT * FROM kisiler",null)

      while(cursor.moveToNext()){
          val kisi =Kisiler(cursor.getInt(cursor.getColumnIndex("kisi_no"))
                            ,cursor.getString(cursor.getColumnIndex("kisi_ad"))
                            ,cursor.getString(cursor.getColumnIndex("kisi_tel")))
          kisilerListesi.add(kisi)
      }
    return kisilerListesi
    }

    fun kisiSil(vo:VeritabaniOlusum,kisi_no:Int){
        val db = vo.writableDatabase
        db.delete("kisiler","kisi_no=?", arrayOf(kisi_no.toString()))
        db.close()
    }

    fun kisiGuncelle(vo: VeritabaniOlusum,kisi_no: Int,kisi_ad: String,kisi_tel: String){
        val db = vo.writableDatabase
        val values = ContentValues()

        values.put("kisi_no",kisi_no)
        values.put("kisi_ad",kisi_ad)
        values.put("kisi_tel",kisi_tel)

        db.update("kisiler",values,"kisi_no=?", arrayOf(kisi_no.toString()))
        db.close()

    }

}