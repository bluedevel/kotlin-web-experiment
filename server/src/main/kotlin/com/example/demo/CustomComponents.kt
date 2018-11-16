package com.example.demo

import kotlinx.html.*

@HtmlTagMarker
fun <R> TagConsumer<R>.appBase(block: TagConsumer<R>.() -> Unit): R {
    return html {
        head {
            link(rel = "stylesheet", href = "https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css")

            meta(name = "viewport", content = "width=device-width, initial-scale=1, shrink-to-fit=no")
        }
        body {
            fancyh1("TestApp", "Build entirely with Kotlin")

            div("test-class") {
                block()
            }

            script(src = "https://code.jquery.com/jquery-3.3.1.slim.min.js") {}
            script(src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js") {}
            script(src = "https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js") {}
        }
    }
}

fun <R> TagConsumer<R>.fancyh1(primary: String, secondary: String) = h1 {
    +primary
    small("text-muted") { +secondary }
}