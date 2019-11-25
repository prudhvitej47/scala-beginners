package com.prudhvi.lectures.part1basics

object DefaultArgs extends App {

    // default args
    def fact(n: Int, acc: Int = 1): Int = {
        if (n <= 1) acc
        else fact(n - 1, n * acc)
    }
    println(fact(5))

    def savePicture(format: String = "jpg", width: Int, height: Int): Unit = println("Saving Picture")
    //savePicture(800, 600) - wrong

    def saveNewPicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("Saving Picture")
    saveNewPicture()
    saveNewPicture("png")
    saveNewPicture(width = 1440)
    saveNewPicture(height = 800, width = 600, format = "jpeg")
}
