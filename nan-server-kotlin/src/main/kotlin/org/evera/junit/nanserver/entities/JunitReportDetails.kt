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
class JunitReportDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Lob
    @JsonRawValue
    var resultString: String? = null

    @CreatedDate
    var createdDate: Date? = null

    @Transient
    var result: Set<JunitReportData>? = null
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
    var summary: JunitReportSummary? = null

    companion object {
        @Transient
        private val mapper = ObjectMapper()
    }
}