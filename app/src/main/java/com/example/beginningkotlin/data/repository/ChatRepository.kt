package com.example.beginningkotlin.data.repository

import com.example.beginningkotlin.data.repository.base.BaseRepository
import com.example.beginningkotlin.data.response_model.FriendlyMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ChatRepository: BaseRepository() {
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var fireBaseAuth : FirebaseAuth
    private lateinit var messagesDatabaseReference: DatabaseReference

    fun initFireBase() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        fireBaseAuth = FirebaseAuth.getInstance()
        messagesDatabaseReference = firebaseDatabase.getReference().child("messages")
    }
    fun attachAuthListener(listener : FirebaseAuth.AuthStateListener) {
        fireBaseAuth.addAuthStateListener(listener)
    }
    fun removeAuthListener(listener : FirebaseAuth.AuthStateListener) {
        fireBaseAuth.removeAuthStateListener(listener)
    }
    fun attachDatabaseListener(listener : ChildEventListener) {
        messagesDatabaseReference.addChildEventListener(listener)
    }
    fun removeDatabaseListener(listener : ChildEventListener) {
        messagesDatabaseReference.removeEventListener(listener)
    }

    fun postMessage(friendlyMessage: FriendlyMessage) {
        messagesDatabaseReference.push().setValue(friendlyMessage)
    }
}