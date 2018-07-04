package com.give3.gizrog.give3.models

import android.os.Parcel
import android.os.Parcelable

class AssessmentSection(val title: String, val grade: Float, val tasks: ArrayList<Task>): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readFloat(),
            parcel.createTypedArrayList(Task.CREATOR))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(title)
        parcel.writeTypedList(tasks)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun countTasksCompleted(): Int {
        return tasks.count { it.complete }
    }

    companion object CREATOR : Parcelable.Creator<AssessmentSection> {
        override fun createFromParcel(parcel: Parcel): AssessmentSection {
            return AssessmentSection(parcel)
        }

        override fun newArray(size: Int): Array<AssessmentSection?> {
            return arrayOfNulls(size)
        }
    }
}