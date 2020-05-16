package org.evera.junit.nanserver.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/")
@Controller
class RootController {

    @get:GetMapping
    val app: String
        get() = "forward:/app"
}

@RequestMapping("/app")
@Controller
class AppController {
    @get:GetMapping
    val root: String
        get() = "forward:/index.html"

    @get:GetMapping("/**")
    val inner: String
        get() = "forward:/index.html"
}