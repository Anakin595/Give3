package com.give3.gizrog.give3.models

import android.os.Parcel
import android.os.Parcelable

data class Section(var id: Int ,var title: String, var studentsNames: ArrayList<String>, var focus: Float): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.readFloat())

    constructor(id: Int ,studentsNames: ArrayList<String>) :
            this(id, "Section " + (id+1).toString(), studentsNames, 3f)

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeInt(id)
        p0?.writeString(title)
        p0?.writeStringList(studentsNames)
        p0?.writeFloat(focus)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Section> {
        override fun createFromParcel(parcel: Parcel): Section {
            return Section(parcel)
        }

        override fun newArray(size: Int): Array<Section?> {
            return arrayOfNulls(size)
        }

    }
}
