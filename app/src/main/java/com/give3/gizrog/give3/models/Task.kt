package com.give3.gizrog.give3.models

import android.os.Parcel
import android.os.Parcelable

data class Task(var title: String, var weight: Int, var isComplete: Boolean): Parcelable {

    constructor(title: String, weight: Int) : this(title, weight, false)

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt()==1 )

    fun copy(): Task {
        return Task(title, weight, isComplete)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(weight)
        parcel.writeInt(if(isComplete) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}
