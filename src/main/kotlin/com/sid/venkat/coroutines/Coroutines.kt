package com.sid.venkat.coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() {
    nonCoroutine()
    withCoroutine()
    withCoroutineLaunch()
    withCoroutineAndYield()

    println("Master finish")
}


fun intTask(){
    println("intTask Starts from ${Thread.currentThread()}")
    for(i in 1 .. 222) {
        val j = i * 44
    }
    println("intTask ends from ${Thread.currentThread()}")
}

suspend fun intTaskWithYield(){
    println("intTask Starts from ${Thread.currentThread()}")
    yield()
    for(i in 1 .. 222) {
        val j = i * 44
    }
    println("intTask ends from ${Thread.currentThread()}")
}

fun stringTask(){
    println("stringTask Starts from ${Thread.currentThread()}")
    for(i in 1 .. 222) {
        val j = "$i abc"
    }
    println("stringTask ends from ${Thread.currentThread()}")
}

private fun nonCoroutine() {
    println("Start nonCoroutine -----------------")
    run {
        intTask()
        stringTask()
        println("Done with run block from ${Thread.currentThread()}")
    }
    println("Stop nonCoroutine -------------\n\n\n")
}

private fun withCoroutine() {
    println("Start withCoroutine -------------")
    runBlocking {
        intTaskWithYield()
        stringTask()
        println("Done with runBlocking block from ${Thread.currentThread()}")
    }
    println("Stop withCoroutine -------------\n" +
            "\n" +
            "\n")
}


private fun withCoroutineLaunch() {
    println("Start withCoroutineLaunch -------------")
    runBlocking {
        launch {intTask() }
        launch {stringTask()}
        println("Done with runBlocking block from ${Thread.currentThread()}") // printed first
    }
    println("Stop withCoroutineLaunch -------------\n\n\n\n")
}

private fun withCoroutineAndYield() {
    println("withCoroutineAndYield")
    runBlocking {
        launch { intTaskWithYield() }
        launch { stringTaskWithYield() }
    }
}

suspend fun stringTaskWithYield() {
    println("stringTask Starts from ${Thread.currentThread()}")
    yield()
    for(i in 1 .. 222) {
        val j = "$i abc"
    }
    println("stringTask ends from ${Thread.currentThread()}")
}

