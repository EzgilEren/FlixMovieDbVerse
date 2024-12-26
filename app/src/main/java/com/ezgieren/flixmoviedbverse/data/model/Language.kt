package com.ezgieren.flixmoviedbverse.data.model

data class Language(
    val iso_639_1: String, // API'nin döndürdüğü dil kodu
    val englishName: String, // Dilin İngilizce adı
    val name: String // Dilin orijinal adı
)