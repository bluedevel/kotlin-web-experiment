package com.example.demo

import kotlinx.html.TagConsumer
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.span

fun <R> TagConsumer<R>.badge() = div(classes = "badge badge-warning p-0") {
    span(classes = "m-1") {
        +"Test kennzeichen"
    }
    button(classes = "btn btn-sm btn-outline-danger m-0 ml-1 border-0 border-left") {
        +"x"
    }
}