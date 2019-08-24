package br.com.fiap.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(val event: String = "", val homeTeam: String = "", val awayTeam: String = "") : Parcelable