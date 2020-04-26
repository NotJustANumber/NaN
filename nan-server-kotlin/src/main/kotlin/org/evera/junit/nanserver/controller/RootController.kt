package org.evera.junit.nanserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/")
class RootController {
    @get:GetMapping
    val root: String
        get() = "forward:/public/index.html";
}