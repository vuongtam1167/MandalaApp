package com.example.mandala.Dataclass

data class Mandala(
    val IsChidren: Boolean,
    val bangCha:MandalaChild,
    val tenBang: String,
    val soBang: Int,
    val capLop: String,
    val diaChis: List<MandalaDiaChi>
)
