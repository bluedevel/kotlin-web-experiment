package com.example.demo

import kotlinx.html.TagConsumer
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.p
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.router

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@Configuration
class RouterConfig {

    @Bean
    fun route(helloWorldHandler: HelloWorldHandler) = router {
        "/hello".nest {
            GET("") {
                ok().contentType(TEXT_HTML).body(fromObject(buildString {
                    val tree = createHTMLDocument().appBase {
                        helloWorldHandler.handle(this)
                    }
                    append(tree.serialize())
                }))
            }
        }
        "/**" {
            notFound().build()
        }
    }

}

@Component
class HelloWorldHandler {
    fun <R> handle(body: TagConsumer<R>) {
        body.run {
            p {
                +"Some text for testing"
            }

            badge()
        }
    }
}
