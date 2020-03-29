package org.evera.junit.nanserver.entities

import org.junit.platform.engine.TestExecutionResult

class JunitReportData {
    var name: String = ""
    var duration: Long = 0
    private var children: MutableSet<JunitReportData>? = null
    var status: String = TestExecutionResult.successful().toString()
    var throwable: String? = null
    var isContainer = false

    var passed = 0;
    var failed = 0;
    var skipped = 0;

    fun addChildren(data: JunitReportData) {
        if (children == null) {
            children = mutableSetOf()
        }
        children!!.add(data)
    }

    fun getChildren(): Set<JunitReportData>? {
        return children
    }

    fun setChildren(children: MutableSet<JunitReportData>?) {
        this.children = children
    }

}