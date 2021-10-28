package com.norbersan.dirtycontexts.beans.longinit

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MockLongInitBeanC {
    @Autowired
    lateinit var beanContainer: LongInitBeanContainer

    @MockkBean
    lateinit var beanC: LongInitBeanC

    @Test
    fun mockBeanC(){
        every { beanC.toString() } answers { "mocked LongInitBeanC"}

        println()
        println("----------------------------")
        println("==> MOCKING LongInitBeanC")
        println("==> " + beanContainer.longInitBeanA.toString())
        println("==> " + beanContainer.longInitBeanB.toString())
        println("==> " + beanContainer.longInitBeanC.toString())
        println("----------------------------")
    }
}