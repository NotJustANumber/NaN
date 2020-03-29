package org.evera.junit.nanserver.entities.legacy

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "testcase")
class LegacyTestCase {
    val name: String = ""
    val time: Double = 0.0

    @JacksonXmlProperty(localName = "classname")
    val className: String = ""
    val skipped: Boolean = false
    val failure =""

}