package Databes

import Model.Hewan

class GlobalVar {
    companion object{
        val ListHewan: MutableList<Hewan> = ArrayList()
        val statusAdd = 1
        val StatusEdit = 2
    }
}