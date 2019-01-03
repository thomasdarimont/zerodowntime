package com.exoscale.boot.zerodowntime

import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
class Person(@Id @GeneratedValue(strategy = IDENTITY) var id: Long = -1,
             val name: String = "") {

    @OneToOne(mappedBy = "person", cascade = [CascadeType.ALL])
    @JoinColumn(name = "id")
    val address: Address = Address()

    init {
        address.person = this
    }

    val addressLine1: String
        get() = address.addressLine1
    val addressLine2: String?
        get() = address.addressLine2
    val city: String
        get() = address.city
    val zipCode: String
        get() = address.zipCode
}

@Entity
class Address(@Id @GeneratedValue(strategy = IDENTITY) var id: Long? = null,
              var addressLine1: String = "",
              var addressLine2: String? = null,
              var city: String = "",
              var zipCode: String = "") {
    @OneToOne
    @JoinColumn(name = "person_id")
    lateinit var person: Person
}