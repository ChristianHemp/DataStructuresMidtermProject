Car Rental Management System
Author: Christian Hemprich

Project Overview / Rubric Analysis
This project is a Car Rental Business Management System written solely in java that allows users to manage customers, vehicles, rentals, returns, potential waitlists, and stores data through CSV files for long-term data storage. The data structures and algorithms used include HashTable, Queue, LinkedList, ArrayList, Various Searching Methods, MergeSort, and more. Other fundamental java concepts such as recursion and inheritance are also demonstrated through inherently recursive algorithms like MergeSort and Vehicle classes.

Main User Interaction:
- View sorted customer database
- View vehicle databases separately based on vehicle type
- Register new Customers
- Register new Vehicles
- Rent vehicles to customers (placed on waitlist automatically if vehicle not available)
- Return rented vehicles and output payment due (automatically moves waitlist and adds vehicle to customer's permanent record)
- Search for specific customer data
- Search for specific vehicle data
- Saves and loads customer data every time program is run for seamless data collection and storage

Java Files:
- Car.java
- CSVManager.java
- Customer.java
- HTEntry.java
- Main.java
- Motorcycle.java
- MyHashTable.java
- MyQueue.java
- MySinglyLinkedList.java
- Node.java
- Person.java
- RentalService.java
- Truck.java
- Vehicle.java

CSV Files:
- customerData.csv
- rentalHistory.csv
- vehicleData.csv

Installation and Usage
Prerequisites: IDE capable of running Java 25 or later (not tested for earlier versions of java but probably works)
Download & Import all files into IDE, ensuring CSV names are correct.
Run from Main class

Personal Notes:
- User interaction currently a little primitive as it's done through console input
- Rental history loaded separately due to difficulty storing custom objects in CSV
- Rental history stores ids that are rebuilt upon running Main method
- Vehicle data in stores multiple ids separated by | in CSV to represent multiple objects in waitlist
- Vehicle waitlist rebuilt upon running Main method