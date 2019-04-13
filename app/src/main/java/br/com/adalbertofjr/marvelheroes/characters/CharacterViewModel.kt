package br.com.adalbertofjr.marvelheroes.characters

import android.os.Parcel
import android.os.Parcelable

data class CharacterViewModel(
    var name: String = "",
    var description: String = "",
    var thumbnail: String = "",
    var thumbnailLandscape: String = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun toString(): String {
        return name
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(thumbnail)
        parcel.writeString(thumbnailLandscape)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterViewModel> {
        override fun createFromParcel(parcel: Parcel): CharacterViewModel {
            return CharacterViewModel(parcel)
        }

        override fun newArray(size: Int): Array<CharacterViewModel?> {
            return arrayOfNulls(size)
        }
    }

}