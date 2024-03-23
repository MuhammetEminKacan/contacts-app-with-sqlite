package com.emin.kacan.kisilerapp.DatabaseOperations

import java.io.Serializable

data class Kisiler(var kisi_no:Int
                   ,var kisi_ad:String
                   ,var kisi_tel:String) : Serializable {
}