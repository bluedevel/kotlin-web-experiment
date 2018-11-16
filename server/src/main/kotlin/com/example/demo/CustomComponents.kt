package com.example.demo

import kotlinx.html.*

@HtmlTagMarker
fun <R> TagConsumer<R>.appBase(block: HtmlBlockTag.() -> Unit) {
    html {
        head {

        }
        body {
            h1 { +"This is the app" }
            div ("test-class") {
                block()
            }
        }
    }
}