package com.example.beginningkotlin.ui.chat

import androidx.lifecycle.MutableLiveData
import com.example.beginningkotlin.data.repository.ChatRepository
import com.example.beginningkotlin.data.response_model.FriendlyMessage
import com.example.beginningkotlin.ui.base.BaseViewModel
import com.example.beginningkotlin.ui.popular_movies.data.repository.MovieDetailsRepository
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*

class ChatViewModel(repository: ChatRepository) :
    BaseViewModel<ChatRepository>(
        repository
    ) {
    var messageAdded = MutableLiveData<FriendlyMessage>()
    var message = MutableLiveData<String>()
    var userAuthStateChanged = MutableLiveData<FirebaseUser>()
    private  var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private  var messagesDatabaseReference: DatabaseReference = firebaseDatabase.getReference().child("messages")
    private  var childEventListener = object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            messageAdded.value = snapshot.getValue(FriendlyMessage::class.java)
        }
        override fun onCancelled(error: DatabaseError) {}
        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
        override fun onChildRemoved(snapshot: DataSnapshot) {}
    }
    private  var  fireBaseAuth = FirebaseAuth.getInstance()
    private  var authStateListener = FirebaseAuth.AuthStateListener { p0 ->
        userAuthStateChanged.value  = p0.getCurrentUser()
    }
    fun attachChildEventListener() {
        messagesDatabaseReference.addChildEventListener(childEventListener)
    }
    fun deattachChildEventListener() {
        messagesDatabaseReference.removeEventListener(childEventListener)

    }
    fun attachAuthEventListener() {
        fireBaseAuth.addAuthStateListener(authStateListener)
    }
    fun deattachAuthListener() {
        fireBaseAuth.removeAuthStateListener(authStateListener)

    }
    fun sendMessage(userName : String) {
        val friendlyMessage : FriendlyMessage = FriendlyMessage(message.value , userName , null)
        messagesDatabaseReference.push().setValue(friendlyMessage)
    }

}