package com.example.igrwijaya.myapplication
import com.google.firebase.database.Exclude



/**
 * Created by igrwijaya on 6/4/2017.
 */
class FirebaseUserAdapter {

    var uid : String? = null
    var name : String? = null

    fun Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    fun Post(id: String, nama : String) {
        this.uid = id
        this.name = nama
    }

    // [START post_to_map]
    @Exclude

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("uid", uid!!)
        result.put("nama", name!!)

        return result
    }
}