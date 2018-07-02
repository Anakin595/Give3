package com.give3.gizrog.give3.models

data class AppData(var sections: ArrayList<Section>, var tasks: MutableList<Task>) {

    fun isComplete(): Boolean {
        return (!sections.isEmpty() && !tasks.isEmpty())
    }
}