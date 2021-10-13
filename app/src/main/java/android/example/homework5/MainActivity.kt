package android.example.homework5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), ContactsFragment.TextViewClickListener,
    ContactDetailsFragment.ButtonClickListener {

    private lateinit var contactsFragment: ContactsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().run {
            contactsFragment = ContactsFragment.newInstance()

            replace(R.id.frameLayout, contactsFragment)
            commit()
        }
    }

    override fun onTextClicked(contact: Contact) {
        supportFragmentManager.beginTransaction().run {
            Bundle().putParcelable(ContactDetailsFragment.CONTACT_EXTRA, contact)
            val myFragment = ContactDetailsFragment.newInstance(contact)

            val tabletSize = resources.getBoolean(R.bool.isTablet)

            if (tabletSize) {
                replace(R.id.secondContainer, myFragment, "Tablet")
            } else {
                replace(R.id.frameLayout, myFragment)
                addToBackStack(null)
            }
            commit()
        }
    }

    override fun onButtonClicked() {
        supportFragmentManager.popBackStack()
        contactsFragment.updateData()
    }
}