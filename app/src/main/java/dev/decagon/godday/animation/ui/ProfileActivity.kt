package dev.decagon.godday.animation.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Interpolator
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.transition.ChangeBounds
import android.transition.Transition
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dev.decagon.godday.animation.R
import dev.decagon.godday.animation.data.DataSource
import dev.decagon.godday.animation.model.Contacts

class ProfileActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var details: Contacts
    private lateinit var call: ImageButton
    private lateinit var phone: TextView

    companion object {
        private const val REQUEST_CALL = 1
        private const val ITEM_IMAGE_RESOURCE_ID = "ItemImageResourceId"

        fun newIntent(context: Context, imageResourceId: Int): Intent {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra(ITEM_IMAGE_RESOURCE_ID, imageResourceId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        window.statusBarColor = ContextCompat.getColor(this, R.color.ash)

        backButton = findViewById(R.id.backArrow)
        backButton.setOnClickListener { supportFinishAfterTransition() }
        call = findViewById(R.id.imageButton)
        phone = findViewById(R.id.phoneNumber)

        setupDetails()
        setupViews()
        call.setOnClickListener { makeCall() }

        window.sharedElementEnterTransition = enterTransition()
        window.sharedElementExitTransition = exitTransition()
    }

    private fun setupDetails() {
        val captureDetails = DataSource.captureContactByImageId(intent.getIntExtra(
            ITEM_IMAGE_RESOURCE_ID, 0))
            details = captureDetails
    }

    private fun setupViews() {
        findViewById<ImageView>(R.id.profileImage).setImageResource(details.imageResourceId)
        findViewById<TextView>(R.id.fullName).text = details.fullName
        findViewById<TextView>(R.id.phoneNumber).text = details.PhoneNumber
        findViewById<TextView>(R.id.email).text = details.email
    }

    private fun enterTransition(): Transition = ChangeBounds().apply { duration = 1000 }

    private fun exitTransition(): Transition {
        return ChangeBounds().apply {
            interpolator = DecelerateInterpolator()
            duration = 1000
        }
    }

    private fun makeCall() {
        val number = Uri.parse("tel: ${phone.text}")
        val intent = Intent(Intent.ACTION_CALL, number)
        if (ContextCompat.checkSelfPermission(this,
            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL)
        }
        if (ContextCompat.checkSelfPermission(this,
            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent)
        }
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        if (requestCode == REQUEST_CALL) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                makeCall()
//            }
//        }
//    }
}