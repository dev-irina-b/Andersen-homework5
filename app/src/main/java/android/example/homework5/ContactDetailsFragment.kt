package android.example.homework5

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.lang.IllegalArgumentException

class ContactDetailsFragment : Fragment(R.layout.fragment_contact_details) {

    companion object {
        const val CONTACT_EXTRA = "CONTACT_EXTRA"

        fun newInstance(contact: Contact) = ContactDetailsFragment().also {
            it.arguments = Bundle().apply {
                putParcelable(CONTACT_EXTRA, contact)
            }
        }
    }

    private lateinit var buttonClickListener: ButtonClickListener

    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextPhoneNumber: EditText
    private lateinit var buttonSave: Button

    private lateinit var contact: Contact

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ButtonClickListener) {
            buttonClickListener = context
        } else {
            throw IllegalArgumentException("host activity must implement ButtonClickListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextFirstName = view.findViewById(R.id.editTextFirstName)
        editTextLastName = view.findViewById(R.id.editTextLastName)
        editTextPhoneNumber = view.findViewById(R.id.editTextPhoneNumber)
        buttonSave = view.findViewById(R.id.buttonSave)

        contact = requireArguments().getParcelable(CONTACT_EXTRA)!!

        editTextFirstName.setText(contact.firstName)
        editTextLastName.setText(contact.lastName)
        editTextPhoneNumber.setText(contact.phoneNumber)

        setUpViews()
    }

    private fun setUpViews() {
        buttonSave.setOnClickListener {
            val index = Database.contacts.indexOf(contact)
            val databaseContact = Database.contacts[index]

            databaseContact.firstName = editTextFirstName.text.toString()
            databaseContact.lastName = editTextLastName.text.toString()
            databaseContact.phoneNumber = editTextPhoneNumber.text.toString()

            buttonClickListener.onButtonClicked()
        }
    }

    interface ButtonClickListener {
        fun onButtonClicked()
    }
}