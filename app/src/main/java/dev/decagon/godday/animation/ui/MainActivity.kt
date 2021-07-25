package dev.decagon.godday.animation.ui

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair.create
import androidx.recyclerview.widget.RecyclerView
import dev.decagon.godday.animation.R
import dev.decagon.godday.animation.adapter.ItemAdapter
import dev.decagon.godday.animation.data.DataSource
import dev.decagon.godday.animation.model.Contacts

class MainActivity : AppCompatActivity(), ItemAdapter.ItemAdapterListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDataSet = DataSource.loadContacts()
        val recyclerView = findViewById<RecyclerView>(R.id.recylerView)
        recyclerView.adapter = ItemAdapter(myDataSet, this)
        recyclerView.setHasFixedSize(true)

    }

    override fun onContactSelected(view: View, contact: Contacts) {
        val profileImage = view.findViewById<CardView>(R.id.cardView)
        val fullName = view.findViewById<TextView>(R.id.fullName)
        val imagePair = create(profileImage as View, "card")
        val namePair = create(fullName as View, "name")
        val options = ActivityOptionsCompat
            .makeSceneTransitionAnimation(this, imagePair, namePair)
        ActivityCompat.startActivity(
            this,
            ProfileActivity.newIntent(this, contact.imageResourceId),
            options.toBundle()
        )
    }

}