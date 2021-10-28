package com.norbersan.dirtycontexts.beans.longstartup

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MockNoLongStartupBean {
    @Autowired
    lateinit var beanContainer: LongStartupBeanContainer

    @Test
    fun mockNoBean(){

        println()
        println("----------------------------")
        println("==> NO MOCKING")
        println("==> " + beanContainer.longStartupBeanA.toString())
        println("==> " + beanContainer.longStartupBeanB.toString())
        println("==> " + beanContainer.longStartupBeanC.toString())
        println("----------------------------")
    }
}