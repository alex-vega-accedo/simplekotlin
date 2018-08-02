package com.simple.kotlin.simplekotlin.adapters

data class User(var name : String = "", var age : Int = 0){

    fun calculate() : Int {
        return age * age
    }
}

class a {

    fun jaja() {
        val jane = User("Jane", 35)
        val (name, age) = jane
        println("$name, $age years of age") // prints "Jane, 35 years of age"
        testingLambdas(User())
    }

    fun testingLambdas(user : User) {
        val product = { a: Int, b: Int -> {
                var c = a * b
                val (name, age) = user
                c = user.calculate() * age
                c
            }
        }

        val result = product(9, 3)
        println(result)
    }

    data class Person(val name: String, val age: Int)

    fun main(args: Array<String>) {

        val people = listOf(
                Person("Jack", 34),
                Person("Shelly", 19),
                Person("Patrick", 13),
                Person("Jill", 12),
                Person("Shane", 22),
                Person("Joe", 18)
        )

        val selectedPerson = people.maxBy({ person ->  person.age })

        println(selectedPerson)
        println("name: ${selectedPerson?.name}" )
        println("age: ${selectedPerson?.age}" )
    }


    fun b() {

        var x = 2

        when (x) {
            1 -> print("x == 1")
            2 -> print("x == 2")
            else -> { // Note the block
                print("x is neither 1 nor 2")
            }
        }
    }
}