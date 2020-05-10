package org.evera.junit.nanserver.entities

import com.fasterxml.jackson.annotation.JsonRawValue
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

/**
 * @author ABIR
 */
@Entity
@EntityListeners(AuditingEntityListener::class)
class TestReportDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    var status = false

    @Lob
    @JsonRawValue
    var resultString: String? = null

    @CreatedDate
    var createdDate: Date? = null

    @Transient
    var result: Set<TestReportData>? = null
        set(result) {
            field = result
            try {
                resultString = mapper.writeValueAsString(result)
            } catch (e: JsonProcessingException) {
                e.printStackTrace()
            }
        }

    @ManyToOne
    @JoinColumn(nullable = false)
    var summary: TestReportSummary? = null

    companion object {
        @Transient
        private val mapper = ObjectMapper()
    }
}