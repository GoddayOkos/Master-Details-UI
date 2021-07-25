package dev.decagon.godday.animation.data

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import dev.decagon.godday.animation.R
import dev.decagon.godday.animation.model.Contacts

object DataSource {

    private val contacts: List<Contacts> = listOf(
        Contacts(R.drawable.messi, "Lionel Messi", "(650) 555-1367"),
        Contacts(R.drawable.cristiano, "Cristiano Ronaldo", "(650) 512-8369"),
        Contacts(R.drawable.dybala, "Paulo Dybala", "(650) 587-3542"),
        Contacts(R.drawable.neymar, "Neymar Junior", "(650) 555-4444"),
        Contacts(R.drawable.lewand, "Robert Lewandoski", "(650) 586-1399"),
        Contacts(R.drawable.image1, "John Smith", "(650) 543-2640"),
        Contacts(R.drawable.image2, "Bella Cruz", "(650) 555-8080"),
        Contacts(R.drawable.image3, "Saint Cray", "(650) 514-8620"),
        Contacts(R.drawable.image4, "Ann Will", "(650) 555-4378"),
        Contacts(R.drawable.image5, "Angelina Smith", "(650) 545-0040"),
        Contacts(R.drawable.image6, "Alita Snow", "(650) 112-0010"),
        Contacts(R.drawable.image7, "Angel White", "(650) 121-0003"),
        Contacts(R.drawable.image8, "Jimmy Smooth", "(650) 233-0022"),
        Contacts(R.drawable.image9, "Snow White", "(650) 578-0000"),
        Contacts(R.drawable.image10, "Becky Lynch", "(650) 515-1110")
    )

    fun loadContacts(): List<Contacts> {
        return contacts
    }

    // Given an imageResourceId, this method returns the contact object whose imageResourceId matches
    // with the given imageResourceId
    fun captureContactByImageId(imageResourceId: Int): Contacts {
        lateinit var obj: Contacts
        for (item in contacts) {
          if (item.imageResourceId == imageResourceId) obj = item
       }
        return obj
    }
}