package com.example.igrwijaya.myapplication
import com.google.firebase.database.Exclude



/**
 * Created by igrwijaya on 6/4/2017.
 */
class FirebaseUserAdapter {

    var desc : String? = null
    var name : String? = null

    fun Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    fun Post(desc: String, nama : String) {
        this.desc = desc
        this.name = nama
    }

    // [START post_to_map]
    @Exclude

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("desc", desc!!)
        result.put("nama", name!!)

        return result
    }
}