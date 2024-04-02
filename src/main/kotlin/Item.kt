package org.example
import java.util.Scanner
open class Item(val name: String, val price: Double)
class Food(name: String, price: Double, val weight: String) : Item(name, price)
class Cloth(name: String, price: Double, val type: String) : Item(name, price)

class ShoppingList {
    private val items = mutableListOf<Item>()

    fun addItem(scanner: Scanner) {
        println("Add Item:")
        println("1. Food")
        println("2. Cloth")
        print("Select item type: ")
        when (val itemType = scanner.nextInt()) {
            1 -> {
                print("Enter food name: ")
                val name = scanner.next()
                print("Enter price: ")
                val price = scanner.nextDouble()
                print("Enter weight: ")
                val weight = scanner.next()
                items.add(Food(name, price, weight))
            }
            2 -> {
                print("Enter cloth name: ")
                val name = scanner.next()
                print("Enter price: ")
                val price = scanner.nextDouble()
                print("Enter type: ")
                val type = scanner.next()
                items.add(Cloth(name, price, type))
            }
            else -> println("Invalid item type.")
        }
    }

    fun displayItems() {
        if (items.isEmpty()) {
            println("Shopping list is empty.")
        } else {
            println("Shopping List:")
            items.forEachIndexed { index, item ->
                println("${index + 1}. ${item.name} - Price: ${item.price}")
                when (item) {
                    is Food -> println("   Weight: ${item.weight}")
                    is Cloth -> println("   Type: ${item.type}")
                }
            }
        }
    }

    fun deleteItem(scanner: Scanner) {
        if (items.isEmpty()) {
            println("Shopping list is empty.")
            return
        }
        displayItems()
        print("Enter the number of the item to delete: ")
        val index = scanner.nextInt()
        if (index in 1..items.size) {
            val deletedItem = items.removeAt(index - 1)
            println("${deletedItem.name} removed from the shopping list.")
        } else {
            println("Invalid item number.")
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val shoppingList = ShoppingList()

    var option: Int
    do {
        println("\nMenu: \n1. Add Item\n2. Display Item \n3. Delete Item \n4. Exit\nSelect an option: ")
        option = scanner.nextInt()
        when (option) {
            1 -> shoppingList.addItem(scanner)
            2 -> shoppingList.displayItems()
            3 -> shoppingList.deleteItem(scanner)
            4 -> println("Exiting..")
            else -> println("Invalid option. Please enter a number between 1 and 4.")
        }
    } while (option != 4)

    scanner.close()
}