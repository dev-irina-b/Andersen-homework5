package android.example.homework5

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import java.lang.IllegalArgumentException

class ContactsFragment : Fragment(R.layout.fragment_contacts) {

    companion object {
        fun newInstance() = ContactsFragment()
    }

    private lateinit var textViewClickListener: TextViewClickListener

    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView

    private lateinit var textViews: List<TextView>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is TextViewClickListener) {
            textViewClickListener = context
        } else {
            throw IllegalArgumentException("host activity must implement TextViewClickListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Database.init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView1 = view.findViewById(R.id.textView1)
        textView2 = view.findViewById(R.id.textView2)
        textView3 = view.findViewById(R.id.textView3)
        textView4 = view.findViewById(R.id.textView4)

        textViews = listOf(textView1, textView2, textView3, textView4)

        setUpViews()

        updateData()
    }

    fun updateData() {
        textViews.forEachIndexed { index, textView ->
            val contact = Database.contacts[index]
            textView.text = getString(R.string.contact, contact.firstName, contact.lastName)
        }
    }

    private fun setUpViews() {
        textViews.forEach { textView ->
            textView.setOnClickListener {
                textViewClickListener.onTextClicked(Database.contacts[textViews.indexOf(it)])
            }
        }
    }

    interface TextViewClickListener {
        fun onTextClicked(contact: Contact)
    }
}