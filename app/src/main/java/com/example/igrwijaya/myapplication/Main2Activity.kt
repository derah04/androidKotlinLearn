package com.example.igrwijaya.myapplication

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.example.igrwijaya.myapplication.user.adapter.UserList
import java.net.URL
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener




class Main2Activity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val TAG = "SignInActivity"

    // Initialize Firebase Auth
    val mFirebaseAuth = FirebaseAuth.getInstance()
    val mFirebaseUser = mFirebaseAuth.currentUser
    var mGoogleApiClient: GoogleApiClient? = null
    var mUsername : TextView? = null
    var mPhoto : ImageView? = null
    var myList : RecyclerView? = null
    var loading : ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        loading = findViewById(R.id.progressBarView) as ProgressBar


        mUsername = findViewById(R.id.namaUser) as TextView
        mPhoto = findViewById(R.id.userPic) as ImageView

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            mFirebaseAuth.signOut()
            Auth.GoogleSignInApi.signOut(mGoogleApiClient)
            mUsername!!.text = "None"
        }

        myList = findViewById(R.id.dataList) as RecyclerView
        myList!!.setHasFixedSize(true)

        myList!!.layoutManager = LinearLayoutManager(this)

        this.loginStatus()
        this.userList()

    }

    fun firebaseLoad(userId : String, name : String){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("users")

        //nyimpen k firebase
        myRef.child(userId).child("name").setValue(name)
    }

    fun loginStatus(){

        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        } else {
            mUsername!!.text = mFirebaseUser.displayName
            if (mFirebaseUser.photoUrl != null) {
//                val url = URL(mFirebaseUser.photoUrl.toString())
//                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//                mPhoto!!.setImageBitmap(bmp)
            }
            this.firebaseLoad(mFirebaseUser.uid,mFirebaseUser.displayName.toString())
        }
    }

    private fun userList() {

        FirebaseDatabase.getInstance().getReference("listing")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        val data = ArrayList<String>()

                        for (dataSnapshots in dataSnapshot.children) {
                            val list = dataSnapshots.getValue(FirebaseUserAdapter::class.java)
                            Log.d("User",list.name.toString())
                            val user = UserList()
                            user.userList(list.name.toString(),list.desc.toString())

                            data.add(list.name.toString())
                        }

                        myList!!.adapter = MyRecyclerViewAdapter()
                        loading?.visibility = View.GONE

                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })
    }

}
