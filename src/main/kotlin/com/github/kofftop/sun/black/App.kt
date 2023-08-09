package com.github.kofftop.sun.black

import com.github.kotlintelegrambot.bot
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class App {

}

fun main(args: Array<String>) {
  runApplication<App>(*args)
}