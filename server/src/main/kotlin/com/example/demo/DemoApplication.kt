package com.example.demo

import kotlinx.html.HtmlBlockTag
import kotlinx.html.h1
import kotlinx.html.p
import kotlinx.html.span
import kotlinx.html.stream.appendHTML
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
                    appendHTML().run {
                        appBase {
                            helloWorldHandler.handle(this)
                        }
                    }
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
    fun handle(body: HtmlBlockTag) {
        body.run {
            custom {
                +"Hallo Welt!"
            }

            p {
                +"Some text for testing"
            }
        }
    }
}

fun HtmlBlockTag.custom(block: () -> Unit = {}) = h1 {
    span {
        +"Custom: "
    }
    block()
}
