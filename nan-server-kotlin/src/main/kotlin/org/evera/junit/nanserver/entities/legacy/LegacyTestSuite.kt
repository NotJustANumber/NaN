package org.evera.junit.nanserver.entities.legacy

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.time.Instant

@JacksonXmlRootElement(localName = "testsuite")
class LegacyTestSuite {
    val name: String = ""
    val packageString = ""
    val timestamp: Instant = Instant.now()
    val hostname = ""
    val tests = 0
    val errors = 0
    val failures = 0
    val time = 0.0

    @JacksonXmlProperty(localName = "testcase")
    @JacksonXmlElementWrapper(useWrapping = false)
    val testCases: List<LegacyTestCase> = mutableListOf()
}
