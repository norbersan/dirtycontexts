package com.norbersan.dirtycontexts.beans.longinit

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MockNoLongInitBean {
    @Autowired
    lateinit var beanContainer: LongInitBeanContainer

    @Test
    fun mockNoBean(){

        println()
        println("----------------------------")
        println("==> NO MOCKING")
        println("==> " + beanContainer.longInitBeanA.toString())
        println("==> " + beanContainer.longInitBeanB.toString())
        println("==> " + beanContainer.longInitBeanC.toString())
        println("----------------------------")
    }
}