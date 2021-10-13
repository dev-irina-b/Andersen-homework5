package android.example.homework5

import kotlin.random.Random

object
Database {
    lateinit var contacts: MutableList<Contact>

    fun init() {
        contacts = mutableListOf()

        val firstNames = listOf("John", "Max")
        val lastNames = listOf("Fry", "Smith")
        val phoneNumbers = LongArray(4) { Random.nextLong(89000000000, 89999999999) }.asList()

        val names = firstNames.flatMap { firstName ->
            lastNames.map { lastName ->
                firstName to lastName
            }
        }
        names.forEach {
            contacts.add(Contact(it.first, it.second, ""))
        }

        contacts.forEachIndexed { index, contact ->
            contact.phoneNumber = phoneNumbers[index].toString()
        }
    }
}